package omtteam.omlib.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Created by Keridos on 27/11/16.
 * This Class
 */
@SuppressWarnings({"EmptyMethod", "WeakerAccess"})
@MethodsReturnNonnullByDefault
public abstract class TileEntityBase extends TileEntity {
    protected boolean updateNBT = false;

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        this.save(nbtTagCompound);
        return new SUpdateTileEntityPacket(this.getBlockPos(), 2, nbtTagCompound);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.readFromNBT(pkt.getTag());
        BlockState state = this.level.getBlockState(getBlockPos());
        this.getLevel().notifyBlockUpdate(pos, state, state, 3);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateTag(CompoundNBT tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(super.getUpdateTag());
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean shouldRefresh(World world, BlockPos pos, BlockState oldState, BlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public void markBlockForUpdate() {
        BlockState state = this.level.getBlockState(this.getBlockPos());
        this.level.markAndNotifyBlock(this.getBlockPos(), null, state, state, 3);
    }

    public void setUpdateNBT(boolean shouldUpdateNBT) {
        this.updateNBT = shouldUpdateNBT;
    }
}
