package io.stepfunc.dnp3_outstation.util;

import io.stepfunc.dnp3.*;

import static org.joou.Unsigned.ubyte;
import static org.joou.Unsigned.ushort;

public class DatabaseUtil {

    // ANCHOR: database_init_function
    public static void initializeDatabase(Database db) {
        // add 10 points of each type
        for (int i = 0; i < 10; i++) {
            // you can explicitly specify the configuration for each point ...
            db.addBinaryInput(ushort(i), EventClass.CLASS1, new BinaryInputConfig(StaticBinaryInputVariation.GROUP1_VAR1, EventBinaryInputVariation.GROUP2_VAR2));
            // ... or just use the defaults
            db.addDoubleBitBinaryInput(ushort(i), EventClass.CLASS1, new DoubleBitBinaryInputConfig());
            db.addBinaryOutputStatus(ushort(i), EventClass.CLASS1, new BinaryOutputStatusConfig());
            db.addCounter(ushort(i), EventClass.CLASS1, new CounterConfig());
            db.addFrozenCounter(ushort(i), EventClass.CLASS1, new FrozenCounterConfig());
            db.addAnalogInput(ushort(i), EventClass.CLASS1, new AnalogInputConfig());
            db.addAnalogOutputStatus(ushort(i), EventClass.CLASS1, new AnalogOutputStatusConfig());
            db.addOctetString(ushort(i), EventClass.CLASS1);
        }

        // define device attributes made available to the master
        db.defineStringAttr(ubyte(0), false, AttributeVariations.DEVICE_MANUFACTURERS_NAME, "Step Function I/O");
        db.defineStringAttr(ubyte(0), true, AttributeVariations.USER_ASSIGNED_LOCATION, "Bend, OR");
    }
    // ANCHOR_END: database_init_function
}
