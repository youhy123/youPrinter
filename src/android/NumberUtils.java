package org.apache.cordova.youprinter;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字化和格式工具
 */
public class NumberUtils {

    //去掉多余的0
    public static String delZero(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(number);
    }

    //去掉多余的0
    public static String delZero(long number) {
        DecimalFormat decimalFormat = new DecimalFormat("###################");
        return decimalFormat.format(number);
    }

    //去掉多余的0
    public static String delZero(String number) {
        if(StringUtils.isEmpty(number)){
            return "0";
        }
        return delZero(Double.valueOf(number));
    }

    public static String delZeroBlank(String number) {
        if(StringUtils.isEmpty(number)){
            return "";
        }
        return delZero(Double.valueOf(number));
    }

    //string  转double*100取整数  适合返回百分数小数
    public static int doubleToIntPercent(String number) {
        double d;
        if (StringUtils.isEmpty(number)) {
            return 0;
        } else {
            d = Double.valueOf(number) * 100;
        }
        return (int) Math.round(d);
    }

    //string  转double*100取整数  适合返回百分数小数
    public static int doubleToIntPercent(double number) {
        double d;
        d = number * 100;
        return (int) Math.round(d);
    }

    //
    //    //四舍五入把double转化int整型，0.5进一，小于0.5不进一
    //    public static int doubleToInt(double number){
    //        BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
    //        return Integer.parseInt(bd.toString());
    //    }
    //四舍五入把double转化int整型，0.5进一，小于0.5不进一
    public static int doubleToCeilInt(double number) {

        double d;
        d = number * 100;
        DecimalFormat df = new DecimalFormat("#.0");
        return (int) Math.ceil(Double.valueOf(df.format(d)));
    }

    //四舍五入把double转化int整型，0.5进一，小于0.5不进一
    public static int doubleToInt(String number) {
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

    //四舍五入把double转化int整型，0.5进一，小于0.5不进一
    public static long doubleToLong(String number) {
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Long.valueOf(bd.toString());
    }

    //每三位逗号分隔
    public static String formatString(String data) {
        String target = delZero(data);

        if ("0".equalsIgnoreCase(target)) {
            return "0";
        }

        DecimalFormat df = new DecimalFormat("#,###.00");
        DecimalFormat df_int = new DecimalFormat("#,###");

        if (isDigit(target)) {
            return df_int.format(Long.valueOf(target));
        } else {
            return df.format(Double.valueOf(target));
        }
    }

    //返回带有万 千万 等信息格式的字符串
    public static String formatNum(String num) {
        if (StringUtils.isEmpty(num)) {
            return "0";
        }
        long target = Double.valueOf(num).longValue();
        if (target == 0) {
            return "0";
        }
        return formatNumLogic(target);
    }


    public static String formatNumLogic(long target) {
        String result = new String();

        DecimalFormat df = new DecimalFormat("#.0");
        DecimalFormat df_second = new DecimalFormat("#.00");

        double b1 = 10000;
        double b2 = 10000000;
        double b3 = 100000000;

        if (target < b1) {
            result = target + "";

        } else if ((target >= b1) && (target < b2)) {
            result = delZero(df_second.format(target / b1)) + "万";


        } else if ((target >= b2) && (target < b3)) {
            result = delZero(df_second.format(target / b2)) + "千万";

        } else {
            result =delZero(df_second.format(target / b3)) + "亿";
        }

        if (result.length() == 0)
            return "0";

        return result;
    }


    public static boolean isPhoneNumber(String phoneNumber) {
        //		String string = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
        String string = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        //		String string = "\\d{11}";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String string = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPostCode(String postcode) {
        String string = "\\p{Digit}{6}";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(postcode);
        return m.matches();
    }

    public static boolean isTel(String tel) {
        String string = "\\d{7,16}";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(tel);
        return m.matches();
    }

    public static boolean isUserName(String userName) {
        String string = "^[a-zA-Z0-9@.]{6,20}$";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(userName);
        return m.matches();
    }

    public static boolean isPassWord(String password) {
        //		String string = "^[a-zA-Z0-9]{6,20}$";
        String string = "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{6,32}$";
        Pattern p = Pattern.compile(string);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static double formatDoublePreciseTwo(double d) {
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatDouble(double d) {
        return (double) Math.round(d * 10) / 10;
    }

    public static long get9RandomNumber() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int number = 0;
        for (int i = 0; i < 9 / 3; i++) {
            while (true) {
                number = random.nextInt(999);
                if (number > 100) {
                    buffer.append(number);
                    break;
                }
            }
        }
        return Long.valueOf(buffer.toString());
    }

    public static long get16RandomNumber() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int number = 0;
        for (int i = 0; i < 16 / 8; i++) {
            while (true) {
                number = random.nextInt(99999999);
                if (number > 10000000) {
                    buffer.append(number);
                    break;
                }
            }
        }
        return Long.valueOf(buffer.toString());
    }

    /**
     * 毫秒值转换为日期
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return formatter.format(date);
    }

    /**
     * 将科学计数法
     */
    public static String doubleFormat(double d) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(d);
    }

