package com.example.wearablehq;

import ohos.app.Context;
import ohos.bundle.IBundleManager;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    public static final Integer REQUEST_CODE = 300;
    static final String PERMISSION_LOCATION = "ohos.permission.LOCATION";

    public static void requestPermissions(Context context) {
        List<String> applyPermissions = new ArrayList<>(0);
        if (context.verifySelfPermission(PERMISSION_LOCATION) != IBundleManager.PERMISSION_GRANTED) {
            if (context.canRequestPermission(PERMISSION_LOCATION)) {
                applyPermissions.add(PERMISSION_LOCATION);
            }
        }
        if (!applyPermissions.isEmpty()) {
            context.requestPermissionsFromUser(applyPermissions.toArray(new String[0]), REQUEST_CODE);
        }
    }
}
