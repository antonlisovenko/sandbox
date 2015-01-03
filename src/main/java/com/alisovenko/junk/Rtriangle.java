package com.alisovenko.junk;

public class Rtriangle
{

protected int x1, y1;
protected int x2, y2;
protected int x3, y3;



public Rtriangle(){};

public Rtriangle(int ax1, int ay1, int ax2, int ay2,int ax3, int ay3){
x1 = ax1; x2=ax2;x3=ax3;
y1= ay1; y2=ay2; y3=ay3;

};


public int getApexX1()
{
    return x1;
}
public int getApexY1()
{
    return y1;
}
public int getApexX2()
{
    return x2;

}
public int getApexY2()
{
    return y2;
}
public int getApexX3()
{
    return x3;
}
public int getApexY3()
{
    return y3;
}
}
