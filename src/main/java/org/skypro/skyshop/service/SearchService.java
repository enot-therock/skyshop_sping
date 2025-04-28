package org.skypro.skyshop.service;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchText) {
        return storageService.searchableStorage().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(searchText.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
