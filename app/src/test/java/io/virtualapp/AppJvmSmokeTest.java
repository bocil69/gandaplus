package io.virtualapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppJvmSmokeTest {

    @Test
    public void arithmeticSmokeTest() {
        assertEquals(4, 2 + 2);
    }
}
