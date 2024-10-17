package lk.ijse.gdse68.aad.posbackend.util;

import java.time.LocalDateTime;
import java.util.Random;

public class AppUtil {
    public static String createOrderId(){
        return "OR" + new Random().nextInt(10000);
    }
    public static LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }
    public static String createOrderDetailsId(){
        return "OD" + new Random().nextInt(10000);
    }
}
