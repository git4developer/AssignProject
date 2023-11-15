package com.task.demo.reposi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.task.demo.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,Integer>{
	
	
	//public Product findByp_Name(String p_Name);
	
	public Product findByuuid(String uuid);

}
