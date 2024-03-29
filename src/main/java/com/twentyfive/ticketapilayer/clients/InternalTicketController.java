package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.dto.ticketDto.TicketAndAddressBook;
import com.twentyfive.twentyfivemodel.filterTicket.AutoCompleteRes;
import com.twentyfive.twentyfivemodel.models.ticketModels.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "InternalTicketController", url = "${twentyfive.db.url}/ticket")
public interface InternalTicketController {

    @RequestMapping(method = RequestMethod.POST, value = "/generate")
    Ticket generateTicket(@RequestBody TicketAndAddressBook ticket,
                          @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value = "/list")
    Page<Ticket> getTicketList(@RequestBody Ticket ticket,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam String username);

    @RequestMapping(method = RequestMethod.POST, value = "/page")
    Page<Ticket> pageTickets(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam String username);

    @RequestMapping(method = RequestMethod.POST, value = "/get/autocomplete")
    Set<AutoCompleteRes> filterAutocomplete(@RequestParam("filterObject") String filterObject,
                                            @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "/getALl/tickets/by/event")
    Page<Ticket> getTicketsByIdEvent(@RequestParam("eventId") String eventId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "/getTicketById/{id}")
    Ticket getTicketById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/getTicket/byCode/{code}")
    Ticket getTickedByCode(@PathVariable String code);

    @RequestMapping(method = RequestMethod.PUT, value = "/setStatus/{id}/{status}")
    Ticket setStatus(@PathVariable String id, @PathVariable Boolean status);

    @RequestMapping(method = RequestMethod.PUT, value = "/update/usedTicket/{id}/{used}")
    Ticket setUsed(@PathVariable String id, @PathVariable Boolean used);

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    Ticket deleteTicket(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/export/excel/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] downloadExcel(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/getBy/eventName/{eventName}")
    List<Ticket> getTicketByEventName(@PathVariable String eventName);

    @RequestMapping(method = RequestMethod.GET, value = "/getBy/ticket/isUsed/{isUsed}")
    List<Ticket> getTicketByIsUsed(@PathVariable Boolean isUsed);

    @RequestMapping(method = RequestMethod.GET, value = "/generate/qrCode/ticket/number")
    byte[] generateQrCode(@RequestParam("url") String url);

    @RequestMapping(method = RequestMethod.GET, value = "/find/all")
    List<Ticket> findAll(@RequestParam("username") String username);
}
