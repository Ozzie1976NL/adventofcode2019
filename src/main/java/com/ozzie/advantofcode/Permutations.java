package com.ozzie.advantofcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static List<Long[]> determinePermutations(long[] integers) {
        final List<Long[]> result = new LinkedList<>();
        List<Long> asList = Arrays.stream(integers).boxed().collect(Collectors.toList());
        permute(asList, 0, result);
        return Collections.unmodifiableList(result);
    }

    static void permute(final List<Long> arr, int k, final List<Long[]> result){
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1, result);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(Arrays.toString(arr.toArray()));
            result.add(arr.toArray(new Long[4]));
        }
    }
}
