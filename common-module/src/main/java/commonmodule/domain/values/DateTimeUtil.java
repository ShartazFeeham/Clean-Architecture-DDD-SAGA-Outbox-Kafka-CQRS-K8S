package commonmodule.domain.values;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    public static final String ASIA_DHAKA = "Asia/Dhaka";

    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of(ASIA_DHAKA));
    }

}
