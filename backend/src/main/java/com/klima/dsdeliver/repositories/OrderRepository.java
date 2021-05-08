package com.klima.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klima.dsdeliver.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	// JOIN FETCH faz o inner join toca no banco de dados uma vez e tras todo mundo junto com os registros correspondentes
	@Query("SELECT obj from Order obj "
			+ " WHERE obj.status = 0 ORDER BY obj.moment ASC ")
	List<Order> findOrderWithProducts();
	
}
