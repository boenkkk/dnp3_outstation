package io.stepfunc.dnp3_outstation.listener;

import io.stepfunc.dnp3.ConnectionState;
import io.stepfunc.dnp3.ConnectionStateListener;

public class TestConnectionStateListener implements ConnectionStateListener {

    @Override
    public void onChange(ConnectionState connectionState) {
        System.out.println("onChange() ConnectionState: " + connectionState);
    }
}
