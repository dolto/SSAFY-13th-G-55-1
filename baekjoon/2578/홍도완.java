import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/// 아이디어
// 5*5 빙고판에 Node를 넣는다.
// Node클래스에 본인 기준하여 빙고인지 확인하는 메서드를 만든다
// 도장을 찍을 때 마다 자신을 기준으로, 가로 세로를 확인하는 메서드를 수행한다
// 중앙에 있을경우 대각선 두개 메서드,
// ij가 같은 위치라면 왼쪽위부터 대각선,
// 4 - i == j 라면 오른쪽 위부터 대각선 까지체크한다
// 체크 할 때마다 성공한다면 빙고를 +1 한다.
// 이렇게 확인한 빙고가 3개이상이 된다면, 카운트를 출력하고 종료한다.
public class 홍도완 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    HashMap<Integer, Node> hashMap = new HashMap<>(25);
    Node[][] map = new Node[5][5];

    for (int i = 0; i < 5; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine().trim());
      for (int j = 0; j < 5; ++j) {
        Node temp = new Node(map, i, j);
        map[i][j] = temp;
        hashMap.put(Integer.parseInt(st.nextToken()), temp);
      }
    }

    int answer = 0;
    int bingo = 0;
    CHECK: for (int i = 0; i < 5; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine().trim());
      for (int j = 0; j < 5; ++j) {
        Node temp = hashMap.get(Integer.parseInt(st.nextToken()));
        temp.check = true;
        // for (int m = 0; m < 5; ++m) {
        // for (int n = 0; n < 5; ++n) {
        // bw.write(String.format("%b ", map[m][n].check));
        // }
        // bw.write("\n");
        // }
        ++answer;
        bingo += temp.bingo();
        if (bingo >= 3) {
          break CHECK;
        }
      }
    }
    bw.write(String.format("%d", answer));

    br.close();
    bw.flush();
    bw.close();
  }
}

class Node {
  boolean check;
  Node[][] map;
  int i, j;

  Node(Node[][] map, int i, int j) {
    this.map = map;
    this.i = i;
    this.j = j;
  }

  private boolean LeftDiagonal() {
    boolean result = true;
    for (int i = 0; i < 5; ++i) {
      if (!map[i][i].check) {
        result = false;
        break;
      }
    }
    return result;
  }

  private boolean RightDiagonal() {
    boolean result = true;
    for (int i = 0; i < 5; ++i) {
      if (!map[4 - i][i].check) {
        result = false;
        break;
      }
    }
    return result;
  }

  private boolean RowCheck() {
    boolean result = true;
    for (int i = 0; i < 5; ++i) {
      if (!map[i][this.j].check) {
        result = false;
        break;
      }
    }
    return result;
  }

  private boolean ColumeCheck() {
    boolean result = true;
    for (int i = 0; i < 5; ++i) {
      if (!map[this.i][i].check) {
        result = false;
        break;
      }
    }
    return result;
  }

  int bingo() {
    int result = 0;
    if (i == 2 && j == 2) {
      if (LeftDiagonal())
        ++result;
      if (RightDiagonal())
        ++result;
    } else if (i == j) {
      if (LeftDiagonal())
        ++result;
    } else if (4 - i == j) {
      if (RightDiagonal())
        ++result;
    }

    if (RowCheck())
      result++;
    if (ColumeCheck())
      result++;
    return result;
  }
}
