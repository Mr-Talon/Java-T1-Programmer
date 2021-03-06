import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Objects;

public class T1_Programmer{
    //窗体
    private JPanel root;

    //组件
    private JButton BACKSPACEButton;
    private JButton DiveideButton;
    private JButton MultipButton;
    private JButton MinusButton;
    private JButton AddButton;
    private JButton a2SCButton;
    private JButton DotButton;
    private JButton a0Button;
    private JButton DECButton;
    private JButton CLCButton;
    private JButton SHEButton;
    private JButton ORButton;
    private JButton ANDButton;
    private JButton XORButton;
    private JButton a1SCButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton a8Button;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton a1Button;
    private JButton OFFButton;
    private JButton EqualButton;
    private JButton HEXButton;
    private JButton LeftButton;
    private JButton RightButton;

    private JLabel ans;
    private JLabel DECState;
    private JLabel HEXState;
    private JLabel grammarError;
    private JLabel overflowError;

    //状态变量与状态常量
    private int state=1;

    private final int DEC=1;
    private final int HEX=2;

    //错误信号
    private int error=0;

    private final int HEX_INPUT_WHEN_DEX =3;   //在10进制情况下 输入a-f
    private final int SC1_WHEN_DEC=8;      //在10进制的时候使用反码按钮
    private final int SC2_WHEN_DEC=9;      //在10进制的时候使用补码按钮
    private final int AND_WHEN_DEC=12;      //在10进制的时候使用与运算按钮
    private final int OR_WHEN_DEC=13;      //在10进制的时候使用或运算按钮
    private final int XOR_WHEN_DEC=14;      //在10进制的时候使用异或运算按钮
    private final int SHE_WHEN_DEC=15;      //在10进制的时候使用移位按钮

    private final int INPUT_DOT_WHEN_HEX =4;   //在16进制输入小数点
    private final int NEGATIVE_WHEN_HEX=11;   //在16进制状态下使用减号充当符号 （16进制的负数应该是通过编码的第一位表示正负）

    private final int INPUT_OVERFLOW=5;     //输入溢出
    private final int OUTPUT_OVERFLOW=6;    //输出溢出

    private final int MISS_PARENTHESES=7;   //缺少括号
    private final int PARENTHESES_ERROR=10;   //括号使用的语法错误（错误情况太多 不做区分）

    private final int EQUAL_ERROR=16;   //等号触发的多种错误 （不做区分）

    private final int DIVIDE_ZERO=17;  //除以0

    private String expression="";   //总表达式
    private String currentString="";   //当前需要展示在JLabel ans上字符串

    //程序中需要的临时变量
    private int numOfLeftParentheses=0;
    private int numOfRightParentheses=0;


