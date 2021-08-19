package com.myapi.service;

import com.myapi.DTO.ComplainTicketDTO;
import com.myapi.DTO.ComplainTicketFormDTO;
import com.myapi.DTO.DepartmentDTO;
import com.myapi.entity.ComplainTicket;
import com.myapi.entity.Department;
import com.myapi.repository.ComplainTicketRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("prototype")
@Getter
public class ComplainTicketService {
    @Autowired
    private ComplainTicketRepository complainTicketRepository;
    public List<ComplainTicketDTO> findAll(){
        List<ComplainTicket> tickets= complainTicketRepository.findAll();
        List<ComplainTicketDTO> complainTicketDTOS=new ArrayList<>();
        for(ComplainTicket complainTicket:tickets){
            complainTicketDTOS.add(new ComplainTicketDTO(complainTicket));
        }
        return complainTicketDTOS;
    }
    public void insertNewComplainTicket(ComplainTicketFormDTO complainTicketFormDTO){
        String ticketid=generateTicketId();
        String answer="";
        boolean processingstatus=false;
        Date processingtime=new Date();
        complainTicketFormDTO.setTimereceive(processingtime);
        complainTicketRepository.insertComplainTicket(ticketid,complainTicketFormDTO.getDeptid(),
                complainTicketFormDTO.getPhonenumber(),complainTicketFormDTO.getCustomeropinion(),
                complainTicketFormDTO.getTimereceive(),answer,processingstatus,processingtime);
    }
    public String generateTicketId(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
