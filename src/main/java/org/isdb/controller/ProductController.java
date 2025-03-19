package org.isdb.controller;

import java.util.List;

import org.isdb.model.Product;
import org.isdb.service.ProductService;

@RestController
@RequestMapping(value = "/Product")
public class ProductController {

	private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public Product saveProduct(@RequestBody Product Product) {

		Product savedPro = service.saveProduct(Product);

		return savedPro;
	}

	@GetMapping("/{id}")
	public Product getProById(@PathVariable("id") int id) {
		Product proById = service.getProById(id);
		return proById;
	}

	@GetMapping
	public List<Product> getallEmp() {
		List<Product> allPro = service.getAllEmp();

		return allPro;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		service.deleteById(id);
	}

	@PutMapping("/{id}")
	public Product updatePro(@PathVariable("id") int id, @RequestBody Product Product) {
		Product updated = service.updatePro(id, Product);
		return updated;
	}

	@GetMapping("/{name}")
	public List<Product> getProByName(@PathVariable String name) {
		List<Product> ProByName = service.getProByName(name);

		return proByName;
	}

}