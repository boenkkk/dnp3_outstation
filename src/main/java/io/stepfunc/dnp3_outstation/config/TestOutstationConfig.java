package io.stepfunc.dnp3_outstation.config;

import io.stepfunc.dnp3.AppDecodeLevel;
import io.stepfunc.dnp3.DecodeLevel;
import io.stepfunc.dnp3.OutstationConfig;

import java.util.Scanner;

import static org.joou.Unsigned.ushort;

public class TestOutstationConfig {

    public static OutstationConfig getOutstationConfig() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("input outstation address: {1} in example 1024");
        int outstationAddr = scanner.nextInt();

        System.out.println("input master address: {1024} in example 1");
        int masterAddr = scanner.nextInt();

        // ANCHOR: outstation_config
        // create an outstation configuration with default values
        OutstationConfig config = new OutstationConfig(
                ushort(outstationAddr), // outstation address
                ushort(masterAddr), // master address
                TestEventBufferConfig.getEventBufferConfig() // event buffer sizes
            ).withDecodeLevel(new DecodeLevel().withApplication(AppDecodeLevel.OBJECT_VALUES)
        );
        // ANCHOR_END: outstation_config

        return config;
    }
}
