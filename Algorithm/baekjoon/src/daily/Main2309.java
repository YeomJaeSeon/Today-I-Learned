package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2309 {
//    9! -> 30만인데, 이거보다 적은 경우의 수 소요 -> brute force
    private static int[] heights = new int[9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++){
            heights[i] = Integer.parseInt(br.readLine());
        }

        int sum = -1;
        for(int i = 0; i < 9; i++){
            for(int j = i + 1; j < 9; j++){
                if(getSumExceptTwoElements(i, j) == 100){
                    List<Integer> result = getListExceptTwoElements(i, j);
                    Collections.sort(result);
                    for (Integer value : result) {
                        System.out.println(value);
                    }

//                    exit
                    return;
                }
            }
        }
    }

    private static List<Integer> getListExceptTwoElements(int i, int j) {
        List<Integer> result = new ArrayList<>();
        for(int v = 0; v < 9; v++){
            if(v != i && v != j){
                result.add(heights[v]);
            }
        }
        return result;
    }

    private static int getSumExceptTwoElements(int i, int j) {
        int sum = 0;
        for(int v = 0; v < 9; v++){
            if(v != i && v != j){
                sum += heights[v];
            }
        }
        return sum;
    }
}
