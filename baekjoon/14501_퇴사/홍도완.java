import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 아이디어
  // 백트레킹 문제이다.
  // 오늘 상담을 할지 안할지를 결정하면서 하면 된다.
  public static void main(String[] args) throws IOException {
    final int N = Integer.parseInt(br.readLine());
    Scajul[] sc = new Scajul[N];
    for (int i = 0; i < N; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      sc[i] = new Scajul(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    bw.write(Integer.toString(solve(0, 0, sc, N)));
    bw.flush();
    br.close();
    bw.close();

  }

  static int solve(int day, int answer, Scajul[] sc, int N) {
    // 기저조건: day가 N일 이후가 되었다면 상담을 종료하고 정산한다
    if (day >= N) {
      return answer;
    }

    int ret = solve(day + 1, answer, sc, N);
    // 기저조건: 오늘 상담을 받았을 때 N일을 넘는다면 상담하지 않는다.
    if (day + sc[day].time <= N) {
      ret = Math.max(ret, solve(day + sc[day].time, answer + sc[day].cost, sc, N));
    }

    return ret;
  }

  static class Scajul {
    int cost, time;

    Scajul(int time, int cost) {
      this.cost = cost;
      this.time = time;
    }

  }
}
