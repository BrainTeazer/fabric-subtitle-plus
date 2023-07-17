package com.brainteazer;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubtitlePlus implements ModInitializer {
	public static final String MOD_ID = "subtitleplus";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

	private void SubtitlePlus() {

		return;
	}




	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.ITEM, new Identifier("subtitleplus", "custom-item"), CUSTOM_ITEM);
		LOGGER.info("Hello Fabric world!");
	}
}