    /**
     * 获取身份证like值
     *
     * @param id 身份证号码
     * @return like值
     */
    public static String getIdNumberLike(String id) {
        //若传入的身份证号码不是正整数型字符串，直接返回false
        if (!isDigit(id)) {
            return null;
        }
        //若是15位身份证，将其改成XXXXXX19YYMMDDXXX%
        if (id.length() == 15) {
            return (id.substring(0, 6) + "19" + id.substring(6) + "%");
        }//若是18位身份证，将其改成15位的身份证
        else if (id.length() == 18) {
            return (id.substring(0, 6) + id.substring(8, 17));
        }
        return null;
    }

    /**
     * 判断字符串是否是正整数
     *
     * @param num 待校验字符串
     * @return 是否是正整数
     */
    public static boolean isDigit(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        }
        return Pattern.compile("^\\+?[1-9][0-9]*$").matcher(num).matches();
    }

    /**
     * 字符串秒转成小时
     *
     * @param num 待校验字符串
     * @return 是否是正整数
     */
    public static String sToHour(String num) {
        String h = "0";
        if (TextUtils.isEmpty(num)) {
            return h + "h";
        }
        double d = Double.valueOf(num) / 3600;
        DecimalFormat dFormat = new DecimalFormat("#.00");
        h = delZero(dFormat.format(d));
        return h + "h";
    }
    /**
     * 字符串秒转成小时
     *
     * @param num 待校验字符串
     * @return 是否是正整数
     */
    public static String sToHourInt(String num) {
        String h = "0";
        if (TextUtils.isEmpty(num)) {
            return h ;
        }
        double d = Double.valueOf(num) / 3600;
        DecimalFormat dFormat = new DecimalFormat("#.00");
        h = delZero(dFormat.format(d));
        int hour=doubleToInt(h+"");
        return hour + "";
    }
    /**
     * 秒转成小时
     *
     * @param num 待校验字符串
     * @return 是否是正整数
     */
    //hangeng  后台接口有变化 变成秒变小时了  不是毫秒了
    public static String msToHour(String num) {
        String h = "0";
        if (TextUtils.isEmpty(num)) {
            return h+"";
        }
        double d = Double.valueOf(num) / 3600;
        DecimalFormat dFormat = new DecimalFormat("#.00");
        h = delZero(dFormat.format(d));
        int hour=doubleToInt(h+"");
        return hour + "";
    }

    public static int dToInt(String number) {
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        if (number.equals("0.0")) {
            return 0;
        }

        return Integer.valueOf(number);
    }

//字节数转kb  mb  gb
    public static String getDataSize(long size) {
        if (size < 0) {
            size = 0;
        }
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }

    }

    /**
     *   提供精确的加法运算。
     *   @param   v1   被加数
     *   @param   v2   加数
     *   @return   两个参数的和
     */

    public   static   double doubleAdd(double   v1, double   v2){
        BigDecimal   b1   =   new   BigDecimal(Double.toString(v1));
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));
        return   b1.add(b2).doubleValue();
    }
}
