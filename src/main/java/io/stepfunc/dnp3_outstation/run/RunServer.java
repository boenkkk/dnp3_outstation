package io.stepfunc.dnp3_outstation.run;

import io.stepfunc.dnp3.AddressFilter;
import io.stepfunc.dnp3.Outstation;
import io.stepfunc.dnp3.OutstationServer;
import io.stepfunc.dnp3_outstation.TestOutstationApplication;
import io.stepfunc.dnp3_outstation.TestOutstationInformation;
import io.stepfunc.dnp3_outstation.config.TestOutstationConfig;
import io.stepfunc.dnp3_outstation.handler.TestControlHandler;
import io.stepfunc.dnp3_outstation.listener.TestConnectionStateListener;

public class RunServer {

    public static void run(OutstationServer server) {
        // ANCHOR: tcp_server_add_outstation
        final Outstation outstation =
                server.addOutstation(
                        TestOutstationConfig.getOutstationConfig(),
                        new TestOutstationApplication(),
                        new TestOutstationInformation(),
                        new TestControlHandler(),
                        new TestConnectionStateListener(),
                        AddressFilter.any());
        // ANCHOR_END: tcp_server_add_outstation

        // ANCHOR: tcp_server_bind
        System.out.println("===============server.bind()===============");
        server.bind();
        // ANCHOR_END: tcp_server_bind

        RunOutstation.run(outstation);
    }
}
