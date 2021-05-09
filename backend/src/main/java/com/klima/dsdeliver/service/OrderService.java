package com.klima.dsdeliver.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klima.dsdeliver.dto.OrderDTO;
import com.klima.dsdeliver.dto.ProductDTO;
import com.klima.dsdeliver.entities.Order;
import com.klima.dsdeliver.entities.Product;
import com.klima.dsdeliver.entities.enums.OrderStatus;
import com.klima.dsdeliver.repositories.OrderRepository;
import com.klima.dsdeliver.repositories.ProductRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> list = repository.findOrderWithProducts();
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING,null);
		for(ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		Double total = order.getProducts().stream().mapToDouble(Product::getPrice).sum();

		order.setTotal(total);
		order = repository.save(order);
		
		return new OrderDTO(order);
		
	}
	
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}
