package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalTicketController;
import com.twentyfive.twentyfivemodel.dto.ticketDto.TicketAndAddressBook;
import com.twentyfive.twentyfivemodel.filterTicket.AutoCompleteRes;
import com.twentyfive.twentyfivemodel.models.ticketModels.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    private final InternalTicketController ticketController;
    private final AuthenticationService authenticationService;

    public TicketController(InternalTicketController ticketController, AuthenticationService authenticationService) {
        this.ticketController = ticketController;
        this.authenticationService = authenticationService;
    }


    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @PostMapping("/generate")
    public ResponseEntity<Object> generateTicket(@RequestBody TicketAndAddressBook ticket) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.generateTicket(ticket, username);
        return ResponseEntity.ok().body(result);
    }

    // @PreAuthorize("hasRole('ROLE_single_realm_role')")
    @PostMapping("/list")
    public ResponseEntity<Object> getTicketList(@RequestBody Ticket ticket,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        String username = authenticationService.getUsername();
        Page<Ticket> result = ticketController.getTicketList(ticket, page, size, username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/page")
    public ResponseEntity<Object> getPageEvents(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        String username = authenticationService.getUsername();
        Page<Ticket> result = ticketController.pageTickets(page, size, username);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @PostMapping("/get/autocomplete")
    public ResponseEntity<Set<AutoCompleteRes>> filterEventAutocomplete(@RequestParam("filterObject") String filterObject) {

        String username = authenticationService.getUsername();
        Set<AutoCompleteRes> result = ticketController.filterAutocomplete(filterObject, username);

        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/getALl/tickets/by/event")
    public ResponseEntity<Page<Ticket>> getTicketsByIdEvent(@RequestParam("eventId") String eventId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        String username = authenticationService.getUsername();
        Page<Ticket> result = ticketController.getTicketsByIdEvent(eventId, page, size, username);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/getTicketById/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable String id) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.getTicketById(id);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/getTicket/byCode/{code}")
    public ResponseEntity<Ticket> getTicketByCode(@PathVariable String code) {
        Ticket result = ticketController.getTickedByCode(code);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @PutMapping("/setStatus/{id}/{status}")
    public ResponseEntity<Object> setStatus(@PathVariable String id, @PathVariable Boolean status) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setStatus(id, status);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @PutMapping("update/usedTicket/{id}/{used}")
    public ResponseEntity<Object> setUsed(@PathVariable String id, @PathVariable Boolean used) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setUsed(id, used);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTicket(@RequestParam("id") String id) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.deleteTicket(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/export/excel/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadExcel(@PathVariable String userId) {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.downloadExcel(userId);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = dateTime.format(formatter);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Lista_Ticket_" + formattedDateTime + ".xlsx")
                .body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/getBy/eventName/{eventName}")
    public ResponseEntity<Object> getTicketByEventName(@PathVariable String eventName) {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.getTicketByEventName(eventName);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/getBy/ticket/isUsed/{isUsed}")
    public ResponseEntity<Object> getTicketByIsUsed(@PathVariable Boolean isUsed) {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.getTicketByIsUsed(isUsed);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/generate/qrCode/ticket/number")
    public ResponseEntity<Object> generateQrCode(@RequestParam("url") String url) {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.generateQrCode(url);
        return ResponseEntity.ok().body(result);
    }

    //@PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/find/all")
    public ResponseEntity<Object> findAll() {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.findAll(username);
        return ResponseEntity.ok().body(result);
    }
}
