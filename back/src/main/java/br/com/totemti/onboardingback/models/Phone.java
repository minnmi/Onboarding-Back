package br.com.totemti.onboardingback.models;


import br.com.totemti.onboardingback.controllers.dtos.persons.PhoneItem;
import br.com.totemti.onboardingback.enums.PhoneDefault;
import br.com.totemti.onboardingback.enums.PhoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PESSOA_TELEFONE")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_TELEFONE_SEQ_GENERATOR")
    @SequenceGenerator(name = "PESSOA_TELEFONE_SEQ_GENERATOR", sequenceName = "PESSOA_TELEFONE_SEQ", allocationSize = 1)
    @Column(name = "ID_PESSOA_TELEFONE")
    private Long id;

    @Column(name = "PET_TELEFONE")
    private String phone;

    @Column(name = "PET_TIPO_TELEFONE")
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    @Column(name = "PET_TELEFONE_PADRAO")
    @Enumerated(EnumType.STRING)
    private PhoneDefault phoneDefault;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", foreignKey = @ForeignKey(name = "PESSOA_TELEFONE_FK"))
    private Person person;

    public static PhoneItem mapsToPhoneItem(Phone phone) {
        return new PhoneItem(phone.getId(), phone.getPhone(), phone.getPhoneType(), phone.getPhoneDefault());
    }
}
