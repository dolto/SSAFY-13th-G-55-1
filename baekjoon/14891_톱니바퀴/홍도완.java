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

  // 제한시간: 2초
  // 제한메모리: 512mb
  // 사용시간: 108ms
  // 사용메모리: 14mb
  /// 아이디어
  // 12시 방향부터 8개가 "시계방향" 순서대로 주어진다.
  // 즉 이걸 배열로 했을 때 pivot을 +1하면 시계방향 이동, -1을 하면 반 시계방향이라는 것이다.
  // 최적의 해를 구하는 것이 아니기 때문에, 그냥 톱니바퀴의 시스템을 만들어놓고, 시뮬레이션 돌리면 된다
  // 톱니바퀴의 오른쪽은 2번 인덱스, 왼쪽은 6번 인덱스이다.
  public static void main(String[] args) throws IOException {
    char[][] tobnis = new char[4][8];
    int[] pivots = new int[4];
    for (int i = 0; i < 4; ++i) {
      tobnis[i] = br.readLine().toCharArray();
    }

    final int K = Integer.parseInt(br.readLine().trim());

    for (int i = 0; i < K; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      IndexDirection comm = new IndexDirection(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()),
          3);
      Queue<IndexDirection> queue = new ArrayDeque<>(4);
      queue.add(comm);
      while (!queue.isEmpty()) {
        IndexDirection temp = queue.poll();
        char tempLeft = tobnis[temp.index][(pivots[temp.index] + 6) % 8];
        char tempRight = tobnis[temp.index][(pivots[temp.index] + 2) % 8];
        if ((temp.leftRight & 2) != 0 && temp.index + 1 < 4) {
          int otherIndex = temp.index + 1;
          char otherLeft = tobnis[otherIndex][(pivots[otherIndex] + 6) % 8];
          if (tempRight != otherLeft) {
            queue.add(new IndexDirection(otherIndex, temp.direction * -1, 2));
          }
        }
        if ((temp.leftRight & 1) != 0 && temp.index - 1 >= 0) {
          int otherIndex = temp.index - 1;
          char otherRight = tobnis[otherIndex][(pivots[otherIndex] + 2) % 8];
          if (tempLeft != otherRight) {
            queue.add(new IndexDirection(otherIndex, temp.direction * -1, 1));
          }
        }

        if (temp.direction == -1)
          pivots[temp.index] = (pivots[temp.index] + 1) % 8;
        else {
          pivots[temp.index] = pivots[temp.index] == 0 ? 7 : pivots[temp.index] - 1;
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < 4; ++i) {
      if (tobnis[i][pivots[i]] == '1') {
        answer += 1 << i;
      }
    }
    bw.write(Integer.toString(answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static class IndexDirection {
    int index, direction, leftRight;

    IndexDirection(int index, int direction, int leftRight) {
      this.index = index;
      this.direction = direction;
      this.leftRight = leftRight;
    }
  }
}
