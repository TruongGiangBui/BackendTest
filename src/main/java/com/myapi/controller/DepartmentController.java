package com.myapi.controller;

import com.myapi.DTO.DepartmentDTO;
import com.myapi.DTO.DepartmentUpdateDTO;
import com.myapi.DTO.RegisterFormDTO;
import com.myapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/api/deptsearchbyname")
    @ResponseBody
    public ResponseEntity<List<DepartmentDTO>> findDepartmentByName(@RequestParam("deptname") String deptname){
        try{
            return new ResponseEntity<>(departmentService.findDepartmentByDeptname(deptname),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/deptsearchbyid")
    @ResponseBody
    public ResponseEntity<List<DepartmentDTO>> findDepartmentByDeptid(@RequestParam("deptid") Integer deptid){
        try{
            return new ResponseEntity<>(departmentService.findDepartmentByDeptid(deptid),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/deptall")
    @ResponseBody
    public ResponseEntity<List<DepartmentDTO>> findAllDepartment(){
        try{
            return new ResponseEntity<>(departmentService.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/createdept")
    @ResponseBody
    public ResponseEntity<String> createdept(@RequestBody String deptname){
        departmentService.insertNewDepartment(deptname);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/api/updatedept")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updatedept(@RequestBody DepartmentUpdateDTO dto){
        Map<String,String> result=departmentService.updateDepartment(dto.getDeptid(),dto.getDeptname());
        if(result.get("status").equals("0")) return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PostMapping("/api/deletedept")
    @ResponseBody
    public ResponseEntity<Map<String,String>> deletedept(@RequestBody Integer deptid){
        Map<String,String> result=departmentService.deleteDepartment(deptid);
        if(result.get("status").equals("0")) return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
