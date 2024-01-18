package com.example.wearablehq.slice;

import com.example.wearablehq.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
    }

    @Override
    public void onActive() {
        super.onActive();
        getAbility().onLeaveForeground();
       Button btnClickAndGo = (Button) findComponentById(ResourceTable.Id_button);
       btnClickAndGo.setClickedListener(component -> {
           Intent intent = new Intent();
           Operation operation = new Intent.OperationBuilder()
                   .withDeviceId("")
                   .withBundleName("com.example.wearablehq")
                   .withAbilityName("com.example.wearablehq.SecondAbility")
                   .build();
           intent.setOperation(operation);
           startAbility(intent);
       });

    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
