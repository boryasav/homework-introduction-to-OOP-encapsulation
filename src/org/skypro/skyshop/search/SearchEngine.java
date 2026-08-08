package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;
import java.util.stream.Collectors;


public class SearchEngine {

    private final Set<Searchable> searchables;

    public SearchEngine() {
        searchables = new HashSet<>();
    }

    public void add(Searchable searchable) {
        Objects.requireNonNull(searchable, "Передаваемый объект не может быть null");
        searchables.add(searchable);
    }

    public Set<Searchable> search(String searchTerm) {
        final String normalizedSearchTerm = normalizeSearchTerm(searchTerm);
        return searchables.stream()
                .filter(s -> s.getSearchTerm().contains(normalizedSearchTerm))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableComparator())));
    }

    public Searchable getBestMatch(String searchTerm) throws BestResultNotFound {
        String normalizedSearchTerm = normalizeSearchTerm(searchTerm);
        Searchable bestMatch = null;
        int maxCount = 0;
        for (Searchable searchable : searchables) {
            int count = countMatches(searchable.getSearchTerm(), normalizedSearchTerm);
            if (count > maxCount) {
                maxCount = count;
                bestMatch = searchable;
            }
        }
        if (bestMatch == null) {
            throw new BestResultNotFound("\nПо запросу \"" + searchTerm + "\" ничего не найдено");
        }
        return bestMatch;
    }

    private int countMatches(String text, String searchTerm) {
        int count = 0;
        int index = 0;
        int indexSubstring = text.indexOf(searchTerm, index);
        while (indexSubstring != -1) {
            count++;
            index = indexSubstring + searchTerm.length();
            indexSubstring = text.indexOf(searchTerm, index);
        }
        return count;
    }

    private String normalizeSearchTerm(String searchTerm) {
        searchTerm = Objects.requireNonNull(
                searchTerm,
                "Поисковый запрос не может быть null")
                .trim().toLowerCase();
        if (searchTerm.isBlank()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустой строкой");
        }
        return searchTerm;
    }
}
