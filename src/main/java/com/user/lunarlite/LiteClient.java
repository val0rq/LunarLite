package com.user.lunarlite;

import com.user.lunarlite.hud.HudManager;
import com.user.lunarlite.input.InputManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class LiteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudManager.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            InputManager.tick(client);
            HudManager.tick(client);
        });
    }
}