import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
// 스위치의 개수가 100개 이하이므로, 그냥 boolean배열로 풀어도 상관 없으나 비트마스크로 해보기로 했다
// 출력이 20개씩 출력이므로, int하나당 20개의 스위치를 담당한다.
// 각 인덱스의 스위치는 bigindex, smallindex... 줄여서 bndex, sndex로 나누어서 구하고
// bndex = index / 20; 으로
// sndex = index % 20; 으로 구한다.
// 남성과 여성 모두 규칙에 맞게, remote[] 배열에 조작할 스위치를 정의하고
// 모듈러 연산으로 한번에 처리한다.
// 단, 여성의 경우, 인덱스를 초과하여 검사할 경우, 문제가 발생할 수 있기 때문에, 상한치, 하한치를 체크하도록 한다.
public class 홍도완 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine().trim());
    int[] switchs = new int[N / 20 + 1];
    st = new StringTokenizer(br.readLine());

    int maxCount = 1 << 20;
    int sw = 1;
    int sindex = 0;
    for (int i = 0; i < N; ++i) {
      String temp = st.nextToken();
      if (sw >= maxCount) {
        sindex += 1;
        sw = 1;
      }
      if (temp.equals("1"))
        switchs[sindex] += sw;
      sw = sw << 1;
      // bw.write(String.format("%d\n", sw));
    }
    // System.out.println(sindex);

    int pcount = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < pcount; ++i) {
      st = new StringTokenizer(br.readLine());
      // 1은 남자 2는 여자
      if (st.nextToken().equals("1")) {
        int step = Integer.parseInt(st.nextToken());
        int[] remote = new int[sindex + 1];
        for (int j = step - 1; j < N; j += step) {
          int bndex = j / 20;
          int sndex = j % 20;
          remote[bndex] += 1 << sndex;
        }
        for (int j = 0; j <= sindex; ++j) {
          // bw.write(String.format("%d %d\n", switchs[j], remote[j]));
          switchs[j] ^= remote[j];
        }
      } else {
        int w = 1;
        int pivot = Integer.parseInt(st.nextToken()) - 1;
        int[] remote = new int[sindex + 1];

        int pbndex = pivot / 20;
        int psndex = pivot % 20;
        // 일단 기준 인덱스를 조작할 스위치 목록에 넣는다.
        remote[pbndex] = 1 << psndex;

        // 상한치 체크 하며, 잉여 스위치를 조건에 넣는 경우를 제거한다
        while (w + pivot < N) {
          int lbndex = (pivot - w) / 20;
          int lsndex = (pivot - w) % 20;
          int left = switchs[lbndex] & (1 << lsndex);
          // 하한치 체크
          if (lsndex < 0)
            break;

          int rbndex = (pivot + w) / 20;
          int rsndex = (pivot + w) % 20;
          if (rbndex > sindex)
            break;
          int right = switchs[rbndex] & (1 << rsndex);

          if ((left == 0 && right == 0) || (left != 0 && right != 0)) {
            remote[lbndex] += 1 << lsndex;
            remote[rbndex] += 1 << rsndex;
          } else {
            break;
          }
          ++w;
        }

        for (int j = 0; j <= sindex; ++j) {
          switchs[j] ^= remote[j];
        }
      }
    }

    // bw.write(String.format("sindex: %d\n", sindex));
    int n = 0;
    // 20개씩 출력하되, n개까지 출력하도록 한다.
    for (int i = 0; i <= sindex; ++i) {
      for (int j = 1; j < maxCount; j = j << 1) {
        if (n++ >= N)
          break;
        if ((switchs[i] & j) == 0) {
          bw.write("0 ");
        } else {
          bw.write("1 ");
        }
      }
      bw.write("\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
