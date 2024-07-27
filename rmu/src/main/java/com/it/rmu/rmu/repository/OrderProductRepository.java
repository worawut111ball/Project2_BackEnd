package com.it.rmu.rmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.rmu.rmu.entity.OrderProductEntity;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer> {

    @Query("select t from OrderProductEntity t where t.ordersId =?1")
    public List<OrderProductEntity> findByOrdersId(Integer ordersId);
    
    @Query("SELECT op FROM OrderProductEntity op WHERE op.ordersId = :orderId")
    List<OrderProductEntity> findByOrderId(@Param("orderId") Integer orderId);

    
    @Modifying(clearAutomatically = true)
    @Query("delete from OrderProductEntity t where t.ordersId = ?1")
    void deleteByOrdersId(Integer ordersId);
    

//	@Query("select t from OrderProductEntity t where t.ordersId =?1")
//	public OrderProductEntity findByOrdersId(Integer ordersId);
//	
//	@Modifying(clearAutomatically = true)
//	@Query("delete from OrderProductEntity t where t.id = ?1")
//	void deleteById(Integer id);
	
}
