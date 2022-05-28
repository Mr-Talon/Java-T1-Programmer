import java.math.BigDecimal;
import java.text.DecimalFormat;

/*
16进制,10进制的反码，补码转换
* */
public class Code_Trans {
    String F_trans(String s) {//反码
        String str_bin = BintoHex.hexStr2BinStr(s);
        StringBuilder str_bin_0 = new StringBuilder(str_bin);
        while(str_bin_0.length() < 32)
        {
            StringBuilder ST = new StringBuilder();
            ST = ST.append('0').append(str_bin_0);
            str_bin_0 = ST;
        }
        str_bin = str_bin_0.toString();
        char[] str = str_bin.toCharArray();//包括符号位和数值位
        for (int i = 0; i < str.length; i++)//转成反码，数值位取反
        {
            if (str[i] == '0')
                str[i] = '1';
            else
                str[i] = '0';
        }
        //二进制反码转十六进制
        String binaryStr = new String(str);//更新后的字符数组再转回字符串
        String hexStr = "";
        String[] strArr = BintoHex.binaryArray;
        boolean flg = true; //前面全是0
        for (int i = 0; i <= 28; i += 4) {
            String tmp = binaryStr.substring(i, i + 4);
            for (int j = 0; j < strArr.length; j ++ )
                if (tmp.equals(strArr[j])) {
                    if (j == 0 && flg) continue;
                    else {
                        hexStr += Integer.toHexString(j);
                        if(j != 0) flg = false;
                    }
                }
        }
        return hexStr.toUpperCase();

    }

    String B_trans(String s) {
        //补码为反码+1
        String str_bin=BintoHex.hexStr2BinStr(s);
        StringBuilder str_bin_0=new StringBuilder(str_bin);
        while(str_bin_0.length() < 32)
        {
            StringBuilder ST=new StringBuilder();
            ST=ST.append('0').append(str_bin_0);
            str_bin_0=ST;
        }
        str_bin=str_bin_0.toString();
        char[] str = str_bin.toCharArray();//字符串转换成字符数组好赋值

        for (int i = 0; i < str.length; i++)//转成反码，数值位取反
        {
            if (str[i] == '0')
                str[i] = '1';
            else
                str[i] = '0';
        }


        int index = -1;
        int flag = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0')
                index = i;//找到最后一个0
        }
        if (index != -1)//整体不用进位
        {
            str[index] = '1';
            for (int i = index + 1; i < str.length; i++) {
                str[i] = '0';
            }
        } else {//整体需要进位即1……11这种情况
            flag = 1;
            for (int i = 0; i < str.length; i++) {
                str[i] = '0';
            }
        }
        if (flag == 0)//整体没有进位，用原始字符数组
        {
            String binaryStr = new String(str);// 更新后的字符数组再转回字符串
            String hexStr = "";
            String[] strArr = BintoHex.binaryArray;
            boolean flg = true; //二进制前面全是0
            for (int i = 0; i <= 28; i += 4) {
                String tmp = binaryStr.substring(i, i + 4);
                for (int j = 0; j < strArr.length; j ++ )
                    if (tmp.equals(strArr[j])) {
                        if (j == 0 && flg) continue;
                        else {
                            hexStr += Integer.toHexString(j);
                            if(j != 0) flg = false;
                        }
                    }
            }
            return hexStr.toUpperCase();
        } else {//整体有进位，唯一的：+0
            return "0";}
    }
}


