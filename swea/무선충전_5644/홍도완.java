import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  // 제한시간 3초
  // 제한메모리 256mb
  // 사용시간: 95ms
  // 사용메모리: 27mb
  //
  /// 아이디어
  // 문제에서 요구하는 것은 단순히 이동경로를 가는동안, 모든 사람의 총 충전량이 큰 순으로 가면 될 것이다.
  // 즉 동시에 2개의 충전기를 이용할 수 있다면, 무조건 하나씩 쓰는것이 좋다.
  //
  public static void main(String[] args) throws Exception {
    final int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; ++t) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 이동 횟수는 +1로 해서 받는다. 최초시작시간에도 충전한다고 했으니, 최초엔 움직이지 않는 식으로 충전한다.
      final int M = Integer.parseInt(st.nextToken()) + 1;
      final int C = Integer.parseInt(st.nextToken());
      Player[] players = new Player[2];
      BC[] bcs = new BC[C];
      for (int i = 0; i < 2; ++i) {
        st = new StringTokenizer(br.readLine());
        int[] move = new int[M];
        for (int j = 1; j < M; ++j) {
          move[j] = Integer.parseInt(st.nextToken());
        }
        // 플레이어는 각각 1,1 10,10 좌표에서 한다.
        players[i] = new Player(i * 9 + 1, i * 9 + 1, move);
      }

      for (int i = 0; i < C; ++i) {
        st = new StringTokenizer(br.readLine());
        bcs[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }

      for (int i = 0; i < M; ++i) {
        BC[] bc = new BC[4];
        for (int j = 0; j < 2; ++j) {
          // 이동 했으므로, 기존에 있었던 후보를 제거
          players[j].canCharger.clear();
          players[j].move(i);
          for (BC b : bcs) {
            if (b.getDistance(players[j]) <= b.C) {
              // 범위의 충전기를 적용
              players[j].canCharger.add(b);
            }
          }

          // 최선의 방법과
          bc[j] = players[j].canCharger.poll();
          // 차선의 방법을 뽑음
          bc[j + 2] = players[j].canCharger.peek();
        }

        // 만약 최선의 방법이 같은 충전소라면
        if (bc[0] != null && bc[1] != null && bc[0] == bc[1]) {
          // 차선이 없는 쪽이 최선을 가져감
          if (bc[2] == null) {
            players[0].answer += bc[0].P;
            if (bc[3] != null)
              players[1].answer += bc[3].P;
          } else if (bc[3] == null) {
            players[1].answer += bc[1].P;
            if (bc[2] != null)
              players[0].answer += bc[2].P;
          }
          // 둘 다 차선이 있다면 더 파워가 강한 차선을 가져감
          else if (bc[2].P > bc[3].P) {
            players[0].answer += bc[2].P;
            players[1].answer += bc[1].P;
          } else {
            players[1].answer += bc[3].P;
            players[0].answer += bc[0].P;
          }
        } else {
          // 최선이 서로 다르다면 굳이 나눌 필요 없이 있는거 쓰면 됨
          for (int j = 0; j < 2; ++j) {
            if (bc[j] != null)
              players[j].answer += bc[j].P;
          }
        }
      }

      bw.write(String.format("\n"));
      bw.write("#");
      bw.write(Integer.toString(t));
      bw.write(" ");
      bw.write(Integer.toString(players[0].answer + players[1].answer));
      bw.write("\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  // 이동하는 유저
  static class Player {
    // y,x 좌표가 아님에 주의
    int x, y;
    PriorityQueue<BC> canCharger = new PriorityQueue<>();
    int[] moveMemory;

    // 직관성과 디버깅의 용이성을 위해 answer를 따로 저장
    int answer = 0;

    Player(int x, int y, int[] moveMemory) {
      this.x = x;
      this.y = y;
      this.moveMemory = moveMemory;
    }

    void move(int index) {
      switch (this.moveMemory[index]) {
        case 1:
          y -= 1;
          break;
        case 2:
          x += 1;
          break;
        case 3:
          y += 1;
          break;
        case 4:
          x -= 1;
          break;
      }
    }
  }

  static class BC implements Comparable<BC> {
    int x, y, C, P;

    BC(int x, int y, int C, int P) {
      this.x = x;
      this.y = y;
      this.C = C;
      this.P = P;
    }

    int getDistance(Player p) {
      return Math.abs(p.x - x) + Math.abs(p.y - y);
    }

    public int compareTo(BC o) {
      // 내림차순 정렬
      return Integer.compare(o.P, this.P);
    };

  }
}
