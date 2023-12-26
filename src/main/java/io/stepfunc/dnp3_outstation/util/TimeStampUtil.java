package io.stepfunc.dnp3_outstation.util;

import io.stepfunc.dnp3.Timestamp;

import static org.joou.Unsigned.ulong;

public class TimeStampUtil {

    public static Timestamp now() {
        return Timestamp.synchronizedTimestamp(ulong(System.currentTimeMillis()));
    }
}
