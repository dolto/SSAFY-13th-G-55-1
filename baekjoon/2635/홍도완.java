import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/// 아이디어
// 두번째 숫자의 범위는 N/2 ~ N까지라고 볼 수 있다
// 그 N/2이하라면, 4번째에서 음수가 되며, N 이상이면 세번째에서 음수가 된다.
// 리스트의 크기는 생각보다 크지 않았다... N이 최대치여도, 30개 이상이 되지 않았으므로, capacity는 30으로 맞춰준다.
// 범위에 맞춰서 규칙에 맞춘 모든 경우의 수를 탐색 한 다음, 최대치를 출력하면 된다.
public class 홍도완 {
  static int N;
  static int acount = 0;

  static List<Integer> answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine().trim());

    for (int i = Math.max(N / 2, 1); i <= N; ++i) {
      int temp = 0;
      int tcount = 1;
      List<Integer> tanswer = new ArrayList<>(30);
      tanswer.add(N);
      tanswer.add(i);
      while (true) {
        temp = tanswer.get(tcount - 1) - tanswer.get(tcount++);
        if (temp >= 0) {
          tanswer.add(temp);
        } else {
          break;
        }
      }

      if (acount < tcount) {
        acount = tcount;
        answer = tanswer;
      }
    }

    bw.write(String.format("%d\n", acount));
    for (int l : answer) {
      bw.write(String.format("%d ", l));
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
