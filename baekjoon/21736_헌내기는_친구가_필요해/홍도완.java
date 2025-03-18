import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    final int[][] OFFSET = {
        { 0, 1 },
        { 1, 0 },
        { -1, 0 },
        { 0, -1 },
    };

    char[][] map = new char[N][M];
    boolean[][] visit = new boolean[N][M];

    Doyung start = null;
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        char temp = (char) br.read();
        map[i][j] = temp;
        if (temp == 'I') {
          start = new Doyung(i, j);
          visit[i][j] = true;
        }
      }
      br.read();
    }

    int answer = 0;
    Queue<Doyung> queue = new ArrayDeque<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      Doyung temp = queue.poll();
      for (int[] of : OFFSET) {
        int tr = temp.r + of[0];
        int tc = temp.c + of[1];

        if (tr < 0 || tr >= N || tc < 0 || tc >= M || visit[tr][tc] || map[tr][tc] == 'X') {
          continue;
        }

        if (map[tr][tc] == 'P') {
          answer += 1;
        }

        visit[tr][tc] = true;
        queue.add(new Doyung(tr, tc));
      }
    }

    if (answer == 0) {
      bw.write("TT");
    } else {
      bw.write(Integer.toString(answer));
    }
    bw.flush();
    br.close();
    bw.close();
  }

  static class Doyung {
    int r, c;

    Doyung(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
}
