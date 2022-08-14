package problems1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 가장큰수 {
    public static void main(String[] args) {
        가장큰수 instance = new 가장큰수();
        System.out.println(instance.solution(new int[]{0, 0, 0, 0}));
    }

    public String solution(int[] numbers) {
        List<Integer> list = Arrays.stream(numbers).boxed().sorted((o1, o2) -> {
            Integer num1 = convert(o1);
            Integer num2 = convert(o2);

            return num2 - num1;
        }).collect(Collectors.toList());

        boolean isAllZero = list.stream().allMatch(v -> v == 0);

        if(isAllZero){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);

        return sb.toString();
    }

    public Integer convert(Integer value){
        StringBuilder sb = new StringBuilder(value.toString());
        String cloneStr = sb.toString();
        int length = sb.length();
        int pt = 0;

        if(sb.length() < 4){
            for(int i = 0; i < 4 - length; i++){
                if(cloneStr.length() == pt){
                    pt = 0;
                }
                sb.append(cloneStr.charAt(pt++));
            }
        }

        return Integer.parseInt(sb.toString());
    }
}

// 정렬 기준을 잡는게 포인트.
// 정렬만 하면 끝나는 문제
/**
 * 1. 시도
 * - 마지막 문자를 4자리수로 늘려서 풀었는데 예외케이스 존재
 * 97, 978
 * -> 내 방식대로라면 9777 < 9788 이라
 * 97897이 되는데, 정답은 97978이다
 *
 * 2. 시도
 * - convert를 다른방식으로함
 * - 12 -> 1212
 * - 1 -> 1111
 * - 123 -> 1231
 * 이런식으로 convert하고 정렬기준을 정함
 *
 * -> 하나의 테스트케이스 통과하지 않는다. (11번 TC)
 *
 * 3. 시도
 * - 결과 모드 0이라면 0이나와야한다.
 */