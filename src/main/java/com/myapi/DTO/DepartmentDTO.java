package com.myapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private int deptid;
    private String deptname;
    private Date createdate;
}
