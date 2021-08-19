package com.myapi.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ComplainTicketFormDTO {
    private int deptid;
    private String phonenumber;
    private String customeropinion;
    private Date timereceive;
}
