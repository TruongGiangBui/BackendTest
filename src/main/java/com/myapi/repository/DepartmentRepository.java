package com.myapi.repository;

import com.myapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsDepartmentByDeptid(Integer deptid);
    List<Department> findDepartmentByDeptid(Integer deptid);
    List<Department> findDepartmentByDeptname(String deptname);
    List<Department> findAll();
    @Modifying
    @Query(value = "insert into department(deptid,deptname,createdate) values(:deptid,:deptname,:createdate)",nativeQuery = true)
    @Transactional
    void insertNewDepartment(@Param("deptid") Integer deptid, @Param("deptname") String deptname, @Param("createdate") Date date);
    @Modifying
    @Query("update Department dept set dept.deptname=:deptname where dept.deptid=:deptid")
    @Transactional
    void updateDepartment(@Param("deptid") Integer deptid, @Param("deptname") String deptname);
    @Modifying
    @Query(value = "delete from Department dept where dept.deptid=:deptid")
    @Transactional
    void deleteDepartment(@Param("deptid") Integer deptid);
    @Query(value = "select deptid from department order by deptid desc limit 1",nativeQuery = true)
    Integer findlastDeptid();
}
