package pl.wolny.JungleBot.TimeSystem;

import java.util.concurrent.TimeUnit;

public class GetTime {
    public int GetTime_Method(String time) {
        StringBuilder preint = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            if (isNumeric(String.valueOf(time.charAt(i)))) {
                preint.append(time.charAt(i));
            }
        }
        int FinalInt = Integer.parseInt(preint.toString());
        if(FinalInt > 0 ){
            return FinalInt;
        }else {
            return -1;
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
