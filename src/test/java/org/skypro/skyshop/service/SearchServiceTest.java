package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String search = "Авокадо";

        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();

        doAnswer(invocation -> {
            productMap.put(UUID.randomUUID(), null);
            articleMap.put(UUID.randomUUID(), null);

            return invocation.getMock();
        }).when(storageService).creatTest();

        Mockito.when(storageService.SearchableStorage())
                .thenReturn(Stream.concat(productMap.values().stream(), articleMap.values().stream()).toList());

        storageService.creatTest();

        Collection<SearchResult> expectedResults = storageService.SearchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));

        Collection<SearchResult> actualResults = searchService.search(search);

        assertTrue(expectedResults.isEmpty());
        assertTrue(actualResults.isEmpty());

        Mockito.verify(storageService, Mockito.atLeastOnce()).SearchableStorage();
    }

    @Test
    void whenThereIsNoObject_ThenStorageReturnsIsBlank() {
        String search = "Авокадо";

        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();

        doAnswer(invocation -> {
            Product cake = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
            Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);
            Article meatBeard = new Article(UUID.randomUUID(), "Мясо-птицы", "Углеводы 70%");

            productMap.put(cake.getId(), cake);
            productMap.put(kofi.getId(), kofi);
            articleMap.put(meatBeard.getId(), meatBeard);

            return invocation.getMock();
        }).when(storageService).creatTest();

        Mockito.when(storageService.SearchableStorage())
                .thenReturn(Stream.concat(productMap.values().stream(), articleMap.values().stream()).toList());

        storageService.creatTest();

        Collection<SearchResult> expectedResults = storageService.SearchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));

        NoSuchProductException thrown = assertThrows(NoSuchProductException.class,
                () -> searchService.search(search), "Такого продукта нет");

        Collection<SearchResult> actualResults = searchService.search(search);

        Mockito.when(expectedResults).thenReturn(actualResults);

        assertEquals(actualResults, thrown.getMessage());
    }

    @Test
    void whenThereIsGiveObject_ThenStorageReturnsObject() {
        String search = "Торт";

        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();

        doAnswer(invocation -> {
            Product cake = new SimpleProduct(UUID.randomUUID(), "Торт", 598);
            Product kofi = new SimpleProduct(UUID.randomUUID(), "Кофе", 813);
            Article meatBeard = new Article(UUID.randomUUID(), "Мясо-птицы", "Углеводы 70%");

            productMap.put(cake.getId(), cake);
            productMap.put(kofi.getId(), kofi);
            articleMap.put(meatBeard.getId(), meatBeard);

            return invocation.getMock();
        }).when(storageService).creatTest();

        Mockito.when(storageService.SearchableStorage())
                .thenReturn(Stream.concat(productMap.values().stream(), articleMap.values().stream()).toList());

        storageService.creatTest();

        Collection<SearchResult> expectedResults = storageService.SearchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));

        Mockito.when(searchService.search(search)).thenReturn(expectedResults);

        storageService.SearchableStorage();

        Collection<SearchResult> actualResults = searchService.search(search);

        assertEquals(expectedResults, actualResults);
    }
}
