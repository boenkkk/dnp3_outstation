package io.stepfunc.dnp3_outstation;

import io.stepfunc.dnp3.BroadcastAction;
import io.stepfunc.dnp3.FunctionCode;
import io.stepfunc.dnp3.OutstationInformation;
import io.stepfunc.dnp3.RequestHeader;
import org.joou.UByte;

public class TestOutstationInformation implements OutstationInformation {

    @Override
    public void processRequestFromIdle(RequestHeader header) {
        System.out.println("processRequestFromIdle() header: "+header);
    }

    @Override
    public void broadcastReceived(FunctionCode functionCode, BroadcastAction action) {
        System.out.println("broadcastReceived() functionCode|action"+functionCode+"|"+action);
    }

    @Override
    public void enterSolicitedConfirmWait(UByte ecsn) {
        System.out.println("enterSolicitedConfirmWait() ecsn: "+ecsn);
    }

    @Override
    public void solicitedConfirmTimeout(UByte ecsn) {
        System.out.println("solicitedConfirmTimeout() ecsn: "+ecsn);
    }

    @Override
    public void solicitedConfirmReceived(UByte ecsn) {
        System.out.println("solicitedConfirmReceived() ecsn: "+ecsn);
    }

    @Override
    public void solicitedConfirmWaitNewRequest() {
        System.out.println("solicitedConfirmWaitNewRequest()");
    }

    @Override
    public void wrongSolicitedConfirmSeq(UByte ecsn, UByte seq) {
        System.out.println("wrongSolicitedConfirmSeq() ecsn|seq: "+ecsn+"|"+seq);
    }

    @Override
    public void unexpectedConfirm(boolean unsolicited, UByte seq) {
        System.out.println("unexpectedConfirm() unsolicited|seq: "+unsolicited+"|"+seq);
    }

    @Override
    public void enterUnsolicitedConfirmWait(UByte ecsn) {
        System.out.println("enterUnsolicitedConfirmWait() ecsn: "+ecsn);
    }

    @Override
    public void unsolicitedConfirmTimeout(UByte ecsn, boolean retry) {
        System.out.println("unsolicitedConfirmTimeout() ecsn|retry: "+ecsn+"|"+retry);
    }

    @Override
    public void unsolicitedConfirmed(UByte ecsn) {
        System.out.println("unsolicitedConfirmed() ecsn: "+ecsn);
    }

    @Override
    public void clearRestartIin() {
        System.out.println("clearRestartIin()");
    }
}