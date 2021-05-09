package com.klima.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.klima.dsdeliver.dto.ProductDTO;
import com.klima.dsdeliver.entities.Product;
import com.klima.dsdeliver.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		
		List<Product> list = repository.findAllByOrderByNameAsc();
		
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
	public ProductDTO insertProduct(ProductDTO dto) {
		Product product = new Product(null,dto.getName(),dto.getPrice(),dto.getDescription(),dto.getImageUri());
		product = repository.save(product);
		return new ProductDTO(product);
	}
	
	public ProductDTO updateProduct(Long id, ProductDTO dto) {
		try {
			Product product = repository.getOne(id);	
			product.setDescription(dto.getDescription());
			product.setImageUri(dto.getImageUri());
			product.setName(dto.getName());
			product.setPrice(dto.getPrice());
			product = repository.save(product);
			return new ProductDTO(product);
			
		}
		catch (RuntimeException e) {
			throw new ResourceAccessException("identificador não encontrado. " + id);
		}
	}
	
	public void deleteProduct(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceAccessException("identificador não encontrado. " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Violação de integridade");
		}
	}
	
}
