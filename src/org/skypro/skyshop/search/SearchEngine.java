package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

public class SearchEngine {

    // и актуальное кол-во элементов в массиве,
    // и кол-во "успешных" вызовов метода add()
    private int size;

    private final Searchable[] array;

    public SearchEngine(int maxSizeArray) {
        if (maxSizeArray < 0) {
            System.out.println("Размер массива должен быть неотрицательным");
            maxSizeArray = 0;
        }
        array = new Searchable[maxSizeArray];
    }

    public Searchable[] search(String searchTerm) {
        if (searchTerm == null) {
            System.out.println("Поисковый запрос не должен быть null");
            return null;
        }

        int resultCount = 0;
        Searchable[] resultsSearch = new Searchable[5];
        for (int i = 0; i < size; i++) {
            Searchable searchable = array[i];

            if (array[i] != null && (searchable.getSearchTerm().toLowerCase()).contains(searchTerm.toLowerCase())) {
                resultsSearch[resultCount] = array[i];
                resultCount++;
            }
            if (resultCount == resultsSearch.length) {
                break;
            }
        }
        return resultsSearch;
    }

    public void add(Searchable newSearchable) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = newSearchable;
                size++;
                return;
            }
        }
    }

    public Searchable searchBestMatch(String search) throws BestResultNotFound {
        search = search.toLowerCase();
        int maxCount = 0;
        Searchable result = null;

        for (int i = 0; i < size; i++) {
            int counter = 0;
            int index = 0;
            int indexSubstring = array[i].getSearchTerm().toLowerCase().indexOf(search, index);

            while (indexSubstring != -1) {
                counter++;
                index = indexSubstring + search.length();
                indexSubstring = array[i].getSearchTerm().indexOf(search, index);
            }

            if (counter > maxCount) {
                maxCount = counter;
                result = array[i];
            }
        }
        if (result == null) {
            throw new BestResultNotFound("\nПо запросу \"" + search + "\" ничего не найдено");
        }
        System.out.println("\nРезультат поиска по запросу \"" + search + "\": " + result);
        return result;
    }
}
