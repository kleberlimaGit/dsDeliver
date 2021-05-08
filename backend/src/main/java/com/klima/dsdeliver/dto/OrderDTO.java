package com.klima.dsdeliver.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.klima.dsdeliver.entities.Order;
import com.klima.dsdeliver.entities.enums.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class OrderDTO {
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	private Double total;
	private List<ProductDTO> products = new ArrayList<>();
	
	
	public OrderDTO(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status,
			Double total) {
	
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
		this.total = total;
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		address = entity.getAddress();
		latitude = entity.getLatitude();
		longitude = entity.getLongitude();
		moment = entity.getMoment();
		status = entity.getStatus();
		total = entity.getTotal();
		products = entity.getProducts().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
	}
	
	
	
}
