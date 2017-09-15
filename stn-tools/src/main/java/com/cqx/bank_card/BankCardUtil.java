package com.cqx.bank_card;

import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * Created by BG307435 on 2017/8/23.
 */
public class BankCardUtil {

    public static String parseCard(String cardStr) {
        if (isBankCard(cardStr)) {
            return "银行卡格式错误.";
        }
        char[] ss = cardStr.replaceAll(" ", "").toCharArray();
        String result = BankInfo.getNameOfBank(ss, 0);
        return result;
    }

    @Test
    public void test(){
        String card = "6214 8358 9689 8499";
        System.out.println(parseCard(card));
        String card1 = "622126835896898499";
        System.out.println(parseCard(card1));
    }


    private static boolean isBankCard(String cardStr) {
        return checkBankCard(cardStr);
    }


    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (StringUtils.isEmpty(nonCheckCodeCardId)
                || !nonCheckCodeCardId.matches("\\d+")
                || nonCheckCodeCardId.length() < 16
                || nonCheckCodeCardId.length() > 19) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }


}
