package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//quack

public class Main12933 {
    static List<List<Character>> list = new ArrayList<>();
    static String request;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        request = br.readLine();
        char[] reqArr = request.toCharArray();
        for (char ch : reqArr) {
            if (list.isEmpty()) {
                list.add(getListContainChar(ch));
            } else {
                boolean hasPush = false;
                for (List<Character> currList : list) {
                    Character lastCh = currList.get(currList.size() - 1);
                    if(lastCh == 'q' && ch == 'u' || lastCh == 'u' && ch == 'a' || lastCh == 'a' && ch == 'c' || lastCh == 'c' && ch == 'k' || lastCh == 'k' && ch =='q'){
                        hasPush = true;
                        currList.add(ch);
                        break;
                    }
                }
                if(!hasPush){
                    list.add(getListContainChar(ch));
                }
            }
        }

        for (List<Character> characters : list) {
            StringBuilder checkSb = new StringBuilder();

            for (Character character : characters) {
                checkSb.append(character);

                if(checkSb.length() == 5 && checkSb.toString().equals("quack")){
                    checkSb = new StringBuilder();
                }
            }
            if(checkSb.length() != 0){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(list.size());
    }
    private static List<Character> getListContainChar(char ch){
        ArrayList<Character> list = new ArrayList<>();
        list.add(ch);
        return list;
    }
}

/**
 * quqacukqauackck
 * qu ac kq
 *   q  u
 */

/**
 * 오리 꽉꽉 문제
 *
 * 자료형은 List<List<Character>> 를 이용했다.
 */