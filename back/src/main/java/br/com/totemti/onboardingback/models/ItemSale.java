package br.com.totemti.onboardingback.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "VENDA_ITEM")
public class ItemSale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENDA_ITEM_SEQ")
    @SequenceGenerator(name = "VENDA_ITEM_SEQ", sequenceName = "VENDA_ITEM_SEQ", allocationSize = 1)
    @Column(name = "ID_VENDA_ITEM")
    private Long id;

    @Column(name = "VEI_QTDE")
    private Integer quantity;

    @Column(name = "VEI_VALOR")
    private BigDecimal value;

    @Column(name = "VEI_DESCONTO")
    private BigDecimal discount;

    @Column(name = "VEI_SUB_TOTAL")
    private BigDecimal partialValue;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", foreignKey = @ForeignKey(name = "VENDA_ITEM_PRODUTO_FK"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE_VENDA", foreignKey = @ForeignKey(name = "CLIENTE_VENDA_ITEM_FK"))
    private Sale sale;
}
