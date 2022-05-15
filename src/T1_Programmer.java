import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class T1_Programmer {
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

    private final int HEX_INPUT_WHEN_DEX =3;
    private final int INPUT_DOT_WHEN_HEX =4;
    private final int INPUT_OVERFLOW=5;
    private final int OUTPUT_OVERFLOW=6;

    private String expression="";   //总表达式
    private String currentString="";   //当前需要展示在JLabel ans上字符串


    public T1_Programmer() {
        /*1-9数字输入的事件处理，有输入溢出判定*/
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a1Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a2Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a3Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a4Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a5Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a6Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a7Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a8Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    String text=a9Button.getText();
                    currentString+=text;
                    ans.setText(currentString);
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });

        /*进制转换按钮 互斥显示状态信号 内部改变状态变量*/
        DECButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state=DEC;
                DECState.setText("DEC");
                HEXState.setText("         ");
            }
        });   //还差进制转换
        HEXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state=HEX;
                DECState.setText("         ");
                HEXState.setText("HEX");
            }
        });   //还差进制转换

        /*a-f数字输入的事件处理，有输入溢出判定，还有语法错误判定*/
        aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=aButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=bButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=cButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=dButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=eButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
        fButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentString.length()<8){
                    if (state==DEC){
                        error=HEX_INPUT_WHEN_DEX;
                    }
                    else {
                        String text=fButton.getText();
                        currentString+=text;
                        ans.setText(currentString);
                    }
                }
                else {
                    error=INPUT_OVERFLOW;
                }
            }
        });
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
