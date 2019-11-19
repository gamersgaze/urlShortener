package com.apps.shortener.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    /**
     * convert the base10 number to base62
     * ex. base10  =1000000000001
     *     base62  = rLHWfKf
     */
    public static String convertBase10ToBase62(long numberValue) {
        final char CHARS[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder base62Key = new StringBuilder();
        while (numberValue > 0) {
            base62Key.append(CHARS[(int) (numberValue % 62)]);
            numberValue = numberValue / 62;
        }
        return base62Key.reverse().toString();
    }

    public static boolean isUrlValid(String url) {
        boolean isValid = true;
        try {
            new URL(url);
        } catch (MalformedURLException exception) {
            isValid = false;
        }
        return isValid;
    }
}
