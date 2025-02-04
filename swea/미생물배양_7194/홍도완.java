import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
///
// 아이디어
// bfs나 dfs로 시도하면 시간초과가 발생할 수 있다.
// b가 1인경우, 무한 로딩이 걸림을 인지해야한다.
// s에서 t로 가는 경우를 구한는 것 보다 t에서 s로 가는 경우가 %,/ 연산을 이용하기 쉽다.
// 그리디로 풀수 있는 문제이다.(문제에서 요구하는 스택이다. 실제로는 백트레이싱으로 해야 완벽한 풀이다.)
class Solution
{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    int test_case = Integer.parseInt(br.readLine());
    for(int i = 1; i<=test_case; ++i){
      StringTokenizer input = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(input.nextToken());
      int t = Integer.parseInt(input.nextToken());
      int a = Integer.parseInt(input.nextToken());
      int b = Integer.parseInt(input.nextToken());
 
      if (b == 1){
        // b가 1인경우
        int temp = t - s;
        if (temp % a == 0){
          bw.write(String.format("#%d %d\n",i, (temp / a)));
        }else{
          bw.write(String.format("#%d -1\n",i));
        }
      }else{
        int day = 0;
        while (t != s) {
          // 당장 b로 모두 나누어진다면
          if(t % b == 0){
            // 이 결과가 s보다 작아지면 안됨
            if(t / b < s){
              ++day;
              // a를 뺌
              t -= a;
            }else {
              ++day;
              // b로 나눔
              t /= b;
            }
          }else {
            ++day;
            // b로 나눌 수 없는경우, a를 뺌
            t -= a;
          }
 
          if(t < s){
            // t가 s보다 작아지면 불가능해짐
            day = -1;
            break;
          }
        }

        // 연산이 끝나면 출력
        bw.write("#"+i+" "+day+"\n");
      }
 
    }
    br.close();
    bw.flush();
    bw.close();
  }
}
