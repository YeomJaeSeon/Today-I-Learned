package tf1.day1;

import java.util.*;

public class 과일_장수 {
    public static void main(String[] args) {
        과일_장수 instance = new 과일_장수();
        System.out.println(instance.solution(4, 3, new int[]{4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
    }
    public int solution(int k, int m, int[] score) {
        Queue<Integer> pq = new PriorityQueue<>();
        for(int s: score){
            pq.offer(s);
        }

        // 불필요 사과 버리기
        int trashApples = score.length % m;
        for(int i = 0; i < trashApples; i++){
            pq.poll();
        }

        // 상자에 담기 -> 싼애들은 싼애들끼리 넣는게 이득 -> 그리디
        int count = 0;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        while(!pq.isEmpty()){
            tmp.add(pq.poll());
            count++;

            if(count == m){
                count = 0;
                list.add(tmp);
                tmp = new ArrayList<>();
            }
        }

        int sum = 0;
        for (List<Integer> integers : list) {
           int min = Collections.min(integers);
           sum += (min * m);
        }

        return sum;
    }
}
