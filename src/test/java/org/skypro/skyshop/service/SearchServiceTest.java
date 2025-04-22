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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;
    private SearchResult searchResult;

    @InjectMocks
    private SearchService searchService;

    @Test
    void whenThereIsNoObject_ThenStorageReturnsIsNull() {
        String search = "Авокадо";
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();

        productMap.put(UUID.randomUUID(), null);
        productMap.put(UUID.randomUUID(), null);
        articleMap.put(UUID.randomUUID(), null);

        Collection<SearchResult> expectedResults = storageService.SearchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));

        Mockito.when(searchService.search(search)).thenReturn(expectedResults);

        Collection<SearchResult> actualResults = searchService.search(search);

        assertTrue(expectedResults.isEmpty());
        assertTrue(actualResults.isEmpty());
    }

//    @Test
//    void whenThereIsNoObject_ThenStorageReturnsIsBlank() {
//        String search = "Авокадо";
//        Map<UUID, Product> productMap = new HashMap<>();
//        Map<UUID, Article> articleMap = new HashMap<>();
//        Product cake = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
//        Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);
//        Article meatBeard = new Article(UUID.randomUUID(), "Мясо-птицы", "Углеводы 70%");
//        productMap.put(UUID.randomUUID(), cake);
//        productMap.put(UUID.randomUUID(), kofi);
//        articleMap.put(UUID.randomUUID(), meatBeard);
//
//        Collection<SearchResult> results = storageService.SearchableStorage().stream()
//                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
//                .map(SearchResult::fromSearchable)
//                .collect(Collectors.toCollection(ArrayList::new));
//
//        Mockito.when(searchService.search(search)).thenReturn(results);
//
//        assertEquals(searchService.search(search), results);
//    }

    @Test
    void whenThereIsGiveObject_ThenStorageReturnsObject() {
        String search = "Торт";
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();

        Product cake = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
        Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);
        Article meatBeard = new Article(UUID.randomUUID(), "Мясо-птицы", "Углеводы 70%");

        productMap.put(cake.getId(), cake);
        productMap.put(kofi.getId(), kofi);
        articleMap.put(meatBeard.getId(), meatBeard);

        Collection<SearchResult> expectedResults = storageService.SearchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));

        Mockito.when(searchService.search(search)).thenReturn(expectedResults);

        Collection<SearchResult> actualResults = searchService.search(search);

        assertEquals(expectedResults, actualResults);
        assertTrue(!expectedResults.isEmpty());
    }
}
