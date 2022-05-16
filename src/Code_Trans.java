/*
16进制,10进制的反码，补码转换
* */
public class Code_Trans {
    String H_D_trans(String s) {
        String x = "反码/补码按钮";
        String y = "16进制/10进制";
        String str_bin = "";//二进制字符串
        String un_str = "";
        long n;//10进制数字
        if (y == "16进制") {
            char[] flag_str = s.toCharArray();//判断正负
                if (flag_str[0] == '-')//负数的情况
                {
                    for (int k = 0; k < s.length(); k++) {//把数值位1~9,A~F提取出来，使成为无符号数
                        if (s.charAt(k) >= 48 && s.charAt(k) <= 57 || s.charAt(k) >= 65 && s.charAt(k) <= 70) {
                            un_str += s.charAt(k);
                        }
                    }
                    n = Integer.parseInt(un_str,16);//16进制转10进制
                    while (n != 0) {
                        str_bin = n % 2 + str_bin;
                        n /= 2;
                    }
                    if (x == "反码") {
                        char[] str = str_bin.toCharArray();//无符号 这里是数值位,只是把字符串转换成字符数组好赋值
                        for (int i = 0; i < str.length; i++)//转成反码，数值位取反
                        {
                            if (str[i] == 0)
                                str[i] = 1;
                            else
                                str[i] = 0;
                        }
                        //二进制反码转十六进制
                        String binaryStr = new String(str);//更新后的字符数组再转回字符串
                        if (binaryStr == null || binaryStr.equals("") || binaryStr.length() % 4 != 0) {
                            return null;
                        }
                        StringBuffer sbs = new StringBuffer();
                        // 二进制字符串是4的倍数，所以四位二进制转换成一位十六进制
                        for (int i = 0; i < binaryStr.length() / 4; i++) {
                            String subStr = binaryStr.substring(i * 4, i * 4 + 4);
                            String hexStr = Integer.toHexString(Integer.parseInt(subStr, 2));
                            sbs.append(hexStr);
                        }

                        return "-"+sbs.toString();
                    }
                    //补码为反码+1
                    if (x == "补码") {
                        char[] str = str_bin.toCharArray();//无符号 这里是数值位
                        for (int i = 0; i < str.length; i++)//转成反码，数值位取反
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
                            if (binaryStr == null || binaryStr.equals("") || binaryStr.length() % 4 != 0) {
                                return null;
                            }
                            StringBuffer sbs = new StringBuffer();
                            // 二进制字符串是4的倍数，所以四位二进制转换成一位十六进制
                            for (i = 0; i < binaryStr.length() / 4; i++) {
                                String subStr = binaryStr.substring(i * 4, i * 4 + 4);
                                String hexStr = Integer.toHexString(Integer.parseInt(subStr, 2));
                                sbs.append(hexStr);
                            }

                            return "-"+sbs.toString();
                        } else {//整体有进位，唯一的：+0
                            return "+0";
                        }
                    }
                }
                else{//是正数，原码反码补码相同
                    if (x == "反码") {
                        return s;//反码是原码本身（十六进制->十进制->二进制->反码->十六进制（本身））
                    }
                    if (x == "补码") {
                        return s;//补码是原码本身（十六进制->十进制->二进制->反码->补码->十六进制（本身））
                    }
                }

            }
            if (y == "10进制") {
                char[] flag_str = s.toCharArray();//判断正负
                    if (flag_str[0] == '-')//负数的情况
                    {
                        for (int k = 0; k < s.length(); k++) {//把数值位提取出来，使成为无符号数

                            if (s.charAt(k) >= 48 && s.charAt(k) <= 57) {

                                un_str += s.charAt(k);
                            }
                        n = Integer.parseInt(un_str);
                        while (n != 0) {
                            str_bin = n % 2 + str_bin;
                            n /= 2;
                        }
                        // 二进制变成反码，因为已经判定是负的，所以下面直接对数值位操作即可
                        if (x == "反码") {
                            char[] str = str_bin.toCharArray();//无符号 这里是数值位，字符串转化为字符数组
                            for (int i = 0; i < str.length; i++)//转成反码，数值位取反
                            {
                                if (str[i] == 0)
                                    str[i] = 1;
                                else
                                    str[i] = 0;
                            }
                            //反码转十进制
                            String str2 = new String(str);//更新后的字符数组再转回字符串
                            n = Integer.parseInt(str2, 2);//字符串转成10进制数字
                            String str_DEC = String.valueOf(n);
                            return "—" + str_DEC;
                        }
                        //补码为反码+1
                        if (x == "补码") {
                            char[] str = str_bin.toCharArray();//无符号。数值位
                            for (int i = 0; i < str.length; i++)//转成反码，符号位不变，数值位取反
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
                            } else {//整体需要进位即1……11这种情况，数值位全为0,+1之后为唯一的数值：+0
                                flag = 1;
                                for (i = 0; i < str.length; i++) {
                                    str[i] = '0';
                                }
                            }
                            //补码转十进制
                            if (flag == 0)//整体没有进位，用原始字符数组
                            {
                                String str2 = new String(str);//更新后的字符数组再转回字符串
                                n = Integer.parseInt(str2, 2);//字符串转成10进制数字
                                String str_DEC = String.valueOf(n);
                                return "-" + str_DEC;
                            } else {//整体有进位，只有一种情况：+0
                                return "+0";
                            }
                        }
                    }
                    }else{//是正数，原码反码补码相同
                        if (x == "反码") {
                            return s;//反码是原码本身（十进制->二进制->反码->十进制（本身））
                        }
                        if (x == "补码") {
                            return s;//补码是原码本身（十进制->二进制->补码->十进制（本身））
                             }
                    }
        }
    return s;//没什么作用，为了语法不出错加的，在前面就已经返回string了，到不了这里
    }
}



