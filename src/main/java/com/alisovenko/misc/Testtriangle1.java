package com.alisovenko.misc;

import org.junit.Test;
import static org.junit.Assert.*;

public class Testtriangle1
{
    @Test
public void testGetRtriangleMethod()
        throws AssertionError
{

    Rtriangle tr = TriangleProvider.getRtriangle();

    int a =tr.getApexX2() - tr.getApexX1();
    int b = tr.getApexY2() - tr.getApexY1();
    int v1v2square = a * a + b * b;

    
    a = tr.getApexX3() - tr.getApexX2();
    b = tr.getApexY3() - tr.getApexY2();
    int v2v3square = a * a + b * b;

   
    a= tr.getApexX3() - tr.getApexX1();
    b= tr.getApexY3() - tr.getApexY1();
    int v1v3square =  a * a + b * b;

   
    assertTrue("Method failed to return a right-angled triangle!!!", (v1v2square == v2v3square + v1v3square) || (v2v3square == v1v3square + v1v2square) || (v1v3square == v1v2square + v2v3square));

}

}
