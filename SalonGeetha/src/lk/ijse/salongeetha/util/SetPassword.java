package lk.ijse.salongeetha.util;

import java.util.Random;

public class SetPassword {


    public static String setPassword() {
        Random random = new Random();
        int value = random.nextInt(21);
        switch (value){
            case 1:return "!!";
            case 2:return "@2";
            case 3:return "##";
            case 4:return "#@";
            case 5:return "$!";
            case 6:return "&%";
            case 7:return "$(";
            case 8:return ")0";
            case 9:return "4%";
            case 10:return "1!";
            case 11:return "2@";
            case 12:return "3#";
            case 13:return "4$";
            case 14:return "5%";
            case 15:return "6^";
            case 16:return "7&";
            case 17:return "8*";
            case 18:return "()";
            case 19:return "**";
            case 20:return "__";
        }
        return "=+";
    }
}
