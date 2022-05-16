package br.com.totemti.onboardingback.services;

import br.com.totemti.onboardingback.adapters.ItemSaleRepositoryAdapter;
import br.com.totemti.onboardingback.adapters.SaleRepositoryAdapter;
import br.com.totemti.onboardingback.controllers.dtos.sales.SaleProduct;
import br.com.totemti.onboardingback.controllers.dtos.sales.SaleRequest;
import br.com.totemti.onboardingback.models.ItemSale;
import br.com.totemti.onboardingback.models.Person;
import br.com.totemti.onboardingback.models.Product;
import br.com.totemti.onboardingback.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaleService {

    private final SaleRepositoryAdapter saleRepositoryAdapter;
    private final ItemSaleRepositoryAdapter itemSaleRepositoryAdapter;
    private final PersonService personService;
    private final ProductService productService;

    @Autowired
    public SaleService(
            SaleRepositoryAdapter saleRepositoryAdapter,
            ItemSaleRepositoryAdapter itemSaleRepositoryAdapter,
            PersonService personService,
            ProductService productService
    ) {
        this.saleRepositoryAdapter = saleRepositoryAdapter;
        this.itemSaleRepositoryAdapter = itemSaleRepositoryAdapter;
        this.personService = personService;
        this.productService = productService;
    }

    public void create(SaleRequest saleRequest) {
        final var sale = new Sale();
        sale.setSaleDate(saleRequest.getSaleDate());
        sale.setUserSale(saleRequest.getUserSale());
        sale.setTotalValue(saleRequest.getTotalValue());
        sale.setValueDiscount(saleRequest.getValueDiscount());
        sale.setSaleStatus(saleRequest.getSaleStatus());
        sale.setComment(saleRequest.getComment());
        sale.setPerson(getPerson(saleRequest));
        this.saleRepositoryAdapter.save(sale);

        for (SaleProduct saleProduct : saleRequest.getSaleProductList()) {

            final var itemSale = new ItemSale();
            final var product = getProduct(saleProduct);
            itemSale.setProduct(product);
            itemSale.setValue(saleProduct.getValue());
            itemSale.setQuantity(saleProduct.getQuantity());
            itemSale.setDiscount(saleProduct.getDiscountValue());

            final var partialValue = saleProduct.getValue()
                    .multiply(new BigDecimal(saleProduct.getQuantity()))
                    .subtract(saleProduct.getDiscountValue());

            itemSale.setPartialValue(partialValue);
            itemSale.setSale(sale);
            this.itemSaleRepositoryAdapter.save(itemSale);

            final var newQuantity = product.getQuantity() - saleProduct.getQuantity();
            product.setQuantity(newQuantity);
            this.productService.save(product);


        }
    }

    private Product getProduct(SaleProduct saleProduct) {
        return this.productService.getProduct(saleProduct.getIdProduct());
    }

    private Person getPerson(SaleRequest saleRequest) {
        return this.personService.getPerson(saleRequest.getIdPerson());
    }


}
