import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 제한시간: 1초
    /// 메모리제한: 512mb
    /// 사용시간: 444ms
    /// 사용메모리: 21mb
    /// 아이디어
  // 일단 섬을 구분한 다음, 각 섬의 모든 타일을 모든 타일간 거리를 구한다
  // 가장 긴 거리를 구해서 출력
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    final int[][] OFFSET = {
        { 0, 1 },
        { 1, 0 },
        { 0, -1 },
        { -1, 0 },
    };

    char[][] map = new char[N][];
    for (int i = 0; i < N; ++i) {
      map[i] = br.readLine().toCharArray();
    }

    int answer = 0;

    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        if (map[i][j] == 'L') {
          Iland iland = new Iland();
          map[i][j] = 0;
          Node start = new Node(i, j, 0);
          Queue<Node> queue = new ArrayDeque<>();
          queue.add(start);

          while (!queue.isEmpty()) {
            Node temp = queue.poll();
            iland.grounds.add(temp);
            for (int[] of : OFFSET) {
              int tr = temp.r + of[0];
              int tc = temp.c + of[1];
              if (tr < 0 || tr >= N || tc < 0 || tc >= M || map[tr][tc] != 'L') {
                continue;
              }

              map[tr][tc] = 0;
              queue.add(new Node(tr, tc, 0));
            }
          }

          // bw.write(Integer.toString(iland.grounds.size()));
          // bw.write("\n");
          boolean flag = false;
          for (Node n : iland.grounds) {
            queue.add(n);
            flag = !flag;
            char origin = flag ? 't' : 'f';
            map[n.r][n.c] = origin;
            int len = 0;
            while (!queue.isEmpty()) {
              Node temp = queue.poll();
              len = temp.layer;
              for (int[] of : OFFSET) {
                int tr = temp.r + of[0];
                int tc = temp.c + of[1];
                if (tr < 0 || tr >= N || tc < 0 || tc >= M || map[tr][tc] == 'W' || map[tr][tc] == origin) {
                  continue;
                }

                map[tr][tc] = origin;
                queue.add(new Node(tr, tc, temp.layer + 1));
              }
            }

            answer = Math.max(answer, len);
          }
        }
      }
    }

    bw.write(Integer.toString(answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static class Node {
    int r, c;
    int layer;
    boolean visited;

    Node(int r, int c, int layer) {
      this.r = r;
      this.c = c;
      this.layer = layer;
    }
  }

  static class Iland {
    List<Node> grounds = new ArrayList<>();
  }

}
