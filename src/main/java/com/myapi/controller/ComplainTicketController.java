package com.myapi.controller;

import com.myapi.DTO.ComplainTicketDTO;
import com.myapi.DTO.ComplainTicketFormDTO;
import com.myapi.DTO.DepartmentDTO;
import com.myapi.service.ComplainTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComplainTicketController {
    @Autowired
    private ComplainTicketService complainTicketService;
    @GetMapping("/api/alltickets")
    @ResponseBody
    public ResponseEntity<List<ComplainTicketDTO>> findAllTickets(){
        try{
            return new ResponseEntity<>(complainTicketService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/createticket")
    @ResponseBody
    public ResponseEntity<String> insertComplainTicket(@RequestBody ComplainTicketFormDTO dto){
        complainTicketService.insertNewComplainTicket(dto);
        return ResponseEntity.ok("ok");
    }
}
