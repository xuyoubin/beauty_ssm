package com.muma.util;

import java.util.Random;

public class ShareCodeUtil {

    /** 自定义进制(0,1没有加入,容易与o,l混淆) */
    private static final char[] r=new char[]{'Q', 'W', 'E', '8', 'A', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P', '5', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'V', 'Y', 'T', 'N', '6', 'B', 'G', 'H'};

    /** (不能与自定义进制有重复) */
    private static final char b='9';

    /** 进制长度 */
    private static final int binLen=r.length;

    /** 序列最小长度 */
    private static final int s=6;

    /**
     * 根据ID生成六位随机码
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(Integer id) {
        char[] buf=new char[32];
        int charPos=32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }

    public static Integer codeToId(String code) {
        char chs[]=code.toCharArray();
        Integer res=0;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }


    public static void main(String[] args) {

        String invitationCode=toSerialCode( 1);
        System.out.println(invitationCode);
        System.out.println(codeToId(invitationCode));
    }

}
