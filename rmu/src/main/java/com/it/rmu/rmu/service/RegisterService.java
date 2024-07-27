package com.it.rmu.rmu.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.rmu.rmu.entity.UserDetailEntity;
import com.it.rmu.rmu.entity.UserEntity;
import com.it.rmu.rmu.model.RegisterRequestModel;
import com.it.rmu.rmu.model.RegisterResponseModel;

import com.it.rmu.rmu.repository.UserDetailRepository;
import com.it.rmu.rmu.repository.UserRepository;
//import com.it.rmu.rmu.utils.Constants;

import jakarta.transaction.Transactional;



@Service
public class RegisterService {
	

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;
    
//	 @Autowired
//	 SendMailService mailService;
	 
    @Transactional
    public RegisterResponseModel getById(Integer userId) {

        RegisterResponseModel response = null;

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()) {

            response = new RegisterResponseModel();
            UserEntity user = userEntity.get();
            UserDetailEntity userDetail = userDetailRepository.findByUserId(userId);

            // user
            response.setUserId(user.getId());
            response.setUserName(user.getUserName());
            response.setPassword(user.getPassword());
            response.setRoleId(user.getRoleId());
            response.setStatus(user.getStatus());
            // userDetail
            if(null != userDetail) {
                response.setFristName(userDetail.getFristName());
                response.setLastName(userDetail.getLastName());
                response.setPhone(userDetail.getPhone());
                response.setAge(userDetail.getAge());
            }
        }


        return response;
    }
    @Transactional
    public Integer save(RegisterRequestModel request) {

        Integer response = null;

        if(null != request) {
        	
        	  UserEntity entity = userRepository.findByUserName(request.getUserName());

              if(null != entity) {
                  return null;
              }
        	

            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(request.getUserName());
            userEntity.setPassword(request.getPassword());
            userEntity.setRoleId(null != request.getRoleId() ? request.getRoleId() : 2);
            userEntity.setStatus(null !=  request.getStatus() ? request.getStatus() : "1");
            userEntity.setCreateBy("Joey");
            userEntity.setCreateDate(new Date());
            userEntity.setUpdateBy("Joey W");
            userEntity.setUpdateDate(new Date());

            userEntity = userRepository.save(userEntity);

            response = userEntity.getId();

            if(null != userEntity) {
                UserDetailEntity userDetailEntity = new UserDetailEntity();
                userDetailEntity.setUserId(userEntity.getId());
                userDetailEntity.setFristName(request.getFristName());
                userDetailEntity.setLastName(request.getLastName());
                userDetailEntity.setPhone(request.getPhone());
                userDetailEntity.setAge(request.getAge());
                userDetailEntity.setCreateBy("Joey");
                userDetailEntity.setCreateDate(new Date());
                userDetailEntity.setUpdateBy("Joey W");
                userDetailEntity.setUpdateDate(new Date());

                userDetailRepository.save(userDetailEntity);

            }
          
        }
        
//        RegisterResponseModel data = getById(response);
//        if(null != data) {
//            String emailTo = "worawut111ball@gmail.com";
//            String subject = "Test Email Joey";
//
//            mailService.sendMail(data,emailTo, subject, Constants.TYPE_SENDMAIL_REGISTER);
//        }


        return response;
    }
    @Transactional
    public Integer update(RegisterRequestModel request, Integer userId) {

        Integer response = null;

        if(null != request) {

            Optional<UserEntity> userEntity = userRepository.findById(userId);

            if(userEntity.isPresent()) {
                UserEntity user = userEntity.get();

                response = user.getId();

                user.setPassword(null != request.getPassword() ? request.getPassword() : user.getPassword());
                user.setStatus(null != request.getStatus() ? request.getStatus() : user.getStatus());
                user.setUpdateBy("update By Joey");
                user.setUpdateDate(new Date());

                userRepository.save(user);

                UserDetailEntity userDetail = userDetailRepository.findByUserId(userId);

                if(null != userDetail) {
                    userDetail.setFristName(null != request.getFristName() ? request.getFristName() : userDetail.getFristName());
                    userDetail.setLastName(null != request.getLastName() ? request.getLastName() : userDetail.getLastName());
                    userDetail.setPhone(null != request.getPhone() ? request.getPhone() : userDetail.getPhone());
                    userDetail.setAge(null != request.getAge() ? request.getAge() : userDetail.getAge());
                    userDetail.setUpdateBy("update By Joey");
                    userDetail.setUpdateDate(new Date());
                    userDetailRepository.save(userDetail);
                }
            }
        }

        return response;
    }
    @Transactional
    public Integer delete(Integer userId) {

        Integer response = null;
        if(null != userId) {
            userRepository.deleteByUserId(userId);
            userDetailRepository.deleteByUserId(userId);
            response = userId;
        }
        return response;
    }
    
    
}
	

