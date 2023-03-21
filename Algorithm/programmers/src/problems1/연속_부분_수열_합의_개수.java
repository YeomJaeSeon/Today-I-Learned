package problems1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 연속_부분_수열_합의_개수 {
    public static void main(String[] args) {
        연속_부분_수열_합의_개수 instance = new 연속_부분_수열_합의_개수();
        System.out.println(instance.solution(new int[]{7, 9, 1, 1, 4}));

    }
    public int solution(int[] elements) {
        List<Value> circularLinkedList = getCircularLinkedList(elements);

        return getSumCount(circularLinkedList);
    }

    private int getSumCount(List<Value> list) {
        Set<Integer> sums = new HashSet<>();

        for(int count = 1; count <= list.size(); count++){
            //count: 개수

            for(int idx = 0; idx < list.size(); idx++){
                Value currValue = list.get(idx);

                int sum = 0;
                for(int i = 0; i < count; i++){
                    sum += currValue.number;
                    currValue = currValue.nextValue;
                }

                sums.add(sum);
            }
        }

        return sums.size();
    }

    private List<Value> getCircularLinkedList(int[] elements){
        List<Value> list = new ArrayList<>();

        for(int i = 0; i < elements.length; i++){
            if(list.size() > 0){
                Value currValue = new Value(elements[i], null);
                Value lastValue = list.get(list.size() - 1);
                lastValue.nextValue = currValue;
                list.add(currValue);
            }else{
                list.add(new Value(elements[i], null));
            }
        }

        list.get(list.size() - 1).nextValue = list.get(0);

        return list;
    }
}

class Value{
    int number;
    Value nextValue;

    Value(int number, Value nextValue){
        this.number = number;
        this.nextValue = nextValue;
    }
}


/**
 * 원형 수열 만들고
 *
 * 만들어질수있는 합의 개수 Set을 이용하여 구하면된다.
 *
 * 모든 case를 검색해봐야하기에 brute force로 풀어야함.
 */