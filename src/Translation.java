import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.*;
public class Translation {
    String s;
    public Translation(String s){
        this.s=s;
    };
    public String Decimal() //十六进制转十进制
    {
        if(s.length()<8) //判断十六进制代表正负数
        { long num = Long.parseLong(s, 16);//十六进制代表正数
        String ss = String.valueOf(num);
        return ss;
        }
        else //十六进制代表负数或者正数
        {
            char firstCharacter = s.charAt(0);
            if(firstCharacter>'7')//当十六进制首字符大于’7‘是负数
            {
                String ss=BintoHex.hexStr2BinStr(s);//调用BintoHEX方法获得二进制字符串
                BigInteger bi = new BigInteger(ss, 2);
                int final1=bi.intValue();
                return String.valueOf(final1);
            }
            else{
                long num = Long.parseLong(s, 16);//正数调用parseLong方法
                String ss = String.valueOf(num);
                return s;
            }

        }
    }
    public String Hexadecimal() {
        char array[]=s.toCharArray();
        BigInteger bi = new BigInteger(s);
        byte[] byteInteger = bi.toByteArray();//调用BigInteger方法获取十进制对应二进制数组
        String ss=BintoHex.bin2HexStr(byteInteger);
        if(ss.length()<8&&array[0]=='-')
        {
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < 8-ss.length(); i++) {//补足十六进制F数量
                sb.append("F");
            }
            return sb+ss;
        }
        char array1[]=ss.toCharArray();
        if(array1[0]=='0') {String strNew = ss.substring(1, ss.length());//删除转换后字符串首字符'o'
        return strNew;}
        return ss;
    }
    /*public static void main(String[] args) {
        Translation tr=new Translation("121231111");    //调试主函数
        String s= tr.Hexadecimal();
        System.out.println(s);

    }*/

}
