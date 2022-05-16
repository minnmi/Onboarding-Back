package br.com.totemti.onboardingback.models;


import br.com.totemti.onboardingback.controllers.dtos.products.ProductQueryResult;
import br.com.totemti.onboardingback.enums.ProductStatus;
import br.com.totemti.onboardingback.enums.ProductType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQ_GENERATOR")
    @SequenceGenerator(name = "PRODUTO_SEQ_GENERATOR", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "PRO_NOME")
    private String name;

    @Setter(AccessLevel.NONE)
    @Column(name = "PRO_TIPO")
    private Integer typeValue;

    @Transient //Variável temporária que não será persistida no banco
    private ProductType type;

    @Column(name = "PRO_QTDE")
    private Integer quantity;

    @Column(name = "PRO_VALOR")
    private BigDecimal value;

    @Column(name = "PRO_VALOR_DESCONTO")
    private BigDecimal discountValue;

    @Setter(AccessLevel.NONE)
    @Column(name = "PRO_DATA_CADASTRO")
    private LocalDate registerDate;

    @Setter(AccessLevel.NONE)
    @Column(name = "PRO_USUARIO_CADASTRO")
    private String userRegister;

    @Column(name = "PRO_SITUACAO")
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_CATEGORIA", foreignKey = @ForeignKey(name = "FK_PRODUTO_CATEGORIA"))
    private Category category;

    public static ProductQueryResult mapsToQueryResult(Product product) {
        final var result = new ProductQueryResult();
        result.setName(product.getName());
        result.setStatus(product.getStatus());
        result.setId(product.getId());
        result.setCategory(Optional.ofNullable(product.getCategory()).map(Category::getName).orElse(null));
        result.setIdCategory(Optional.ofNullable(product.getCategory()).map(Category::getId).orElse(null));
        result.setQuantity(product.getQuantity());
        result.setDiscountValue(product.getDiscountValue());
        result.setValue(product.getValue());
        result.setType(product.getType());
        return result;
    }

    @PrePersist //Anotação para quando quiser fazer alguma coisa antes de salvar no banco de dados
    public void setup() {
        this.typeValue = Optional.ofNullable(type)
                .map(ProductType::getValue)
                .orElse(null);


        this.registerDate = LocalDate.now();

        this.userRegister = "Admin";

    }

    public ProductType getType() {
        return ProductType.getEnum(typeValue);
    }
}
