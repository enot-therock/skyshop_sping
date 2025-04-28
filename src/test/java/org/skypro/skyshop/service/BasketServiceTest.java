package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void whenAddIsBlankProduct_ThenBasketReturnException() {
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(UUID.randomUUID()));
    }

    @Test
    void whenAddProduct_ThenAddProductToBasket() {
        Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);

        when(storageService.getProductById(kofi.getId())).thenReturn(Optional.of(kofi));
        basketService.addProduct(kofi.getId());

        Mockito.verify(productBasket).addProduct(kofi.getId());
    }

    @Test
    void whenProductBasketIsBlank_ThenGetUserBasketReturnIsBlank() {
        when(productBasket.allProducts()).thenReturn(Map.of());

        UserBasket actualResult = basketService.getUserBaskets();

        assertTrue(actualResult.getItems().isEmpty());
    }

    @Test
    void whenProductBasketGiveIsProduct_ThenGetUserBasketReturnProduct() {
        Product meat = new SimpleProduct(UUID.randomUUID(), "Мясо", 1296);
        Product orange = new SimpleProduct(UUID.randomUUID(), "Апельсин", 44);

        when(storageService.getProductById(orange.getId())).thenReturn(Optional.of(orange));
        when(storageService.getProductById(meat.getId())).thenReturn(Optional.of(meat));

        basketService.addProduct(meat.getId());
        basketService.addProduct(orange.getId());

        when(productBasket.allProducts()).thenReturn(Map.of(orange.getId(), 1, meat.getId(), 1));

        UserBasket expectedResult = new UserBasket(List.of(new BasketItem(orange, 1), new BasketItem(meat, 1)));
        UserBasket actualResult = basketService.getUserBaskets();

        assertEquals(actualResult.getItems().contains(orange.getProduct()), expectedResult.getItems().contains(orange.getProduct()));
        assertEquals(actualResult.getItems().contains(meat.getProduct()), expectedResult.getItems().contains(meat.getProduct()));
    }
}
