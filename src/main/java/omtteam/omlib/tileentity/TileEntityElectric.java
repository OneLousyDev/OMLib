package omtteam.omlib.tileentity;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import mcp.MethodsReturnNonnullByDefault;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import omtteam.omlib.compatibility.OMLibModCompatibility;
import omtteam.omlib.handler.OMConfig;
import omtteam.omlib.power.OMEnergyStorage;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static omtteam.omlib.compatibility.OMLibModCompatibility.*;

/**
 * Created by Keridos on 27/04/17.
 * This Class
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
@Optional.InterfaceList({
        @Optional.Interface(iface = "cofh.redstoneflux.api.IEnergyReceiver", modid = CoFHApiModId)})
@MethodsReturnNonnullByDefault
public abstract class TileEntityElectric extends TileEntityOwnedBlock {
    protected OMEnergyStorage storage;
    protected Object teslaContainer;
    protected Object euContainer;
    protected Object rfContainer;
    protected boolean wasAddedToEnergyNet = false;

    @Override
    public CompoundNBT writeToNBT(CompoundNBT nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("maxStorage", this.storage.getMaxEnergyStored());
        nbtTagCompound.setInteger("energyStored", this.storage.getEnergyStored());
        nbtTagCompound.setInteger("maxIO", this.storage.getMaxReceive());
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(CompoundNBT nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        this.storage.setCapacity(nbtTagCompound.getInteger("maxStorage"));
        this.storage.setEnergyStored(nbtTagCompound.getInteger("energyStored"));
        this.storage.setMaxReceive(nbtTagCompound.getInteger("maxIO"));
    }

    @Override
    public void onLoad() {
        if (IC2Loaded && OMConfig.GENERAL.EUSupport && !wasAddedToEnergyNet && !this.getWorld().isRemote) {
            addToIc2EnergyNetwork();
            wasAddedToEnergyNet = true;
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        onChunkUnload();
    }

    @Override
    public void onChunkUnload() {
        if (wasAddedToEnergyNet &&
                OMLibModCompatibility.IC2Loaded) {
            removeFromIc2EnergyNetwork();

            wasAddedToEnergyNet = false;
        }
    }

    //-------------------------- Energy API Functions --------------------------------------------------

    public int getEnergyStored(@Nullable Direction facing) {
        OMEnergyStorage storage = (OMEnergyStorage) this.getCapability(CapabilityEnergy.ENERGY, facing);
        if (storage != null) {
            return storage.getEnergyStored();
        }
        return 0;
    }

    public int getMaxEnergyStored(@Nullable Direction facing) {
        OMEnergyStorage storage = (OMEnergyStorage) this.getCapability(CapabilityEnergy.ENERGY, facing);
        if (storage != null) {
            return storage.getMaxEnergyStored();
        }
        return 0;
    }

    public void setCapacity(int maxStorage, @Nullable Direction facing) {
        OMEnergyStorage storage = (OMEnergyStorage) this.getCapability(CapabilityEnergy.ENERGY, facing);
        if (storage != null) {
            storage.setCapacity(maxStorage);
        }
    }

    public void setEnergyStored(int energy, @Nullable Direction facing) {
        OMEnergyStorage storage = (OMEnergyStorage) this.getCapability(CapabilityEnergy.ENERGY, facing);
        if (storage != null) {
            storage.setEnergyStored(energy);
        }
    }

    public int extractEnergy(int energy, @Nullable Direction facing) {
        OMEnergyStorage storage = (OMEnergyStorage) this.getCapability(CapabilityEnergy.ENERGY, facing);
        if (storage != null) {
            return storage.extractEnergy(energy, false);
        }
        return 0;
    }

    //--------------------------------------------------------------------------------------------------

    @SuppressWarnings({"unchecked"})
    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (OMLibModCompatibility.TeslaLoaded) {
            if (hasTeslaCapability(capability, facing) && getTeslaCapability(capability, facing) != null) {
                return getTeslaCapability(capability, facing);
            }
        }
        if (IC2Loaded) {
            if (hasEUCapability(capability, facing) && getEUCapability(capability, facing) != null) {
                return getEUCapability(capability, facing);
            }
        }
        if (CoFHApiLoaded) {
            if (hasRFCapability(capability, facing) && getRFCapability(capability, facing) != null) {
                return getRFCapability(capability, facing);
            }
        }
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) storage;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hasCapability(Capability<?> capability, @Nullable Direction facing) {
        // This method replaces the instanceof checks that would be used in an interface based
        // system. It can be used by other things to see if the TileEntity uses a capability or
        // not.
        if (OMLibModCompatibility.TeslaLoaded) {
            if (hasTeslaCapability(capability, facing)) {
                return true;
            }
        }
        if (IC2Loaded) {
            if (hasEUCapability(capability, facing)) {
                return true;
            }
        }
        if (CoFHApiLoaded) {
            if (hasRFCapability(capability, facing)) {
                return true;
            }
        }
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }
}
