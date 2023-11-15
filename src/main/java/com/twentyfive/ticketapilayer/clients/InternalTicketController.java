package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.TicketFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import com.twentyfive.twentyfivemodel.models.ticketModels.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "InternalTicketController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/ticket")
public interface InternalTicketController {

    @RequestMapping(method = RequestMethod.POST, value="/generate")
    Ticket generateTicket(@RequestBody Ticket ticket,
                          @RequestParam("id") String id,
                          @RequestParam("name") String name,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("email") String email,
                          @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value="/list")
    Page<Ticket> getTicketList(@RequestBody TicketFilter ticket,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam String username);

    @RequestMapping(method = RequestMethod.POST, value = "/get/autocomplete")
    Page<Ticket> filterAutocomplete(@RequestParam("filterObject") String filterObject,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "/getALl/tickets/by/event")
    Page<Ticket> getTicketsByIdEvent(@RequestParam("eventId") String eventId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value="/getTicketById/{id}")
    Ticket getTicketById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/getTicket/byCode/{code}")
    Ticket getTickedByCode(@PathVariable String code);

    @RequestMapping(method = RequestMethod.PUT, value="/setStatus/{id}/{status}")
    Ticket setStatus(@PathVariable String id, @PathVariable Boolean status);

    @RequestMapping(method = RequestMethod.PUT, value="/update/usedTicket/{id}/{used}")
    Ticket setUsed(@PathVariable String id, @PathVariable Boolean used);

    @RequestMapping(method = RequestMethod.DELETE, value="/delete")
    Ticket deleteTicket(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.GET, value="/export/excel/{userId}", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] downloadExcel(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.GET, value="/getBy/eventName/{eventName}")
    List<Ticket> getTicketByEventName(@PathVariable String eventName);

    @RequestMapping(method = RequestMethod.GET, value="/getBy/ticket/isUsed/{isUsed}")
    List<Ticket> getTicketByIsUsed(@PathVariable Boolean isUsed);

    @RequestMapping(method = RequestMethod.GET, value="/generate/qrCode/ticket/number")
    byte[] generateQrCode(@RequestParam("url") String url);

    @RequestMapping(method = RequestMethod.GET, value="/find/all")
    List<Ticket> findAll(@RequestParam("username") String username);
}
