package problems1;

import java.util.*;

public class 디펜스_게임 {
    public static void main(String[] args) {
        디펜스_게임 instance = new 디펜스_게임();
        System.out.println(instance.solution(2, 5, new int[]{3, 3, 3, 3}));

    }
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int round = -1;

        for(int i = 0; i < enemy.length; i++){
            int enemyCnt = enemy[i];
            pq.offer(enemyCnt);

            n -= enemyCnt;

            if(n < 0){
                if(k == 0){
                    round = i;
                    break;
                }

                n += pq.poll();
                k--;
            }
        }

        if(round == -1){
            return enemy.length;
        }

        return round;
    }
}

/**
 * 무적권을 사용할 라운드는 우선순위 큐를 이용하여, 가장 많은 적들이 있는 라운드에 사용하면 된다.
 *
 * 주의할점은 무적권을 다 사용하지 않고, 전체 라운드 끝낼수 있는 경우 조심
 */
