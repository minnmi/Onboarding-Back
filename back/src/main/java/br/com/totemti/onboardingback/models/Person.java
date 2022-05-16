package br.com.totemti.onboardingback.models;

import br.com.totemti.onboardingback.controllers.dtos.persons.PersonQueryResult;
import br.com.totemti.onboardingback.enums.Gender;
import br.com.totemti.onboardingback.enums.PersonStatus;
import br.com.totemti.onboardingback.enums.PersonType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PESSOA")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ_GENERATOR")
    @SequenceGenerator(name = "PESSOA_SEQ_GENERATOR", sequenceName = "PESSOA_SEQ", allocationSize = 1)
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "PES_NOME")
    private String name;

    @Column(name = "PES_NASCIMENTO")
    private LocalDate birth;

    @Column(name = "PES_CPF_CNPJ")
    private String document;

    @Column(name = "PES_SEXO")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "PES_INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;

    @Column(name = "PES_ATIVO")
    @Enumerated(EnumType.STRING)
    private PersonStatus status;

    @Column(name = "PES_TIPO")
    @Enumerated(EnumType.STRING)
    private PersonType type;

    @OneToMany(mappedBy = "person")
    private Set<Phone> phones;

    @OneToMany(mappedBy = "person")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "person")
    private Set<Email> emails;


    public static PersonQueryResult mapsToQueryResult(Person person) {
        final var personQueryResult = new PersonQueryResult();
        personQueryResult.setId(person.getId());
        personQueryResult.setStatus(person.getStatus());
        personQueryResult.setName(person.getName());
        personQueryResult.setDocument(person.getDocument());
        personQueryResult.setGender(person.getGender());
        personQueryResult.setBirth(person.getBirth());
        personQueryResult.setInscricaoEstadual(person.getInscricaoEstadual());
        personQueryResult.setType(person.getType());
        return personQueryResult;
    }

}
