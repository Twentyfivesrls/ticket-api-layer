package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationcontroller.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalEventController;
import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.twentyfive.twentyfivemodel.filterTicket.EventFilter;



import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<Object> filterEventList(@RequestBody EventFilter event, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int sizeP) {

        String username = authenticationService.getUsername();
        Page<Event> result = eventController.filterEventList(event, page, sizeP, username);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/filter/event/autocomplete")
    public ResponseEntity<Page<Event>> filterEventAutocomplete(@RequestParam("filterObject") String filterObject, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        String username = authenticationService.getUsername();
        Page<Event> result = eventController.filterAutocomplete(filterObject, page, size, username);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getEventList() {
        String username = authenticationService.getUsername();
        List<Event> result = eventController.getEventList(username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveEvent(@RequestBody Event event) {
        String username = authenticationService.getUsername();
        System.out.println("evento :"+ event);
        event.setUserId(username);
        event.setEnabled(true);
        Event result = eventController.saveEvent(event);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable String id) {
        String username = authenticationService.getUsername();
        Event result = eventController.getEventById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/export/excel/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadExcel(@PathVariable String userId) {
        String username = authenticationService.getUsername();
        byte[] result = eventController.downloadExcel(userId);
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

    @GetMapping("/get/event/byFields")
    public ResponseEntity<Event> getEventByField(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("location") String location, @RequestParam("enabled") Boolean enabled) {
        String username = authenticationService.getUsername();

        String tmp = date;
        System.out.println("DATE  :"+tmp);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        LocalDateTime dateTime = LocalDateTime.parse(date, formatter).atZone(ZoneOffset.UTC).toLocalDateTime();

        System.out.println("DATE  FORMATTED:"+dateTime);


        Event result = eventController.getEventByField(name, description, dateTime, location, enabled);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String id){
        String username = authenticationService.getUsername();
        eventController.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
