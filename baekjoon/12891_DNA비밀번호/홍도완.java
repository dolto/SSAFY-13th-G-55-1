import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  /// 시간제한: 2초
  /// 메모리제한: 512MB
  /// 사용시간: 272ms
  /// 메모리: 22mb
  /// 아이디어
  // P로 비밀번호의 크기가 주어지기 때문에, 고정 윈도우슬라이딩을 이용하면 된다.
  // need로 필요한 알파벳 갯수와, 현재 개수를 저장하고 갱신한다.
  // 윈도우를 오른쪽으로 밀면서 조건에 맞으면 정답 +1 한다.
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int S = Integer.parseInt(st.nextToken());
    final int P = Integer.parseInt(st.nextToken());
    char[] dna = br.readLine().toCharArray();
    st = new StringTokenizer(br.readLine());

    // a,c,g,t
    int[][] need = {
        { Integer.parseInt(st.nextToken()), 0 },
        { Integer.parseInt(st.nextToken()), 0 },
        { Integer.parseInt(st.nextToken()), 0 },
        { Integer.parseInt(st.nextToken()), 0 }
    };
    int answer = 0;
    int lo = 0, hi = 0;

    while (hi < P) {
      cal(dna[hi++], 1, need);
    }
    if (isOk(need)) {
      answer += 1;
    }
    while (hi < S) {
      cal(dna[hi++], 1, need);
      cal(dna[lo++], -1, need);
      if (isOk(need)) {
        answer += 1;
      }
    }

    bw.write(Integer.toString(answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static boolean isOk(int[][] need) {
    for (int[] n : need) {
      if (n[0] > n[1])
        return false;
    }
    return true;
  }

  static void cal(char key, int add, int[][] need) {
    switch (key) {
      case 'A':
        need[0][1] += add;
        break;
      case 'C':
        need[1][1] += add;
        break;
      case 'G':
        need[2][1] += add;
        break;
      case 'T':
        need[3][1] += add;
        break;
    }
  }
}
