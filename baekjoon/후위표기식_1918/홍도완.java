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
  // 괄호안에 있다는 의미는, 어떤 식 안에 있다는 것과 같은 의미다.
  // 자신의 연산자 정보와, 자신 클래스를 가질 수 있는 스택과 수를 가진 스택을 가진 클래스를 만든다.
  // 또한 부모도 저장한다.
  // 최초의 연산자(아무것도 없는) 클래스 인스턴스를 만든다.
  // target을 최초의 연산자로 한다.
  // 수가 들어오면 target 수에 넣는다.
  // 연산자가 들어오면 target보다 우선순위가 높은지 확인한다.
  // 우선순위가 높으면, target을 연산자 클래스로 바꾼다.
  // 그렇지 않으면 높아질 때 까지 target을 부모로 옮긴다.
  // 열린 괄호가 들어오면 아무것도 없는 연산자 클래스를 만들고, 우선순위가 높은 경우와 동일한 행동을 한다.
  // 닫힌 괄호가 들어오면, 공백 연산자를 만날 때 까지 target을 부모로 이동시킨 후, 그 부모를 target으로 한다.
  //
  // 괄호 작업이 끝나면, 최초 클래스의 하위로 들어가서 더 이상 하위가 없을 때 까지 pop하면서 내려가며, 내려갈 때 마다 존재하는 수를
  // 출력한다.
  // 더 이상 하위가 없으면 본인의 연산자를 출력하고 부모로 간다.
  //
  public static void main(String[] args) throws IOException {
    char[] problem = br.readLine().toCharArray();
    for (char c : problem) {
      switch (c) {
        case '+':
        case '-':
        case '*':
        case '/':
          Ops.addOps(new Ops(c));
          break;
        case '(':
          Ops.hardAddOps(new Ops(c));
          break;
        case ')':
          Ops.findClose();
          break;
        default:
          Ops.addNum(c);
          break;
      }
    }

    Ops.target = Ops.root;

    while (!Ops.root.stack.isEmpty() || Ops.root != Ops.target) {
      while (!Ops.target.stack.isEmpty()) {
        while (!Ops.target.numStack.isEmpty()) {
          bw.write(Ops.target.numStack.removeLast());
        }
        Ops.target = Ops.target.stack.removeLast();
      }
      while (!Ops.target.numStack.isEmpty()) {
        bw.write(Ops.target.numStack.removeLast());
      }
      if (Ops.target.ops != '(')
        bw.write(Ops.target.ops);
      Ops.target = Ops.target.parent;
    }
    if (Ops.target.ops != '(')
      bw.write(Ops.target.ops);

    bw.flush();
    bw.close();
    br.close();
  }

}

class Ops {
  char ops;
  int level;
  ArrayDeque<Ops> stack = new ArrayDeque<>(50);
  ArrayDeque<Character> numStack = new ArrayDeque<>(2);
  Ops parent;

  static Ops root = new Ops('(');
  static Ops target = root;

  Ops(char ops) {
    this.ops = ops;
    switch (ops) {
      case '(':
        level = 0;
        break;
      case '+':
      case '-':
        level = 1;
        break;
      case '*':
      case '/':
        level = 2;
        break;
    }
  }

  static void addNum(char c) {
    target.numStack.push(c);
  }

  static void addOps(Ops temp) {
    if (target.level < temp.level) {
      hardAddOps(temp);
    } else {
      target = target.parent;
      addOps(temp);
    }
  }

  static void hardAddOps(Ops temp) {
    // if (!target.numStack.isEmpty())
    // temp.numStack.push(target.numStack.pop());
    target.stack.push(temp);
    temp.parent = target;
    target = temp;
  }

  static void findClose() {
    while (target.ops != '(') {
      target = target.parent;
    }
    target = target.parent;
  }

}
