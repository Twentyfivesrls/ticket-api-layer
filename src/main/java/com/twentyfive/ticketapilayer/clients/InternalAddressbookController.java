package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.AddressBookFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.AddressBook;
import com.twentyfive.twentyfivemodel.models.ticketModels.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "InternalAddressbookController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/addressbook")
public interface InternalAddressbookController {

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{email}")
    AddressBook deleteAddressBook(@PathVariable String email);

    @RequestMapping(method = RequestMethod.GET , value="/getById/{id}")
    AddressBook getById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/firstName/{firstName}")
    List<AddressBook> getByFirstName(@PathVariable String firstName);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/last/name/{lastName}")
    List<AddressBook> getByLastName(@PathVariable String lastName);

    @RequestMapping(method = RequestMethod.GET , value="/get/addressBook/by/email")
    AddressBook getByEmail(@RequestParam("email") String email);

    @RequestMapping(method = RequestMethod.POST , value="/list")
    Page<AddressBook> getAdressbookList(@RequestBody AddressBookFilter filter, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value = "/get/autocomplete")
    Page<AddressBook> filterAutocomplete(@RequestParam("filterObject") String filterObject, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.PUT , value="/update/{email}")
    AddressBook updateAddressBook(@PathVariable String email, @RequestBody AddressBook addressBook);

    @RequestMapping(method = RequestMethod.POST, value = "/save/addressbook")
    AddressBook saveAddressbook(@RequestBody AddressBook addressBook);
}
