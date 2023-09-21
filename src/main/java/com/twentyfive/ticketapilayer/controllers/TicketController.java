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

import javax.xml.stream.EventFilter;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private InternalTicketController ticketController;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/generate/{name}/{lastName}/{dateOfBirth}")
    public ResponseEntity<Object> generateTicket(@RequestBody Ticket ticket, @PathVariable String name, @PathVariable String lastName, @PathVariable LocalDateTime dateOfBirth) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.generateTicket(ticket, name, lastName, dateOfBirth);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getTicketList(@RequestBody TicketFilter ticket) {
        String username = authenticationService.getUsername();
        Page<Ticket> result = ticketController.getTicketList(ticket);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getTicketById/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable String id) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.getTicketById(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/setStatus/{id}/{status}")
    public ResponseEntity<Object> setStatus(@PathVariable String id, @PathVariable Boolean status) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setStatus(id, status);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("update/usedTicket/{id}/{used}")
    public ResponseEntity<Object> setUsed(@PathVariable String id, @PathVariable Boolean used) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.setUsed(id, used);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable String id) {
        String username = authenticationService.getUsername();
        Ticket result = ticketController.deleteTicket(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value="/export/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadExcel() {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.downloadExcel();
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
    public ResponseEntity<Object> getEventList(@PathVariable String ticketNumber) {
        String username = authenticationService.getUsername();
        byte[] result = ticketController.getEventList(ticketNumber);
        return ResponseEntity.ok().body(result);
    }

}
