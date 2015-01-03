package com.alisovenko.careerup;

import java.util.*;

/**
 * http://www.careercup.com/question?id=5739588970610688
 * todo remove me!
 *
 * @author alisovenko 14.10.14
 */
public class ClusterizeNumbers2 {
    public static ArrayList<char[]> getSets(char[][] pairs){
        HashMap<Character, Character> unionFindMap = new HashMap<Character, Character>();
        //add the mappings of left to right
        for(char[] pair : pairs){
            union(unionFindMap, pair[0], pair[1]);
        }

        //organize group the mapped content
        HashMap<Character, ArrayList<Character>> resultCollator = new HashMap<Character, ArrayList<Character>>();
        for(Map.Entry<Character, Character> entry : unionFindMap.entrySet()){
            Character val = entry.getValue();
            Character key = entry.getKey();
            if(val == null){
                val = key;
            }
            ArrayList<Character> list = resultCollator.get(val);
            if(list == null){
                list = new ArrayList<Character>();
                resultCollator.put(val, list);
            }
            list.add(key);
        }

        //make the output
        ArrayList<char[]> results = new ArrayList<char[]>(resultCollator.size());
        for(ArrayList<Character> list : resultCollator.values()){
            char[] set = new char[list.size()];
            for(int i = 0; i < list.size(); i++){
                set[i] = list.get(i);
            }
            results.add(set);
        }
        return results;
    }

    private static void union(HashMap<Character, Character> unionFindMap, char c1, char c2){
        if(!unionFindMap.containsKey(c2)){
            unionFindMap.put(c2, null);
        }
        char dest =  find(unionFindMap, c2);
        unionFindMap.put(c1, dest);
    }

    private static char find(HashMap<Character, Character> unionFindMap, char c){
        Character dest = unionFindMap.get(c);
        if(dest == null){
            return c;
        }
        char newDest = find(unionFindMap, dest);
        unionFindMap.put(c, newDest);
        return newDest;
    }

    public static void main(String[] args) {
        char[][] input = {
                {'a', 'b'},
                {'c', 'd'},
                {'e', 'f'},
                {'g', 'h'},
                {'a', 'd'},
                {'f', 'g'}
        };
        getSets(input).forEach(s -> System.out.println(Arrays.toString(s)));
    }
}
