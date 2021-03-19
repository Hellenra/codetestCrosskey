package com.company;

import static org.junit.jupiter.api.Assertions.*;

class fileReadTest {

    //Tests the power-function used to to the calculation in fileRead
    @org.junit.jupiter.api.Test
    void power() {
        double output = fileRead.power(4,4);
        assertEquals(256,output);
    }

}