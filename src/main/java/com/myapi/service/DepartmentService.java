package com.myapi.service;

import com.myapi.DTO.DepartmentDTO;
import com.myapi.entity.Department;
import com.myapi.repository.DepartmentRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Scope("prototype")
@Getter
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<DepartmentDTO> findDepartmentByDeptid(Integer deptid){
        List<Department> departments= departmentRepository.findDepartmentByDeptid(deptid);
        List<DepartmentDTO> departmentDTOS=new ArrayList<>();
        for(Department department:departments){
            departmentDTOS.add(new DepartmentDTO(department.getDeptid(),department.getDeptname(),department.getCreatedate()));
        }
        return departmentDTOS;
    }
    public List<DepartmentDTO> findDepartmentByDeptname(String deptname){
        List<Department> departments= departmentRepository.findDepartmentByDeptname(deptname);
        List<DepartmentDTO> departmentDTOS=new ArrayList<>();
        for(Department department:departments){
            departmentDTOS.add(new DepartmentDTO(department.getDeptid(),department.getDeptname(),department.getCreatedate()));
        }
        return departmentDTOS;
    }
    public List<DepartmentDTO> findAll(){
        List<Department> departments= departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS=new ArrayList<>();
        for(Department department:departments){
            departmentDTOS.add(new DepartmentDTO(department.getDeptid(),department.getDeptname(),department.getCreatedate()));
        }
        return departmentDTOS;
    }
    public void insertNewDepartment(String deptname){
        Integer lastid=departmentRepository.findlastDeptid();
        if(lastid==null) lastid=0;
        lastid+=1;
        Date date=new Date();
        departmentRepository.insertNewDepartment(lastid,deptname,date);
    }
    public Map<String,String> updateDepartment(Integer deptid, String deptname){
        Map<String,String> result=new HashMap<>();
        if(!departmentRepository.existsDepartmentByDeptid(deptid)){
            result.put("message","department not existed");
            result.put("status","0");
        }else{
            departmentRepository.updateDepartment(deptid,deptname);
            result.put("message","department updated");
            result.put("status","1");
        }
        return result;
    }
    public Map<String,String> deleteDepartment(Integer deptid){
        Map<String,String> result=new HashMap<>();
        if(!departmentRepository.existsDepartmentByDeptid(deptid)){
            result.put("message","department not existed");
            result.put("status","0");
        }else{
            departmentRepository.deleteDepartment(deptid);
            result.put("message","department deleted");
            result.put("status","1");
        }
        return result;
    }
}
