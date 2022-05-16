package br.com.totemti.onboardingback.repositories;

import br.com.totemti.onboardingback.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("select address " +
            "from Address address " +
            "where address.person.id=:idPerson")
    List<Address> findAllByPersonId(Long idPerson);

    @Modifying
    @Query("delete from Address a where a.person.id = :idPerson")
    void deleteAllByPersonId(Long idPerson);

}
