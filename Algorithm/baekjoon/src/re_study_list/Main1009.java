package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main1009 {
    static int T;
    static int a, b;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        settingList();

        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            int lastNumber = findLastNumber(a, b);

            if(lastNumber % 10 == 0){
                System.out.println(10);
            }else{
                System.out.println(lastNumber % 10);
            }
        }

    }

    private static void settingList() {
        /**
         * 1:  1 ..
         * 2: 2 4 8 6 ..
         * 3: 3 9 7 1 ..
         * 4: 4 6 ..
         * 5: 5 ..
         * 6: 6 ..
         * 7: 7 9 3 1 ..
         * 8: 8 4 2 6 ..
         * 9: 9 1 ..
         * 10: 0 ..
         *
         * .. 반복
         */

        //10
        list.add(new ArrayList<>(List.of(0)));
        // 1
        list.add(new ArrayList<>(List.of(1)));
        // 2
        list.add(new ArrayList<>(List.of(2, 4, 8, 6)));
        //3
        list.add(new ArrayList<>(List.of(3, 9, 7, 1)));
        //4
        list.add(new ArrayList<>(List.of(4, 6)));
        //5
        list.add(new ArrayList<>(List.of(5)));
        //6
        list.add(new ArrayList<>(List.of(6)));
        //7
        list.add(new ArrayList<>(List.of(7, 9, 3, 1)));
        //8
        list.add(new ArrayList<>(List.of(8, 4, 2, 6)));
        //9
        list.add(new ArrayList<>(List.of(9, 1)));
    }

    private static int findLastNumber(int a, int b) {
        List<Integer> targetList = list.get(a % 10);

        int targetIdx = b % targetList.size();

        if(targetIdx == 0){
            targetIdx = targetList.size() - 1;
        }else{
            targetIdx--;
        }
        return targetList.get(targetIdx);
    }
}
