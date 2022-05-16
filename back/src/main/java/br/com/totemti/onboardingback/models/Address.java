package br.com.totemti.onboardingback.models;


import br.com.totemti.onboardingback.controllers.dtos.persons.AddressItem;
import br.com.totemti.onboardingback.enums.AddressDefault;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PESSOA_ENDERECO")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_ENDERECO_SEQ_GENERATOR")
    @SequenceGenerator(name = "PESSOA_ENDERECO_SEQ_GENERATOR", sequenceName = "PESSOA_ENDERECO_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PESSOA_ENDERECO")
    private Long id;

    @Column(name = "PEN_CEP")
    private String cep;

    @Column(name = "PEN_LOGRADOURO")
    private String place;

    @Column(name = "PEN_NUMERO")
    private String houseNumber;

    @Column(name = "PEN_BAIRRO")
    private String neighborhood;

    @Column(name = "PEN_CIDADE")
    private String city;

    @Column(name = "PEN_UF")
    private String uf;

    @Column(name = "PEN_ENDERECO_PADRAO")
    @Enumerated(EnumType.STRING)
    private AddressDefault addressDefault;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA")
    private Person person;

    public static AddressItem mapsToAddressItem(Address address) {
        return new AddressItem(address.getId(), address.getPlace(), address.getNeighborhood(), address.getCep(), address.getCity(), address.getUf(), address.getAddressDefault());
    }
}
