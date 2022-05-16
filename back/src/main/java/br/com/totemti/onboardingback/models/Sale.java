package br.com.totemti.onboardingback.models;

import br.com.totemti.onboardingback.enums.SaleStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE_VENDA")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_VENDA_SEQ_GENERATOR")
    @SequenceGenerator(name = "CLIENTE_VENDA_SEQ_GENERATOR", sequenceName = "CLIENTE_VENDA_SEQ", allocationSize = 1)
    @Column(name = "ID_CLIENTE_VENDA")
    private Long id;

    @Column(name = "CLV_DATA_VENDA")
    private LocalDate saleDate;

    @Column(name = "CLV_USUARIO_VENDA")
    private String userSale;

    @Column(name = "CLV_VALOR_TOTAL")
    private BigDecimal totalValue;

    @Column(name = "CLV_DESCONTO_VENDA")
    private BigDecimal valueDiscount;

    @Setter(AccessLevel.NONE)
    @Column(name = "CLV_SITUACAO")
    private Integer saleStatusValue;

    @Transient //tudo que é transiente não é peristido no banco
    private SaleStatus saleStatus;

    @Column(name = "CLV_OBSERVACAO")
    private String comment;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private List<ItemSale> itemSales;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", foreignKey = @ForeignKey(name = "CLIENTE_VENDA_PESSOA_FK"))
    private Person person;


    @PrePersist
    public void setup() {
        this.saleStatusValue = SaleStatus.ABERTO.getValue();
//                Optional.ofNullable(saleStatus)
//                .map(SaleStatus::getValue)
//                .orElseGet(() -> null);

//        Se saleDate ou userSale não forem nulos, serão usados conforme está no banco,
//        se forem nulos, serão usados a data do dia da venda e o nome "Admin"

        this.saleDate = Optional.ofNullable(this.saleDate).orElse(LocalDate.now());

        this.userSale = Optional.ofNullable(this.userSale).orElse("Admin");
    }

    public SaleStatus getSaleStatus() {
        return SaleStatus.getEnum(saleStatusValue);
    }
}
