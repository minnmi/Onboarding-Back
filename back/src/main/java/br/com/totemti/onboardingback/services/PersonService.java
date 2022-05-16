package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.PersonRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.persons.*;
import br.com.totemti.onboardingback.exceptions.EntityNotFoundException;
import br.com.totemti.onboardingback.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepositoryAdapter personRepositoryAdapter;
    private final EmailService emailService;
    private final PhoneService phoneService;
    private final AddressService addressService;

    @Autowired
    public PersonService(
            PersonRepositoryAdapter personRepositoryAdapter,
            EmailService emailService,
            PhoneService phoneService,
            AddressService addressService
    ) {
        this.personRepositoryAdapter = personRepositoryAdapter;
        this.emailService = emailService;
        this.phoneService = phoneService;
        this.addressService = addressService;
    }

    public void create(CreatePersonRequest createPersonRequest) {
        final var person = createPerson(createPersonRequest);
        createEmails(person, createPersonRequest.getEmailList());
        createAddresses(person, createPersonRequest.getAddressList());
        createPhones(person, createPersonRequest.getPhonesList());

    }

    private void createAddresses(Person person, List<AddressRequest> addressList) {
        if (addressList == null) {
            throw new EntityNotFoundException("The address list is missing.");
        }
        for (AddressRequest addressRequest : addressList) {
            addressService.create(addressRequest, person);
        }
    }

    private void createPhones(Person person, List<PhoneRequest> phonesList) {
        if (phonesList == null) {
            throw new EntityNotFoundException("The phone list is missing.");
        }
        for (PhoneRequest phoneRequest : phonesList) {
            phoneService.create(phoneRequest, person);
        }
    }

    private void createEmails(Person person, List<EmailRequest> emailList) {
        if (emailList == null) {
            throw new EntityNotFoundException("The email list is missing.");
        }
        for (EmailRequest emailRequest : emailList) {
            emailService.create(emailRequest, person);
        }
    }

    private Person createPerson(CreatePersonRequest createPersonRequest) {
        final var person = new Person();
        person.setName(createPersonRequest.getName());
        person.setBirth(createPersonRequest.getBirth());
        person.setDocument(createPersonRequest.getDocument());
        person.setGender(createPersonRequest.getGender());
        person.setInscricaoEstadual(createPersonRequest.getInscricaoEstadual());
        person.setStatus(createPersonRequest.getStatus());
        person.setType(createPersonRequest.getType());
        return personRepositoryAdapter.save(person);
    }

    public Page<PersonQueryResult> findAll(PersonQueryParameter personQueryParameter) {
        final var name = personQueryParameter.getName();
        final var status = personQueryParameter.getStatus();
        final var type = personQueryParameter.getType();
        final var pageable = personQueryParameter.getPageable();

        final var persons = this.personRepositoryAdapter.findAll(type, name, status, pageable);

        return persons.map(person -> Person.mapsToQueryResult(person));
    }

    public void deleteById(Long idPerson) {
        //toDo: Validar se a pessoa possui registros
        deleteAllPhones(idPerson);
        deleteAllEmails(idPerson);
        deleteAllAddresses(idPerson);


        personRepositoryAdapter.deleteById(idPerson);
    }

    private void deleteAllEmails(Long idPerson) {
        final var emailList = emailService.findAllByPersonId(idPerson);
        for (var email : emailList) {
            emailService.deleteById(email.getId());
        }
    }

    private void deleteAllAddresses(Long idPerson) {
        final var addressList = addressService.findAllByPersonId(idPerson);
        for (var address : addressList) {
            addressService.deleteById(address.getId());
        }
    }

    private void deleteAllPhones(Long idPerson) {
        final var phoneList = phoneService.findAllByPersonId(idPerson);
        for (var phone : phoneList) {
            phoneService.deleteById(phone.getId());
        }
    }

    public void update(UpdatePersonRequest updatePersonRequest, Long idPerson) {
        Person person = getPerson(idPerson);
        updateFields(person, updatePersonRequest);
        updateAddresses(person, updatePersonRequest);
        updateEmails(person, updatePersonRequest);
        updatePhones(person, updatePersonRequest);
        save(person);
    }

    private void updatePhones(Person person, UpdatePersonRequest updatePersonRequest) {
        this.phoneService.deleteAllByPersonId(person.getId());
        createPhones(person, updatePersonRequest.getPhones());
    }

    private void updateEmails(Person person, UpdatePersonRequest updatePersonRequest) {
        this.emailService.deleteAllByPersonId(person.getId());
        createEmails(person, updatePersonRequest.getEmails());
    }

    private void updateFields(Person person, UpdatePersonRequest updatePersonRequest) {
        person.setName(updatePersonRequest.getName());
        person.setBirth(updatePersonRequest.getBirth());
        person.setDocument(updatePersonRequest.getDocument());
        person.setGender(updatePersonRequest.getGender());
        person.setInscricaoEstadual(updatePersonRequest.getInscricaoEstadual());
        person.setStatus(updatePersonRequest.getStatus());
    }

    private void updateAddresses(Person person, UpdatePersonRequest updatePersonRequest) {
        this.addressService.deleteAllByPersonId(person.getId());
        createAddresses(person, updatePersonRequest.getAddresses());
    }

    private void save(Person person) {
        this.personRepositoryAdapter.save(person);
    }

    Person getPerson(Long idPerson) {
        return this.personRepositoryAdapter.maybeFindById(idPerson)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public PersonQueryResult findById(Long idPerson) {
        return this.personRepositoryAdapter.maybeFindById(idPerson)
                .map(Person::mapsToQueryResult)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

}
