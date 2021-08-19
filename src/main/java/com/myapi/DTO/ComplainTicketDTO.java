package com.myapi.DTO;

import com.myapi.entity.ComplainTicket;
import lombok.Data;

import java.util.Date;

@Data
public class ComplainTicketDTO {
    private String ticketid;
    private String deptname;
    private String phonenumber;
    private String customeropinion;
    private Date timereceive;
    private String answer;
    private boolean processingstatus;
    private Date processingtime;
    public ComplainTicketDTO(ComplainTicket complainTicket){
        this.ticketid=complainTicket.getTicketid();
        this.deptname=complainTicket.getDepartment().getDeptname();
        this.phonenumber=complainTicket.getPhonenumber();
        this.customeropinion=complainTicket.getCustomeropinion();
        this.timereceive=complainTicket.getTimereceive();
        this.answer=complainTicket.getAnswer();
        this.processingstatus=complainTicket.isProcessingstatus();
        this.processingtime=complainTicket.getProcessingtime();
    }
}
