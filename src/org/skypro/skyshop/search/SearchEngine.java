package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchEngine {

    private final List<Searchable> list;

    public SearchEngine() {
        list = new LinkedList<>();
    }

    public List<Searchable> search(String searchTerm) {
        if (searchTerm == null) {
            throw new IllegalArgumentException("Поисковый запрос не должен быть null");
        }

        List<Searchable> resultsSearch = new ArrayList<>();

        for (Searchable searchable : list) {
            if (searchable != null && (searchable.getSearchTerm().toLowerCase()).contains(searchTerm.toLowerCase())) {
                resultsSearch.add(searchable);
            }
        }
        return resultsSearch;
    }

    public void add(Searchable newSearchable) {
        if (newSearchable == null) {
            throw new IllegalArgumentException("Передаваемый объект не может быть null");
        }
        list.add(newSearchable);
    }

    public Searchable searchBestMatch(String search) throws BestResultNotFound {
        search = search.toLowerCase();
        int maxCount = 0;
        Searchable result = null;

        for (Searchable searchable : list) {
            int counter = 0;
            int index = 0;
            int indexSubstring = searchable.getSearchTerm().toLowerCase().indexOf(search, index);

            while (indexSubstring != -1) {
                counter++;
                index = indexSubstring + search.length();
                indexSubstring = searchable.getSearchTerm().indexOf(search, index);
            }

            if (counter > maxCount) {
                maxCount = counter;
                result = searchable;
            }
        }
        if (result == null) {
            throw new BestResultNotFound("\nПо запросу \"" + search + "\" ничего не найдено");
        }
        return result;
    }
}
