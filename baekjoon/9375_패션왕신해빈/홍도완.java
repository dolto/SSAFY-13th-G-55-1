import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    final int T = Integer.parseInt(br.readLine());
    for (int t = 0; t < T; ++t) {
      final int N = Integer.parseInt(br.readLine());
      HashMap<String, Integer> closet = new HashMap<>();
      long answer = 1;
      for (int i = 0; i < N; ++i) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        String category = st.nextToken();
        closet.put(category, closet.getOrDefault(category, 1) + 1);

      }
      for (int c : closet.values()) {
        answer *= c;
      }
      answer -= 1;
      bw.write(Long.toString(answer));
      bw.write("\n");

    }

    bw.flush();
    bw.close();
    br.close();
  }
}
