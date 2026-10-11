package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {

    private final List<Searchable> list;

    public SearchEngine() {
        list = new LinkedList<>();
    }

    public Map<String, Searchable> search(String searchTerm) {
        validateNotNull(searchTerm);

        //List<Searchable> resultsSearch = new ArrayList<>();

        Map<String, Searchable> map = new TreeMap<>(); // только что написал

        for (Searchable searchable : list) {
            if (searchable != null && (searchable.getSearchTerm().toLowerCase()).contains(searchTerm.toLowerCase())) {
                //resultsSearch.add(searchable);
                map.put(searchable.getSearchTerm(), searchable);
            }
        }
        return map;
    }

    public void add(Searchable searchable) {
        validateNotNull(searchable);
        list.add(searchable);
    }

    public Searchable searchBestMatch(String search) throws BestResultNotFound {
        validateNotNull(search);
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

    public void validateNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Передаваемый объект не может быть null");
        }
    }
}
