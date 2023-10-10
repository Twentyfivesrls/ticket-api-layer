package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationcontroller.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalAddressbookController;
import com.twentyfive.twentyfivemodel.filterTicket.AddressBookFilter;
import com.twentyfive.twentyfivemodel.models.ticketModels.AddressBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
@CrossOrigin(origins = "*")
public class AddressbookController {

    @Autowired
    private InternalAddressbookController addressbookController;


    @Autowired
    private AuthenticationService authenticationService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAddressBook(@PathVariable String id) {
        String username = authenticationService.getUsername();
        AddressBook result = addressbookController.deleteAddressBook(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        String username = authenticationService.getUsername();
        AddressBook result = addressbookController.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getBy/firstName/{firstName}")
    public ResponseEntity<Object> getByFirstName(@PathVariable String firstName) {
        String username = authenticationService.getUsername();
        List<AddressBook> result = addressbookController.getByFirstName(firstName);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getBy/last/name/{lastName}")
    public ResponseEntity<Object> getByLastName(@PathVariable String lastName) {
        String username = authenticationService.getUsername();
        List<AddressBook> result = addressbookController.getByLastName(lastName);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/addressBook/by/email")
    public ResponseEntity<Object> getByEmail(@RequestParam("email") String email) {
        String username = authenticationService.getUsername();
        List<AddressBook> result = addressbookController.getByEmail(email);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAdressbookList() {
        String username = authenticationService.getUsername();
        Page<AddressBook> result = addressbookController.getAdressbookList();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAddressBook(@PathVariable String id, @RequestBody AddressBook addressBook) {
        String username = authenticationService.getUsername();
        AddressBook result = addressbookController.updateAddressBook(id, addressBook);
        return ResponseEntity.ok().body(result);
    }

}
