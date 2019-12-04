package cn.newtol.springbootblog.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:
 * @Description: 加密工具类
 * @Date: Created in 21:57 2018/3/20
 */
public class EncryptUtil {
    private static final char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private EncryptUtil() {
    }

    /**
     * 32位MD5加密，结果大写
     * @param str
     * @return
     */
    public static String MD5(String str) {
        return encode(str, "MD5");
    }

    /**
     * 32位SHA1加密，结果大写
     * @param str
     * @return
     */
    public static String sha1(String str) {
        return encode(str, "SHA-1");
    }

    private static String encode(String str, String algorithm) {
        String rs = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(str.toString().getBytes("UTF-8"));
            rs = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("该加密方式不存在");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 将byte数组变为16进制对应的字符串
     * @param byteArray byte数组
     * @return 转换结果
     */
    private static String byteToStr(byte[] byteArray) {
        int len = byteArray.length;
        StringBuilder strDigest = new StringBuilder(len * 2);
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);

    }

    //md5加密
    public static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0){
                a += 256;
            }
            if (a < 16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}