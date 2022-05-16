package br.com.totemti.onboardingback.services;


import br.com.totemti.onboardingback.adapters.PhoneRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.persons.PhoneItem;
import br.com.totemti.onboardingback.controllers.dtos.persons.PhoneRequest;
import br.com.totemti.onboardingback.models.Person;
import br.com.totemti.onboardingback.models.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    private final PhoneRepositoryAdapter phoneRepositoryAdapter;

    @Autowired
    public PhoneService(PhoneRepositoryAdapter phoneRepositoryAdapter) {
        this.phoneRepositoryAdapter = phoneRepositoryAdapter;
    }

    public void create(PhoneRequest phoneRequest, Person person) {
        final var phone = new Phone();
        phone.setPhone(phoneRequest.getPhone());
        phone.setPhoneDefault(phoneRequest.getPhoneDefault());
        phone.setPhoneType(phoneRequest.getPhoneType());
        phone.setPerson(person);
        phoneRepositoryAdapter.save(phone);
    }

    public List<Phone> findAllByPersonId(Long idPerson) {
        return phoneRepositoryAdapter.findAllByPersonId(idPerson);
    }

    public void deleteById(Long id) {
        phoneRepositoryAdapter.deleteById(id);
    }

    public List<PhoneItem> findPhonesItemsByPersonId(Long idPerson) {
        final var phones = this.phoneRepositoryAdapter.findAllByPersonId(idPerson);
        return phones.stream().map(Phone::mapsToPhoneItem).collect(Collectors.toList());
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.phoneRepositoryAdapter.deleteAllByPersonId(idPerson);
    }
}
