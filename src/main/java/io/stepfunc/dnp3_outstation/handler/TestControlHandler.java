package io.stepfunc.dnp3_outstation.handler;

import io.stepfunc.dnp3.*;
import io.stepfunc.dnp3_outstation.util.TimeStampUtil;
import org.joou.UShort;

import static org.joou.Unsigned.ushort;

// ANCHOR: control_handler
public class TestControlHandler implements ControlHandler {

    @Override
    public void beginFragment() {
        System.out.println("===============beginFragment()===============");
    }

    @Override
    public void endFragment(DatabaseHandle database) {
        System.out.println("===============endFragment()===============");
    }

    @Override
    public CommandStatus selectG12v1(Group12Var1 control, UShort index, DatabaseHandle database) {
        System.out.println("===============selectG12v1()===============");
        try {
            if (index.compareTo(ushort(10)) < 0 && (control.code.opType == OpType.LATCH_ON || control.code.opType == OpType.LATCH_OFF)) {
                return CommandStatus.SUCCESS;
            } else {
                return CommandStatus.NOT_SUPPORTED;
            }
        } finally {
            System.out.println("===============end selectG12v1()===============");
        }
    }

    @Override
    public CommandStatus operateG12v1(Group12Var1 control, UShort index, OperateType opType, DatabaseHandle database) {
        System.out.println("===============operateG12v1()===============");
        try {
            if (index.compareTo(ushort(10)) < 0 && (control.code.opType == OpType.LATCH_ON || control.code.opType == OpType.LATCH_OFF)) {
                boolean status = control.code.opType == OpType.LATCH_ON;
                database.transaction(db ->
                        db.updateBinaryOutputStatus(
                                new BinaryOutputStatus(
                                        index,
                                        status,
                                        new Flags(Flag.ONLINE),
                                        TimeStampUtil.now()
                                ), UpdateOptions.detectEvent()
                        )
                );
                return CommandStatus.SUCCESS;
            } else {
                return CommandStatus.NOT_SUPPORTED;
            }
        } finally {
            System.out.println("===============end operateG12v1()===============");
        }
    }

    @Override
    public CommandStatus selectG41v1(int value, UShort index, DatabaseHandle database) {
        System.out.println("===============selectG41v1()===============");
        try {
            return selectAnalogOutput(index);
        } finally {
            System.out.println("===============end selectG41v1()===============");
        }
    }

    @Override
    public CommandStatus operateG41v1(int value, UShort index, OperateType opType, DatabaseHandle database) {
        System.out.println("===============operateG41v1()===============");
        try {
            return operateAnalogOutput(value, index, database);
        } finally {
            System.out.println("===============end operateG41v1()===============");
        }
    }

    @Override
    public CommandStatus selectG41v2(short value, UShort index, DatabaseHandle database) {
        System.out.println("===============selectG41v2()===============");
        try {
            return selectAnalogOutput(index);
        } finally {
            System.out.println("===============end selectG41v2()===============");
        }
    }

    @Override
    public CommandStatus operateG41v2(short value, UShort index, OperateType opType, DatabaseHandle database) {
        System.out.println("===============operateG41v2()===============");
        try {
            return operateAnalogOutput(value, index, database);
        } finally {
            System.out.println("===============end operateG41v2()===============");
        }
    }

    @Override
    public CommandStatus selectG41v3(float value, UShort index, DatabaseHandle database) {
        System.out.println("===============selectG41v3()===============");
        try {
            return selectAnalogOutput(index);
        } finally {
            System.out.println("===============end selectG41v3()===============");
        }
    }

    @Override
    public CommandStatus operateG41v3(float value, UShort index, OperateType opType, DatabaseHandle database) {
        System.out.println("===============operateG41v3()===============");
        try {
            return operateAnalogOutput(value, index, database);
        } finally {
            System.out.println("===============end operateG41v3()===============");
        }
    }

    @Override
    public CommandStatus selectG41v4(double value, UShort index, DatabaseHandle database) {
        System.out.println("===============selectG41v4()===============");
        try {
            return selectAnalogOutput(index);
        } finally {
            System.out.println("===============end selectG41v4()===============");
        }
    }

    @Override
    public CommandStatus operateG41v4(double value, UShort index, OperateType opType, DatabaseHandle database) {
        System.out.println("===============operateG41v4()===============");
        try {
            return operateAnalogOutput(value, index, database);
        } finally {
            System.out.println("===============end operateG41v4()===============");
        }
    }

    private CommandStatus selectAnalogOutput(UShort index) {
        System.out.println("===============selectAnalogOutput()===============");
        try {
            return index.compareTo(ushort(10)) < 0 ? CommandStatus.SUCCESS : CommandStatus.NOT_SUPPORTED;
        } finally {
            System.out.println("===============end selectAnalogOutput()===============");
        }
    }

    private CommandStatus operateAnalogOutput(double value, UShort index, DatabaseHandle database) {
        System.out.println("===============operateAnalogOutput()===============");
        try {
            if (index.compareTo(ushort(10)) < 0) {
                database.transaction(db ->
                        db.updateAnalogOutputStatus(
                                new AnalogOutputStatus(
                                        index,
                                        value,
                                        new Flags(Flag.ONLINE),
                                        TimeStampUtil.now()
                                ), UpdateOptions.detectEvent()
                        )
                );

                return CommandStatus.SUCCESS;
            } else {
                return CommandStatus.NOT_SUPPORTED;
            }
        } finally {
            System.out.println("===============end operateAnalogOutput()===============");
        }
    }
}
// ANCHOR_END: control_handler