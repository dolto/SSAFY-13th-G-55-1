import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 시간제한 2초
  /// 메모리제한 4mb
  // 걸린시간 464ms
  // 메모리 17mb
  /// 아이디어
  // 왼쪽부터 수를 집어 넣어야 하므로, 수가 소수인지 판별하고, 소수가 아니면 가지치기를 한다.
  // 그 외에는 모든 n자릿 수를 구하면 된다 (1~9)
  // 메모리제한이 4mb이며, 시간은 비교적 넉넉한 2초이기 때문에, 에라토스테네스를 사용하라는 건 아닌 것 같다
  // 메모리를 17mb나 먹었는데도 통과된 것은 아마 위의 제한은 c언어인듯 하다
  public static void main(String[] args) throws IOException {
    final int N = br.read() - '0';

    int maxPrime = 1;
    for (int i = 0; i < N; ++i) {
      maxPrime *= 10;
    }
    // 메모리가 최대 400mb이기 때문에 불가능 할 것 같다
    // boolean[] primes = new boolean[maxPrime + 1];

    // primes[1] = true;
    // for (int i = 2; i < maxPrime / 2; ++i) {
    // if (!primes[i]) {
    // for (int j = i + i; j < maxPrime; j += i) {
    // primes[j] = true;
    // }
    // }
    // }

    StringBuilder sb = new StringBuilder();
    solve(0, N, 0, sb);
    bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();

  }

  static void solve(int cnt, int N, int selected, StringBuilder sb) {
    // 1은 소수가아님
    if (selected == 1)
      return;
    for (int i = 2; i <= selected / 2; ++i) {
      if (selected % i == 0)
        return;
    }
    if (cnt == N) {
      sb.append(Integer.toString(selected) + "\n");
      return;
    }

    selected *= 10;
    for (int i = 1; i < 10; ++i) {
      int temp = i;
      selected += temp;
      solve(cnt + 1, N, selected, sb);
      selected -= temp;
    }
  }
}
