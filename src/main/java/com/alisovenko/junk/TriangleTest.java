package com.alisovenko.junk;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TriangleTest {

    @Test
    public void testGetRtriangleMethod() {
        Rtriangle tr = TriangleProvider.getRtriangle();

        int v1ShiftX = tr.getApexX2() - tr.getApexX1();
        int v1ShiftY = tr.getApexY2() - tr.getApexY1();
        int v2ShiftX = tr.getApexX3() - tr.getApexX2();
        int v2ShiftY = tr.getApexY3() - tr.getApexY2();
        int v3ShiftX = tr.getApexX1() - tr.getApexX3();
        int v3ShiftY = tr.getApexY1() - tr.getApexY3();

        int v1v2ScalarProduct = (v1ShiftX * v2ShiftX) + (v1ShiftY * v2ShiftY);
        int v1v3ScalarProduct = (v1ShiftX * v3ShiftX) + (v1ShiftY * v3ShiftY);
        int v2v3ScalarProduct = (v2ShiftX * v3ShiftX) + (v2ShiftY * v3ShiftY);

        assertTrue("Method failed to return a right-angled triangle!!!",
                v1v2ScalarProduct == 0 || v1v3ScalarProduct == 0 || v2v3ScalarProduct == 0);

    }

}