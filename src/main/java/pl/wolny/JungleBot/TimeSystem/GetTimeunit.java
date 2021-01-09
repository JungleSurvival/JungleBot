package pl.wolny.JungleBot.TimeSystem;

import java.util.concurrent.TimeUnit;

public class GetTimeunit {
    public TimeUnit GetTimeunit_method (String time){
        for(int i=0;i<time.length();i++){
            if(!isNumeric(String.valueOf(time.charAt(i)))){
                switch (String.valueOf(time.charAt(i))) {
                    case "m":
                        return TimeUnit.MINUTES;
                    case "h":
                        return TimeUnit.HOURS;
                    case "d":
                        return TimeUnit.DAYS;
                }
            }
        }
        return TimeUnit.MILLISECONDS;
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

