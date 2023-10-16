package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.EventFilter;
import java.util.List;

@FeignClient(name = "InternalEventController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/event")
public interface InternalEventController {

    @RequestMapping(method = RequestMethod.POST, value="/filter")
    Page<Event> filterEventList(@RequestBody EventFilter event, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size);

    @RequestMapping(method = RequestMethod.GET, value="/list")
    List<Event> getEventList();

    @RequestMapping(method = RequestMethod.POST, value="/save")
    Event saveEvent(@RequestBody Event event);

    @RequestMapping(method = RequestMethod.GET, value="/get/{id}")
    Event getEventById(@PathVariable String id);

    //todo fix this
    @RequestMapping(method = RequestMethod.GET, value="/export/excel")
    byte[] downloadExcel();

    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}/{status}")
    Event updateEvent(@PathVariable String id, @PathVariable Boolean status);

    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    Event updateEventById(@PathVariable String id, @RequestBody Event event);
}
