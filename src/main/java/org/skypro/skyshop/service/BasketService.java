package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
