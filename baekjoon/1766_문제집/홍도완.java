import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 제한메모리: 128mb
  /// 제한시간: 2초
  /// 사용메모리: 47mb
  /// 사용시간: 656ms
  /// 아이디어
  // 쉬운 문제 순으로, 확인을 하되, 위상정렬 접목한다
  // 또한 현재 풀이 가능한 문제중 가능한 쉬운문제를 골라야 하므로
  // 우선순위 큐를 이용해본다
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());

    Qest[] qests = new Qest[N];

    for (int i = 0; i < N; ++i) {
      qests[i] = new Qest(i + 1);
    }
    for (int i = 0; i < M; ++i) {
      st = new StringTokenizer(br.readLine());
      int easy = Integer.parseInt(st.nextToken()) - 1;
      int hard = Integer.parseInt(st.nextToken()) - 1;
      qests[easy].harder.add(qests[hard]);
      qests[hard].easyer.add(qests[easy]);
    }
    for (Qest q : qests) {
      Collections.sort(q.easyer);
      Collections.sort(q.harder);
    }

    PriorityQueue<Qest> queue = new PriorityQueue<>();
    for (int i = 0; i < N; ++i) {
      if (qests[i].easyer.isEmpty()) {
        queue.add(qests[i]);
      }
    }

    while (!queue.isEmpty()) {
      Qest temp = queue.poll();
      bw.write(Integer.toString(temp.difical));
      bw.write(" ");

      for (Qest q : temp.harder) {
        q.easyer.remove(temp);
        if (q.easyer.isEmpty())
          queue.add(q);
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static class Qest implements Comparable<Qest> {
    int difical;
    ArrayList<Qest> easyer = new ArrayList<>();
    ArrayList<Qest> harder = new ArrayList<>();

    Qest(int d) {
      this.difical = d;
    }

    @Override
    public int compareTo(Qest o) {
      return Integer.compare(this.difical, o.difical);
    }
  }
}