    public T1_Programmer() {
        /*0-9数字输入的事件处理*/
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){          //如果当前错误信号存在不允许输入
                    if (currentString.length()<8){           //没有溢出 正常进行
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            //由于括号也会显示  所有的操作数会作为括号的结束条件  将括号加入总表达式
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a1Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;        //内部错误信号
                        overflowError.setText("OF");    //计算器界面显示溢出错误
                    }
                }
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a2Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a3Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a4Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a5Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a6Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a7Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a8Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text=a9Button.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //按钮0需要防止过多的0输入
                //表达式开头只有在小数点输入的时候可以是0，而且这个时候0一定是字符串的首个元素  如果第一个元素输入了0，第二个元素输入小数点
                //就不会进入return的分支，因为此时字符串长度已经等于2
                //只有在开头连续输入0 才是不被允许的 这里不返回错误信号  指示不允许用户这么输入
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.length()==1&&currentString.charAt(0) == '0'){
                            //当前字符串长度是1 并且输入的操作数是0  就造成了重复输入0
                            return;
                        }
                        //其他逻辑和1-9一样
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="";
                        }
                        String text="0";
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });

        /*a-f数字输入的事件处理，有输入溢出判定，还有语法错误判定*/
        aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (state==DEC){       //对于a-f的字符 只能在16进制输入状态下输入
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {      //正确输入情况
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=aButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=bButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=cButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if(currentString.contains(")")){
                            return;
                        }
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=dButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if(currentString.contains(")")){
                        return;
                    }
                    if (currentString.length()<8){
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=eButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });
        fButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if(currentString.contains(")")){
                        return;
                    }
                    if (currentString.length()<8){
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=fButton.getText();
                            currentString+=text;
                            ans.setText(currentString);
                        }
                    }
                    else {
                        error=INPUT_OVERFLOW;
                        overflowError.setText("OF");
                    }
                }
            }
        });

        /*小数点按钮的事件处理*/
        DotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(error==0){
                    if (state==HEX){    //语法错误1：16进制的情况下输入小数点
                        error=INPUT_DOT_WHEN_HEX;
                        grammarError.setText("GE");
                    }
                    else {    //十进制模式
                        if (currentString==""){
                            return;
                        }
                        if(currentString.contains(")")){
                            return;
                        }
                        if (currentString.length()<8){
                            if (currentString.contains(".")){   //如果字符串中已经有了小数点就不可以再次加入
                                return;
                            }
                            if (currentString.contains("(")){
                                expression+=currentString;
                                currentString="";
                            }
                            String text=".";     //正常输入的情况
                            currentString+=text;
                            ans.setText(currentString);
                        }
                        else {           //小数点造成的溢出
                            error=INPUT_OVERFLOW;
                            overflowError.setText("OF");
                        }
                    }
                }
            }
        });

        //括号的事件处理
        LeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //左括号可能会跟在 操作符或者左括号后面  或者是负号后面
                if (error==0){
                    if (currentString.contains("(")){   //左括号前面是左括号  就是嵌套括号的情况
                        expression+=currentString;
                        currentString="";
                    }
                    if (currentString.contains(")")||isContainNum(currentString)){    //左括号前面是右括号或者是操作数  语法错误
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (currentString.contains("-")){
                        expression+=currentString;
                        currentString="";
                    }
                    String text="(";
                    currentString+=text;
                    ans.setText(currentString);
                    numOfLeftParentheses++;
                }
            }
        });
        RightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //右括号前面只可能是 右括号或者是操作数
                if (error==0){
                    if(expression==""||currentString==""){
                        return;
                    }
                    if (!notContainSymbol(currentString.substring(1))||currentString.contains("(")){    //右括号前面是左括号或者是其他操作符  语法错误
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                    }
                    if (!currentString.contains(")")){
                        if (state==DEC){
                            expression+=currentString;   //右括号和左括号不同 左括号可以理解为一个字符 右括号需要理解为一个操作符
                            currentString="";   //输入有操作符 代表前一个操作数的结束  需要把操作数置入总表达式
                        }
                        else if (state==HEX){
                            String temp=new Translation(currentString).Decimal();
                            expression+=temp;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    else {   //前面的操作数是右括号  嵌套括号
                        expression+=currentString;
                        currentString="";
                    }
                    String text=")";
                    currentString+=text;
                    ans.setText(currentString);
                    numOfRightParentheses++;
                }
            }
        });

        /*进制转换按钮 互斥显示状态信号 内部改变状态变量*/
        DECButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0||error==INPUT_DOT_WHEN_HEX){   //十进制按钮可以消除16进制的语法错误
                    //只有在当前输入框中只有数字 并且当前计算器状态是16进制 的时候才有转换成十进制
                    if (currentString.length()>0&&state==HEX&&notContainPARENTHESES(currentString)){
                        currentString=new Translation(currentString).Decimal();
                        if(currentString.length()>8){  //16进制转10进制可能出现溢出问题
                            currentString="";
                            error=OUTPUT_OVERFLOW;
                            overflowError.setText("OF");
                            return;
                        }
                        else {
                            ans.setText(currentString);
                            if (currentString.substring(0,1)=="-"){
                                //如果转换过来是负号  就要自动加一个括号
                                currentString="("+currentString+")";
                            }
                        }
                    }
                    //状态变化是一直需要执行的
                    state=DEC;
                    DECState.setText("DEC");
                    HEXState.setText("         ");

                    //16进制输入小数点产生的错误回到10进制应该消除
                    if (error==INPUT_DOT_WHEN_HEX){
                        error=0;
                        grammarError.setText("         ");
                    }
                }
            }
        });
        HEXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0||error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC||error==AND_WHEN_DEC||
                        error==XOR_WHEN_DEC||error==OR_WHEN_DEC){
                    //16进制按钮可以消除10进制的语法错误
                    //只有在当前输入框中只有数字的时候才有转换成十六进制
                    if (currentString.length()>0&&state==DEC&&notContainPARENTHESES(currentString)){
                        if (currentString.contains(".")){   //十进制小数无法转换成16进制
                            error=INPUT_DOT_WHEN_HEX;
                            grammarError.setText("GE");
                            return;
                        }
                        else { //由于8为输入数字的10进制转16进制不会出现溢出问题 所以没有溢出判断
                            currentString=new Translation(currentString).Hexadecimal();
                            ans.setText(currentString);
                        }
                    }
                    state=HEX;
                    DECState.setText("         ");
                    HEXState.setText("HEX");

                    if (error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC||error==AND_WHEN_DEC||
                            error==XOR_WHEN_DEC||error==OR_WHEN_DEC){   //如果已经触发了输入语法错误 通过转换状态应该消除错误信号
                        error=0;
                        grammarError.setText("         ");
                    }
                }
            }
        });

        /*功能按键回退一格*/
        BACKSPACEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentString.length()>0){
                    currentString=currentString.substring(0,currentString.length()-1);

                    //通过回退按钮使得当前输入文本没有溢出  就将溢出信号去除
                    if (error==INPUT_OVERFLOW&&currentString.length()<8){
                        error=0;
                        overflowError.setText("         ");
                    }

                    //这个错误会在两种情况发生 一个是16进制模式下输入了小数点 一个是10进制小数转换成16进制
                    //如果通过回退按钮删除了小数点 这个错误信号应该消失
                    if (error==INPUT_DOT_WHEN_HEX&&!currentString.contains(".")){
                        error=0;
                        grammarError.setText("         ");
                    }

                    //如果10进制情况下输入了ABCDEF可以通过删除ABCDEF去除语法错误
                    if (error==HEX_INPUT_WHEN_DEX&&notContainABCDEF(currentString)){
                        error=0;
                        grammarError.setText("         ");
                    }

                    //通过回退按钮使得当前输入文本清空 直接清除所有错误
                    if (currentString.length()==0&&grammarError.getText()!=""){
                        error=0;
                        grammarError.setText("         ");
                    }

                    if(error==0){
                        ans.setText(currentString);
                    }
                }
            }
        });
        /*功能按键清空输入栏*/
        CLCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentString="";
                ans.setText(currentString);
                expression="";

                numOfLeftParentheses=0;
                numOfRightParentheses=0;

                //清零按钮按下之后 所有错误信号和所有输入全部清空
                error=0;
                overflowError.setText("         ");
                grammarError.setText("         ");
            }
        });

        /*功能按键 加法按钮*/
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //加号前面只可能是右括号或者是数字
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (currentString.contains("(")){   //加号前面是左括号就是括号相关的错误
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式  包括数字和右括号
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //前面可能是右括号 或者是操作数   此分支处理操作数的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 前面是右括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入  在总表达式中 操作符不可以连续出现
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="+";
                    }
                }
            }
        });
        /*功能按键 减法按钮*/
        MinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //减号前面只可能是左括号和右括号或者是数字  10进制情况下可以用减号表示负数
                if (error==0){
                    if (state==DEC){
                        if (expression.length()==0&&currentString==""){
                            //减号用作负号 要么是表达式的第一项 要么就是左括号之后
                            String text="-";
                            currentString=text;
                            ans.setText(currentString);
                        }
                        else if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="-";
                            ans.setText(currentString);
                        }
                        else {
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }

                    }
                    else {
                        //16进制情况下 不允许输入减号作为负号
                        if ((expression.length()==0&&currentString=="")||currentString.contains("(")){
                            error=NEGATIVE_WHEN_HEX;
                            grammarError.setText("GE");
                            return;
                        }
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    if (!Objects.equals(expression, "")){   //由于减号可以作为负号出现在总表达式的第一个字符 所以增加了这个判断
                        String lastCharE=expression.substring(expression.length()-1);
                        if (notContainSymbol(lastCharE)&&!currentString.contains("-")){
                            expression+="-";
                        }
                    }
                }
            }
        });
        /*功能按键 乘法按钮*/
        MultipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //乘号和加号一模一样
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (currentString.contains("(")){
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (state==DEC){
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="*";
                    }
                }
            }
        });
        /*功能按键 除法按钮*/
        DiveideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (currentString.contains("(")){
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (state==DEC){
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="/";
                    }
                }
            }
        });
        /*功能按键 或运算按钮*/
        ORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //或运算和加号一模一样  只是在10进制情况下使用是语法错误
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (currentString.contains("(")){
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (state==DEC){
                        error=OR_WHEN_DEC;
                        grammarError.setText("GE");
                        return;
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="|";
                    }
                }
            }
        });
        /*功能按键 与运算按钮*/
        ANDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //与运算和加号一模一样
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (currentString.contains("(")){
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (state==DEC){
                        error=AND_WHEN_DEC;
                        grammarError.setText("GE");
                        return;
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="&";
                    }
                }
            }
        });
        /*功能按键 异或运算按钮*/
        XORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (state==DEC){
                        error=XOR_WHEN_DEC;
                        grammarError.setText("GE");
                        return;
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="^";
                    }
                }
            }
        });
        /*功能按键 移位按钮*/
        SHEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){
                        return;
                    }
                    if (state==DEC){
                        error=SHE_WHEN_DEC;
                        grammarError.setText("GE");
                        return;
                    }
                    else if (state==HEX){
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    String lastCharE=expression.substring(expression.length()-1);
                    if (notContainSymbol(lastCharE)){
                        expression+="<";
                    }
                }
            }
        });
        /*功能按键 求反码按钮*/
        a1SCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(error==0){
                    if (state==DEC){    //10进制状态下不可以使用反码功能  会返回错误状态信号
                        error=SC1_WHEN_DEC;
                        grammarError.setText("GE");
                    }
                    else if (state==HEX){
                        currentString=new Code_Trans().F_trans(currentString);
                        ans.setText(currentString);
                    }
                }
            }
        });
        /*功能按键 求补码按钮*/
        a2SCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(error==0){
                    if (state==DEC){    //10进制状态下不可以使用反码功能  会返回错误状态信号
                        error=SC2_WHEN_DEC;
                        grammarError.setText("GE");
                    }
                    else if (state==HEX){
                        currentString=new Code_Trans().B_trans(currentString);
                        ans.setText(currentString);
                    }
                }
            }
        });

        /*功能按键 等号按钮*/
        EqualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //等号前面可以是 右括号 操作数   不能是左括号 操作符
                if(error==0){
                    if (numOfRightParentheses!=numOfLeftParentheses){  //处理少括号的语法错误
                        error=MISS_PARENTHESES;
                        grammarError.setText("GE");
                        return;
                    }
                    if (currentString.contains("(")){    //括号前面是左括号运算没有结束 错误
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (!notContainSymbol(expression.substring(expression.length()-1))&&currentString==""){  //等号前面不可以是操作符
                        error=EQUAL_ERROR;
                        grammarError.setText("GE");
                        return;
                    }

                    //16进制走这条分支
                    if (state==HEX){
                        if (!currentString.contains(")")){     //前面可能是右括号 或者是操作数   此分支处理操作数的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                        }
                        else{      //此分支 前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                        }

                        System.out.println("cal前："+expression);
                        try{
                            currentString=Calculator.compute(expression);    //计算结果返回一个字符串  10进制
                        }
                        catch (ArithmeticException exception){   //算法会抛出除以0的异常对象
                            error=DIVIDE_ZERO;
                            grammarError.setText("GE");
                            return;
                        }
                        System.out.println("cal后："+currentString);

                        //16进制后处理
                        BigDecimal Up = BigDecimal.valueOf(2147483647);    //16进制上界
                        BigDecimal Down = BigDecimal.valueOf(-2147483648);    //16进制下界
                        BigDecimal tempAns1 =BigDecimal.valueOf(Double.parseDouble(currentString));
                        if (tempAns1.compareTo(Up) < 0 && tempAns1.compareTo(Down) > 0){
                            if(currentString.contains(".")){
                                //计算结果有小数  只有16进制除法会遇到这种情况  自动转换成10进制模式
                                state=DEC;
                                DECState.setText("DEC");
                                HEXState.setText("         ");
                                if (currentString.length()>9){    //对于无限小数
                                    int point=currentString.indexOf(".");
                                    int need_to_left=8-point-1;
                                    tempAns1=BigDecimal.valueOf(Double.parseDouble(currentString.substring(0,8)));
                                    if (Integer.parseInt(currentString.substring(7,8)) >=5){   //根据返回字符串的第九位判断是否需要四舍五入
                                        String plus="0.";
                                        for(int i=0;i<need_to_left-1;i++){
                                            plus+="0";
                                        }
                                        plus+="1";
                                        Double plusNum=Double.parseDouble(plus);
                                        tempAns1=tempAns1.add(BigDecimal.valueOf(plusNum));
                                        ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                        expression="";
                                    }
                                    else {
                                        ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                        expression="";
                                    }
                                }
                                else {
                                    ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                    expression="";
                                }
                            }
                            else {
                                currentString=new Translation(currentString).Hexadecimal();
                                ans.setText(currentString);
                                expression="";
                            }
                        }
                        else {
                            //输出结果溢出
                            error=OUTPUT_OVERFLOW;
                            overflowError.setText("OE");
                        }
                    }
                    else{//10进制走这条分支
                        expression+=currentString;

                        System.out.println("cal前："+expression);
                        try{
                            currentString=Calculator.compute(expression);    //计算结果返回一个浮点数
                        }
                        catch (ArithmeticException exception){
                            error=DIVIDE_ZERO;
                            grammarError.setText("GE");
                            return;
                        }
                        System.out.println("cal后："+currentString);

                        //10进制计算结果后处理
                        BigDecimal Up = BigDecimal.valueOf(99999999);    //10进制上界
                        BigDecimal Down = BigDecimal.valueOf(-9999999);    //10进制下界
                        BigDecimal tempAns1 =BigDecimal.valueOf(Double.parseDouble(currentString));
                        if (tempAns1.compareTo(Up) < 0 && tempAns1.compareTo(Down) > 0){
                            //得到的结果在10进制表示范围内
                            if (currentString.length()>9){    //对于无限小数
                                int point=currentString.indexOf(".");
                                int need_to_left=8-point-1;
                                tempAns1=BigDecimal.valueOf(Double.parseDouble(currentString.substring(0,8)));
                                if (Integer.parseInt(currentString.substring(7,8)) >=5){   //根据返回字符串的第九位判断是否需要四舍五入
                                    String plus="0.";
                                    for(int i=0;i<need_to_left-1;i++){
                                        plus+="0";
                                    }
                                    plus+="1";
                                    Double plusNum=Double.parseDouble(plus);
                                    tempAns1=tempAns1.add(BigDecimal.valueOf(plusNum));
                                    ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                    expression="";
                                }
                                else {
                                    ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                    expression="";
                                }
                            }
                            else {
                                ans.setText(currentString);
                                expression="";
                            }
                        }
                        else {
                            //输出结果溢出
                            error=OUTPUT_OVERFLOW;
                            overflowError.setText("OE");
                        }
                    }
                }
            }
        });
    }

    /*一些辅助函数 让事件处理的代码简洁一点*/
    public boolean notContainPARENTHESES(String currentString){
        return  !currentString.contains("(") &&
                !currentString.contains(")") ;

    }
    public boolean notContainABCDEF(String currentString){
        return  !currentString.contains("A") &&
                !currentString.contains("B") &&
                !currentString.contains("C") &&
                !currentString.contains("D") &&
                !currentString.contains("E") &&
                !currentString.contains("F") ;
    }
    public boolean notContainSymbol(String currentString){
        return  !currentString.contains("+") &&
                !currentString.contains("-") &&
                !currentString.contains("*") &&
                !currentString.contains("/") &&
                !currentString.contains("&") &&
                !currentString.contains("|") &&
                !currentString.contains("^") &&
                !currentString.contains("<");
    }
    public boolean isContainNum(String currentString){
        return  currentString.contains("1")||
                currentString.contains("2")||
                currentString.contains("3")||
                currentString.contains("4")||
                currentString.contains("5")||
                currentString.contains("6")||
                currentString.contains("7")||
                currentString.contains("8")||
                currentString.contains("9")||
                currentString.contains("0")||
                currentString.contains("A")||
                currentString.contains("B")||
                currentString.contains("C")||
                currentString.contains("D")||
                currentString.contains("E")||
                currentString.contains("F");
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("T1_Programmer");
        T1_Programmer window=new T1_Programmer();
        frame.setContentPane(window.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //由于OFF按钮需要把整个JFrame对象关闭掉  需要能够引用到frame  所以写在main函数中
        window.OFFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
            }
        });
    }

}
