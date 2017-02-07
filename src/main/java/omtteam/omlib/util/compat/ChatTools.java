package omtteam.omlib.util.compat;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class ChatTools {

    public static void addChatMessage(@Nonnull ICommandSender sender, @Nonnull ITextComponent component) {
        if (sender instanceof EntityPlayer) {
            ((EntityPlayer) sender).addChatComponentMessage(component);
        } else {
            sender.addChatMessage(component);
        }
    }

}