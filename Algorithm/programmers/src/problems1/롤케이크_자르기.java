package problems1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 롤케이크_자르기 {
    static int count = 0;
    static int[] toppings;

    public static void main(String[] args) {
        롤케이크_자르기 instance = new 롤케이크_자르기();
        System.out.println(instance.solution(new int[]{1, 2, 3, 1, 4}));
    }

    // 공평하게 종류수를 나누는 가지수
    public int solution(int[] topping) {
        toppings = topping;

        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();

        for (int j : topping) {
            if(!rightMap.containsKey(j)){
                rightMap.put(j, 1);
            }else{
                rightMap.put(j, rightMap.get(j) + 1);
            }
        }

        for(int index = 0; index < topping.length; index++){
            if(isFair(index, leftMap, rightMap)){
                count++;
            }
        }

        return count;
    }
    private boolean isFair(int index, Map<Integer, Integer> leftMap, Map<Integer, Integer> rightMap){
        // 0 ~ index
        // index + 1 ~ last

        int topping = toppings[index];

        if(rightMap.get(topping) == 1){
            rightMap.remove(topping);
        }else if(rightMap.get(topping) > 1){
            rightMap.put(topping, rightMap.get(topping) - 1);
        }

        if(!leftMap.containsKey(topping)){
            leftMap.put(topping, 1);
        }else{
            leftMap.put(topping, leftMap.get(topping) + 1);
        }

        return leftMap.size() == rightMap.size();
    }
}

/**
 * 매 루프마다 각 set을 만들면 시간초과
 * (1,000,000) * 2의 set이 만들어지고 세팅되닌까 오래걸림
 *
 * -> 총 두개의 Map을 이용해서 시간 줄일수있음
 */