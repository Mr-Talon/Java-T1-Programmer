import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

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
    private final int INPUT_DOT_WHEN_HEX =4;   //在16进制输入小数点
    private final int INPUT_OVERFLOW=5;     //输入溢出
    private final int OUTPUT_OVERFLOW=6;    //输出溢出
    private final int MISS_PARENTHESES=7;   //缺少括号
    private final int SC1_WHEN_DEC=8;      //在10进制的时候使用反码按钮
    private final int SC2_WHEN_DEC=9;      //在10进制的时候使用补码按钮

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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.contains("(")||currentString.contains(")")){
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
                        if(currentString.length()==1){
                            if (currentString.charAt(0) == '0'){
                                return;
                            }
                        }
                        if (currentString.contains("(")||currentString.contains(")")){
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
        });   //0只能在开头出现一次

        /*a-f数字输入的事件处理，有输入溢出判定，还有语法错误判定*/
        aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (currentString.length()<8){
                        if (state==DEC){       //对于a-f的字符 只能在16进制输入状态下输入
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {      //正确输入情况
                            if (currentString.contains("(")||currentString.contains(")")){
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
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")||currentString.contains(")")){
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
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")||currentString.contains(")")){
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
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")||currentString.contains(")")){
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
                    if (currentString.length()<8){
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")||currentString.contains(")")){
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
                    if (currentString.length()<8){
                        if (state==DEC){
                            error=HEX_INPUT_WHEN_DEX;
                            grammarError.setText("GE");
                        }
                        else {
                            if (currentString.contains("(")||currentString.contains(")")){
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
                        if (currentString.length()<8){
                            if (currentString.contains(".")){   //如果字符串中已经有了小数点就不可以再次加入
                                return;
                            }
                            if (currentString.contains("(")||currentString.contains(")")){
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
                if (error==0){
                    if (currentString.contains("(")||currentString.contains(")")){
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
                if (error==0){
                    if (currentString.contains("(")||currentString.contains(")")){    //每次只会显示一个括号
                        currentString="";
                    }
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
                if (error==0||error==INPUT_DOT_WHEN_HEX){
                    //只有在当前输入框中只有数字的时候才有转换成十进制
                    if (currentString.length()>0&&state==HEX&&notContainSymbol(currentString)){
                        currentString=new Translation(currentString).Decimal();
                        if(currentString.length()>8){
                            currentString="";
                            error=OUTPUT_OVERFLOW;
                            overflowError.setText("OF");
                        }
                        else {
                            ans.setText(currentString);
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
                if (error==0||error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC){
                    //只有在当前输入框中只有数字的时候才有转换成十六进制
                    if (currentString.length()>0&&state==DEC&&notContainSymbol(currentString)){
                        if (currentString.contains(".")){   //十进制小数无法转换成16进制
                            error=INPUT_DOT_WHEN_HEX;
                            grammarError.setText("GE");
                            return;
                        }
                        else {
                            currentString=new Translation(currentString).Hexadecimal();
                            ans.setText(currentString);
                        }
                    }
                    state=HEX;
                    DECState.setText("         ");
                    HEXState.setText("HEX");

                    if (error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC){   //如果已经触发了输入语法错误 通过转换状态应该消除错误信号
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

                    //通过回退按钮使得当前输入文本清空 直接清除所有错误
                    if (currentString.length()==0&&grammarError.getText()!=""){
                        error=0;
                        grammarError.setText("         ");
                        overflowError.setText("         ");
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
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="+";
                    }
                }
            }
        });
        /*功能按键 减法按钮*/
        MinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="-";
                    }
                }
            }
        });
        /*功能按键 等号按钮*/
        EqualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(error==0){
                    if (numOfRightParentheses!=numOfLeftParentheses){  //处理少括号的语法错误
                        error=MISS_PARENTHESES;
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

                        currentString=Calculator.compute(expression);    //计算结果返回一个字符串
//                        currentString=tempAns2.stripTrailingZeros().toPlainString();
                        currentString=new Translation(currentString).Hexadecimal();
                        ans.setText(currentString);
                        expression="";
                    }
                    else{//10进制走这条分支
                        expression+=currentString;
                        currentString=Calculator.compute(expression);    //计算结果返回一个浮点数
                        ans.setText(currentString);
                        expression="";

//                        //计算结果后处理
//                        double Up = 99999999.0;    //10进制上界
//                        double Down = -9999999.0;    //10进制下界
//                        if (tempAns1<=Up &&tempAns1>=Down){
//                            //得到的结果在10进制表示范围内
//                            if (tempAns1.toString().length()>9){    //对于无限小数
//                                BigDecimal tempAns2=BigDecimal.valueOf(tempAns1);    //将浮点数转换成 大整数类型  方便对于多余的0的处理
//                                currentString=tempAns2.stripTrailingZeros().toPlainString();  //去除多余的0
//                                int point=currentString.indexOf(".");
//                                int need_to_left=8-point-1;
//
//                            }
//                            else {
//                                BigDecimal tempAns2=BigDecimal.valueOf(tempAns1);    //将浮点数转换成 大整数类型  方便对于多余的0的处理
//                                currentString=tempAns2.stripTrailingZeros().toPlainString();  //去除多余的0
//                                ans.setText(currentString);
//                                expression="";
//                            }
//                        }
//                        else {
//                            //输出结果溢出
//                            error=OUTPUT_OVERFLOW;
//                            overflowError.setText("OE");
//                        }
                    }
                }
            }
        });
        /*功能按键 乘法按钮*/
        MultipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
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
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="/";
                    }
                }
            }
        });
        /*功能按键 或运算按钮*/
        ORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="|";
                    }
                }
            }
        });
        /*功能按键 与运算按钮*/
        ANDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
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
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="^";
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
                        error=SC1_WHEN_DEC;
                        grammarError.setText("GE");
                    }
                    else if (state==HEX){
                        currentString=new Code_Trans().B_trans(currentString);
                        ans.setText(currentString);
                    }
                }
            }
        });
        /*功能按键 移位按钮*/
        SHEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0){
                    if (expression.length()==0&&currentString.length()==0){   //表达式的最前面不应该是乘号
                        return;
                    }
                    if (state==DEC){      //十进制直接将当前字符串写入总表达式
                        expression+=currentString;
                        currentString="";
                        ans.setText(currentString);
                    }
                    else if (state==HEX){    //十六进制将当前字符串先转换成10进制 算法只处理十进制
                        if (!currentString.contains(")")){     //乘号前面可能是右括号 或者是操作数   此分支处理乘号的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{      //此分支 乘号的前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    //防止过多的符号输入
                    String lastChar=expression.substring(expression.length()-1);
                    if (notContainSymbolWithoutRight(lastChar)){
                        expression+="<";
                    }
                }
            }
        });
    }

    public boolean notContainSymbol(String currentString){
        return  !currentString.contains("+") &&
                !currentString.contains("-") &&
                !currentString.contains("*") &&
                !currentString.contains("/") &&
                !currentString.contains("(") &&
                !currentString.contains(")") &&
                !currentString.contains("&") &&
                !currentString.contains("|") &&
                !currentString.contains("^") &&
                !currentString.contains("<<") &&
                !currentString.contains(">>");
    }

    public boolean notContainSymbolWithoutRight(String currentString){
        return  !currentString.contains("+") &&
                !currentString.contains("-") &&
                !currentString.contains("*") &&
                !currentString.contains("/") &&
                !currentString.contains("(") &&
                !currentString.contains("&") &&
                !currentString.contains("|") &&
                !currentString.contains("^") &&
                !currentString.contains("<<") &&
                !currentString.contains(">>");
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
