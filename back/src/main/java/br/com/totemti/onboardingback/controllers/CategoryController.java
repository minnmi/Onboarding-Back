package br.com.totemti.onboardingback.controllers;

import br.com.totemti.onboardingback.commons.PageableResponseBase;
import br.com.totemti.onboardingback.commons.ResponseBase;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryQueryParameter;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryQueryResult;
import br.com.totemti.onboardingback.controllers.dtos.categories.CategoryRequest;
import br.com.totemti.onboardingback.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//O controller mapeia as requisições que vão para o front

@AllArgsConstructor
@RestController
@RequestMapping(value = "/categories")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping //Inserir dados novos no banco
    public ResponseEntity<ResponseBase<Void>> create(@RequestBody CategoryRequest categoryRequest) {
        this.categoryService.create(categoryRequest);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @GetMapping//Buscar os dados do banco
    public ResponseEntity<PageableResponseBase<CategoryQueryResult>> findAll(CategoryQueryParameter categoryQueryParameter) {
        final var categories = this.categoryService.findAll(categoryQueryParameter);
        return ResponseEntity.ok(PageableResponseBase.of(categories));
    }

    @GetMapping("/{idCategory}") //busca pelo id
    public ResponseEntity<ResponseBase<CategoryQueryResult>> findById(@PathVariable Long idCategory) {
        var category = this.categoryService.findById(idCategory);
        return ResponseEntity.ok(ResponseBase.of(category));
    }

    @DeleteMapping("/{idCategory}") //Deletar dados do banco
    public ResponseEntity<ResponseBase<Void>> deleteById(@PathVariable Long idCategory) {
        this.categoryService.deleteById(idCategory);
        return ResponseEntity.ok(ResponseBase.of());
    }

    @PutMapping("/{idCategory}") //Editam dados do banco (Editar todos os dados)
    public ResponseEntity<ResponseBase<Void>> update(@RequestBody CategoryRequest categoryRequest, @PathVariable Long idCategory) {
        this.categoryService.update(categoryRequest, idCategory);
        return ResponseEntity.ok(ResponseBase.of());
    }

}
