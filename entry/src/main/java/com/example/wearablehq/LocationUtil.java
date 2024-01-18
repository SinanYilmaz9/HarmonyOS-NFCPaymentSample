package com.example.wearablehq;

import ohos.app.Context;
import ohos.location.Locator;
import ohos.location.LocatorCallback;
import ohos.location.RequestParam;

public class LocationUtil {
    private Context context;

    public LocationUtil(Context context) {
        this.context = context;
    }

    private RequestParam getRequestParam() {
        return new RequestParam(RequestParam.PRIORITY_FAST_FIRST_FIX, 0, 0);
    }

    public void startDeviceLocation(LocatorCallback requestLocationCallback) {
        RequestParam requestParam = getRequestParam();
        Locator locator = new Locator(context);
        locator.requestOnce(requestParam, requestLocationCallback);

    }
}

