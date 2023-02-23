package tf1.day1;

import java.util.*;

public class 명예의_전당 {
    public static void main(String[] args) {
        명예의_전당 instance = new 명예의_전당();
        System.out.println(Arrays.toString(instance.solution(
                3, new int[]{10, 100, 20, 150, 1, 100, 200}
        )));
    }
    public int[] solution(int k, int[] score) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> pq=  new PriorityQueue();
        for (int s : score) {
            if(pq.size() < k){
                pq.offer(s);
            }else{
                Integer target = pq.peek();
                if(target < s){
                    pq.poll();
                    pq.offer(s);
                }
            }
            list.add(pq.peek());
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}

/**
 * 발표점수를 내기위해, 우선순위큐를 이용함.
 *
 * 새로운날의 점수가 우선순위큐의 peek보다 크면 큐의 상태를 바꿔줌
 */
