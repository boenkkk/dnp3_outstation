package io.stepfunc.dnp3_outstation.run;

import io.stepfunc.dnp3.LinkErrorMode;
import io.stepfunc.dnp3.OutstationServer;
import io.stepfunc.dnp3.Runtime;
import io.stepfunc.dnp3.TlsServerConfig;

import java.util.Scanner;

public class RunTls {

    public static void run(Runtime runtime, TlsServerConfig config) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("input ipport: {127.0.0.1:20001}");
        String ipport = scanner.next();

        // ANCHOR: create_tls_server
        OutstationServer server = OutstationServer.createTlsServer(
                runtime,
                LinkErrorMode.CLOSE,
                ipport,
                config
        );
        // ANCHOR_END: create_tls_server

        try {
            RunServer.run(server);
        } finally {
            server.shutdown();
        }
    }
}
