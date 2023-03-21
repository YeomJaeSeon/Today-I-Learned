package tf1.day2;

import java.util.*;

public class 푸드_파이트_대회 {
    static Queue<Integer> q = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) {
        푸드_파이트_대회 instance = new 푸드_파이트_대회();
        System.out.println(instance.solution(new int[]{1, 3, 4, 6}));
    }

    public String solution(int[] food) {
        fillQAndStack(food);
        String result = makeResult();

        return result;
    }
    private void fillQAndStack(int[] food){
        for(int i = 1; i < food.length; i++){
            int foodCnt = food[i];
            int qFoodCnt = foodCnt / 2;
            int stackFoodCnt = foodCnt / 2;

            while(qFoodCnt != 0){
                q.offer(i);
                qFoodCnt--;
            }

            while(stackFoodCnt != 0){
                stack.push(i);
                stackFoodCnt--;
            }
        }
    }
    private String makeResult(){
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            sb.append(q.poll());
        }
        sb.append(0);
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}

/**
 * 왼 -> 오 선수는 Q이용
 * 오 -> 왼 선수는 stack이용
 */