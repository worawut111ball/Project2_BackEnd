package com.it.rmu.rmu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it.rmu.rmu.entity.PaymentEntity;
import com.it.rmu.rmu.model.PaymentResponseModel;
import com.it.rmu.rmu.repository.OrderRepository;
import com.it.rmu.rmu.repository.PaymentRepository;
import com.it.rmu.rmu.utils.Constants;
import com.it.rmu.rmu.utils.ImgUtils;

import jakarta.transaction.Transactional;
@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	
	
	@Transactional
	public Integer delete(Integer paymentImgId) throws IOException {
		Integer response = null;
		if(null != paymentImgId) {
			orderRepository.deleteByPaymentImgId(paymentImgId);
			this.removeImgAndDeleteFileImgByPaymentImgId(paymentImgId);
			response = paymentImgId;
		}
		return response;
	}
	
	public List<PaymentResponseModel> getPaymentImgByOrderId(Integer ordersId){
		List<PaymentResponseModel> response = null;
		
		List<PaymentEntity> paymentImgList = paymentRepository.findByPaymentImgId(ordersId);
		
		if(null != paymentImgList) {
			response = new ArrayList<>();
			for(PaymentEntity  paymentImg : paymentImgList) {
				PaymentResponseModel objectResponse = new PaymentResponseModel();
				objectResponse.setPaymentImgId(paymentImg.getId());
				objectResponse.setOrdersId(paymentImg.getOrdersId());
				objectResponse.setPaymentImgName(paymentImg.getPaymentImgName());
				objectResponse.setPaymentImgPath(paymentImg.getPaymentImgPath());
				objectResponse.setPaymentImgData(paymentImg.getPaymentImgData());
				objectResponse.setStatus(paymentImg.getStatus());
				response.add(objectResponse);
			}
		}
		
		return response;
	}
	@Transactional
	public void removeImgAndDeleteFileImgByPaymentImgId(Integer ordersId) throws IOException {
		List<PaymentEntity> paymentImgList = paymentRepository.findByPaymentImgId(ordersId);
		paymentRepository.deleteAll(paymentImgList);
	}
	
	@Transactional
	public void deleteImgByFileName(String fileName) throws IOException {
		PaymentEntity paymentImgList = paymentRepository.findByPaymentImgName(fileName);
		paymentRepository.delete(paymentImgList);
	}
	
	public byte[] getImageByte(String fileName) throws IOException, DataFormatException {
		PaymentEntity paymentImg = paymentRepository.findByPaymentImgName(fileName);
		
		if(null != paymentImg) {
			return ImgUtils.decompressImage(paymentImg.getPaymentImgData());
		}

        return null;
	}
	
	@Transactional
	public Integer saveImage(MultipartFile file, Integer ordersId) throws IOException {
		Integer response = null;
		if(null != file && null != ordersId) {
			PaymentEntity paymentImg = new PaymentEntity();
			String preFixNameFile = ImgUtils.genaratePrefixFile();
			String genarateFileName = ImgUtils.genarateFileName() +ImgUtils.subStrFileName(file.getOriginalFilename());
			String fileName = ImgUtils.concatStr(preFixNameFile, genarateFileName);
			paymentImg.setPaymentImgName(fileName);
			paymentImg.setPaymentImgPath(ImgUtils.getPathInput());
			paymentImg.setOrdersId(ordersId);
			paymentImg.setPaymentImgData(ImgUtils.compressImage(file.getBytes()));
			paymentImg.setStatus("1");
			paymentImg.setCreateBy("Joey");
			paymentImg.setCreateDate(new Date());
			paymentImg.setUpdateBy("Joey Update");
			paymentImg.setUpdateDate(new Date());
			paymentImg = paymentRepository.save(paymentImg);
			response = paymentImg.getId();
			
//			ImgUtils.saveFile(file, fileName, Constants.PATH_TYPE_INPUT);
		}
		return response;
	}
	@Transactional
	public void deleteImgSever(String fileName) throws IOException {
		if(null != fileName) {
			ImgUtils.deleteFile(fileName, Constants.PATH_TYPE_INPUT);
		}
		
	}
	 // New method to get all payment images
	 public List<PaymentResponseModel> getAllPayments() {
	        List<PaymentResponseModel> response = new ArrayList<>();
	        List<PaymentEntity> paymentImgList = paymentRepository.findAll();
	        if (null != paymentImgList) {
	            for (PaymentEntity paymentImg : paymentImgList) {
	                PaymentResponseModel objectResponse = new PaymentResponseModel();
	                objectResponse.setPaymentImgId(paymentImg.getId());
	                objectResponse.setOrdersId(paymentImg.getOrdersId());
	                objectResponse.setPaymentImgName(paymentImg.getPaymentImgName());
	                objectResponse.setPaymentImgPath(paymentImg.getPaymentImgPath());
	                objectResponse.setPaymentImgData(paymentImg.getPaymentImgData());
	                objectResponse.setStatus(paymentImg.getStatus());
	                response.add(objectResponse);
	            }
	        }
	        return response;
	    }
		
}
