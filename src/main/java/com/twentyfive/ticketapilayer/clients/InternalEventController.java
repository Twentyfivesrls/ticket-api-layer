package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.AutoCompleteRes;
import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@FeignClient(name = "InternalEventController", url = "${twentyfive.db.url}/event")
public interface InternalEventController {

    @RequestMapping(method = RequestMethod.POST, value="/filter")
    Page<Event> filterEventList(@RequestBody Event event,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value="/page")
    Page<Event> pageEvents(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value = "/filter/event/autocomplete")
    Set<AutoCompleteRes> filterAutocomplete(@RequestParam("filterObject") String filterObject,
                                            @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value="/list")
    List<Event> getEventList(@RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value="/save")
    Event saveEvent(@RequestBody Event event);

    @RequestMapping(method = RequestMethod.GET, value="/get/{id}")
    Event getEventById(@PathVariable String id);

    //todo fix this
    @RequestMapping(method = RequestMethod.GET, value="/export/excel/{userId}")
    byte[] downloadExcel(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}/{status}")
    Event updateEvent(@PathVariable String id, @PathVariable Boolean status);

    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    Event updateEventById(@PathVariable String id, @RequestBody Event event);

    @RequestMapping(method = RequestMethod.GET, value="/get/event/byFields")
    Event getEventByField(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("date") @DateTimeFormat(pattern="dd/MM/yy, HH:mm") LocalDateTime date,
                          @RequestParam("location") String location,
                          @RequestParam("enabled") Boolean enabled);

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    Event deleteEvent(@PathVariable String id);

}
