package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

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
        Objects.requireNonNull(searchTerm, "Передаваемый объект не может быть null");

        Set<Searchable> results = new TreeSet<>(new SearchableComparator());
        for (Searchable searchable : searchables) {
            if (searchable != null && (searchable.getSearchTerm().toLowerCase()).contains(searchTerm.toLowerCase())) {
                results.add(searchable);
            }
        }
        return results;
    }

    public Searchable searchBestMatch(String searchTerm) throws BestResultNotFound {
        Objects.requireNonNull(searchTerm, "Передаваемый объект не может быть null");

        Searchable result = null;
        int maxCount = 0;
        for (Searchable searchable : searchables) {
            if (searchable != null) {
                int count = countMatches(searchable.getSearchTerm().toLowerCase(), searchTerm.toLowerCase());
                if (count > maxCount) {
                    maxCount = count;
                    result = searchable;
                }
            }
        }
        if (result == null) {
            throw new BestResultNotFound("\nПо запросу \"" + searchTerm + "\" ничего не найдено");
        }
        return result;
    }

    private int countMatches(String string, String substring) {
        int count = 0;
        int index = 0;
        int indexSubstring = string.indexOf(substring, index);
        while (indexSubstring != -1) {
            count++;
            index = indexSubstring + substring.length();
            indexSubstring = string.indexOf(substring, index);
        }
        return count;
    }
}
