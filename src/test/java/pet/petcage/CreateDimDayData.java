package pet.petcage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user chenzuoli on 2020/5/17 15:45
 * description:
 */
public class CreateDimDayData {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start_date = "2020-01-01";
        String end_date = "2030-01-01";
        String cur_date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        Date start = simpleDateFormat.parse(start_date);
        Date end = simpleDateFormat.parse(end_date);
        long duration_days = (end.getTime()-start.getTime())/86400000;
        for (int i = 0; i < duration_days; i++) {

        }
    }
}
