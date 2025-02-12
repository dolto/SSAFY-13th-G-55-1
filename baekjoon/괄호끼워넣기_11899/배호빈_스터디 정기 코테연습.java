
import java.util.*;
import java.io.*;
public class main_11899 {
    public static void main(String[] args) throws IOException {
        //아이디어 문제를 보니 '('가 나올때 '('가 나오지 않으면 없앨 수 없는 괄호이고 올바른 괄호만 제거 스택에 남은 괄호만큼 새로 사야하는 괄호입니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character>stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(stack.isEmpty()){
                stack.push(c);
                continue;
            }

            if (c == ')') {
                if(stack.peek() == '('){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }else{
                stack.push(c);
            }
        }
        System.out.println(stack.size());
    }
}
