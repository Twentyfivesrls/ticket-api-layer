package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationcontroller.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalEventController;
import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.EventFilter;
import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private InternalEventController eventController;


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/filter")
    public ResponseEntity<Object> filterEventList(@RequestBody EventFilter event, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        String username = authenticationService.getUsername();
        Page<Event> result = eventController.filterEventList(event, size, page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getEventList() {
        String username = authenticationService.getUsername();
        List<Event> result = eventController.getEventList();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveEvent(@RequestBody Event event) {
        String username = authenticationService.getUsername();
        event.setUserId(username);
        Event result = eventController.saveEvent(event);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable String id) {
        String username = authenticationService.getUsername();
        Event result = eventController.getEventById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/export/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadExcel() {
        String username = authenticationService.getUsername();
        byte[] result = eventController.downloadExcel();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=exported_data.xlsx")
                .body(result);
    }

    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<Object> updateEvent(@PathVariable String id, @PathVariable Boolean status) {
        String username = authenticationService.getUsername();
        Event result = eventController.updateEvent(id, status);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEventById(@PathVariable String id, @RequestBody Event event) {
        String username = authenticationService.getUsername();
        Event result = eventController.updateEventById(id, event);
        return ResponseEntity.ok().body(result);
    }
}
