package com.brainteazer;

import com.brainteazer.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.SubtitlesHud;

public class SubtitlePlusClient implements ClientModInitializer {
	static public MinecraftClient CLIENT;
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		CLIENT = MinecraftClient.getInstance();
		KeyInputHandler.register();
	}
}