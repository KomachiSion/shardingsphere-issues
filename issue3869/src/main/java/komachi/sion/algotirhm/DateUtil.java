package komachi.sion.algotirhm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author yangyi
 */
public class DateUtil {
    
    public static Date parseDateFromStrYyyyMMdd2(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int getSeason(Date createDate) {
        int month = createDate.getMonth();
        if (month >= 0 && month < 3) {
            return 1;
        } else if (month >=3 && month < 6) {
            return 2;
        } else if (month >= 6 && month < 9) {
            return 3;
        } else {
            return 4;
        }
    }
}
