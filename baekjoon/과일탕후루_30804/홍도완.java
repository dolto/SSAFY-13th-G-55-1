import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/// 제한시간: 2초
/// 제한메모리: 1024mb
// 걸린시간: 428ms
// 메모리: 39mb
/// 아이디어
// 일단 배열에 과일들을 넣는다.
// 투포인터 lo와 hi를 각각 -1로 설정한다. (이유는 0번째부터 검사해야 하기 때문이며, 0번만 선택했을  때 0 - -1식으로 하나를 선택했다를 알릴 수 있기 때문
// 만약 과일종류의 개수가 2개 이하라면 hi를 +1 해줘서 다음 과일을 과일 종류에 넣는다.
// 만약 과일종류의 개수가 2개 초과라면 lo를 +1 해주고 뺀 과일을 종류에서 빼준다.
// 이 과정을 마칠 때 과일종류의 개수가 2개 이하라면 정답을 갱신한다.
public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int answer;

  public static void main(String[] args) throws IOException {
    final int N = Integer.parseInt(br.readLine().trim());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] huru = new int[N];
    HashMap<Integer, Integer> fuirt = new HashMap<>(N);
    for (int i = 0; i < N; ++i) {
      int temp = Integer.parseInt(st.nextToken());
      huru[i] = temp;
    }

    int lo = -1, hi = -1;
    while (hi < N - 1) {
      if (fuirt.size() <= 2) {
        hi += 1;
        if (hi < N) {
          fuirt.put(huru[hi], fuirt.getOrDefault(huru[hi], 0) + 1);
        }
      }
      if (fuirt.size() > 2) {
        lo += 1;
        if (lo < N) {
          fuirt.put(huru[lo], fuirt.getOrDefault(huru[lo], 0) - 1);
          if (fuirt.get(huru[lo]) <= 0) {
            fuirt.remove(huru[lo]);
          }
        }
      }
      if (fuirt.size() <= 2) {
        answer = Math.max(answer, hi - lo);
      }

      // bw.write(String.format("%d %d %d %d\n", lo, hi, fuirt.size(), answer));
    }

    bw.write(Integer.toString(answer));

    bw.flush();
    bw.close();
    br.close();
  }
}
