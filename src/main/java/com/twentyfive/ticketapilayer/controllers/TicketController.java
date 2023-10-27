package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationcontroller.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalTicketController;
import com.twentyfive.twentyfivemodel.filterTicket.TicketFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private InternalTicketController ticketController;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/generate")
    public ResponseEntity<Object> generateTicket(@RequestBody Ticket ticket,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("email") String email) {
        String username = authenticationService.getUsername();
        ticket.setUserId(username);
        ticket.setActive(false);
        Ticket result = ticketController.generateTicket(ticket, name, lastName, email, username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getTicketList(@RequestBody TicketFilter ticket,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        String username = authenticationService.getUsername();
        Page<Ticket> result = ticketController.getTicketList(ticket, page, size, username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getTicket/byCode/{code}")
    public ResponseEntity<Object> getTicketByCode(@PathVariable String code) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.getTicketByCode(code);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/setStatus/{code}/{status}")
    public ResponseEntity<Object> setStatus(@PathVariable String code, @PathVariable Boolean status) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setStatus(code, status);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("update/usedTicket/{code}/{used}")
    public ResponseEntity<Object> setUsed(@PathVariable String code, @PathVariable Boolean used) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setUsed(code, used);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTicket(@RequestParam("code") String code) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.deleteTicket(code);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/export/excel/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadExcel(@PathVariable String userId) {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.downloadExcel(userId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=exported_data.xlsx")
                .body(result);
    }

    @GetMapping("/getBy/eventName/{eventName}")
    public ResponseEntity<Object> getTicketByEventName(@PathVariable String eventName) {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.getTicketByEventName(eventName);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getBy/ticket/isUsed/{isUsed}")
    public ResponseEntity<Object> getTicketByIsUsed(@PathVariable Boolean isUsed) {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.getTicketByIsUsed(isUsed);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/generate/qrCode/ticket/number/{ticketNumber}")
    public ResponseEntity<Object> generateQrCode(@PathVariable String ticketNumber) {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.generateQrCode(ticketNumber);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/find/all")
    public ResponseEntity<Object> findAll() {
        String username = authenticationService.getUsername();
        List<Ticket> result = ticketController.findAll(username);
        return ResponseEntity.ok().body(result);
    }

}
