package com.myapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "department")
public class Department {
    @Id
    private int deptid;
    private String deptname;
    private Date createdate;
    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private Set<ComplainTicket> complainTickets;
}
