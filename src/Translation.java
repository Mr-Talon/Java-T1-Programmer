import java.util.Scanner;
import java.util.*;
public class Translation {
    String s;
    public Translation(String s){
        this.s=s;
    };
    public String Decimal() {

        long num = Long.parseLong(s, 16);
        String ss = String.valueOf(num);
        return ss;
    }
    public String Hexadecimal() {
        int n=Integer.parseInt(s);
        String ss="";
        char c=' ';
        if(n==0) {
            ss="0";
        }
        int m;
        while(n!=0) {
            m=n%16;
            if(m/10==1)
                c=(char)(65+(m%10));
            else if(m/10==0)
                c=(char)(48+m);
            ss=c+ss;
            n=n/16;
        }
        return ss;
    }
}