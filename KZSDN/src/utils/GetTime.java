package utils;
import java.util.Date;
import java.text.SimpleDateFormat;
public class GetTime {

    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
