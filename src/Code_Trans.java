/*
16进制,10进制的反码，补码转换
* */
public class Code_Trans {
    String F_trans(String s) {//反码
        String str_bin;//二进制字符串
        long n;//10进制数字
            n = Integer.parseInt(s, 16);//16进制转10进制
            str_bin=Long.toBinaryString(n);
            char[] str = str_bin.toCharArray();//包括符号位和数值位
        if(str[0]=='1'){
            for (int i = 1; i < str.length; i++)//转成反码，数值位取反
            {
                if (str[i] == 0)
                    str[i] = 1;
                else
                    str[i] = 0;
            }
            //二进制反码转十六进制
            String binaryStr = new String(str);//更新后的字符数组再转回字符串
            n= Integer.parseInt(binaryStr, 2);//2进制转回10进制
            String sbs;
            sbs=Long.toHexString(n);
            return sbs;
        }
         else {//正数,反码是本身
            return s;}
    }

    String B_trans(String s) {
        //补码为反码+1
        String str_bin;//二进制字符串
        long n;//10进制数字
            n = Integer.parseInt(s, 16);//16进制转10进制
            str_bin=Long.toBinaryString(n);
            char[] str = str_bin.toCharArray();//字符串转换成字符数组好赋值
        if(str[0]=='1'){//负数
            for (int i = 1; i < str.length; i++)//转成反码，数值位取反
            {
                if (str[i] == 0)
                    str[i] = 1;
                else
                    str[i] = 0;
            }
            int index = -1;
            int i, flag = 0;
            for (i = 0; i < str.length; i++) {
                if (str[i] == '0')
                    index = i;//找到最后一个0
            }
            if (index != -1)//整体不用进位
            {
                str[index] = '1';
                for (i = index + 1; i < str.length; i++) {
                    str[i] = '0';
                }
            } else {//整体需要进位即1……11这种情况
                flag = 1;
                for (i = 0; i < str.length; i++) {
                    str[i] = '0';
                }
            }
            if (flag == 0)//整体没有进位，用原始字符数组
            {
                String binaryStr = new String(str);//更新后的字符数组再转回字符串
                n= Integer.parseInt(binaryStr, 2);//2进制转回10进制
                String sbs;
                sbs=Long.toHexString(n);
                return sbs;
            } else {//整体有进位，唯一的：+0
                return "+0";}
        } else {//正数，补码是本身
            return s;}
    }
}