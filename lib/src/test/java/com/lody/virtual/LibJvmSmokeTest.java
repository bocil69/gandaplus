package com.lody.virtual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LibJvmSmokeTest {

    @Test
    public void arithmeticSmokeTest() {
        assertEquals(6, 3 + 3);
    }
}
