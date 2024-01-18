package com.example.wearablehq;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.bundle.ElementName;
import ohos.bundle.IBundleManager;
import ohos.location.Location;
import ohos.location.LocatorCallback;
import ohos.nfc.NfcController;
import ohos.nfc.cardemulation.CardEmulation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainAbility extends Ability {
    private static final String TAG = "--HarmonyOS--";
    private static final String APP_TAG = "LOGGG";
    private NfcController mNfcController;
    private CardEmulation mCardEmulation;
    private ElementName elementName;
    private Location mCurrentLocation;
    private Boolean isFocusedCurrentLocation = false;

    private final LocatorCallback mLocationCallback = new LocatorCallback() {
        @Override
        public void onLocationReport(Location location) {
            mCurrentLocation = location;
            String s = String.valueOf(location.getLongitude());
            LogUtil.debug(APP_TAG, "_____________________________________________________________");
            LogUtil.debug(APP_TAG, "_____________________________________________________________");
            LogUtil.debug(APP_TAG, "Location found! time: " + (LocalDateTime.now().toString()) + "=" + s);
            if (isFocusedCurrentLocation == false) {

                if (mCurrentLocation != null && mCurrentLocation.getLongitude() != 0) {

                }

            }
        }

        @Override
        public void onStatusChanged(int i) {
        }

        @Override
        public void onErrorReport(int i) {

        }
    };

    private LocationUtil mLocationUtil;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initData();
        PermissionUtil.requestPermissions(getContext());

        startLocationService();
    }

    @Override
    public void onRequestPermissionsFromUserResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsFromUserResult(requestCode, permissions, grantResults);
        if (requestCode != PermissionUtil.REQUEST_CODE) {
            return;
        }
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == IBundleManager.PERMISSION_GRANTED) {
                this.restart();

            } else {
            }
        }
    }

    private void initData() {
        mNfcController = NfcController.getInstance(this);
        mCardEmulation = CardEmulation.getInstance(mNfcController);
        mCardEmulation.setListenMode(CardEmulation.ENABLE_MODE_ALL);
        elementName = new ElementName(this.getElementName().getDeviceId(),
                "com.example.wearablehq",
                "com.example.wearablehq.HceServiceShellService");
        register();
    }

    private void register() {
        List<String> list = new ArrayList<>();
        list.add("325041592E5359532E4444463031");
        list.add("A0000000041010");
        list.add("A0000000042203");
        list.add("A0000000031010");
        mCardEmulation.registerForegroundPreferred(this, elementName);
        mCardEmulation.registerAids(elementName, CardEmulation.CATEGORY_PAYMENT, list);
        mCardEmulation.isDefaultForType(elementName, CardEmulation.CATEGORY_PAYMENT);
    }

    private void queryAids() {
        Logger.getLogger("LOg#me").log(Level.WARNING, "MainAbility--queryAids");
        try {
            List aids = mCardEmulation.getAids(elementName, CardEmulation.CATEGORY_PAYMENT);
            Logger.getLogger("LOg#me").log(Level.WARNING, "MainAbility--aids::" + aids.toString() + ":size:" + aids.size());
        } catch (IllegalArgumentException e) {
            Logger.getLogger("LOg#me").log(Level.WARNING, "IllegalArgumentException when getAids");
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        queryAids();
    }

    private void startLocationService() {
        LogUtil.debug(APP_TAG, "Location service started! time: " + LocalDateTime.now().toString());
        mLocationUtil = new LocationUtil(getContext());
        mLocationUtil.startDeviceLocation(mLocationCallback);
    }
}

