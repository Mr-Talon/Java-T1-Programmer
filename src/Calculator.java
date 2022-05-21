import java.util.HashMap;
import java.util.Stack;
/**
 * @author 3dfish2dweb
 * @description 实现计算功能
 * 我们都知道，对于计算机来说，后缀表达式更方便计算，而中缀表达式对于人来说更加直观
 * 为了用程序实现中缀表达式的计算，我们可以利用栈将中缀表达式转换为后缀表达式，再计算后缀表达式（即使用下方实现的eval()方法）
 * 在这里，同样是利用栈进行一个中缀转后缀的操作，只不过我们的计算不是得到整个后缀表达式之后再计算，
 * 而是在中缀转后缀的过程中进行计算。
 * 因为后缀表达式是从前往后依次扫描，因此我们一旦确定了前部的某一段（包含开头），就可以提前将其计算出来，再放到原来位置，不影响最终结果。
 * @create 2022/5/14 15:04
 */
public class Calculator {
    private static final Stack<Character> op = new Stack<>(); // 存操作符
    private static final Stack<Double> num = new Stack<>(); // 存操作数
    private static final HashMap<Character, Integer> priMap = new HashMap<>(); // 优先级哈希表
    static {
        // 初始化哈希表
        priMap.put('(', 0);
        priMap.put('<', 1);
        priMap.put('>', 1);
        priMap.put('|', 2);
        priMap.put('^', 3);
        priMap.put('&', 4);
        priMap.put('+', 5);
        priMap.put('-', 5);
        priMap.put('*', 6);
        priMap.put('/', 6);
    }
    private static void eval(){ //只有十进制的时候可以计算小数
        double x = 0;
        char c = op.peek(); op.pop();
        if(c == '(') return; // 左括号无效，直接返回
        double b = num.peek(); num.pop();
        double a = num.peek(); num.pop();
        switch (c) {
            case '+': x = a + b; break;
            case '-': x = a - b; break;
            case '*': x = a * b; break;
            case '/':
            {
                if (Math.abs(b) < 1e-9)
                    throw new ArithmeticException("除数不能为0！");
                else x = a / b;
                break;
            }
            case '<': {
                for (int i = (int)b; i > 0; i -- )
                    a *= 2;
                x = a;
                break;
            }
            case '>' : {
                for (int i = (int)b; i > 0; i -- )
                    a /= 2;
                x = a;
                break;
            }
            case '|' : x = ((int)a | (int)b); break;
            case '^' : x = ((int)a ^ (int)b); break;
            case '&' : x = ((int)a & (int)b); break;
        }
        num.push(x);
    }
    private static void init() {
        while(!num.empty()) num.pop();
        while(!op.empty()) op.pop();
        op.push('('); //// 初始化操作符栈底
    }
    public static double compute(String s){ //静态方法，直接使用类名.方法名调用
//        System.out.println("get " + s);
        init();
        int n = s.length();
        for (int i = 0; i < n; i ++ ) {
            if (Character.isDigit(s.charAt(i))) {
                double x = 0;
                int j = i, dot = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    x = x * 10 + (s.charAt(j) - '0');
                    j ++;
                }
                if (j < n && s.charAt(j) == '.') { // 小数部分
                    j ++;
                    double y = 0;
                    while (j < n && Character.isDigit(s.charAt(j))) {
                        y = y * 10 + (s.charAt(j) - '0');
                        j ++;
                        dot ++;
                    }
                    x += y * (Math.pow(0.1, dot));
                }
                i = j - 1;
                num.push(x);
            } else if (s.charAt(i) == '(')
                op.push(s.charAt(i));
            else if (s.charAt(i) == ')') {
                while(op.peek() != '(')
                    eval();
                op.pop();
            } else
            {
                char c = s.charAt(i);
                if(c != '-' || (c == '-' && i > 0 && (Character.isDigit(s.charAt(i - 1)) || s.charAt(i - 1) == ')'))) { // 当前‘-’不是负号

                    while (!op.empty() && priMap.get(op.peek()) > priMap.get(c))
                        eval();

                    op.push(c);
                } else { // 如果是负号，则相当于-1 * 这个数
                    num.push(-1.0);
                    while(!op.empty() && priMap.get(op.peek()) > priMap.get('*'))
                        eval();
                    op.push('*');
                }
            }
        }
        while (num.size() > 1) //使最后只剩一个数
            eval();
        double res = num.peek();
//        System.out.println("return " + res);
        return res;
    }
}

