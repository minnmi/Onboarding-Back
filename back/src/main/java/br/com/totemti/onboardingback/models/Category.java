package br.com.totemti.onboardingback.models;

import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryQueryResult;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryRequest;
import br.com.totemti.onboardingback.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PRODUTO_CATEGORIA")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_CATEGORIA")
    private Long id;

    @Column(name = "PRC_NOME")
    private String name;

    @Column(name = "PRC_SITUACAO")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(String name, CategoryStatus status) {
        ValidateName(name);
        ValidateStatus(status);
        this.name = name;
        this.status = status;
    }

    public Category() {

    }

    public static Category mapsToCategory(CategoryRequest categoryRequest) {
        return new Category(categoryRequest.getName(), categoryRequest.getStatus());
    }

    public static CategoryQueryResult mapsToQueryResult(Category category) {
        final var result = new CategoryQueryResult();
        result.setName(category.getName());
        result.setStatus(category.getStatus());
        result.setIdCategory(category.getId());
        return result;
    }

    private void ValidateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalStateException("Name cannot be empty!");
        }
        if (name.isBlank()) {
            throw new IllegalStateException("Name cannot be blank!");
        }
    }

    private void ValidateStatus(CategoryStatus status) {
        if (status == null) {
            throw new IllegalStateException("Status cannot be NULL!");
        }
    }
}
