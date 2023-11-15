package com.task.demo.contr;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.demo.entity.Product;
import com.task.demo.service.ProductService;

@RestController
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@GetMapping("/newdb")
	public ResponseEntity<List<Product>> getAndSave() {
		log.info("Inside get method of ControllerClass");

		return ResponseEntity.of(Optional.of(this.productService.GetAndSave()));

//		              this.productService.getProduct();
//		              return (ResponseEntity<Void>) ResponseEntity.status(HttpStatus.ACCEPTED);

	}

	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		Product saveProduct = this.productService.saveProduct(product);

		return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getProduct() {
		log.info("Inside get method of ControllerClass");

		return ResponseEntity.of(Optional.of(this.productService.getProduct()));

//		              this.productService.getProduct();
//		              return (ResponseEntity<Void>) ResponseEntity.status(HttpStatus.ACCEPTED);

	}

	// Get latest_EPOC

	@GetMapping("/get/{uuid}")
	public ResponseEntity<Long> getEPoc(@PathVariable("uuid") String uuid) {
		log.info("Inside COntroller class method");
		return ResponseEntity.of(Optional.of(this.productService.getLatestEPoc(uuid)));
	}

	// getLatestEpoc data for UUid
	@GetMapping("/UpdatedResult/{uuid}")
	public ResponseEntity<List<Product>> getLatestEpocData(@PathVariable("uuid") String uuid) {
		log.info("get Data based on latest Epoc");
		return ResponseEntity.of(Optional.of(this.productService.getOneDetailByUuid(uuid)));
	}

	@PutMapping("/update/{uuid}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("uuid") String uuid) {
		log.info("Inside Controller Update Method");

		Product updateProduct = this.productService.updateProduct(product, uuid);
		return ResponseEntity.ok(updateProduct);
	}

	@GetMapping("/getByUuid/{uuid}")
	public ResponseEntity<List<Product>> getByUuid(@PathVariable("uuid") String uuid) {
		log.info("Inside Controller Update Method");
		return ResponseEntity.ok(this.productService.getProductByUuidSortDesc(uuid));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		String deleteProduct = this.productService.deleteProduct(id);
		return ResponseEntity.ok(deleteProduct);
	}

	@GetMapping("/collection")
	public ResponseEntity<List<Product>> getByUuid() {
		log.info("Inside Controller Update Method");
		return ResponseEntity.ok(this.productService.GetAndSave());
	}

//	@GetMapping("/abc")
//	public ResponseEntity<?> get() {
//		
//	}

	@GetMapping("/DataBtwnIso")
	public ResponseEntity<List<Product>> getDataBetweenTwoIso(@RequestParam(value = "uuid1") String uuid1,
			@RequestParam(value = "uuid2") String uuid2) {

		List<Product> dataBtwnDate = this.productService.getDataBtwnDate(uuid1, uuid2);
		return ResponseEntity.ok(dataBtwnDate);

	}

}
