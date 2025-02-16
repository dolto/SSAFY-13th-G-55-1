import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  static int answer = Integer.MAX_VALUE;
  static int N;
  static Point[] nodes;

  /// 아이디어
  // 일단 노드를 입력받고, 연결하며 모든 노드의 합을 구한다.
  // 임의의 노드를 선택하고 확장하며 연결된 모든 노드를 1번 구역으로 나눈다.
  // 1번노드의 합과 모든노드의 합이 같다면 구역은 하나라는 의미, 2번 구역을 조율한다.
  // 가능한 모든 조합을 백트레킹으로 시도하며, 가지치기로는 2번구역의 노드가 연결되지 않았다면 리턴한다.
  // 주의할 점은 dfs든 bfs든 모든 경우의 2번 구역을 구할 수 없기 때문에, 반례가 무조건 존재할 수 밖에 없어진다.
  // 이거 때문에 4일이란 시간이 흘렀다... ㅠㅠ
  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine().trim());
    nodes = new Point[N];
    st = new StringTokenizer(br.readLine());
    Part sum = new Part();
    for (int i = 0; i < N; ++i) {
      int temp = Integer.parseInt(st.nextToken());
      nodes[i] = new Point(temp);
      sum.sum += temp;
    }
    for (int i = 0; i < N; ++i) {
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());

      for (int j = 0; j < M; ++j) {
        int t = Integer.parseInt(st.nextToken()) - 1;
        if (nodes[i] != nodes[t]) {
          if (!nodes[i].marge.contains(nodes[t]))
            nodes[i].marge.add(nodes[t]);
          if (!nodes[t].marge.contains(nodes[i]))
            nodes[t].marge.add(nodes[i]);
        }
      }
    }

    // 1. 0번 노드와 연결된 모든 노드를 A구역으로 친다.
    sum.initPart(nodes[0], 1);
    // 2. 만약 sum과 asum이 다르면 끊긴 구간이 있다는 의미
    if (sum.sum > sum.asum) {
      for (Point n : nodes) {
        if (n.part == 0) {
          sum.initPart(n, 2);
          break;
        }
      }

      // 만약 구간이 두개라면 그대로 계산, 아니라면 -1
      if (sum.sum == sum.asum + sum.bsum)
        answer = Math.abs(sum.asum - sum.bsum);
      else
        answer = -1;
    } else {
      for (int i = 1; i <= N; ++i) {
        sum.solve(0, i, new ArrayList<>(N));
      }
    }

    bw.write(String.format("%d", answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static class Part {
    int sum, asum, bsum;

    void initPart(Point node, int part) throws IOException {
      int sum = 0;
      Queue<Point> q = new ArrayDeque<>(N);
      q.add(node);
      node.check = true;
      node.part = part;
      sum = node.p;

      while (!q.isEmpty()) {
        Point temp = q.poll();
        for (Point p : temp.marge) {
          if (!p.check) {
            q.add(p);
            p.check = true;
            p.part = part;
            sum += p.p;
          }
        }
      }
      initChecker();

      // bw.write(String.format("%d %d %d\n", sum, this.sum, part));

      switch (part) {
        case 1:
          asum = sum;
          break;
        case 2:
          bsum = sum;
          break;
      }

    }

    void initChecker() {
      for (Point n : nodes) {
        n.check = false;
      }
    }

    boolean checkSum(int part) throws IOException {
      int sum = 0;
      Point node = null;
      for (Point p : nodes) {
        if (p.part == part) {
          node = p;
          break;
        }
      }
      if (node == null) {
        switch (part) {
          case 1:
            return false;
          case 2:
            return true;
        }
      }

      initChecker();
      Queue<Point> q = new ArrayDeque<>(N);
      q.add(node);
      node.check = true;
      sum = node.p;

      while (!q.isEmpty()) {
        Point temp = q.poll();
        for (Point p : temp.marge) {
          // bw.write(String.format("%d %b %d %d\n", temp.p, p.check, p.part, p.p));
          if (!p.check && p.part == part) {
            q.add(p);
            p.check = true;
            sum += p.p;
          }
        }
        // bw.write("\n\n");
      }

      switch (part) {
        case 1:
          // bw.write(String.format("asum: %d, sum: %d\n", asum, sum));
          return asum == sum;
        case 2:
          return bsum == sum;
      }
      return false;
    }

    void solve(int n, int cnt, ArrayList<Point> result) throws IOException {
      if (!checkSum(2)) {
        bw.write("?? ");
        return;
      }
      if (n == cnt) {
        if (answer > Math.abs(asum - bsum) && checkSum(1)) {
          answer = Math.min(answer, Math.abs(asum - bsum));
        }
        return;
      }

      for (int i = n; i < N; ++i) {
        if (nodes[i].part == 1) {
          result.add(nodes[i]);
          asum -= nodes[i].p;
          bsum += nodes[i].p;
          nodes[i].part = 2;
          solve(n + 1, cnt, result);
          asum += nodes[i].p;
          bsum -= nodes[i].p;
          nodes[i].part = 1;
          result.remove(result.size() - 1);
        }
      }
    }
  }

  static class Point {
    int p, part;
    boolean check;
    ArrayList<Point> marge = new ArrayList<>(N);

    Point(int p) {
      this.p = p;
    }
  }
}
