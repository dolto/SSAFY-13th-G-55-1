import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홍도완 {
  ///아이디어
  // 배열의 크기를 N이라고 하고, 일 수를 D라고 한다면
  // 연속적인 0~K-1 수열의 합을 구한다.
  // L을 0으로, R을 K-1로 하고, R이 N-1이 될 때 까지 반복한다.
  // L을 갱신하기 전에, 수열의 합에서 빼고, R을 갱신한 다음 수열의 합에서 더한다.
  // 갱신된 수열 중 최댓값을 구한다.
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int l = 0, r = k;
    int total = 0;
    int answer = 0;

    st = new StringTokenizer(br.readLine());
    int[] ns = new int[n];
    for (int i = 0; i < n; ++i) {
      ns[i] = Integer.parseInt(st.nextToken());
      if (i < k) {
        total += ns[i];
      }
    }

    answer = total;
    for (int i = r; i < n; ++i) {
      total -= ns[l++];
      total += ns[r++];

      answer = Math.max(answer, total);
    }

    bw.write(String.format("%d", answer));
    bw.flush();
    bw.close();
    br.close();
  }

}
