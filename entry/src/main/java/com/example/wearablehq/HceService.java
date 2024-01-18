package com.example.wearablehq;


import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.nfc.cardemulation.HostService;
import ohos.rpc.IRemoteObject;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HceService extends HostService {
    private static final String TAG = "--HarmonyOS--";

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        Logger.getLogger("LOg#me").log(Level.INFO,  "HceService--onStart");
    }

    @Override
    public void onCommand(Intent intent, boolean restart, int startId) {
        super.onCommand(intent, restart, startId);
        Logger.getLogger("LOg#me").log(Level.INFO, "HceService--onCommand");
    }

    @Override
    public byte[] handleRemoteCommand(byte[] bytes, IntentParams intentParams) {
        Logger.getLogger("LOg#me").log(Level.INFO, "HceService----handleRemoteCommand");
        Logger.getLogger("LOg#me").log(Level.INFO, Arrays.toString(bytes));
        return new byte[]{(byte) 0x90, 0x00};
    }

    @Override
    public void disabledCallback(int errCode) {
        Logger.getLogger("LOg#me").log(Level.INFO, "HceService--disabledCallback::errCode:" + errCode);
    }

    @Override
    public IRemoteObject onConnect(Intent intent) {
        return super.onConnect(intent);
    }

    @Override
    public void onDisconnect(Intent intent) {
        super.onDisconnect(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}