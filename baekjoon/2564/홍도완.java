import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

///아이디어
// 만약 동근이의 경비차와 상점의 위치가 건너편관계라면, 좌측,우측으로 이동한 거리중 최소치를 반환하면 된다
// 같은 방향에 존재한 상점이라면, 경비차와 상점의 거리차의 절댓값을 구하면 된다
// 건너편이 아니라면, 한바퀴를 돌지 않는 곳을 이동하는 것이 최단거리이다.
// 신에게는 백만 switch문이 있습니다...
public class 홍도완 {
  static int X, Y;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    X = Integer.parseInt(st.nextToken());
    Y = Integer.parseInt(st.nextToken());

    int size = Integer.parseInt(br.readLine());
    int[] d = new int[size], l = new int[size];
    int dd = 0;
    int dl = 0;

    for (int i = 0; i < size; ++i) {
      st = new StringTokenizer(br.readLine());
      d[i] = Integer.parseInt(st.nextToken());
      l[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    dd = Integer.parseInt(st.nextToken());
    dl = Integer.parseInt(st.nextToken());

    int answer = 0;
    for (int i = 0; i < size; ++i) {
      answer += get_length(dd, dl, d[i], l[i]);
    }

    bw.write(String.format("%d", answer));
    bw.flush();
    bw.close();
    br.close();
  }

  static int get_length(int dd, int dl, int td, int tl) {
    int result = 0;
    switch (dd) {
      case 1:
        switch (td) {
          case 1:
            result = Math.abs(dl - tl);
            break;
          case 2:
            int left = dl + Y + tl;
            int right = X - dl + Y + X - tl;
            result = Math.min(left, right);
            break;
          case 3:
            result = dl + tl;
            break;
          case 4:
            result = X - dl + tl;
            break;
        }
        break;
      case 2:
        switch (td) {
          case 2:
            result = Math.abs(dl - tl);
            break;
          case 1:
            int left = dl + Y + tl;
            int right = X - dl + Y + X - tl;
            result = Math.min(left, right);
            break;
          case 3:
            result = dl + Y - tl;
            break;
          case 4:
            result = X - dl + Y - tl;
            break;
        }
        break;
      case 3:
        switch (td) {
          case 3:
            result = Math.abs(dl - tl);
            break;
          case 4:
            int left = dl + X + tl;
            int right = Y - dl + X + Y - tl;
            result = Math.min(left, right);
            break;
          case 1:
            result = dl + tl;
            break;
          case 2:
            result = Y - dl + tl;
            break;
        }
        break;
      case 4:
        switch (td) {
          case 4:
            result = Math.abs(dl - tl);
            break;
          case 3:
            int left = dl + X + tl;
            int right = Y - dl + X + Y - tl;
            result = Math.min(left, right);
            break;
          case 1:
            result = dl + X - tl;
            break;
          case 2:
            result = Y - dl + X - tl;
            break;
        }
        break;
    }
    return result;
  }
}
