package usung.com.mqttclient.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能说明<br>
 * 此类名称和其他包中的类名冲突，在使用是容易混淆，顾更名为helper  2015-01-06
 *
 * @author lijian
 */
public class StringHelper {
    static DecimalFormat df = new DecimalFormat("#.00");

    public static boolean isEmpty(String str) {
        if (null == str || "".compareTo(str) == 0 || "null".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        if (null == str || "".compareTo(str) == 0 || "null".equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * 转换为String
     *
     * @param obj
     * @return
     */
    public static String toStrNotNull(Object obj) {
        if (null == obj || "null".equals(obj)) {
            return "";
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 转换为int  double float类型的字符串
     *
     * @param obj
     * @return
     */
    public static String toIntNotNull(Object obj) {
        if (null == obj || "null".equals(obj)) {
            return "0";
        } else {
            return String.valueOf(obj);
        }
    }

    public static String replace(String inString, String oldPattern,
                                 String newPattern) {
        if (inString == null) {
            return null;
        }
        if (oldPattern == null || newPattern == null) {
            return inString;
        }
        StringBuffer sbuf = new StringBuffer();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));

        return sbuf.toString();
    }


    /**
     * 数字字符串转ASCII码字符串
     *
     * @return ASCII字符串
     */
    public static String StringToAsciiString(String content) {
        String result = "";
        int max = content.length();
        for (int i = 0; i < max; i++) {
            char c = content.charAt(i);
            String b = Integer.toHexString(c);
            result = result + b;
        }
        return result;
    }

    /**
     * 十六进制字符串装十进制
     *
     * @param hex 十六进制字符串
     * @return 十进制数值
     */
    public static int hexStringToAlgorism(String hex) {
        hex = hex.toUpperCase();
        int max = hex.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = hex.charAt(i - 1);
            int algorism = 0;
            if (c >= '0' && c <= '9') {
                algorism = c - '0';
            } else {
                algorism = c - 55;
            }
            result += Math.pow(16, max - i) * algorism;
        }
        return result;
    }

    /**
     * 十六转二进制
     *
     * @param hex 十六进制字符串
     * @return 二进制字符串
     */
    public static String hexStringToBinary(String hex) {
        hex = hex.toUpperCase();
        String result = "";
        int max = hex.length();
        for (int i = 0; i < max; i++) {
            char c = hex.charAt(i);
            switch (c) {
                case '0':
                    result += "0000";
                    break;
                case '1':
                    result += "0001";
                    break;
                case '2':
                    result += "0010";
                    break;
                case '3':
                    result += "0011";
                    break;
                case '4':
                    result += "0100";
                    break;
                case '5':
                    result += "0101";
                    break;
                case '6':
                    result += "0110";
                    break;
                case '7':
                    result += "0111";
                    break;
                case '8':
                    result += "1000";
                    break;
                case '9':
                    result += "1001";
                    break;
                case 'A':
                    result += "1010";
                    break;
                case 'B':
                    result += "1011";
                    break;
                case 'C':
                    result += "1100";
                    break;
                case 'D':
                    result += "1101";
                    break;
                case 'E':
                    result += "1110";
                    break;
                case 'F':
                    result += "1111";
                    break;
            }
        }
        return result;
    }

    /**
     * ASCII码字符串转数字字符串
     *
     * @return 字符串
     */
    public static String AsciiStringToString(String content) {
        String result = "";
        int length = content.length() / 2;
        for (int i = 0; i < length; i++) {
            String c = content.substring(i * 2, i * 2 + 2);
            int a = hexStringToAlgorism(c);
            char b = (char) a;
            String d = String.valueOf(b);
            result += d;
        }
        return result;
    }

    /**
     * 将十进制转换为指定长度的十六进制字符串
     *
     * @param algorism  int 十进制数字
     * @param maxLength int 转换后的十六进制字符串长度
     * @return String 转换后的十六进制字符串
     */
    public static String algorismToHEXString(int algorism, int maxLength) {
        String result = "";
        result = Integer.toHexString(algorism);

        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return patchHexString(result.toUpperCase(), maxLength);
    }

    /**
     * 字节数组转为普通字符串（ASCII对应的字符）
     *
     * @param bytearray byte[]
     * @return String
     */
    public static String bytetoString(byte[] bytearray) {
        String result = "";
        char temp;

        int length = bytearray.length;
        for (int i = 0; i < length; i++) {
            temp = (char) bytearray[i];
            result += temp;
        }
        return result;
    }

    /**
     * 二进制字符串转十进制
     *
     * @param binary 二进制字符串
     * @return 十进制数值
     */
    public static int binaryToAlgorism(String binary) {
        int max = binary.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = binary.charAt(i - 1);
            int algorism = c - '0';
            result += Math.pow(2, max - i) * algorism;
        }
        return result;
    }

    /**
     * 十进制转换为十六进制字符串
     *
     * @param algorism int 十进制的数字
     * @return String 对应的十六进制字符串
     */
    public static String algorismToHEXString(int algorism) {
        String result = "";
        result = Integer.toHexString(algorism);

        if (result.length() % 2 == 1) {
            result = "0" + result;

        }
        result = result.toUpperCase();

        return result;
    }

    /**
     * HEX字符串前补0，主要用于长度位数不足。
     *
     * @param str       String 需要补充长度的十六进制字符串
     * @param maxLength int 补充后十六进制字符串的长度
     * @return 补充结果
     */
    static public String patchHexString(String str, int maxLength) {
        String temp = "";
        for (int i = 0; i < maxLength - str.length(); i++) {
            temp = "0" + temp;
        }
        str = (temp + str).substring(0, maxLength);
        return str;
    }

    /**
     * 将一个字符串转换为int
     *
     * @param s          String 要转换的字符串
     * @param defaultInt int 如果出现异常,默认返回的数字
     * @param radix      int 要转换的字符串是什么进制的,如16 8 10.
     * @return int 转换后的数字
     */
    public static int parseToInt(String s, int defaultInt, int radix) {
        int i = 0;
        try {
            i = Integer.parseInt(s, radix);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    /**
     * 将一个十进制形式的数字字符串转换为int
     *
     * @param s          String 要转换的字符串
     * @param defaultInt int 如果出现异常,默认返回的数字
     * @return int 转换后的数字
     */
    public static int parseToInt(String s, int defaultInt) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    /**
     * 十六进制串转化为byte数组
     *
     * @return the array of byte
     */
    public static final byte[] hex2byte(String hex)
            throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static final String byte2hex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 检查密码格式
     *
     * @param passwd
     * @return
     */
    public static boolean checkPWD(String passwd) {
        char item;
        boolean charRight = false;
        for (int i = 0; i < passwd.length(); i++) {
            item = passwd.charAt(i);
            if ((item >= '0' && item <= '9') || (item >= 'a' && item <= 'z')
                    || (item >= 'A' && item <= 'Z')) {
                charRight = true;
            } else {
                charRight = false;
            }
        }
        if (charRight == true) {
            return true;
        }
        return charRight;
    }

    /**
     * 字符串为null时转为0
     */
    public static int null2Zero(Integer it) {
        if (null == it) {
            return 0;
        } else {
            try {
                return it;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    /**
     * 验证信用卡号是否符合规则
     *
     * @param creditCardNO
     * @return
     */
    public static boolean creditCardVailid(String creditCardNO) {
        Pattern pattern = Pattern.compile("^[0-9]{16}$");
        Matcher m = pattern.matcher(creditCardNO);
        return m.matches();
    }

    /**
     * 保留2位小数
     *
     * @param data
     * @return
     */
    public static String formatDecimalData(Double data) {
        if (data == null) {
            return "";
        } else {
            if (df == null) {
                df = new DecimalFormat();
            }
            return df.format(data);
        }
    }


    /**
     * 保留2位小数
     *
     * @param data
     * @return
     */
    public static String formatDecimalData(String data) {
        if (data == null) {
            return "";
        } else {
            if (df == null) {
                df = new DecimalFormat();
            }
            return df.format(Double.valueOf(data));
        }
    }

    /**
     * 保留n位小数
     */
    public static String formatDecimalData(double data, int n) {
        StringBuilder builder = new StringBuilder("0.");
        for (int i = 0; i < n; i++) {
            builder.append("0");
        }
        DecimalFormat df = new DecimalFormat(builder.toString());
        return df.format(Double.valueOf(data));
    }

    /**
     * 校验登录名
     *
     * @param name
     * @return
     */
    public static boolean checkLoginName(String name) {
        String regex = "^[a-zA-Z]\\w*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    /**
     * 校验密码
     *
     * @param passWord
     * @return
     */
    public static boolean checkPassWord(String passWord) {
        return passWord.length() >= 6;
    }

    /**
     * 校验登录名*(登录名只能是数字或英文字母)
     *
     * @param name
     * @return
     */
    public static boolean checkLoginNameTwo(String name) {
        String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    /**
     * 判断是Jsonobject还是jsonarray或者不是json对象
     */
    public enum JSON_TYPE {
        /**
         * JSONObject
         */
        JSON_TYPE_OBJECT,
        /**
         * JSONArray
         */
        JSON_TYPE_ARRAY,
        /**
         * 不是JSON格式的字符串
         */
        JSON_TYPE_ERROR
    }

    /***
     * 获取JSON类型 判断规则 判断第一个字母是否为{或[ 如果都不是则不是一个JSON格式的文本
     * 校验是否是json对象（包括JSONObject、JSONArray）
     *
     * @param str
     * @return
     */
    public static JSON_TYPE getJSONType(String str) {
        if (TextUtils.isEmpty(str)) {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }

        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];

        if (firstChar == '{') {
            return JSON_TYPE.JSON_TYPE_OBJECT;
        } else if (firstChar == '[') {
            return JSON_TYPE.JSON_TYPE_ARRAY;
        } else {
            return JSON_TYPE.JSON_TYPE_ERROR;
        }
    }

    /**
     * 传一个list进来，过滤掉list里相同的元素，只保留一个  StringHelper.getOnlyoneList(hasSame(list));
     */
    public static ArrayList<Object> getOnlyoneList(HashSet<Object> set) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        Iterator<Object> it = set.iterator();
        while (it.hasNext()) {
            Object object = it.next();
            arrayList.add(object);
        }
        return arrayList;
    }

    public static HashSet<Object> hasSame(List<? extends Object> list) {
        if (null == list) {
            return new HashSet<Object>();
        } else {
            return new HashSet<Object>(list);
        }
    }

    /**
     * 剪切并拼接“...”到字符串
     *
     * @param start  截取起始
     * @param end    截取结束
     * @param length 期望限定的字符串长度
     * @param str    待操作的字符串
     * @return
     */
    public static String subAndAppndString(int start, int end, int length, String str) {
        String tempStr = "";
        if (isNotEmpty(str)) {
            if (str.length() < length) {
                tempStr = str;
            } else {
                tempStr = str.substring(start, end) + "...";
            }
        }
        return tempStr;
    }

    /**
     * 数字转中文，仅支持1-5
     *
     * @return
     */
    public static String numTOChinese(int tempRate) {
        String tempStr = "";
        switch (tempRate) {
            case 1:
                tempStr = "一";
                break;
            case 2:
                tempStr = "二";
                break;
            case 3:
                tempStr = "三";
                break;
            case 4:
                tempStr = "四";
                break;
            case 5:
                tempStr = "五";
                break;
            default:
                break;
        }
        return tempStr;
    }

    /**
     * 清除指定的字符串
     *
     * @param tempString  待操作的字符串
     * @param clearString 需要清除的字符
     * @return
     */
    public static String ClearString(String tempString, String clearString) {
        String str = "";
        if (tempString.contains(clearString)) {
            str = tempString.replace(clearString, "").trim();
        } else {
            str = tempString;
        }
        return str;
    }

    /**
     * 改变部分字体的颜色
     *
     * @param context     上下文
     * @param content     需要改变的内容
     * @param colorResid  颜色值
     * @param startOffset 开始位置
     * @param endOffset   结束位置
     * @return 改变后的样式
     */
    public static SpannableStringBuilder changePartTextColor(Context context, String content, int colorResid, int startOffset, int endOffset) {
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorResid)), startOffset, endOffset, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 改变部分文字大小
     *
     * @param context     上下文
     * @param content     内容
     * @param textSize    大小
     * @param startOffset 开始位置
     * @param endOffset   结束位置
     * @return 改变后的样式
     */
    public static SpannableStringBuilder changePartTextSize(Context context, String content, int textSize, int startOffset, int endOffset) {
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new AbsoluteSizeSpan(textSize, true), startOffset, endOffset, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 截取指定字符并替换成”*“
     *
     * @param str_Old
     * @param start
     * @param end
     */
    public static String interceptAndReplaceChar(String str_Old, int start, int end) {
        if (TextUtils.isEmpty(str_Old) || str_Old.length() < end) {
            return "";
        }
        //字符串截取
        String str_Intercept = str_Old.substring(start, end);
        //字符串替换
        String str_New = str_Old.replaceFirst(str_Intercept, "****");
        return str_New;
    }

    /**
     * 校验手机号
     *
     * @param phoneNO
     * @return
     */
    public static boolean phoneNOAvailable(String phoneNO) {
        if (isEmpty(phoneNO)) {
            return false;
        } else if (phoneNO.charAt(0) != '1') {
            return false;
        } else if (phoneNO.length() != 11) {
            return false;
        } else {
            return true;
        }
    }

    //得到color span
    public static CharSequence setColorSpanString(String span, String text) {
        if (text == null) {
            return null;
        }
        if (text.contains(span)) {
            SpannableString string = new SpannableString(text);
            int start = text.indexOf(span);
            int end = start + span.length();
            string.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return string;
        } else {
            return text;
        }
    }

    public static String returnTargetIfNull(String string, String target) {
        return TextUtils.isEmpty(string) ? target : string;
    }

    public static String returnNoIfNull(String string) {
        return returnTargetIfNull(string, "无");
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0 && !s.endsWith(".")) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
