package io.stepfunc.dnp3_outstation.run;

import io.stepfunc.dnp3.*;
import io.stepfunc.dnp3_outstation.util.DatabaseUtil;
import io.stepfunc.dnp3_outstation.util.TimeStampUtil;
import org.joou.UByte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.joou.Unsigned.*;
import static org.joou.Unsigned.ushort;

public class RunOutstation {

    public static void run(Outstation outstation) {
        // Setup initial points
        // ANCHOR: database_init
        outstation.transaction(DatabaseUtil::initializeDatabase);
        // ANCHOR_END: database_init

        boolean binaryValue = false;
        DoubleBit doubleBitBinaryValue = DoubleBit.DETERMINED_OFF;
        boolean binaryOutputStatusValue = false;
        long counterValue = 0;
        long frozenCounterValue = 0;
        double analogValue = 0.0;
        double analogOutputStatusValue = 0.0;

        final Flags onlineFlags = new Flags(Flag.ONLINE);
        final UpdateOptions detectEvent = UpdateOptions.detectEvent();

        // Handle user input
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                final String line = reader.readLine();
                switch (line) {
                    case "x" -> {
                        return;
                    }
                    case "enable" -> outstation.enable();
                    case "disable" -> outstation.disable();
                    case "bi" -> {
                        binaryValue = !binaryValue;
                        final boolean pointValue = binaryValue;
                        outstation.transaction(db -> {
                            BinaryInput value =
                                    new BinaryInput(
                                            ushort(7),
                                            pointValue,
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateBinaryInput(value, detectEvent);
                        });
                    }
                    case "dbbi" -> {
                        doubleBitBinaryValue = doubleBitBinaryValue == DoubleBit.DETERMINED_OFF
                                        ? DoubleBit.DETERMINED_ON
                                        : DoubleBit.DETERMINED_OFF;
                        final DoubleBit pointValue = doubleBitBinaryValue;
                        outstation.transaction(db -> {
                            DoubleBitBinaryInput value =
                                    new DoubleBitBinaryInput(
                                            ushort(7),
                                            pointValue,
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateDoubleBitBinaryInput(value, detectEvent);
                        });
                    }
                    case "bos" -> {
                        binaryOutputStatusValue = !binaryOutputStatusValue;
                        final boolean pointValue = binaryOutputStatusValue;
                        outstation.transaction(db -> {
                            BinaryOutputStatus value =
                                    new BinaryOutputStatus(
                                            ushort(7),
                                            pointValue,
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateBinaryOutputStatus(value, detectEvent);
                        });
                    }
                    case "co" -> {
                        counterValue += 1;
                        final long pointValue = counterValue;
                        outstation.transaction(db -> {
                            Counter value =
                                    new Counter(
                                            ushort(7),
                                            uint(pointValue),
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateCounter(value, detectEvent);
                        });
                    }
                    case "fco" -> {
                        frozenCounterValue += 1;
                        final long pointValue = frozenCounterValue;
                        outstation.transaction(db -> {
                            FrozenCounter value =
                                    new FrozenCounter(
                                            ushort(7),
                                            uint(pointValue),
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateFrozenCounter(value, detectEvent);
                        });
                    }
                    case "ai" -> {
                        analogValue += 1;
                        final double pointValue = analogValue;
                        outstation.transaction(db -> {
                            AnalogInput value =
                                    new AnalogInput(
                                            ushort(7),
                                            pointValue,
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateAnalogInput(value, detectEvent);
                        });
                    }
                    case "aos" -> {
                        analogOutputStatusValue += 1;
                        final double pointValue = analogOutputStatusValue;
                        outstation.transaction(db -> {
                            AnalogOutputStatus value =
                                    new AnalogOutputStatus(
                                            ushort(7),
                                            pointValue,
                                            onlineFlags,
                                            TimeStampUtil.now());
                            db.updateAnalogOutputStatus(value, detectEvent);
                        });
                    }
                    case "os" -> outstation.transaction(db -> {
                        List<UByte> octetString = new ArrayList<>();
                        for (byte octet : "Hello".getBytes(StandardCharsets.US_ASCII)) {
                            octetString.add(ubyte(octet));
                        }

                        db.updateOctetString(ushort(7), octetString, detectEvent);
                    });
                    default -> System.out.printf("Unknown command: %s%n", line);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
