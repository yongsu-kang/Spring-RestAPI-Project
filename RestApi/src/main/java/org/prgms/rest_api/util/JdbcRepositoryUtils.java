package org.prgms.rest_api.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcRepositoryUtils {
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
