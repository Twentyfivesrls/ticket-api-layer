package com.twentyfive.ticketapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.ticketapilayer.clients.InternalAddressbookController;
import com.twentyfive.twentyfivemodel.filterTicket.AddressBookFilter;
import com.twentyfive.twentyfivemodel.filterTicket.AutoCompleteRes;
import com.twentyfive.twentyfivemodel.models.ticketModels.AddressBook;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
//@PreAuthorize("hasRole('ROLE_single_realm_role')")
@RequestMapping("/addressbook")
@CrossOrigin(origins = "*")
public class AddressbookController {

    private final InternalAddressbookController addressbookController;
    private final AuthenticationService authenticationService;

    public AddressbookController(InternalAddressbookController addressbookController, AuthenticationService authenticationService) {
        this.addressbookController = addressbookController;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/allElement")
    public ResponseEntity<List<AddressBook>> getAll(){
        String username = authenticationService.getUsername();
        List<AddressBook> result = addressbookController.getAll(username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<AddressBook> getByUsername(){
        String username = authenticationService.getUsername();
        AddressBook result = addressbookController.getByUsername(username);
        return ResponseEntity.ok().body(result);
    }

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
        AddressBook result = addressbookController.getByEmail(email);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getAddressbookList(@RequestBody AddressBookFilter filter,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int sizeP) {
        String username = authenticationService.getUsername();
        Page<AddressBook> result = addressbookController.getAddressbookList(filter, page, sizeP, username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/page")
    public ResponseEntity<Object> pageAddressBook(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int sizeP) {
        String username = authenticationService.getUsername();
        Page<AddressBook> result = addressbookController.pageAddressBook(page, sizeP, username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/get/autocomplete")
    public ResponseEntity<Set<AutoCompleteRes>> filterEventAutocomplete(@RequestParam("filterObject") String filterObject) {

        String username = authenticationService.getUsername();
        Set<AutoCompleteRes> result = addressbookController.filterAutocomplete(filterObject, username);

        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAddressBook(@PathVariable String id, @RequestBody AddressBook addressBook) {
        String username = authenticationService.getUsername();
        AddressBook result = addressbookController.updateAddressBook(id, addressBook);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save/addressbook")
    public ResponseEntity<AddressBook> saveAddressbook(@RequestBody AddressBook addressBook){
        String username = authenticationService.getUsername();
        addressBook.setUserId(username);
        AddressBook result = addressbookController.saveAddressbook(addressBook);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ROLE_single_realm_role')")
    @GetMapping("/countRubrica")
    public ResponseEntity<Object> countRubrica() {
        String username = authenticationService.getUsername();
        long count = addressbookController.countRubrica(username);
        return ResponseEntity.ok().body(count);
    }
}
