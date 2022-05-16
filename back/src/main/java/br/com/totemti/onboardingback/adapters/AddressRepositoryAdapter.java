package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.models.Address;
import br.com.totemti.onboardingback.repositories.IAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AddressRepositoryAdapter {

    private final IAddressRepository addressRepository;

    public void save(Address address) {
        addressRepository.save(address);
    }

    public List<Address> findAllByPersonId(Long idPerson) {
        return addressRepository.findAllByPersonId(idPerson);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.addressRepository.deleteAllByPersonId(idPerson);
    }
}
