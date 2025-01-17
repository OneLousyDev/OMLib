package omtteam.omlib.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import omtteam.omlib.api.network.ISyncableTE;

/**
 * Created by nico on 23/05/17.
 */
public class MessageOpenGUITile implements IMessage {
    private int x, y, z;

    public MessageOpenGUITile() {
    }

    public MessageOpenGUITile(TileEntity te) {
        this.x = te.getPos().getX();
        this.y = te.getPos().getY();
        this.z = te.getPos().getZ();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public static class MessageHandlerOpenGUITile implements IMessageHandler<MessageOpenGUITile, IMessage> {
        @Override
        public IMessage onMessage(MessageOpenGUITile messageIn, MessageContext ctx) {
            final MessageOpenGUITile message = messageIn;
            final ServerPlayerEntity player = ctx.getServerHandler().player;
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                World world = player.world;
                TileEntity te = world.getTileEntity(new BlockPos(message.x, message.y, message.z));
                if (te instanceof ISyncableTE) {
                    ((ISyncableTE) te).getSyncPlayerList().add(player);
                }
            });
            return null;
        }
    }
}
