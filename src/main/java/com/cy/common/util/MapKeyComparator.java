package com.cy.common.util;

import java.util.Comparator;

/**
 * 比较函数
 */
public class MapKeyComparator implements Comparator<String> {

    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }

}
