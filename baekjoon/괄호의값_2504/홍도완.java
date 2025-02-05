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

  /// 아이디어
  // 스택 하나 만든다 (소괄호와 대괄호를 동시에 저장)
  // 정답 스택을 하나 만든다.
  // 괄호가 닫힐 때, 스택의 열린 괄호와 같으면 올바른 괄호다.
  // 괄호가 올바르게 닫힐 때, 괄호 스택의 크기와, 값을 정답에 넣는다.
  // 넣기 전에, 앞 정답의 스택 크기가 자기보다 크면, 큰것들을 전부 가져와서 더한 후, 값을 곱해준다.
  // 괄호가 전부 끝나면 나머지 스택의 값도 더해준다. 단, 올바른 괄호가 아니라면 0이된다.
  public static void main(String[] args) throws IOException {
    char[] input = br.readLine().toCharArray();
    ArrayDeque<Character> valueStack = new ArrayDeque<>(input.length);
    ArrayDeque<V> answerStack = new ArrayDeque<>(input.length);
    int answer = 0;
    CHACK: for (char c : input) {
      char temp;
      switch (c) {
        case '(':
          valueStack.push(c);
          break;
        case '[':
          valueStack.push(c);
          break;
        case ')':
          if (valueStack.isEmpty()) {
            answer = 0;
            answerStack.clear();
            break CHACK;
          }
          temp = valueStack.pop();
          if (temp == '(') {
            V v = new V(valueStack.size(), 0);
            while (!answerStack.isEmpty() && v.length < answerStack.peek().length) {
              v.value += answerStack.pop().value;
            }
            if (v.value == 0)
              v.value = 2;
            else
              v.value *= 2;
            answerStack.push(v);
          } else {
            answer = 0;
            answerStack.clear();
            break CHACK;
          }
          break;
        case ']':
          if (valueStack.isEmpty()) {
            answer = 0;
            answerStack.clear();
            break CHACK;
          }
          temp = valueStack.pop();
          if (temp == '[') {
            V v = new V(valueStack.size(), 0);
            while (!answerStack.isEmpty() && v.length < answerStack.peek().length) {
              v.value += answerStack.pop().value;
            }
            if (v.value == 0)
              v.value = 3;
            else
              v.value *= 3;
            answerStack.push(v);
          } else {
            answer = 0;
            answerStack.clear();
            break CHACK;
          }
          break;
      }
    }

    if (valueStack.isEmpty()) {
      while (!answerStack.isEmpty()) {
        answer += answerStack.pop().value;
      }
      bw.write(String.format("%d", answer));
    } else {
      bw.write("0");
    }
    bw.flush();
    bw.close();
    br.close();
  }
}

class V {
  int length, value;

  V(int l, int v) {
    length = l;
    value = v;
  }
}
