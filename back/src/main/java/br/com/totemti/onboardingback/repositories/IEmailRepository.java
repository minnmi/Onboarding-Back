package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmailRepository extends JpaRepository<Email, Long> {

    @Query("select email " +
            "from Email email " +
            "where email.person.id=:idPerson")
    List<Email> findAllByPersonId(Long idPerson);

    @Modifying
    @Query("delete from Email e where e.person.id = :idPerson")
    void deleteAllByPersonId(Long idPerson);

}
