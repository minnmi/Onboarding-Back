package br.com.totemti.onboardingback.adapters;

import br.com.totemti.onboardingback.models.Email;
import br.com.totemti.onboardingback.repositories.IEmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmailRepositoryAdapter {

    private final IEmailRepository emailRepository;

    public void save(Email email) {
        emailRepository.save(email);
    }

    public void deleteById(Long id) {
        emailRepository.deleteById(id);
    }

    public List<Email> findAllByPersonId(Long idPerson) {

        return emailRepository.findAllByPersonId(idPerson);
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.emailRepository.deleteAllByPersonId(idPerson);
    }
}
