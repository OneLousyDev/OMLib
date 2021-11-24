package omtteam.omlib.util;

import java.util.Objects;

import net.minecraft.nbt.CompoundNBT;

/**
 * Created by Keridos on 17/08/17.
 * This Class is the class used for declaring targeting settings to the targeting system for turrets.
 */
public class TargetingSettings {
    private boolean targetPlayers;
    private boolean targetMobs;
    private boolean targetPassive;
    private int range;
    private int maxRange;

    private TargetingSettings() {
    }

    public TargetingSettings(boolean targetPlayers, boolean targetMobs, boolean targetPassive, int range,
                             int maxRange) {
        this.targetPlayers = targetPlayers;
        this.targetMobs = targetMobs;
        this.targetPassive = targetPassive;
        this.range = range;
        this.maxRange = maxRange;
    }

    public static TargetingSettings readFromNBT(CompoundNBT nbtTagCompound) {
        TargetingSettings settings = new TargetingSettings();
        if (nbtTagCompound.contains("targetingSettings")) {
            CompoundNBT nbt = nbtTagCompound.getCompound("targetingSettings");
            settings.targetPlayers = nbt.getBoolean("targetPlayers");
            settings.targetMobs = nbt.getBoolean("targetMobs");
            settings.targetPassive = nbt.getBoolean("targetPassive");
            settings.range = nbt.getInt("range");
            settings.maxRange = nbt.getInt("maxRange");
        } else {
            settings.targetPlayers = nbtTagCompound.getBoolean("attacksPlayers");
            settings.targetMobs = nbtTagCompound.getBoolean("attacksMobs");
            settings.targetPassive = nbtTagCompound.getBoolean("attacksNeutrals");
            settings.range = nbtTagCompound.getInt("currentMaxRange");
            settings.maxRange = nbtTagCompound.getInt("upperBoundMaxRange");
        }
        return settings;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbtTagCompound) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("targetPlayers", this.targetPlayers);
        nbt.putBoolean("targetMobs", this.targetMobs);
        nbt.putBoolean("targetPassive", this.targetPassive);
        nbt.putInt("range", this.range);
        nbt.putInt("maxRange", this.maxRange);
        nbtTagCompound.put("targetingSettings", nbt);
        return nbtTagCompound;
    }

    public boolean isTargetPlayers() {
        return targetPlayers;
    }

    public TargetingSettings setTargetPlayers(boolean targetPlayers) {
        this.targetPlayers = targetPlayers;
        return this;
    }

    public boolean isTargetMobs() {
        return targetMobs;
    }

    public TargetingSettings setTargetMobs(boolean targetMobs) {
        this.targetMobs = targetMobs;
        return this;
    }

    public boolean isTargetPassive() {
        return targetPassive;
    }

    public TargetingSettings setTargetPassive(boolean targetPassive) {
        this.targetPassive = targetPassive;
        return this;
    }

    public int getRange() {
        return range;
    }

    public TargetingSettings setRange(int range) {
        if (range <= maxRange && range > -1) {
            this.range = range;
        }
        return this;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public TargetingSettings setMaxRange(int maxRange) {
        this.maxRange = maxRange;
        if (this.maxRange < this.range) {
            this.range = this.maxRange;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetingSettings that = (TargetingSettings) o;
        return targetPlayers == that.targetPlayers &&
                targetMobs == that.targetMobs &&
                targetPassive == that.targetPassive &&
                range == that.range &&
                this.maxRange == that.range;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetPlayers, targetMobs, targetPassive, range, maxRange);
    }
}
