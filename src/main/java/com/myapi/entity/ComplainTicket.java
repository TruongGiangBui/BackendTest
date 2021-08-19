package com.myapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "complainticket")
public class ComplainTicket {
    @Id
    private String ticketid;
    private int deptint;
    private String phonenumber;
    private String customeropinion;
    private Date timereceive;
    private String answer;
    private boolean processingstatus;
    private Date processingtime;
    @ManyToOne
    @JoinColumn(name = "deptid",referencedColumnName = "deptid")
    private Department department;
}
