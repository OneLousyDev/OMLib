package omtteam.omlib.util.camo;

import static omtteam.omlib.util.world.BlockUtil.getBlockStateFromNBT;
import static omtteam.omlib.util.world.BlockUtil.writeBlockFromStateToNBT;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;

public class CamoSettings {
    protected BlockState camoBlockState;
    protected int lightValue = 0;
    protected int lightOpacity = 15;

    public static CamoSettings getSettingsFromNBT(CompoundNBT tag) {
        CamoSettings settings = new CamoSettings();
        settings.setCamoBlockState(getBlockStateFromNBT(tag));
        settings.setLightValue(tag.getInt("light_value"));
        settings.setLightOpacity(tag.getInt("light_opacity"));
        return settings;
    }

    public BlockState getCamoBlockState() {
        return camoBlockState;
    }

    public void setCamoBlockState(BlockState camoBlockState) {
        this.camoBlockState = camoBlockState;
    }

    public int getLightValue() {
        return lightValue;
    }

    public void setLightValue(int lightValue) {
        this.lightValue = lightValue;
    }

    public int getLightOpacity() {
        return lightOpacity;
    }

    public void setLightOpacity(int lightOpacity) {
        this.lightOpacity = lightOpacity;
    }

    public void writeNBT(CompoundNBT tag) {
        writeBlockFromStateToNBT(tag, this.camoBlockState);
        tag.putInt("light_value", this.lightValue);
        tag.putInt("light_opacity", this.lightOpacity);
    }
}
