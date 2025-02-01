import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 아이디어
//가로세로크기가 최대 100이라서 배열로 풀면 쉽게 풀리겠지만... 수식으로 풀어보자
//사각형을 입력 받고, 그 넓이를 구하여 더한다
//이후 사각형의 넓이를 구할 때, 이전의 사각형과 겹치는 부분의 사각형을 빼준다
//겹치는 부분끼리의 사각형은 두번 빠진 것이니, 다시 더해준다
//이렇게 하면 3개 까지의 겹치는 부분은 처리가 되지만, 4개째로 겹치는 부분도 다시 더해주기 때문에, 중복 더해주게 된다.
//마지막에 4개가 전부 겹치는 공통부분을 빼주면 문제해결 성공!!
public class 홍도완 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int answer = 0;
    Reactengle[] rects = new Reactengle[4];

    for (int i = 0; i < 4; ++i) {
      st = new StringTokenizer(br.readLine());
      rects[i] = new Reactengle(
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()));
      int temp = rects[i].getExtent();
      // bw.write(String.format("before: %d\n\n", temp));
      Reactengle[] trs = new Reactengle[i];
      for (int j = 0; j < i; ++j) {
        trs[j] = rects[i].getOverArea(rects[j]);
        if (trs[j] != null) {
          temp -= trs[j].getExtent();
          for (int k = 0; k < j; ++k) {
            if (trs[k] != null) {
              Reactengle t = trs[j].getOverArea(trs[k]);
              if (t != null)
                temp += t.getExtent();
            }
          }
          // bw.write(String.format("middle: %d\n\n", temp));
        }
      }

      // bw.write(String.format("after: %d\n\n", temp));
      answer += temp;
    }

    Reactengle over = rects[0];
    for (int i = 1; i < 4; ++i) {
      if (over == null)
        break;
      over = over.getOverArea(rects[i]);
    }

    if (over != null)
      answer -= over.getExtent();

    bw.write(String.format("%d", answer));
    bw.flush();
    bw.close();
    br.close();
  }
}

class Reactengle {
  private int x, y, p, q;

  Reactengle(int x, int y, int p, int q) {
    this.x = x;
    this.y = y;
    this.p = p;
    this.q = q;
  }

  int getExtent() {
    int xl = p - x;
    int yl = q - y;
    int result = xl * yl;
    return result;
  }

  Reactengle getOverArea(Reactengle other) {
    int mx = Math.max(this.x, other.x);
    int my = Math.max(this.y, other.y);
    int mp = Math.min(this.p, other.p);
    int mq = Math.min(this.q, other.q);
    if (mx >= mp || my >= mq) {
      return null;
    }
    Reactengle temp = new Reactengle(mx, my, mp, mq);
    return temp;
  }
}
