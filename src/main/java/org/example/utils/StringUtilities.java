package org.example.utils;

public class StringUtilities {

    public static final String concatStrings(Object... objects) {
        final StringBuilder sb = new StringBuilder();
        if (objects != null) {
            for (final Object s : objects) {
                sb.append(s != null ? s : "");
            }
        }
        return sb.toString();
    }
}
