package inventory.management.system;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidDate(String dateStr) {
        if (!isNonEmpty(dateStr)) return false;

        String[] parts = dateStr.split("-");
        if (parts.length != 3) return false;

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidNumber(String n) {
        if (!isNonEmpty(n) || n.length()!=11) return false;
        for(int i=0;i<n.length();i++){
            char x = n.charAt(i);
            if(x >= '0' && x <= '9' )
                continue;
            else return false;
        }
        return true;
    }

    public static boolean isValidID(String id, Database x) {
        return !x.contains(id.trim());
    }

    public static boolean isValidID(String id,char y){
        id=id.trim();
        if(id.length()!=5)
            return false;
        if(id.charAt(0)!=y)
            return false;
        for(int i=1;i<5;i++){
            char x = id.charAt(i);
            if(x >= '0' && x <= '9' )
                continue;
            else return false;
        }
        return true;
    }


    public static boolean isValidEmail(String email) {

        String regex = "^[a-zA-Z0-9][._]?[a-zA-Z0-9]+([-._][a-zA-Z0-9]+(_?[a-zA-Z0-9]+)*)*@[a-zA-Z0-9]+([-.][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,4}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

        /*if (!isNonEmpty(email)) return false;

        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        if (atIndex == 0 || atIndex == -1 || atIndex != lastAtIndex|| atIndex == email.length()-1 ||email.contains(" ") ) {
            return false;
        }
        char beforeAt = email.charAt(atIndex - 1);
        if (!Character.isLetterOrDigit(beforeAt) || email.contains("..")) {
            return false;
        }

        String domainPart = email.substring(atIndex+1);
        int dotIndex = domainPart.indexOf('.');

        if (dotIndex == -1 || dotIndex == 0) {
            return false;
        }
        int lastDotIndex = domainPart.lastIndexOf('.');
        if (lastDotIndex == domainPart.length() - 1) {
            return false;
        }
        return true;*/

    }

    public static boolean isValidName(String name) {
        if (!isNonEmpty(name)) return false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' '  && c != '\'') {
                return false;
            }
        }
        return true;
    }

}


