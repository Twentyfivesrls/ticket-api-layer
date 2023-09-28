package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.TicketFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "InternalTicketController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/ticket")
public interface InternalTicketController {

    @RequestMapping(method = RequestMethod.POST, value="/generate/{name}/{lastName}/{dateOfBirth}")
    Ticket generateTicket(@RequestBody Ticket ticket, @PathVariable String name, @PathVariable String lastName, @PathVariable LocalDateTime dateOfBirth);

    @RequestMapping(method = RequestMethod.POST, value="/list")
    Page<Ticket> getTicketList(@RequestBody TicketFilter ticket);

    @RequestMapping(method = RequestMethod.GET, value="/getTicketById/{id}")
    Ticket getTicketById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.PUT, value="/setStatus/{id}/{status}")
    Ticket setStatus(@PathVariable String id, @PathVariable Boolean status);

    @RequestMapping(method = RequestMethod.PUT, value="/update/usedTicket/{id}/{used}")
    Ticket setUsed(@PathVariable String id, @PathVariable Boolean used);

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    Ticket deleteTicket(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value="/export/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] downloadExcel();

    @RequestMapping(method = RequestMethod.GET, value="/getBy/eventName/{eventName}")
    List<Ticket> getTicketByEventName(@PathVariable String eventName);

    @RequestMapping(method = RequestMethod.GET, value="/getBy/ticket/isUsed/{isUsed}")
    List<Ticket> getTicketByIsUsed(@PathVariable Boolean isUsed);

    @RequestMapping(method = RequestMethod.GET, value="/generate/qrCode/ticket/number/{ticketNumber}")
    byte[] getEventList(@PathVariable String ticketNumber);
}