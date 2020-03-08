package com.muma.util;

/**
 * 字符串替换* 工具类
 */
public class StringReplaceUtil {

    /**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     *
     * @param userName 用户名
     * @return 替换后的用户名
     */
    public static String userNameReplaceWithStar(String userName) {
        String userNameAfterReplaced = "";

        if (userName == null){
            userName = "";
        }

        int nameLength = userName.length();

        if (nameLength <= 1) {
            userNameAfterReplaced = "*";
        } else if (nameLength == 2) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{0})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{1})");
        } else if (nameLength >= 3 && nameLength <= 5) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{1})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{1})");
        } else if(nameLength == 6){
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{1})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})");
        } else if (nameLength == 7) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})");
        } else if (nameLength == 8) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})");
        } else if (nameLength == 9) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{2})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{3})");
        } else if (nameLength == 10) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{3})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{3})");
        } else if (nameLength >= 11) {
            userNameAfterReplaced = replaceAction(userName, "(?<=[\\u4e00-\\u9fa5_a-zA-Z0-9]{3})[\\u4e00-\\u9fa5_a-zA-Z0-9](?=[\\u4e00-\\u9fa5_a-zA-Z0-9]{4})");
        }

        return userNameAfterReplaced;

    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 身份证号替换，保留前四位和后四位
     *
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String idCardReplaceWithStar(String idCard) {

        if (idCard.isEmpty() || idCard == null) {
            return null;
        } else {
            return replaceAction(idCard, "(?<=\\d{3})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换，保留后四位
     *
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWithStar(String bankCard) {

        if (bankCard.isEmpty() || bankCard == null) {
            return null;
        } else {
            return replaceAction(bankCard, "(?<=\\d{3})\\d(?=\\d{4})");
        }
    }


    public static void main(String[] args) {
        String re = bankCardReplaceWithStar("4391880081522983");
        System.out.println("银行卡："+re);
        String id = idCardReplaceWithStar("350526199211147013");
        System.out.println("身份证："+id);
        String name = userNameReplaceWithStar("q1222");
        System.out.println("字符串："+name);


    }


}
