package org.isdb.service;

import java.util.List;
import java.util.Optional;

import org.isdb.model.Product;
import org.isdb.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product saveProduct(Product product) {
		int saveProduct = repository.save(product);
		return getProById(saveProduct);
	}

	public Product getProById(int id) {

		Optional<Product> byIdOptional = repository.findById(id);
		// TODO Auto-generated method stub
		return byIdOptional.get();
	}

	public List<Product> getAllPro() {
		List<Product> allPro = repository.findAll();

		return allPro;
	}

	public void deleteById(int id) {
		repository.deleteById(id);

	}

	public Product updatePro(int id, Product product) {
		product.setId(id);
		repository.update(product);
		return getProById(id);
	}

	public List<Product> getProByName(String name) {
		List<Product> pro = repository.findByName(name);
		return pro;
	}

}