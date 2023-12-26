package io.stepfunc.dnp3_outstation.run;

import io.stepfunc.dnp3.Outstation;
import io.stepfunc.dnp3.Runtime;
import io.stepfunc.dnp3.SerialSettings;
import io.stepfunc.dnp3_outstation.TestOutstationApplication;
import io.stepfunc.dnp3_outstation.TestOutstationInformation;
import io.stepfunc.dnp3_outstation.config.TestOutstationConfig;
import io.stepfunc.dnp3_outstation.handler.TestControlHandler;

import java.time.Duration;
import java.util.Scanner;

public class RunSerial {

    public static void run(Runtime runtime) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("input serialPath: {/dev/pts/4}");
        String serialPath = scanner.next();

        // ANCHOR: create_serial_server
        Outstation outstation = Outstation.createSerialSession2(
                runtime,
                serialPath,
                new SerialSettings(),
                Duration.ofSeconds(5), // try to open the port every 5 seconds
                TestOutstationConfig.getOutstationConfig(),
                new TestOutstationApplication(),
                new TestOutstationInformation(),
                new TestControlHandler(),
                state -> System.out.println("Port state change: " + state)
        );
        // ANCHOR_END: create_serial_server

        RunOutstation.run(outstation);
    }
}
