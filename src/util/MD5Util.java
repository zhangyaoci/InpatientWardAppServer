package util;

import java.util.Random;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;


public class MD5Util {

    //密码加密（加盐）
    public static String generate(String password) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int length = stringBuilder.length();
        if (length < 16) {
            for (int i = 0; i < 16 - length; i++) {
                stringBuilder.append("0");
            }
        }


        //随机生成一个数字字符串
        String salt = stringBuilder.toString();

        //密码进行加密
        password = md5Hex(password + salt);

        //48位，其中2/3存摘要，1/3存盐
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }


    //校验加盐后是否和原文一致
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        System.out.println(md5Hex(password + salt));
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String string) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(string.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }


}
