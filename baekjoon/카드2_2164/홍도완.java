import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    Queue<Integer> deck = new ArrayDeque<>(n);
    for (int i = 1; i <= n; ++i) {
      deck.add(i);
    }

    while (deck.size() != 1) {
      deck.poll();
      deck.add(deck.poll());
    }

    bw.write(String.format("%d", deck.poll()));

    bw.flush();
    br.close();
    bw.close();
  }
}
