import java.math.BigDecimal;
import java.math.RoundingMode;
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
 *
 * 为了计算方便，使用BigDecimal类模拟，精度较高，且可自动除末尾0
 * @create 2022/5/14 15:04
 */
public class Calculator {
    private static final Stack<Character> op = new Stack<>(); // 存操作符
    private static final Stack<BigDecimal> num = new Stack<>(); // 存操作数
    private static final HashMap<Character, Integer> priMap = new HashMap<>(); // 优先级哈希表

    static {
        // 初始化优先级哈希表，优先级高的先运算
        priMap.put('(', 0);
        priMap.put('<', 1);
        priMap.put('|', 2);
        priMap.put('^', 3);
        priMap.put('&', 4);
        priMap.put('+', 5);
        priMap.put('-', 5);
        priMap.put('*', 6);
        priMap.put('/', 6);
    }
    private static void eval(){
        BigDecimal x = new BigDecimal(0);
        if(op.empty()) { // 异常：操作数冗余
            throw new NullPointerException("格式错误！");
        }
        char c = op.peek(); op.pop();
        if(c == '(') return; // 左括号无效，直接返回
        if(num.size() < 1) { // 异常：操作符冗余
            throw new NullPointerException("格式错误！");
        }
        BigDecimal b = num.peek(); num.pop(); // 栈中先弹出的是第二操作数b
        BigDecimal a = num.peek(); num.pop(); // 后弹出的是第一操作数a
        switch (c) { // 分情况执行对应运算
            case '+': x = a.add(b); break;
            case '-': x = a.subtract(b); break;
            case '*': x = a.multiply(b); break;
            case '/':
            {
                try {
                    x = a.divide(b, 8, RoundingMode.HALF_UP);// 保留8位小数，四舍五入
                } catch(ArithmeticException e) {
                    throw new ArithmeticException("除数不能为0！");
                }
                break;
            }
            case '<': { // 移位符号，b>0左移b个bit，b<0右移-b个bit
                int k = b.intValue();
                String binStr = Integer.toBinaryString(a.intValue()); // 转为2进制字符串进行移位模拟
                binStr = Code_Trans.formatBin(binStr); //标准化为32位2进制字符串
                if(k > 0) { // 左移
                    for(int j = 0; j < k; j ++ )
                        binStr = binStr + "0";
                    binStr = binStr.substring(k, k + 32); // 截取低位部分
                } else { // 右移
                    for(int j = 0; j > k; j -- )
                        binStr = "0" + binStr;
                    binStr = binStr.substring(0, 32); // 截取高位部分
                }
                String hexStr = Code_Trans.binToHex(binStr); // 先转为16进制字符串
                String dec = new Translation(hexStr).Decimal(); // 再转为10进制字符串
                x = new BigDecimal(Integer.parseInt(dec)); // 再转为int，然后转为BigDecimal
                break;
            }
            // 逻辑运算就转为int后直接运算，然后再转为BigDecimal
            case '|' : x = new BigDecimal(a.intValue() | b.intValue()); break;
            case '^' : x = new BigDecimal(a.intValue() ^ b.intValue()); break;
            case '&' : x = new BigDecimal(a.intValue() & b.intValue()); break;
        }
        num.push(x); // 运算结果再压入栈
    }
    private static void init() { // 初始化，将栈清空
        while(!num.empty()) num.pop();
        while(!op.empty()) op.pop();
        op.push('('); //// 初始化操作符栈底
    }
    public static String compute(String infixExpression){ // 得到一个中缀表达式字符串，返回对应的计算结果
        init();
        int n = infixExpression.length();
        for (int i = 0; i < n; i ++ ) { // 逐字符扫描表达式
            if (Character.isDigit(infixExpression.charAt(i))) { // 当前字符是数字
                BigDecimal x = new BigDecimal(0);
                int j = i, dot = 0; // j记录从下标i开始一直扫描到哪个字符的下标， dot记录小数点位数
                while (j < n && Character.isDigit(infixExpression.charAt(j))) { // 向后扫描获取完整的数字
                    x = x.multiply(BigDecimal.valueOf(10));
                    x = x.add(BigDecimal.valueOf(infixExpression.charAt(j) - '0'));
                    j ++;
                }
                if (j < n && infixExpression.charAt(j) == '.') { //遇到小数点，处理小数部分
                    j ++;
                    BigDecimal y = new BigDecimal(0);
                    while (j < n && Character.isDigit(infixExpression.charAt(j))) { // 向后扫描获取完整的数字
                        y = y.multiply(BigDecimal.valueOf(10));
                        y = y.add(BigDecimal.valueOf(infixExpression.charAt(j) - '0'));
                        j ++;
                        dot ++;
                    }
                    for (int k = 0; k < dot; k ++ ) { //根据dot的值来将扫描到的整数变为小数
                        y = y.divide(BigDecimal.valueOf(10), 8, RoundingMode.HALF_UP); // 8位小数，四舍五入
                    }
                    x = x.add(y); // 整数部分加上小数部分
                }
                i = j - 1; // 由于上面for循环会++， 想要下面处理j下标的字符，i就要在j前面一个下标的位置
                num.push(x);
            } else if (infixExpression.charAt(i) == '(') // 左括号，直接压入栈
                op.push(infixExpression.charAt(i));
            else if (infixExpression.charAt(i) == ')') { // 右括号，一直eval()计算，直到操作数栈顶为左括号
                while(op.peek() != '(')
                    eval();
                op.pop(); // 把左括号弹出，表示括号里的全部处理完毕
            } else // 否则是其他的操作符: + - * / < | ^ &
            {
                char c = infixExpression.charAt(i);
                if(c != '-' || (i > 0 && (Character.isDigit(infixExpression.charAt(i - 1)) || infixExpression.charAt(i - 1) == ')'))) {
                    // 当前"-"不是负号
                    // 如果操作符栈不空，并且栈顶符号优先级大于等于当前符号优先级，就一直eval()计算
                    while (!op.empty() && priMap.get(op.peek()) >= priMap.get(c))
                        eval();
                    op.push(c); // 此时保证当前字符的优先级在栈中是最高的
                } else { // 当前"-"是负号，则相当于(-1) * (后面的一系列表达式)
                    num.push(BigDecimal.valueOf(-1)); // 操作数栈压入-1后，下面相当于扫描到一个"*"
                    while(!op.empty() && priMap.get(op.peek()) >= priMap.get('*')) // 同上
                        eval();
                    op.push('*');
                }
            }
        }
        while (num.size() > 1) //使最后只剩一个数
            eval();
        return num.peek().stripTrailingZeros().toPlainString(); //去末尾0且变为PlainString(无科学计数法)
    }
}

