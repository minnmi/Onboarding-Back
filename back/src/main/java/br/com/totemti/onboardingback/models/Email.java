package br.com.totemti.onboardingback.models;

import br.com.totemti.onboardingback.controllers.dtos.persons.EmailItem;
import br.com.totemti.onboardingback.enums.EmailDefault;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PESSOA_EMAIL")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_EMAIL_SEQ_GENERATOR")
    @SequenceGenerator(name = "PESSOA_EMAIL_SEQ_GENERATOR", sequenceName = "PESSOA_EMAIL_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PESSOA_EMAIL")
    private Long id;

    @Column(name = "PEE_EMAIL")
    private String email;

    @Column(name = "PEE_EMAIL_PADRAO")
    @Enumerated(EnumType.STRING)
    private EmailDefault emailDefault;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", foreignKey = @ForeignKey(name = "PESSOA_EMAIL_FK"))
    private Person person;

    public static EmailItem mapsToEmailItem(Email email) {
        return new EmailItem(email.getId(), email.getEmail(), email.getEmailDefault());
    }


}
