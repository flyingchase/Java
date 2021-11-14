package tmall.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
    // 实体类中的时间属性是 Date
    // MySQL要求使用 datetime 类型字段保存时间信息
    // jdbc 通过 java.sql.Timestamp 获取
    public static java.sql.Timestamp d2t(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }


}
