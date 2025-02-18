import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
// 힐링을 위한 간단한 백트래킹 문제이다.
// StringBuilder를 이용해서, 정답을 관리하였다.
public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());

    solve(0, N, M, new StringBuilder());
    bw.flush();
    bw.close();
    br.close();
  }

  static void solve(int cnt, int n, int m, StringBuilder sb) throws IOException {
    if (cnt == m) {
      bw.write(sb.toString());
      bw.write("\n");
      return;
    }

    for (int i = 1; i <= n; ++i) {
      sb.append(String.format("%d ", i));
      solve(cnt + 1, n, m, sb);
      sb.setLength(sb.length() - 2);
    }
  }
}
