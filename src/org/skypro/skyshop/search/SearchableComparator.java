package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        int result = Integer.compare(o2.getName().length(), o1.getName().length());
        if (result == 0) {
            result = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }
        return result;
    }
}
