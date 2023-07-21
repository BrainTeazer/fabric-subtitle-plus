package com.brainteazer.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.registry.api.GuiProvider;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

@Config(name="subtitleplus")
public class SubtitlePlusConfig implements ConfigData {

    public boolean showDistance = false;
    @ConfigEntry.Gui.Excluded
    public static SubtitlePlusConfig CONFIG;

    public enum positionEnum {
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        TOP_CENTER,
        TOP,
        LEFT,
        RIGHT

    }


    public Set<positionEnum> positions = new HashSet<positionEnum>(){{
        add(positionEnum.TOP);
    }
    };




    static {
        AutoConfig.register(SubtitlePlusConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SubtitlePlusConfig.class).getConfig();

    }
}
