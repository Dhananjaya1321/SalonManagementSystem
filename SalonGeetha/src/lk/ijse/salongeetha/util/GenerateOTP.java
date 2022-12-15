package lk.ijse.salongeetha.util;

import java.util.Random;

public class GenerateOTP {
    public static String getOTP(){
        Random random=new Random();
        int otp=random.nextInt(1000000);
        return String.valueOf(otp);
    }

}
