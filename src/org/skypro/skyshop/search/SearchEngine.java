package org.skypro.skyshop.search;

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
}
