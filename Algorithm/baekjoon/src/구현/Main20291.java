package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main20291 {
    static int N;
    static Map<String, Integer> fileExtensionMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String fileFullName = br.readLine();
            StringTokenizer st = new StringTokenizer(fileFullName, ".");
            String fileName = st.nextToken();
            String extensionName = st.nextToken();


            if(!fileExtensionMap.containsKey(extensionName)){
                // not exists
                fileExtensionMap.put(extensionName, 1);
            }else{
                // exists
                Integer cnt = fileExtensionMap.get(extensionName);
                fileExtensionMap.put(extensionName, cnt + 1);
            }
        }

        List<FileExtensionInfo> fileExtensionInfoList = fileExtensionMap.entrySet().stream().map(i -> new FileExtensionInfo(i.getKey(), i.getValue())).sorted(Comparator.comparing(o -> o.name)).collect(Collectors.toList());

        for (FileExtensionInfo fileExtensionInfo : fileExtensionInfoList) {
            System.out.println(fileExtensionInfo.name + " " + fileExtensionInfo.count);
        }
    }
}

class FileExtensionInfo{
    String name;
    int count;

    public FileExtensionInfo(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
