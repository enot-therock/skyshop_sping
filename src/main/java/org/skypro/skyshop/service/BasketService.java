package org.skypro.skyshop.service;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException();
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBaskets() {
        Map<UUID, Integer> basket = productBasket.allProducts();
        List<BasketItem> basketItems = productBasket.allProducts()
                .entrySet()
                .stream()
                .map(m -> new BasketItem(storageService.getProductById(m.getKey())
                        .orElseThrow(NoSuchProductException::new), m.getValue()))
                .toList();
        return new UserBasket(basketItems);
    }
}


