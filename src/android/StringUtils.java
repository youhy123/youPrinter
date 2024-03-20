package org.apache.cordova.youprinter;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: [字符串工具类] </p>
 * <p>Description: [系统中常用的字符串处理方法]</p>
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * @author <a href="mailto: [wangzhuo-neu@neusoft.com]">王卓</a>
 * @version $Revision$
 * @update [修改人] [修改时间]
 */
public class StringUtils {

    /**
     * <p>Discription:判读字符串是否为空，当字符串为【null或“”或 “null”】时返回true</p>
     *
     * @param str
     * @return
     * @author 王卓 2013-1-17
     * @update [修改人] [修改时间] [变更描述]
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }
        str = str.trim();
        return !(str.length() > 0 && !"null".equalsIgnoreCase(str) && !"-".equalsIgnoreCase(str));
    }

    /**
     * <p>Discription:判读字符串是否只有字母或数字，只有的情况下返回true，其他情况下返回false</p>
     *
     * @param str
     * @return boolean
     * @author 苑鹏 2014-4-15
     * @update [修改人] [修改时间] [变更描述]
     */
    public static boolean isNumOrChar(String str) {
        int j = 0;
        boolean ret = false;
        if (!isEmpty(str)) {
            // 循环判断各个字节
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') ||
                        (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ||
                        (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
                    j = j + 1;
                }
            }
            // 判断长度是否一致
            if (j == str.length()) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * <p>Discription:如果字符串为null返回空字符串</p>
     *
     * @param str
     * @return
     * @author 苑鹏 2014-4-9
     * @update [修改人] [修改时间] [变更描述]
     */
    public static String nullToSpace(String str) {
        if (!isEmpty(str)){
            return str;
        }else {
            return "";
        }
    }
    /**
     * <p>Discription:去掉字符串首个是，的符号</p>
     *
     * @param str
     * @return
     * @author 苑鹏 2014-4-9
     * @update [修改人] [修改时间] [变更描述]
     */
    public static String deleteDot(String str) {

        String first="";
        String end="";
        if(str.length()>0){
            first = str.substring(0,1);
            end=str.substring(1);
        }
        if(first.equals(",")){
            str =end;
        }else{
            str =first+end;
        }
       return str;
    }
    /**
     * <p>Discription:去掉字符串后边多余的逗号</p>
     *
     * @param str
     * @return
     * @author 王卓 2012-9-27
     * @update [修改人] [修改时间] [变更描述]
     */
    public static String trimLastComma(String str) {

        if (null == str) {
            return str;
        }
        str = str.trim();
        int length = str.length();
        if (0 == length) {
            return str;
        }
        if (str.endsWith(",") || str.endsWith("，")) {
            return str.substring(0, length - 1);
        } else {
            return str;
        }
    }

    /**
     * <p>Discription:获取指定位数的完整流水号,不够位在左侧补零</p>
     *
     * @param totalLenght 指定流水号位数
     * @return
     * @author 王卓 2013-3-20
     * @update [修改人] [修改时间] [变更描述]
     */
    public static String getFullSerialNum(int serial_num, int totalLenght) {
        String s = serial_num + "";
        StringBuffer strB = new StringBuffer(s);
        if (totalLenght < 0 || strB.length() > totalLenght) {
            return s;
        }
        for (int i = 0, j = totalLenght - strB.length(); i < j; i++) {
            strB = new StringBuffer("0").append(strB);
        }
        return strB.toString();
    }

    /**
     * <p>Discription:判断full字符串中，是否包含c串</p>
     *
     * @param full 以逗号隔开的codevalue值，如："04,1101"
     * @param c    单一的codevalue值，如："04"
     * @return
     * @author 王卓 2013-4-19
     * @update [修改人] [修改时间] [变更描述]
     */
    public static boolean containsCodevalue(String full, String c) {
        //其中任意一个为空，返回false
        if (isEmpty(full) || isEmpty(c)) {
            return false;
        }
        full = full.trim();
        c = c.trim();
        //若两个字符串的长度相等，则判断两个字符串是否一样
        if (full.length() == c.length()) {
            return full.equals(c);
        }//若被包含字符串的长度大于full字符串，返回false
        else if (full.length() < c.length()) {
            return false;
        } else {
            //判断是否在full字符串的两头
            if (full.startsWith(c + ",") || full.endsWith("," + c)) {
                return true;
            }//判断是否在full字符串的中间
            else
                return full.contains("," + c + ",");
        }

    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    //根据实际需求  如果null返回字符串空
    public static String setEmptyString(String value) {
        if (isEmpty(value)) {
            return "-";
        } else {
            return value;
        }
    }

    //根据实际需求  如果null返回字符串空
    public static String setEmptyStringBlank(String value) {
        if (isEmpty(value)) {
            return "";
        } else {
            return value;
        }
    }

    //根据实际需求  如果null返回默认“0”
    public static String setEmptyInt(String value) {
        if (isEmpty(value)) {
            return "0";
        } else {
            return value;
        }

    }
    //根据实际需求  如果null返回默认“0”
    public static String setEmptyHour(String value) {
        if (TextUtils.isEmpty(value)||value.equals("0.0")) {
            return "0h";
        } else {
            return value+"h";
        }

    }

    /*** 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static String MapContainsStringKey(String key, Map<String,String> map){
        if(TextUtils.isEmpty(key)){
           return "";
        }
        if(map.containsKey(key)){
            return map.get(key);
        }
        return "";
    }
    public static int MapContainsIntKey(String key, Map<String,Integer> map){
        if(TextUtils.isEmpty(key)){
            return 0;
        }
        if(map.containsKey(key)){
            return map.get(key);
        }
        return 0;
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
