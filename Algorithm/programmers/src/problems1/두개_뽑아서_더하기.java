package problems1;

import java.util.*;

public class 두개_뽑아서_더하기 {
    static int M = 2;
    static int N;
    static int[] result = new int[2];
    static int[] staticNumbers;
    static Set<Integer> listSet = new LinkedHashSet<>();
    public static void main(String[] args) {
        두개_뽑아서_더하기 instance = new 두개_뽑아서_더하기();
        System.out.println(
                Arrays.toString(instance.solution(new int[]{2, 1, 3, 4, 1}))
        );
    }
    public int[] solution(int[] numbers) {
        N = numbers.length;
        staticNumbers = numbers;

        recursive(0, 0);

        ArrayList<Integer> list = new ArrayList<>(listSet);
        Collections.sort(list);

        return list.stream().mapToInt(i -> i).toArray();
    }
    private void recursive(int m, int start){
        if(m == M){
            int sum = 0;
            for(int i = 0; i < M; i++){
                sum += staticNumbers[result[i]];
            }
            listSet.add(sum);
            return;
        }
        for(int i = start; i < N; i++){
            result[m] = i;
            recursive(m + 1, i + 1);
        }
    }
}
