package com.it.rmu.rmu.service;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.rmu.rmu.entity.OrderEntity;
import com.it.rmu.rmu.entity.OrderProductEntity;

import com.it.rmu.rmu.model.OrderRequestModel;
import com.it.rmu.rmu.model.OrderResponseModel;

import com.it.rmu.rmu.repository.OrderProductRepository;
import com.it.rmu.rmu.repository.OrderRepository;

import jakarta.transaction.Transactional;




@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderProductRepository orderProductRepository;
	


	@Transactional
	public OrderResponseModel getById(Integer ordersId) {
	    OrderResponseModel response = null;

	    Optional<OrderEntity> orderEntity = orderRepository.findById(ordersId);

	    if (orderEntity.isPresent()) {
	        OrderEntity order = orderEntity.get();
	        response = new OrderResponseModel();

	        // Set order details
	        response.setOrdersId(order.getId());
	        response.setUserId(order.getUserId());
	        response.setAddress(order.getAddress());
	        response.setProvinceId(order.getProvinceId());
	        response.setAmphureId(order.getAmphureId());
	        response.setTambonId(order.getTambonId());
	        response.setZipcode(order.getZipcode());
	        response.setStatus(order.getStatus());
	        response.setCreateDate(order.getCreateDate());
	
	        List<OrderProductEntity> orderProducts = orderProductRepository.findByOrdersId(ordersId);
	        if (orderProducts != null && !orderProducts.isEmpty()) {
	            List<Integer> productIds = new ArrayList<>();
	            List<Integer> quantities = new ArrayList<>();

	            for (OrderProductEntity orderProduct : orderProducts) {
	                productIds.add(orderProduct.getProductId());
	                quantities.add(orderProduct.getQuantity());
	            }

	            response.setProductId(productIds.toArray(new Integer[0]));  
	            response.setQuantity(quantities.toArray(new Integer[0]));  
	        }
	    }

	    return response;
	}


	 @Transactional
	    public List<OrderResponseModel> getByUserId(Integer userId) {
	        List<OrderResponseModel> responses = new ArrayList<>();

	        // Find orders by userId
	        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);

	        for (OrderEntity order : orderEntities) {
	            OrderResponseModel response = new OrderResponseModel();

	            // Set order details
	            response.setOrdersId(order.getId());
	            response.setUserId(order.getUserId());
	            response.setAddress(order.getAddress());
	            response.setProvinceId(order.getProvinceId());
	            response.setAmphureId(order.getAmphureId());
	            response.setTambonId(order.getTambonId());
	            response.setZipcode(order.getZipcode());
	            response.setStatus(order.getStatus());
	            response.setCreateDate(order.getCreateDate());

	            // Find order products by ordersId
	            List<OrderProductEntity> orderProducts = orderProductRepository.findByOrderId(order.getId());
	            if (orderProducts != null && !orderProducts.isEmpty()) {
	                List<Integer> productIds = new ArrayList<>();
	                List<Integer> quantities = new ArrayList<>();

	                for (OrderProductEntity orderProduct : orderProducts) {
	                    productIds.add(orderProduct.getProductId());
	                    quantities.add(orderProduct.getQuantity());
	                }

	                response.setProductId(productIds.toArray(new Integer[0]));
	                response.setQuantity(quantities.toArray(new Integer[0]));
	            }

	            responses.add(response);
	        }

	        return responses;
	    }
	
	@Transactional
	public List<OrderResponseModel> getAll() {
	    List<OrderResponseModel> response = new ArrayList<>();
	    List<OrderEntity> orderEntities = orderRepository.findAll();

	    if (orderEntities != null) {
	        for (OrderEntity order : orderEntities) {
	            OrderResponseModel responseObject = new OrderResponseModel();

	          
	            responseObject.setOrdersId(order.getId());
	            responseObject.setUserId(order.getUserId());
	            responseObject.setAddress(order.getAddress());
	            responseObject.setProvinceId(order.getProvinceId());
	            responseObject.setAmphureId(order.getAmphureId());
	            responseObject.setTambonId(order.getTambonId());
	            responseObject.setZipcode(order.getZipcode());
	            responseObject.setStatus(order.getStatus());
	            responseObject.setCreateDate(order.getCreateDate());

	            List<OrderProductEntity> orderProducts = orderProductRepository.findByOrdersId(order.getId());
	            if (orderProducts != null) {
	                List<Integer> productIds = new ArrayList<>();
	                List<Integer> quantities = new ArrayList<>();

	                for (OrderProductEntity orderProduct : orderProducts) {
	                    productIds.add(orderProduct.getProductId());
	                    quantities.add(orderProduct.getQuantity());
	                }

	                responseObject.setProductId(productIds.toArray(new Integer[0]));  
	                responseObject.setQuantity(quantities.toArray(new Integer[0]));  
	            }

	            response.add(responseObject);
	        }
	    }

	    return response;
	}

