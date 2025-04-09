package org.skypro.skyshop.service;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addBasket(UUID id) {
        if (storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException("Такой товар уже есть");
        } else {
            productBasket.addProductBasket(id);
        }
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems =
                productBasket.allProducts().entrySet().stream()
                .map(m -> {
                    Product product = storageService.getProductById(m.getKey())
                            .orElseThrow(NoSuchProductException::new);
                    return new BasketItem(product, m.getValue());
                })
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}


