package Main.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import Main.entity.Order;

public interface OrderService {
	
	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);
}
