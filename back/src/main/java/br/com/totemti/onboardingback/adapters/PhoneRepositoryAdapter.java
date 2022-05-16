package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.models.Phone;
import br.com.totemti.onboardingback.repositories.IPhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PhoneRepositoryAdapter {

    private final IPhoneRepository phoneRepository;

    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

    public List<Phone> findAllByPersonId(Long idPerson) {
        return phoneRepository.findAllByPersonId(idPerson);
    }

    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.phoneRepository.deleteAllByPersonId(idPerson);
    }
}

