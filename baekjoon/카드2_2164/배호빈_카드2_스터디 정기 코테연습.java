
import java.util.*;
import java.io.*;
public class Main_2164 {
    //순서가 홀수이면 큐에서 버리고 짝수이면 앞에서 뺴서 뒤로 넣습니다. 이걸 큐에 하나만 남을 때까지 반복해서 마지막 하나가 남으면 출력을 합니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            que.addLast(i);
        }
        int idx = 1;
        while(que.size() != 1){
            if(idx % 2 == 0){
                int tmp = que.pollFirst();
                que.addLast(tmp);
            }else{
                que.pollFirst();
            }
            idx++;
        }
        System.out.println(que.poll());

    }
}
