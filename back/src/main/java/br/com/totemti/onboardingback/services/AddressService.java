package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.AddressRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.persons.AddressItem;
import br.com.totemti.onboardingback.controllers.dtos.persons.AddressRequest;
import br.com.totemti.onboardingback.models.Address;
import br.com.totemti.onboardingback.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepositoryAdapter addressRepositoryAdapter;

    @Autowired
    public AddressService(AddressRepositoryAdapter addressRepositoryAdapter) {
        this.addressRepositoryAdapter = addressRepositoryAdapter;
    }

    public void create(AddressRequest addressRequest, Person person) {
        final var address = new Address();
        address.setPerson(person);
        address.setCep(addressRequest.getCep());
        address.setPlace(addressRequest.getPlace());
        address.setHouseNumber(addressRequest.getHouseNumber());
        address.setNeighborhood(addressRequest.getNeighborhood());
        address.setCity(addressRequest.getCity());
        address.setUf(addressRequest.getUf());
        address.setAddressDefault(addressRequest.getAddressDefault());
        addressRepositoryAdapter.save(address);
    }

    public List<Address> findAllByPersonId(Long idPerson) {
        return addressRepositoryAdapter.findAllByPersonId(idPerson);
    }

    public void deleteById(Long id) {
        addressRepositoryAdapter.deleteById(id);
    }

    public List<AddressItem> findAddressesByPersonId(Long idPerson) {
        final var addresses = this.addressRepositoryAdapter.findAllByPersonId(idPerson);
        return addresses.stream().map(Address::mapsToAddressItem).collect(Collectors.toList());
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.addressRepositoryAdapter.deleteAllByPersonId(idPerson);


    }
}
