package com.alisovenko.jdk.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;

import java.util.Map;

/**
 * Testing the initialization of enum (creation of static map with enum values)
 * 
 * <p>Created: 23.10.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class StaticInitialization {
    private enum Some {
        ONE("HI"),
        TWO("BYE");
        
        static Map<String, Some> mapped;
        static {
            mapped = new HashMap<String, StaticInitialization.Some>();
            for(Some en : Some.values()) {
                mapped.put(en.name(), en);
            }
        }
        
        private Some(String value) {
            //
        }
    }
    
    @Test
    public void test() {
        assertEquals(2, Some.mapped.size());
        assertEquals(Some.ONE, Some.mapped.get("ONE"));
        assertEquals(Some.TWO, Some.mapped.get("TWO"));
    }

}
