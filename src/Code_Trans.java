
/*
16进制,10进制的反码，补码转换
* */
public class Code_Trans {
    public static String formatBin(String oldBin) { //将不满32位的二进制字符串添加前导0到32位
        String zeros = "";
        for (int i = 0; i < 32 - oldBin.length(); i++)
            zeros += "0";
        return zeros + oldBin;
    }
    public static String hexToBin(String hex) {
        /*
            由于 Integer.parseInt(String s, int radix)函数在基数radix为16
            即将16进制字符串转为32位整型时，会保留符号位,
            只计算31位无符号整型,范围为(0x00000000 ~ 0x7fffffff)
            即(0 ~ 2147483647),无法表示负数
            若要输入负数16进制,需要单独在前字符串加一个"-"表示负数
            但是我们这里需要输入的是带符号位的十六进制字符串,
            而我们的Translation类帮我们实现了这一方法
         */
        int dec = Integer.parseInt(new Translation(hex).Decimal());//Decimal()返回的是字符串，范围在32位int内，可以转为int
        return Integer.toBinaryString(dec); //toBinaryString方法返回int的补码形式，带符号位
    }
    public static String binToHex(String bin) { //二进制字符串转小写16进制字符串
        String hex = "";
        String[] strArr = BintoHex.binaryArray;
        boolean flg = true; //前面全是0
        for (int i = 0; i <= 28; i += 4) {
            String tmp = bin.substring(i, i + 4);
            for (int j = 0; j < strArr.length; j++)
                if (tmp.equals(strArr[j])) {
                    if (j == 0 && flg) continue;
                    else {
                        hex += Integer.toHexString(j);
                        if(j != 0) flg = false;
                    }
                }
        }

        return hex.equals("") ? "0" : hex; //全是0时hex是空字符串
    }
    String F_trans(String s) {//反码
        String str_bin = hexToBin(s); //转为二进制字符串
        str_bin = formatBin(str_bin); //标准化为32位二进制
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
        String hexStr = binToHex(binaryStr);//二进制字符串转十六进制字符串
        return hexStr.toUpperCase();//大写
    }

    String B_trans(String s) {
        //补码为反码+1
        String fHex = F_trans(s); //先变为反码16进制
        String str_bin = hexToBin(fHex); //后转2进制
        str_bin = formatBin(str_bin); //标准化为32位
        char[] str = str_bin.toCharArray();//字符串转换成字符数组好赋值
        // +1操作
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
            String hexStr = binToHex(binaryStr);//二进制字符串转十六进制字符串
            return hexStr.toUpperCase();//大写
        } else {//整体有进位，唯一的：+0
            return "0";
        }
    }
}


