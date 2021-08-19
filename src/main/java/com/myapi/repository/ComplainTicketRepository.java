package com.myapi.repository;

import com.myapi.entity.ComplainTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ComplainTicketRepository extends JpaRepository<ComplainTicket,String> {
    @Override
    List<ComplainTicket> findAll();
    List<ComplainTicket> findComplainTicketByPhonenumber(String phonenumber);
    List<ComplainTicket> findComplainTicketByTicketid(String ticketid);
    @Query("select ticket from ComplainTicket ticket where ticket.department.deptname=:deptname")
    List<ComplainTicket> findComplainTicketByDepartmentname(@Param("deptname") String deptname);
    @Query("select ticket from ComplainTicket ticket where ticket.timereceive>=:fromdate and ticket.timereceive<=:todate")
    List<ComplainTicket> findComplainTicketByTimecreate(String fromdate,String todate);
    @Modifying
    @Query(value = "insert into complainticket(ticketid,deptid,phonenumber,customeropinion,timereceive,answer,processingstatus,processingtime) " +
            "values(:ticketid,:deptid,:phonenumber,:customeropinion,:timereceive,:answer,:processingstatus,:processingtime)",nativeQuery = true)
    @Transactional
    void insertComplainTicket(@Param("ticketid") String ticketid,@Param("deptid") int deptid,
                              @Param("phonenumber") String phonenumber,@Param("customeropinion") String opinion,
                              @Param("timereceive") Date timereceive,@Param("answer") String answer,
                              @Param("processingstatus") boolean processingstatus,@Param("processingtime") Date processingtime );

}
