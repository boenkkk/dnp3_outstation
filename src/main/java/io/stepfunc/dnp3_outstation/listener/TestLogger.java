package io.stepfunc.dnp3_outstation.listener;

import io.stepfunc.dnp3.LogLevel;
import io.stepfunc.dnp3.Logger;

public class TestLogger implements Logger {

    @Override
    public void onMessage(LogLevel logLevel, String message) {
        System.out.print("onMessage() logLevel|message: "+logLevel+"|"+message);
    }
}
