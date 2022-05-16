package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.enums.PersonStatus;
import br.com.totemti.onboardingback.enums.PersonType;
import br.com.totemti.onboardingback.models.Person;
import br.com.totemti.onboardingback.repositories.IPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PersonRepositoryAdapter {

    private final IPersonRepository personRepository;


    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Page<Person> findAll(PersonType type, String name, PersonStatus status, Pageable pageable) {
        return personRepository.findAll(type, name, status, pageable);
    }

    public void deleteById(Long idPerson) {
        personRepository.deleteById(idPerson);
    }

    public Optional<Person> maybeFindById(Long idPerson) {
        return this.personRepository.findById(idPerson);
    }

}
