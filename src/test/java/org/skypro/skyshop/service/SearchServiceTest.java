package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void whenThereIsNoObject_ThenStorageReturnsIsNull() {
        String searchText = "Лаваш";
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();
        productMap.put(UUID.randomUUID(), null);
        articleMap.put(UUID.randomUUID(), null);

        try {
            Mockito.when(storageService.SearchableStorage()).thenThrow(NullPointerException.class);
        } catch (NullPointerException e) {
            System.out.println("Список продуктов пустой");
        }

        assertEquals(searchService.search(searchText), searchText);
    }

    @Test
    void whenThereIsNoObject_ThenStorageReturnsIsBlank() {
        String searchText = "Лаваш";
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();
        Product product = new SimpleProduct(UUID.randomUUID(), "сыр", 12);
        Article article = new Article(UUID.randomUUID(), "краб", "камчатский");
        productMap.put(UUID.randomUUID(), product);
        articleMap.put(UUID.randomUUID(), article);

        Mockito.when(storageService.SearchableStorage()).thenReturn(List.of(product, article));

        assertEquals(searchService.search(searchText), searchText);
    }
}
