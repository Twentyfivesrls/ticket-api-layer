package com.twentyfive.ticketapilayer.clients;

import com.twentyfive.twentyfivemodel.filterTicket.AddressBookFilter;
import com.twentyfive.twentyfivemodel.filterTicket.AutoCompleteRes;
import com.twentyfive.twentyfivemodel.models.ticketModels.AddressBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "InternalAddressbookController", url = "${twentyfive.db.url}/addressbook")
public interface InternalAddressbookController {

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    AddressBook deleteAddressBook(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/allElement")
    List<AddressBook> getAll(@RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET , value="/getById/{id}")
    AddressBook getById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/firstName/{firstName}")
    List<AddressBook> getByFirstName(@PathVariable String firstName);

    @RequestMapping(method = RequestMethod.GET , value="/getBy/last/name/{lastName}")
    List<AddressBook> getByLastName(@PathVariable String lastName);

    @RequestMapping(method = RequestMethod.GET , value="/get/addressBook/by/email")
    AddressBook getByEmail(@RequestParam("email") String email);

    @RequestMapping(method = RequestMethod.GET, value = "/findByUsername")
    AddressBook getByUsername(@RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST , value="/list")
    Page<AddressBook> getAddressbookList(@RequestBody AddressBookFilter filter,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST , value="/page")
    Page<AddressBook> pageAddressBook(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.POST, value = "/get/autocomplete")
    Set<AutoCompleteRes> filterAutocomplete(@RequestParam("filterObject") String filterObject,
                                            @RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.PUT , value="/update/{id}")
    AddressBook updateAddressBook(@PathVariable String id, @RequestBody AddressBook addressBook);

    @RequestMapping(method = RequestMethod.POST, value = "/save/addressbook")
    AddressBook saveAddressbook(@RequestBody AddressBook addressBook);

    @RequestMapping(method = RequestMethod.GET, value="/countRubrica")
    long countRubrica(@RequestParam("username") String username);
}
