import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 아이디어
  // 일단 입력받은 집을 오름차순으로 정렬한다.
  // C는 무조건 2 이상이므로 0번과 N-1번 인덱스에 공유기를 설치한다.
  // 그 중앙에 공유기를 설치한다.
  // 0번째와 N-1번중 중앙 인덱스보다 더 거리가 더 가까운 쪽을 기록하고, 먼쪽의 중앙에 공유기를 설치한다.
  // 같은 방식으로 3개를 비교하고 중앙을 선택하는 형식으로 하면 될 것 같다.
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken()) - 2;

    int[] house = new int[N];
    // boolean[] connected = new boolean[N];
    PriorityQueue<Path> nextCenter = new PriorityQueue<>();
    ArrayList<Path> cs = new ArrayList<>(C);

    for (int i = 0; i < N; ++i) {
      house[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(house);

    // connected[0] = true;
    // connected[N - 1] = true;
    Path start = new Path(0, N - 1, house);
    nextCenter.add(start);
    int answer = Integer.MAX_VALUE;

    while (C >= 0) {
      Path target = nextCenter.poll();
      cs.add(target);
      int cindex = target.sindex + (target.eindex - target.sindex) / 2;
      Path left = new Path(target.sindex, cindex, house);
      Path right = new Path(cindex, target.eindex, house);

      if (C == 0)
        break;
      if (target.sindex != cindex)
        nextCenter.add(left);
      if (target.eindex != cindex)
        nextCenter.add(right);
      C -= 1;
    }

    Collections.sort(cs);
    answer = cs.get(0).dist;

    bw.write(String.format("%d", answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static class Path implements Comparable<Path> {
    int sindex, eindex;
    int dist;

    Path(int start, int end, int[] house) {
      this.sindex = start;
      this.eindex = end;
      this.dist = house[end] - house[start];
    }

    @Override
    public int compareTo(Path o) {
      return Integer.compare(this.dist, o.dist);
    }
  }
}
