package com.it.rmu.rmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.it.rmu.rmu.entity.PaymentEntity;


public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{
	
	@Query("select t from PaymentEntity t where t.ordersId = ?1")
	public List<PaymentEntity> findByPaymentImgId(Integer ordersId);
	
	@Modifying(clearAutomatically = true)
	@Query("delete from PaymentEntity t where t.ordersId = ?1")
	void deleteByPaymentImgId(Integer ordersId);
	
	@Query("select t from PaymentEntity t where t.paymentImgName = ?1")
	public PaymentEntity findByPaymentImgName(String paymentImgName);
}
