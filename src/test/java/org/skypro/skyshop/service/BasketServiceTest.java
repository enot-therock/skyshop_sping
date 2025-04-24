package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;
    private StorageService storageService;

    @InjectMocks
    private BasketService service;

    @Test
    void whenAddIsBlankProduct_ThenBasketReturnException() {

    }

    @Test
    void whenAddProduct_ThenAddProductToBasket() {

    }

    @Test
    void whenProductBasketIsBlank_ThenGetUserBasketReturnIsBlank() {

    }

    @Test
    void whenProductBasketGiveIsProduct_ThenGetUserBasketReturnProduct() {

    }
}
