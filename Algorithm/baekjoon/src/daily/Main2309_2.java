package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//재귀함수 이용
public class Main2309_2 {
    private static int[] heights = new int[9];
    private static int[] result = new int[7];
    private static boolean isFinish = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++){
            heights[i] = Integer.parseInt(br.readLine());
        }

        recursive(0, 0);
    }
    private static void recursive(int m, int idx){
        if(m == 7){
            int sum = 0;
            for(int i = 0; i < 7; i++){
                sum += heights[result[i]];
            }
            if(sum == 100){
                printResult();
                isFinish = true;
            }
            return;
        }
        for(int i = idx; i < 9; i++){
            result[m] = i;
            recursive(m + 1, i + 1);
            if(isFinish) break;
        }
    }
    private static void printResult(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            list.add(heights[result[i]]);
        }
        Collections.sort(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
