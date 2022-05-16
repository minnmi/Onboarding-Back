package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone, Long> {

    @Query("select phone " +
            "from Phone phone " +
            "where phone.person.id=:idPerson")
    List<Phone> findAllByPersonId(Long idPerson);

    @Modifying
    @Query("delete from Phone p where p.person.id = :idPerson")
    void deleteAllByPersonId(Long idPerson);

}
