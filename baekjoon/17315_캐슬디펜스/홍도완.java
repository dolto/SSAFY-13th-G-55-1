import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  // 시간: 1초
  // 메모리제한: 192mb
  // 사용시간: 292ms
  // 메모리: 19mb
  /// 아이디어
  // 궁수는 3명까지 배치가 가능하며, 규칙상 동시에 공격할 수 있다 (비효율적)
  // 적이 성벽에 닿으면 제거되고, 적은 한방에 죽으며, 성벽이 애초에 한줄밖에 없는것이 명시 되어 있으므로 궁수는 반드시 N+1번행에 있어야
  // 한다.
  // 우선적으로 궁수의 열 위치에 적이 많으면 그곳에 궁수를 배치한다.
  // 적이 번갈아서 오는경우는 상관이 없지만, 적이 다른곳에서 오는데도 불구하고, 궁수가 발사하지 못하면 손해이므로, 생각을 해봐야 한다.
  // 일단 조합으로 풀어보자.
  // 하지만 궁수가 3명이고 배치도 고작 최대 15이므로, 시간안에 가능할 것 같은데... 일단 해보자
  // 굳이 배열로 해야하나? 적들을 객체로 다루고, 좌표를 저장하면 되지 않을까?
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    final int C = Integer.parseInt(st.nextToken());
    ArrayList<Enemy> enemys = new ArrayList<>(N * M);
    int[] archers = new int[M];
    for (int i = 0; i < N; ++i) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; ++j) {
        if (st.nextToken().trim().equals("1"))
          enemys.add(new Enemy(i, j));
      }
    }

    for (int i = M - 1; i >= M - 3; --i) {
      archers[i] = 1;
    }

    int answer = play(archers, enemys, N, M, C);
    while (nextPermutation(archers)) {
      answer = Math.max(answer, play(archers, enemys, N, M, C));
    }

    bw.write(Integer.toString(answer));

    bw.flush();
    br.close();
    bw.close();
  }

  static int play(int[] archers, ArrayList<Enemy> enemys, final int N, final int M, final int C) {
    int answer = 0;
    for (Enemy e : enemys) {
      e.atacked = false;
      e.shot = false;
    }
    for (int i = N; i >= 0; --i) {
      for (int j = 0; j < M; ++j) {
        if (archers[j] == 1) {
          int min = Integer.MAX_VALUE;
          Enemy index = null;
          for (Enemy e : enemys) {
            if (e.r >= i || e.atacked) {
              continue;
            }
            int dis = Math.abs(i - e.r) + Math.abs(j - e.c);
            if (dis <= C) {
              if (min > dis) {
                min = dis;
                index = e;
              } else if (min == dis && index.c > e.c) {
                index = e;
              }
            }
          }
          if (index != null && !index.shot) {
            index.shot = true;
            answer += 1;
          }
        }
      }

      for (Enemy e : enemys) {
        if (e.shot) {
          e.atacked = true;
        }
      }
    }

    return answer;
  }

  static class Enemy {
    int r, c;
    boolean atacked;
    boolean shot;

    Enemy(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  static boolean nextPermutation(int[] arr) {
    int i = arr.length - 1;
    while (i > 0 && arr[i] <= arr[i - 1]) {
      i -= 1;
    }
    if (i == 0) {
      return false;
    }
    int j = arr.length - 1;
    while (arr[j] <= arr[i - 1]) {
      j -= 1;
    }
    swap(arr, i - 1, j);
    int k = arr.length - 1;
    while (i < k) {
      swap(arr, i, k);
      i += 1;
      k -= 1;
    }
    return true;
  }

  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
