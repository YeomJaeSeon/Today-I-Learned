package tf1.day2;

import java.util.*;

public class 숫자_짝꿍 {
    public static void main(String[] args) {
        숫자_짝꿍 instance = new 숫자_짝꿍();
        System.out.println(instance.solution("100", "203045"));

    }
    public String solution(String X, String Y) {
        Map<Character, Integer> xMap = initMap(X);
        Map<Character, Integer> yMap = initMap(Y);
        List<CharNum> list = new ArrayList<>();


        for(int i = 0; i <= 9; i++){
            String strI = String.valueOf(i);
            Integer xValue = xMap.get(strI.charAt(0));
            Integer yValue = yMap.get(strI.charAt(0));

            if(xValue != null && yValue != null){
                int minValue = Math.min(xValue, yValue);
                list.add(new CharNum(strI.charAt(0), minValue));
            }
        }

        if(list.isEmpty()){
            return "-1";
        }
        if(list.size() == 1 && list.get(0).ch == '0'){
            return "0";
        }

        Collections.sort(list, (o1, o2) -> o2.ch - o1.ch);

        StringBuilder sb = new StringBuilder();
        for (CharNum charNum : list) {
            Character ch = charNum.ch;
            int count = charNum.count;
            for(int i = 0; i < count; i++){
                sb.append(ch);
            }
        }

        return sb.toString();
    }
    static Map<Character, Integer> initMap(String str){
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, 1);
            }else{
                Integer value = map.get(ch);
                map.put(ch, value + 1);
            }
        }

        return map;
    }
}

class CharNum{
    Character ch;
    int count;

    public CharNum(Character ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}

/**
 * 쉬운 구현문제
 *
 * Map을 이용하여 각 문자가 몇개씩 있는지 확인
 *
 * 두 맵의 각 key(문자)의 개수 중 최소를 구함.
 *
 * List를 만들어 정렬후(ch 내림차순) 문자열 병함(StringBuilder 이용)
 *
 * 예외상황 두개를 고려해야함.
 * 0으로만 이루어졌을경우 -> "0"
 * 짝궁없으면  -1
 */