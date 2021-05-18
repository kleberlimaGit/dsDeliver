package com.klima.dsdeliver.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.klima.dsdeliver.entities.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	@Size(min = 5, max = 60, message = "O produto deve conter pelo menos 5 caracteres")
	@NotBlank
	private String name;
	@Positive(message = "Valor não pode ser negativo")
	private Double price;
	@NotBlank(message = "Descrição não pode ser vazia")
	private String description;
	private String imageUri;
	
	public ProductDTO(Long id, String name, Double price, String description, String imageUri) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUri = imageUri;
	}
	
	public ProductDTO(Product entity){
		id = entity.getId();
		name = entity.getName();
		price = entity.getPrice();
		description = entity.getDescription();
		imageUri = entity.getImageUri();
	}
}
