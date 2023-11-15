package com.task.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.task.demo.entity.Product;
import com.task.demo.helper.EpocToDateHelper;
import com.task.demo.helper.GetDiff;
import com.task.demo.reposi.ProductRepository;

@Repository
public class ProductService {

	Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ProductRepository pr;

	// for saving the data
	public Product saveProduct(Product product) {

		return this.mongoTemplate.save(product);

	}

	// getting all record
	public List<Product> getProduct() {
		log.info("Inside get Product method Service Class");

		AggregationOperation sort = Aggregation.sort(Direction.ASC, "id");

		Aggregation aggregation = Aggregation.newAggregation(sort);

		List<Product> contacts = mongoTemplate
				.aggregate(aggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();

		return contacts;

	}

	// for Update the exsisting field
	public Product updateProduct(Product p, String name) {

		log.info("Inside Update method of Service class");

		Product product = new Product();
		MatchOperation match = Aggregation.match(Criteria.where("uuid").is(name));

		Aggregation aggregation = Aggregation.newAggregation(match);
		List<Product> mappedResults = mongoTemplate
				.aggregate(aggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();
		Iterator<Product> iterator = mappedResults.iterator();

		while (iterator.hasNext()) {
			Product next = iterator.next();
			product.setId(p.getId());
			product.setP_Name(p.getP_Name());
			product.setP_Type(p.getP_Type());
			product.setEpoc_Version(p.getEpoc_Version());
			product.setUuid(next.getUuid());
			product.setIso_creadtedOn(next.getIso_creadtedOn());
		}

		return this.mongoTemplate.save(product);

	}

	public Long getLatestEPoc(String uuid) {

		MatchOperation match = Aggregation.match(Criteria.where("uuid").is(uuid));

		SortOperation sort = Aggregation.sort(Direction.DESC, "Epoc_Version");
		LimitOperation limit = Aggregation.limit(1);

		Aggregation newAggregation = Aggregation.newAggregation(match, sort, limit);
		List<Product> mappedResults = this.mongoTemplate
				.aggregate(newAggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();
		Long l = 0L;
		Iterator<Product> iterator = mappedResults.iterator();
		while (iterator.hasNext()) {
			Product next = iterator.next();
			l = next.getEpoc_Version();
		}
		return l;

	}

	public List<Product> getOneDetailByUuid(String uuid) {
		MatchOperation match = Aggregation.match(Criteria.where("uuid").is(uuid));
		SortOperation sort = Aggregation.sort(Direction.DESC, "Epoc_Version");
		LimitOperation limit = Aggregation.limit(1);
		Aggregation newAggregation = Aggregation.newAggregation(match, sort, limit);

		List<Product> list = new ArrayList<>();
		Product next = null;
		List<Product> mappedResults = this.mongoTemplate
				.aggregate(newAggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();
		Iterator<Product> iterator = mappedResults.iterator();
		while (iterator.hasNext()) {
			next = iterator.next();
		}
		list.add(next);
		return list;
	}

	public List<Product> getProductByUuidSortDesc(String name) {

		log.info("Inside getProductByUuidSortDesc methhod of service class");

		AggregationOperation match = Aggregation.match(Criteria.where("uuid").is(name));
		AggregationOperation sort = Aggregation.sort(Direction.DESC, "Epoc_Version"); // epoc_Version

		Aggregation newAggregation = Aggregation.newAggregation(match, sort);

		List<Product> mappedResults = mongoTemplate
				.aggregate(newAggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();
		Iterator<Product> iterator = mappedResults.iterator();
		while (iterator.hasNext()) {
			Product next = iterator.next();
			Long epoc_Version = next.getEpoc_Version();
			System.out.println(EpocToDateHelper.getEpocIntoDate(epoc_Version));
		}
		return mappedResults;

	}

	// for deleting the recods based on id
	public String deleteProduct(int id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));

		this.mongoTemplate.findAndRemove(q, Product.class);
		return "Product is Deleted for this " + id + " given id ";

	}

	public List<Product> GetAndSave() {
		log.info("Get Data from db And save it to another db");
		SortOperation sort = Aggregation.sort(Direction.ASC, "id");
		Aggregation newAggregation = Aggregation.newAggregation(sort);
		int total_Data_Send_To_New_Db = 0;
		List<Product> mappedResults = this.mongoTemplate
				.aggregate(newAggregation, mongoTemplate.getCollectionName(Product.class), Product.class)
				.getMappedResults();

		boolean flag = this.mongoTemplate.collectionExists("productBackUp");
		if (flag != true) {
			this.mongoTemplate.createCollection("productBackUp");
		}

		// if(this.mongoTemplate.getCollectionName("productBackUp))
		List<Product> list = new ArrayList<>();
		Iterator<Product> iterator = mappedResults.iterator();
		while (iterator.hasNext()) {
			Product next = iterator.next();
			String iso_creadtedOn = next.getIso_creadtedOn();
			Long diffDay = GetDiff.getDiffDay(iso_creadtedOn);
			Long inputDiiffValue = 1L;
			if (diffDay == inputDiiffValue) {

				this.mongoTemplate.save(next, "productBackUp");
				total_Data_Send_To_New_Db++;

				list.add(next);

				int id = next.getId();

				Query q = new Query();
				q.addCriteria(Criteria.where("id").is(id));

				Product findAndRemove = this.mongoTemplate.findAndRemove(q, Product.class, "detail");
				System.out.println("This data has been deleted from detail documents::->" + findAndRemove);

			}

		}

		System.out.println("total data moved from souce to destionation:-> " + total_Data_Send_To_New_Db);
		return list;

	}

	public List<Product> getDataBtwnDate(String uuid1, String uuid2) {
		MatchOperation match = Aggregation.match(Criteria.where("Iso_creadtedOn").gte(uuid1).lte(uuid2));
		Aggregation newAggregation = Aggregation.newAggregation(match);

		List<Product> mappedResults = this.mongoTemplate.aggregate(newAggregation,
				mongoTemplate.getCollectionName(Product.class), Product.class).getMappedResults();
		
		
		// this.mongoTemplate.

		return mappedResults;
	}

}
