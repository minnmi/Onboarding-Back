package br.com.totemti.onboardingback.controllers;


import br.com.totemti.onboardingback.commons.PageableResponseBase;
import br.com.totemti.onboardingback.commons.ResponseBase;
import br.com.totemti.onboardingback.controllers.dtos.persons.*;
import br.com.totemti.onboardingback.services.AddressService;
import br.com.totemti.onboardingback.services.EmailService;
import br.com.totemti.onboardingback.services.PersonService;
import br.com.totemti.onboardingback.services.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/persons")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class PersonController {

    private final PersonService personService;
    private final EmailService emailService;
    private final AddressService addressService;
    private final PhoneService phoneService;

    @Transactional
    @PostMapping
    public ResponseEntity<ResponseBase<Void>> create(@RequestBody final CreatePersonRequest createPersonRequest) {
        this.personService.create(createPersonRequest);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @GetMapping//Buscar os dados do banco
    public ResponseEntity<PageableResponseBase<PersonQueryResult>> findAll(PersonQueryParameter personQueryParameter) {
        final var persons = this.personService.findAll(personQueryParameter);
        return ResponseEntity.ok(PageableResponseBase.of(persons));
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<ResponseBase<PersonQueryResult>> findById(@PathVariable Long idPerson) {
        var person = this.personService.findById(idPerson);
        return ResponseEntity.ok(ResponseBase.of(person));
    }

    @Transactional
    @DeleteMapping("/{idPerson}")
    public ResponseEntity<ResponseBase<Void>> deleteById(@PathVariable Long idPerson) {
        this.personService.deleteById(idPerson);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @Transactional
    @PutMapping("/{idPerson}")
    public ResponseEntity<ResponseBase<Void>> update(@PathVariable Long idPerson, @RequestBody UpdatePersonRequest updatePersonRequest) {
        this.personService.update(updatePersonRequest, idPerson);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @GetMapping("/{idPerson}/emails")
    public ResponseEntity<ResponseBase<List<EmailItem>>> findEmailsByPersonId(@PathVariable Long idPerson) {
        var emails = this.emailService.findEmailItemsByPersonId(idPerson);
        return ResponseEntity.ok(ResponseBase.of(emails));
    }

    @GetMapping("/{idPerson}/addresses")
    public ResponseEntity<ResponseBase<List<AddressItem>>> findAddressesByPersonId(@PathVariable Long idPerson) {
        var addresses = this.addressService.findAddressesByPersonId(idPerson);
        return ResponseEntity.ok(ResponseBase.of(addresses));
    }

    @GetMapping("/{idPerson}/phones")
    public ResponseEntity<ResponseBase<List<PhoneItem>>> findPhonesByPersonId(@PathVariable Long idPerson) {
        var phones = this.phoneService.findPhonesItemsByPersonId(idPerson);
        return ResponseEntity.ok(ResponseBase.of(phones));
    }


}
