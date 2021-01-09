package pl.wolny.JungleBot.TimeSystem;

import java.util.concurrent.TimeUnit;

public class IsValid {
    public boolean ValidateTime (String time){
        boolean HasAtLeastOneNumber = false;
        boolean ValidTimeUnit = false;
        for(int i=0;i<time.length();i++){
            if(isNumeric(String.valueOf(time.charAt(i))) && !HasAtLeastOneNumber){
                HasAtLeastOneNumber = true;
            }else if (!ValidTimeUnit) {
                switch (String.valueOf(time.charAt(i))) {
                    case "m":
                    case "d":
                    case "h":
                        ValidTimeUnit = true;
                        break;
                }
            }
        }
        if(ValidTimeUnit && HasAtLeastOneNumber){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
