import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

  // 아이디어
  // 모든 일곱난쟁이의 경우의수를 고른다.
  static int[] answer = new int[7];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] mans = new int[9];
    for (int i = 0; i < 9; ++i) {
      int temp = Integer.parseInt(br.readLine().trim());
      mans[i] = temp;
    }
    dfs(0, new boolean[9], mans, 0);
    Arrays.sort(answer);
    for (int i = 0; i < 7; ++i) {
      bw.write(String.format("%d\n", answer[i]));
    }
    br.close();
    bw.flush();
    bw.close();
  }

  static void dfs(int n, boolean[] visited, int[] mans, int total) {
    if (total > 100)
      return;
    if (n == 7) {
      if (total == 100) {
        int index = 0;
        for (int i = 0; i < 9; ++i) {
          if (visited[i])
            answer[index++] = mans[i];
        }
      }
      return;
    }

    for (int i = 0; i < 9; ++i) {
      if (!visited[i]) {
        visited[i] = true;
        dfs(n + 1, visited, mans, total + mans[i]);
        visited[i] = false;
      }
    }
  }
}
