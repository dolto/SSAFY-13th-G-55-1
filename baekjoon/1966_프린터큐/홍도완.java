import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    final int T = Integer.parseInt(br.readLine());
    for (int t = 0; t < T; ++t) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      final int N = Integer.parseInt(st.nextToken());
      final int I = Integer.parseInt(st.nextToken());

      Queue<Docs> queue = new ArrayDeque<>(N);
      st = new StringTokenizer(br.readLine());
      int[] impos = new int[N];
      for (int i = 0; i < N; ++i) {
        int temp = Integer.parseInt(st.nextToken());
        Docs input = new Docs(temp);
        if (i == I) {
          input.target = true;
        }
        queue.add(input);
        impos[i] = temp;
      }

      Arrays.sort(impos);

      int answer = 0;
      while (!queue.isEmpty()) {
        Docs temp = queue.poll();
        if (temp.impo == impos[queue.size()]) {
          answer += 1;
          if (temp.target)
            break;
        } else {
          queue.add(temp);
        }
      }

      bw.write(Integer.toString(answer));
      bw.write("\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static class Docs {
    int impo;
    boolean target = false;

    Docs(int impo) {
      this.impo = impo;
    }
  }
}
