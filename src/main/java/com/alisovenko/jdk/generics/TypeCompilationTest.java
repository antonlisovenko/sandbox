package com.alisovenko.jdk.generics;

/**
 * TBD: add comments for TypeCompilationTest.java.
 * 
 * <p>Created: 14.10.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class TypeCompilationTest {
    public class SuperClass<T> {
        public void setT(T obj) {
            System.out.println("In Object");
        }
    }
    
    public class SubClass<X> extends SuperClass<Number> {
        

        public void setT(Number obj) {
            System.out.println("In number");
        }
    }
    
    public static void main(String[] args) {
        //new TypeCompilationTest().new SubClass().setT(new StringBuffer());
    }
}
     