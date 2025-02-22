package com.it.rmu.rmu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.it.rmu.rmu.entity.RoleEntity;
import com.it.rmu.rmu.model.RoleRequestModel;
import com.it.rmu.rmu.model.RoleResponeModel;
import com.it.rmu.rmu.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleResponeModel getById(Integer id) {
        // ดึงข้อมูล
        Optional<RoleEntity> roleEntity =  roleRepository.findById(id);

        RoleResponeModel response = null;


        if(roleEntity.isPresent()) {
            response = new RoleResponeModel();
            // มีข้อมูลที่ได้จาก Table role ของ DB
            RoleEntity entity = roleEntity.get();

            // เอาข้อมูลที่ได้จาก Table มา Map เข้ากับ Object Response 
            response.setId(entity.getId());
            response.setRoleName(entity.getRoleName());
            response.setRoleDescription(entity.getRoleDescription());
            response.setStatus(entity.getStatus());
            
            
        }

        return response;
    }

    public List<RoleResponeModel> getAll(){

        List<RoleEntity> roleEntityList = roleRepository.findAll();
        List<RoleResponeModel> responseList = null;
        if(null != roleEntityList) {
            responseList = new ArrayList<>();

            for(RoleEntity roleEntity : roleEntityList) {
                RoleResponeModel roleObj = new RoleResponeModel();
                roleObj.setId(roleEntity.getId());
                roleObj.setRoleName(roleEntity.getRoleName());
                roleObj.setRoleDescription(roleEntity.getRoleDescription());
                roleObj.setStatus(roleEntity.getStatus());
                
              
                responseList.add(roleObj);
            }
        }

        return responseList;
    }
    
    public void save(RoleRequestModel request) {

        if(null != request) {

            RoleEntity entity = new RoleEntity();

            entity.setRoleName(request.getRoleName());
            entity.setRoleDescription(request.getRoleDescription());
            entity.setStatus(request.getStatus());
            entity.setCreateBy("JOEY");
            entity.setCreateDate(new Date());
            entity.setUpdateBy("JOEY");
            entity.setUpdateDate(new Date());

            roleRepository.save(entity);
        }
    }
    
    public void update(RoleRequestModel request) {

        if(null != request) {
            if(null != request.getId()) {

                Optional<RoleEntity> roleEntity =  roleRepository.findById(request.getId());

                if(roleEntity.isPresent()) {
                    RoleEntity entity = roleEntity.get();
                    entity.setRoleName(request.getRoleName());
                    entity.setRoleDescription(request.getRoleDescription());
                    entity.setStatus(request.getStatus());
                    roleRepository.save(entity);
                }
            }
        }
    }
    
    public void delete(Integer id) {

        roleRepository.deleteById(id);

    }
}