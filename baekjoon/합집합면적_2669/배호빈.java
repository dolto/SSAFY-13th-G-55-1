package algo;
import java.util.*;
import java.io.*;

public class Main_14696 {
    public static void main(String[] args) throws IOException {
        // 아이디어 플레이어 A, B에 해당하는 해시맵을 만들고 모양에 따른 숫자를 저장한뒤
        // 비교하여 결국 둘다 숫자가 같다면 boolean을 이용 D를 출력합니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
//            HashMap<Integer, Integer> mapA = new HashMap<>();
//            HashMap<Integer, Integer> mapB = new HashMap<>();
            int[] countA = new int[5];
            int[] countB = new int[5];


            int a = Integer.parseInt(st.nextToken());

            for(int j = 0; j < a; j++){
                countA[Integer.parseInt(st.nextToken())]++;
            }

            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());

            for(int j = 0; j < b; j++){
                countB[Integer.parseInt(st.nextToken())]++;
            }

            boolean d = false;
            for(int j = 4; j >=1; j--){
                if(countA[j] > countB[j]){
                    System.out.println("A");
                    break;
                } else if (countA[j] < countB[j]) {
                    System.out.println("B");
                    break;
                }
                if(j == 1){
                    d = true;
                }
            }
            if(d){
                System.out.println("D");
            }


        }
    }
}
