package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportResult;
import br.com.totemti.onboardingback.enums.PersonStatus;
import br.com.totemti.onboardingback.enums.PersonType;
import br.com.totemti.onboardingback.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    @Query("select person " +
            "from Person person " +
            "where (:name is null or lower(person.name) like concat('%', lower(:name), '%')) " +
            "and (:status is null or person.status = :status) " +
            "and (:type is null or person.type = :type) ")
    Page<Person> findAll(PersonType type, String name, PersonStatus status, Pageable pageable);


    @Query("select " +
            "new br.com.totemti.onboardingback.controllers.dtos.reports.PersonReportResult( " +
            "  person.name, " +
            "  person.type, " +
            "  person.inscricaoEstadual,  " +
            "  phone.phone, " +
            "  email.email  " +
            " ) " +
            "from Person person " +
            "left join person.phones phone " +
            "left join person.emails email " +
            "where (:idPerson is null or person.id = :idPerson) " +
            "and  (:type is null or person.type = :type)")
    List<PersonReportResult> findPersonBy(
            Long idPerson,
            PersonType type
    );
}
