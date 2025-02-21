import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  /// 아이디어
  // 예산을 정렬한다.
  // 예산을 이분탐색하며, 해당 구간이 총예산의 평균보다 낮으면 오른쪽, 크면 왼쪽으로간다.
  public static void main(String[] args) throws IOException {
    int L = 0, R = Integer.parseInt(br.readLine());
    int M = (R - 1) / 2;
    StringTokenizer st = new StringTokenizer(br.readLine());
    final long Total = Long.parseLong(br.readLine());
    final long TotalAvg = Total / R;
    long sum = 0;
    long[] list = new long[R];
    for (int i = 0; i < R; ++i) {
      long temp = Integer.parseInt(st.nextToken());
      list[i] = temp;
      sum += temp;
    }
    Arrays.sort(list);
    // bw.write(String.format("%d", (Total) / 4));

    while (L < R) {
      bw.write(String.format("%d %d\n", L, R));
      if (list[M] < TotalAvg) {
        L = M + 1;
        M = (L + R - 1) / 2;
      } else {
        R = M;
        M = (L + R - 1) / 2;
      }
    }
    while (list[M] < TotalAvg) {
      M += 1;
    }

    long osum = 0;
    int count = list.length - M;
    for (int i = M; i < list.length; ++i) {
      osum += list[i];
    }

    sum = sum - osum;
    bw.write(String.format("%d", (Total - sum) / count));
    bw.flush();
    bw.close();
    br.close();
  }
}
