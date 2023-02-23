package tf1.day1;

import java.util.*;

public class 둘만의암호 {
    Set<Character> skipSet = new HashSet<>();
    public static void main(String[] args) {
        둘만의암호 instance = new 둘만의암호();
        System.out.println(instance.solution("aukks", "wbqd", 5));
    }
    public String solution(String s, String skip, int index) {
        char[] answer = new char[s.length()];

        for(int i = 0; i < skip.length(); i++){
            skipSet.add(skip.charAt(i));
        }

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            answer[i] = jumpCharacter(ch, index);
        }

        return new String(answer);
    }
    private Character jumpCharacter(char originCh, int index){
        while(index > 0){

            originCh = originCh + 1 > 'z' ? 'a' : (char)(originCh + 1); // jump
            while(skipSet.contains(originCh)){
                originCh = originCh + 1 > 'z' ? 'a' : (char)(originCh + 1); // jump
            }
            index--;
        }

        return originCh;
    }
}

/**
 * 쉬운 구현문제
 *
 * skip할 char들을 set자료구조에 넣어서 체크했다.
 */
