public class BintoHex {
        private static String hexStr="0123456789ABCDEF";
public static String[]binaryArray=
        {"0000","0001","0010","0011",
        "0100","0101","0110","0111",
        "1000","1001","1010","1011",
        "1100","1101","1110","1111"};
public static String bytes2BinStr(byte[]bArray){

        String outStr="";
        int pos=0;
        for(byte b:bArray){
        //高四位
        pos=(b&0xF0)>>4;
        outStr+=binaryArray[pos];
        //低四位
        pos=b&0x0F;
        outStr+=binaryArray[pos];
        }
        return outStr;
        }

/**
 *
 * @param bytes
 * @return 将二进制数组转换为十六进制字符串  2-16
 */
public static String bin2HexStr(byte[]bytes){

        String result="";
        String hex="";
        for(int i=0;i<bytes.length;i++){
        //字节高4位
        hex=String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
        //字节低4位
        hex+=String.valueOf(hexStr.charAt(bytes[i]&0x0F));
        result+=hex;  //+" "
        }
        return result;
        }

/**
 *
 * @param hexString
 * @return 将十六进制转换为二进制字节数组   16-2
 */
public static byte[]hexStr2BinArr(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len=hexString.length()/2;
        byte[]bytes=new byte[len];
        byte high=0;//字节高四位
        byte low=0;//字节低四位
        for(int i=0;i<len;i++){
        //右移四位得到高位
        high=(byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
        low=(byte)hexStr.indexOf(hexString.charAt(2*i+1));
        bytes[i]=(byte)(high|low);//高地位做或运算
        }
        return bytes;
        }

public static String hexStr2BinStr(String hexString){
        return bytes2BinStr(hexStr2BinArr(hexString));
        }
        public static void main(String[] args) {
                String s= hexStr2BinStr("2");
                System.out.println(s);
        }
}

