package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
     void whenThereIsNoObject_ThenStorageReturnsIsNull() {
        String searchText = "Авокадо";

        Mockito.when(storageService.searchableStorage()).thenReturn(List.of());

        Collection<SearchResult> actualResults = searchService.search(searchText);

        assertTrue(actualResults.isEmpty());
    }

    @Test
    void whenThereIsNoObject_ThenStorageReturnsIsBlank() {
        String searchText = "Авокадо";

        Product product = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
        Product product1 = new SimpleProduct(UUID.randomUUID(), "Апельсин", 600);
        Article article = new Article(UUID.randomUUID(), "Манго", "Фрукт");

        when(storageService.searchableStorage()).thenReturn(List.of(product, product1, article));

        Collection<SearchResult> actualResult = searchService.search(searchText);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void whenThereIsGiveObject_ThenStorageReturnsObject() {
        String searchText = "Торт";

        Product product = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
        Product product1 = new SimpleProduct(UUID.randomUUID(), "Апельсин", 600);
        Article article = new Article(UUID.randomUUID(), "Манго", "Фрукт");

        when(storageService.searchableStorage()).thenReturn(List.of(product, product1, article));

        Collection<SearchResult> actualResult = searchService.search(searchText);

        assertFalse(actualResult.isEmpty());
    }
}
