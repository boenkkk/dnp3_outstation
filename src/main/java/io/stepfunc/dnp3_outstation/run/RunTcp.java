package io.stepfunc.dnp3_outstation.run;

import io.stepfunc.dnp3.LinkErrorMode;
import io.stepfunc.dnp3.OutstationServer;
import io.stepfunc.dnp3.Runtime;

import java.util.Scanner;

public class RunTcp {

    public static void run(Runtime runtime) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("input ipport: {127.0.0.1:20000}");
        String ipport = scanner.next();

        // ANCHOR: create_tcp_server
        // OutstationServer server = OutstationServer.createTcpServer(runtime, LinkErrorMode.CLOSE, "127.0.0.1:20000");
        OutstationServer server = OutstationServer.createTcpServer(
                runtime,
                LinkErrorMode.CLOSE,
                ipport
        );
        // ANCHOR_END: create_tcp_server

        try {
            RunServer.run(server);
        } finally {
            server.shutdown();
        }
    }
}
