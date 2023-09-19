package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main11866 {
    private static int N, K;
    private static YoSaeElement[] yoSaeElements;
    public static void main(String[] args) throws IOException {
        initInput();
        List<String> result = makeYosaeList();
        printResult(result);
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        yoSaeElements = new YoSaeElement[N];
        for(int i = 0; i < N; i++){
            yoSaeElements[i] = new YoSaeElement(i + 1, true);
        }
    }
    private static List<String> makeYosaeList(){
        List<String> result = new ArrayList<>();
        int pt = 0;
        int count = 1;
        while(containsAtLeastOneExisted()){
            YoSaeElement yoSaeElement = yoSaeElements[pt];

            if(!yoSaeElement.isExisted()){
                // not existed
                pt = goPt(pt);
                continue;
            }

            if(count == K){
                // if K번째 이면 notExisted상태로 만든다.
                yoSaeElement.changeToNotExisted();
                result.add(String.valueOf(yoSaeElement.getIndex()));
                count = 1;
            }else{
                count++;
            }
            pt = goPt(pt);
        }

        return result;
    }
    private static void printResult(List<String> result){
        String answer = String.join(", ", result);
        System.out.print("<");
        System.out.print(answer);
        System.out.println(">");
    }
    private static int goPt(int pt){
        pt++;
        if(pt == yoSaeElements.length){
            pt = 0;
        }
        return pt;
    }

    /**
     * 하나라도 existed가 true인 요소가 있는지 여부 체크
     */
    private static boolean containsAtLeastOneExisted(){
        for (YoSaeElement yoSaeElement : yoSaeElements) {
            if(yoSaeElement.isExisted()){
                return true;
            }
        }
        return false;
    }
}

class YoSaeElement{
    private int index;
    private boolean existed;

    YoSaeElement(int index, boolean existed){
        this.index = index;
        this.existed = existed;
    }

    public int getIndex() {
        return index;
    }

    public boolean isExisted() {
        return existed;
    }
    public void changeToNotExisted(){
        this.existed = false;
    }
}


/**
 * 1 2 3 4 5 6 7
 *
 * 3
 */