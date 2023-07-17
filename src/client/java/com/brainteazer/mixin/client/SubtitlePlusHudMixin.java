package com.brainteazer.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.SubtitlesHud;
import net.minecraft.client.gui.hud.SubtitlesHud.SubtitleEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.text.*;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;
import java.util.Objects;

@Mixin(SubtitlesHud.class)
public class SubtitlePlusHudMixin {
        @Shadow
        @Final
        private MinecraftClient client;

    @Redirect(method = "onSoundPlayed", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private boolean setColor(List<Object> entries, Object entry, SoundInstance sound, WeightedSoundSet soundSet) {

        final boolean[] isNew = {true};

        // get the name of the sound
        String subtitleText = Objects.requireNonNull(soundSet.getSubtitle()).getString();

        // get the position of the sound
        Vec3d soundPosition = new Vec3d(sound.getX(), sound.getY(), sound.getZ());

        long distanceFromPlayer = getDistanceFromPlayer(soundPosition);

        Text text = Text.of(subtitleText + " " + distanceFromPlayer);

        // create a new subtitle entry with position and name of sound
        SubtitleEntry newEntry = new SubtitleEntry(text, soundPosition);

        // check if the sound is already present in the entries (if yes, update the entry)
        entries.forEach(e -> {
            String entryText = ((SubtitleEntry) e).getText().getString();
            if ( entryText.contains( subtitleText )) {
                int index = entries.indexOf(e);
                entries.set(index, newEntry);
                isNew[0] = false;
            }
        });

        // add a new entry if the sound is not already present in the entries
        if (isNew[0]) {
            return entries.add(newEntry);
        }
        return false;
    }

    long getDistanceFromPlayer(Vec3d position) {
        ClientPlayerEntity player = this.client.player;
        assert player != null;
        Vec3d playerPosition = player.getPos();

        // use distance formula for 3D points
        double distance =
                Math.sqrt(
                        Math.pow(playerPosition.x - position.x, 2.0) +
                        Math.pow(playerPosition.y - position.y, 2.0) +
                        Math.pow(playerPosition.z - position.z, 2.0)
                );

        return (long) Math.floor(distance);

    }



}


