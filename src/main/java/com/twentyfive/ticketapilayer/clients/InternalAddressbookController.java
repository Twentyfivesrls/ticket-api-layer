package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.AddressBookFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.AddressBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "InternalAddressbookController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/addressbook")
public interface InternalAddressbookController {

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    AddressBook deleteAddressBook(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET , value="/getById/{id}")
    AddressBook getById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/firstName/{firstName}")
    List<AddressBook> getByFirstName(@PathVariable String firstName);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/last/name/{lastName}")
    List<AddressBook> getByLastName(@PathVariable String lastName);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/date/of/birth/{dateOfBirth}")
    List<AddressBook> getByDateOfBirth(@PathVariable LocalDateTime dateOfBirth);

    @RequestMapping(method = RequestMethod.POST , value="/list")
    Page<AddressBook> getAdressbookList(@RequestBody AddressBookFilter filter);

    @RequestMapping(method = RequestMethod.PUT , value="/update/{id}")
    AddressBook updateAddressBook(@PathVariable String id, @RequestBody AddressBook addressBook);
}