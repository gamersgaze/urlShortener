package com.apps.shortener.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    /**
     * input:   1000000000001L
     * expected: rLHWfKf
     */
    @Test
    void should_generateCorrectBase62Value() {
        Long input=1000000000001L;
        String expectedBase62="rLHWfKf";
        String base62=Utils.convertBase10ToBase62(input);

        //assert
        assertEquals(base62,expectedBase62);
    }

    /**
     * input:   1000000000001L
     * expected size: 7
     */
    @Test
    void should_generateCorrectBase62Length() {
        Long input=1000000000001L;
        Integer expectedBase62Length=7;
        String base62=Utils.convertBase10ToBase62(input);

        //assert
        assertEquals(base62.length(),expectedBase62Length);
    }

}