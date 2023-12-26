package io.stepfunc.dnp3_outstation;

import io.stepfunc.dnp3.*;
import io.stepfunc.dnp3.Runtime;
import io.stepfunc.dnp3_outstation.config.TestTlsServerConfig;
import io.stepfunc.dnp3_outstation.listener.TestLogger;
import io.stepfunc.dnp3_outstation.run.RunSerial;
import io.stepfunc.dnp3_outstation.run.RunTcp;
import io.stepfunc.dnp3_outstation.run.RunTls;

import java.util.Scanner;

public class OutstationExample {

    public static void main(String[] args) {
        // Setup logging
        Logging.configure(new LoggingConfig(), new TestLogger());

        // Create the Tokio runtime
        Runtime runtime = new Runtime(new RuntimeConfig());

        try {
            run(runtime, args);
        } finally {
            runtime.shutdown();
        }
    }

    private static void run(Runtime runtime, String[] args) {
        // if(args.length != 1) {
        //     System.err.println("You must specify a transport");
        //     System.err.println("Usage: outstation-example <transport> (tcp, serial, tls-ca, tls-self-signed)");
        //     return;
        // }
        // final String type = args[0];

        Scanner scanner = new Scanner(System.in);

        System.out.println("input transport: {tcp, serial, tls-ca, tls-self-signed}");
        String type = scanner.next();

        switch (type) {
            case "tcp" -> RunTcp.run(runtime);
            case "serial" -> RunSerial.run(runtime);
            case "tls-ca" -> RunTls.run(runtime, TestTlsServerConfig.getCaTlsConfig());
            case "tls-self-signed" -> RunTls.run(runtime, TestTlsServerConfig.getSelfSignedTlsConfig());
            default -> System.err.printf("Unknown transport: %s%n", type);
        }
    }
}