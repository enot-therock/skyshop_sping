package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService() {
        this.storageService = new StorageService();
    }

    public Collection<SearchResult> search(String searchText) {
        return storageService.SearchableResult().stream()
                .filter(v -> v.searchableName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }
}
