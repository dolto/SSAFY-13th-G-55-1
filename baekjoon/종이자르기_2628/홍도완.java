import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 홍도완 {
  ///아이디어
  // 세로와 가로를 자르고, 가장 큰 세로와 가로롤 곱하면 정답
  // 가로와 세로의 자르는 지점을 1부터n인덱스까지 전부 기록한 다음, 오름차순 정렬
  // 마지막 인덱스에 각각 가로세로 최대크기를 넣는다.
  // 모든 지점(0 인덱스는 0이므로)의 사이를 비교하며, 가장 큰 가로세로를 구한다
  // 둘을 곱하면 된다.
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int pSizeX = Integer.parseInt(st.nextToken());
    int pSizeY = Integer.parseInt(st.nextToken());
    int cutCount = Integer.parseInt(br.readLine().trim());

    List<Integer> cutXs = new ArrayList<>(cutCount + 2);
    List<Integer> cutYs = new ArrayList<>(cutCount + 2);

    cutXs.add(0);
    cutYs.add(0);

    for (int i = 0; i < cutCount; ++i) {
      st = new StringTokenizer(br.readLine());
      if (st.nextToken().equals("0")) {
        int pivot = Integer.parseInt(st.nextToken());
        cutYs.add(pivot);
      } else {
        int pivot = Integer.parseInt(st.nextToken());
        cutXs.add(pivot);
      }
    }
    Collections.sort(cutXs);
    Collections.sort(cutYs);

    cutXs.add(pSizeX);
    cutYs.add(pSizeY);

    int ax = 0, ay = 0;
    for (int i = 0; i < cutXs.size() - 1; ++i) {
      ax = Math.max(ax, cutXs.get(i + 1) - cutXs.get(i));
    }
    for (int i = 0; i < cutYs.size() - 1; ++i) {
      ay = Math.max(ay, cutYs.get(i + 1) - cutYs.get(i));
    }

    bw.write(String.format("%d", ax * ay));

    bw.flush();
    bw.close();
    br.close();
  }
}
