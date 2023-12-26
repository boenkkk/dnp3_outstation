package io.stepfunc.dnp3_outstation.config;

import io.stepfunc.dnp3.EventBufferConfig;

import static org.joou.Unsigned.ushort;

public class TestEventBufferConfig {

    // ANCHOR: event_buffer_config
    public static EventBufferConfig getEventBufferConfig() {
        return new EventBufferConfig(
                ushort(10), // binary
                ushort(10), // double-bit binary
                ushort(10), // binary output status
                ushort(5), // counter
                ushort(5), // frozen counter
                ushort(5), // analog
                ushort(5), // analog output status
                ushort(3) // octet string
        );
    }
    // ANCHOR_END: event_buffer_config
}
