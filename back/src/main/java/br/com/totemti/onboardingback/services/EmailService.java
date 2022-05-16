package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.EmailRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.persons.EmailItem;
import br.com.totemti.onboardingback.controllers.dtos.persons.EmailRequest;
import br.com.totemti.onboardingback.models.Email;
import br.com.totemti.onboardingback.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {
    private final EmailRepositoryAdapter emailRepositoryAdapter;

    @Autowired
    //Notação para o spring injetar as dependências no construtor.
    //a depedência nesse caso é o adapter
    public EmailService(EmailRepositoryAdapter emailRepositoryAdapter) {
        this.emailRepositoryAdapter = emailRepositoryAdapter;
    }

    public void create(EmailRequest emailRequest, Person person) {
        final var email = new Email(); //instancia o email
        email.setEmail(emailRequest.getEmail());
        email.setEmailDefault(emailRequest.getEmailDefault());
        email.setPerson(person);
        emailRepositoryAdapter.save(email);
    }

    public List<Email> findAllByPersonId(Long idPerson) {
        return emailRepositoryAdapter.findAllByPersonId(idPerson);
    }

    public void deleteById(Long id) {
        emailRepositoryAdapter.deleteById(id);
    }

    public List<EmailItem> findEmailItemsByPersonId(Long idPerson) {
        final var emails = this.emailRepositoryAdapter.findAllByPersonId(idPerson);
        return emails.stream().map(Email::mapsToEmailItem).collect(Collectors.toList());
    }

    public void deleteAllByPersonId(Long idPerson) {
        this.emailRepositoryAdapter.deleteAllByPersonId(idPerson);
    }
}

