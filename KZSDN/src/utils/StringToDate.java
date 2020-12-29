package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    public static Date stringTodate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate=simpleDateFormat.parse(date);
        return nowDate;
    }

}
