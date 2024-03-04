package server.spring.api.common.util;

import java.util.regex.Pattern;

public class MemberUtil {
    public static String phoneNumberBarAdd(String phoneNo) {

        if (phoneNo == null) {
            return null;
        }

        if (phoneNo.length() == 8) {
            return phoneNo.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (phoneNo.length() == 12) {
            return phoneNo.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }

        return phoneNo.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }

    public static String phoneNumberBarRemove(String phoneNo) {
        return phoneNo.replace("-", "");
    }

    public static boolean validateUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return false;
        }

        if (!Pattern.matches("^[a-zA-Z0-9]*$", userId)) {
            return false;
        }

        return true;
    }

    public static String validatePhoneNumber(String phoneNo) {
        // 하이픈(-) 삭제
        phoneNo = phoneNo.replace("-", "");

        // 국내 전화번호 방식 체크
        if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phoneNo)) {
            System.out.println("error");
        }

        return phoneNo;
    }

      public static String validateName(String name) {
        // 한글+영문+숫자, 특수문자는 제외
        if (!Pattern.matches("^[a-zA-Z0-9가-힣()]*$", name)) {
            System.out.println("error");
        }

        return name;
      }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        if (!Pattern.matches(
                "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
                email)) {
            return false;
        }
        return true;
    }
}