@Transactional
public Integer save(OrderRequestModel request) {
    Integer response = null;
    if (null != request) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(request.getUserId());
        orderEntity.setAddress(request.getAddress());
        orderEntity.setProvinceId(request.getProvinceId());
        orderEntity.setAmphureId(request.getAmphureId());
        orderEntity.setTambonId(request.getTambonId());
        orderEntity.setZipcode(request.getZipcode());
        orderEntity.setStatus(null != request.getStatus() ? request.getStatus() : "1");
        orderEntity.setCreateBy("ball");
        orderEntity.setCreateDate(new Date());
        orderEntity.setUpdateBy("ball W");
        orderEntity.setUpdateDate(new Date());

        orderEntity = orderRepository.save(orderEntity);

        response = orderEntity.getId();

        List<Integer> productIds = Arrays.asList(request.getProductId());
        List<Integer> quantities = Arrays.asList(request.getQuantity());

        for (int i = 0; i < productIds.size(); i++) {
            Integer id = productIds.get(i);
            Integer qty = quantities.get(i);

            OrderProductEntity orderProduct = new OrderProductEntity();
            orderProduct.setOrdersId(response);
            orderProduct.setProductId(id);
            orderProduct.setQuantity(qty);
            orderProduct.setCreateBy("ball");
            orderProduct.setCreateDate(new Date());
            orderProduct.setUpdateBy("ball W");
            orderProduct.setUpdateDate(new Date());
            orderProductRepository.save(orderProduct);
        }
    }

    return response;
}



@Transactional
public Integer update(OrderRequestModel request, Integer ordersId) {
    Integer response = null;

    if (request != null) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(ordersId);

        if (optionalOrder.isPresent()) {
            OrderEntity order = optionalOrder.get();

            
            orderRepository.updateOrderDetails(
                request.getUserId(),
                request.getAddress(),
                request.getProvinceId(),
                request.getAmphureId(),
                request.getTambonId(),
                request.getZipcode(),
                request.getStatus() != null ? request.getStatus() : order.getStatus(),
                "update By ball",
                new Date(),
                ordersId
            );

            
            orderProductRepository.deleteByOrdersId(ordersId);

           
            Integer[] productIds = request.getProductId();
            Integer[] quantities = request.getQuantity();

            for (int i = 0; i < productIds.length; i++) {
                Integer ids = productIds[i];
                Integer qty = quantities[i];

                OrderProductEntity orderProduct = new OrderProductEntity();
                orderProduct.setOrdersId(ordersId);
                orderProduct.setProductId(ids);
                orderProduct.setQuantity(qty);
                orderProduct.setCreateBy("Create By ball");
                orderProduct.setCreateDate(new Date());
                orderProduct.setUpdateBy("update By ball");
                orderProduct.setUpdateDate(new Date());
                orderProductRepository.save(orderProduct);
            }

            response = ordersId;
        } else {
            throw new RuntimeException("Order not found for id: " + ordersId);
        }
    }

    return response;
}



@Transactional
public Integer delete(Integer ordersId) {

    Integer response = null;
    if(null != ordersId) {
    	orderRepository.deleteByOrderId(ordersId);
    	orderProductRepository.deleteByOrdersId(ordersId);
        response = ordersId;
    }
    return response;
}

}
