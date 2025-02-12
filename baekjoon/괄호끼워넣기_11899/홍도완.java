import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 홍도완 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    char[] problem = br.readLine().toCharArray();
    ArrayDeque<Character> stack = new ArrayDeque<>();

    for (char c : problem) {
      if (stack.isEmpty()) {
        stack.add(c);
      } else {
        if (c == ')' && stack.peek() == '(') {
          stack.pop();
        } else {
          stack.push(c);
        }
      }

    }
    bw.write(String.format("%d", stack.size()));
    bw.flush();
    br.close();
    bw.close();
  }
}
