package com.alisovenko.geeks4geeks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import java.util.*;
import java.util.stream.Collectors;

public class SubsetFinder {

    public static void main(String[] args) {
        new SubsetFinder()
                .findSubsets(ImmutableList.of(1, 2, 3, 4, 5), 7)
                .forEach(System.out::println);
    }

    public List<Collection<Integer>> findSubsets(Collection input, int k) {
        // The helper method relies on ordering of the input collection
        List<Collection<Integer>> listOfLists = findSubsetHelper(ImmutableSortedSet.copyOf(input), k);

        // Prune sets which are duplicates or subsets of other sets
        return listOfLists.stream().filter(
                candidate -> listOfLists.stream().noneMatch(
                        lol -> candidate != lol && lol.containsAll(candidate)
                )
        ).collect(Collectors.toList());
    }

    private List<Collection<Integer>> findSubsetHelper(Collection inputSet, int k) {
        List<Collection<Integer>> listOfLists = new ArrayList<>();
        List<Integer> copy = new ArrayList<>(inputSet);

        while (!copy.isEmpty()) {
            int v = copy.remove(copy.size() - 1);
            if (v == k || (copy.isEmpty() && v <= k)) {
                // No need to look for subsets if the element itself == k, or
                // if it's the last remaining element and <= k.
                listOfLists.add(new ArrayList<>(Arrays.asList(v)));
            } else if (v < k) {
                findSubsetHelper(copy, k - v).forEach(subList -> {
                    subList.add(v);
                    listOfLists.add(subList);
                });
            }
        }

        return listOfLists;
    }
}
