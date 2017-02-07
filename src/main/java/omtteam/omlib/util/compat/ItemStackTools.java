package omtteam.omlib.util.compat;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemStackTools {

    /**
     * Add (or decrease) a number of items from a stack.
     * If the number of items drops below 0 then null will be returned on 1.10 and the
     * 'null' itemstack on 1.11. Otherwise the same modified stack is returned.
     */
    @Nonnull
    public static ItemStack incStackSize(@Nonnull ItemStack stack, int amount) {
        stack.grow(amount);
        return stack;
    }

    /**
     * Make a safe copy of an itemstack
     */
    @Nonnull
    public static ItemStack safeCopy(@Nonnull ItemStack stack) {
        return stack.copy();
    }

    /**
     * Get the stacksize from a stack
     */
    public static int getStackSize(@Nonnull ItemStack stack) {
        return stack.getCount();
    }

    /**
     * Set the stacksize on a stack. Returns the same stack or null if the new
     * amount was 0. On 1.11 it will return the 'null' itemstack
     * Note that this will modify the stack also if amount == 0. On 1.10
     * the stacksize will be set to 0 and on 1.11 the stack will become the EMPTY
     * stack.
     */
    @Nonnull
    public static ItemStack setStackSize(@Nonnull ItemStack stack, int amount) {
        if (amount <= 0) {
            stack.setCount(0);
            return ItemStack.EMPTY;
        }
        stack.setCount(amount);
        return stack;
    }

    /**
     * Check if this is a valid stack. Tests for null on 1.10.
     */
    public static boolean isValid(@Nonnull ItemStack stack) {
        return !stack.isEmpty();
    }

    /**
     * Check if this is an empty stack. Tests for null on 1.10.
     */
    public static boolean isEmpty(@Nonnull ItemStack stack) {
        return stack.isEmpty();
    }

    public static void makeEmpty(@Nonnull ItemStack stack) {
        stack.setCount(0);
    }

    /**
     * Load an ItemStack from NBT.
     */
    @Nonnull
    public static ItemStack loadFromNBT(@Nonnull NBTTagCompound nbt) {
        return new ItemStack(nbt);
    }

    @Nonnull
    public static ItemStack getEmptyStack() {
        return ItemStack.EMPTY;
    }

    /**
     * Extract itemstack out of a slot and return a new stack.
     * Supports both IItemHandler as IInventory
     *
     * @param tileEntity
     * @param slot
     * @param amount
     */
    @Nonnull
    public static ItemStack extractItem(@Nullable TileEntity tileEntity, int slot, int amount) {
        if (tileEntity != null && tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            IItemHandler capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            return capability.extractItem(slot, amount, false);
        } else if (tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;
            return inventory.decrStackSize(slot, amount);
        }
        return ItemStackTools.getEmptyStack();
    }

    /**
     * Get an item from an inventory
     * Supports both IItemHandler as IInventory
     *
     * @param tileEntity
     * @param slot
     */
    @Nonnull
    public static ItemStack getStack(@Nullable TileEntity tileEntity, int slot) {
        if (tileEntity != null && tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            IItemHandler capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            return capability.getStackInSlot(slot);
        } else if (tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;
            return inventory.getStackInSlot(slot);
        }
        return ItemStackTools.getEmptyStack();
    }

    /**
     * Set a stack in a specific slot. This will totally replace whatever was in the slot before
     * Supports both IItemHandler as IInventory. Does not check for failure
     *
     * @param tileEntity
     * @param slot
     * @param stack
     */
    public static void setStack(@Nullable TileEntity tileEntity, int slot, @Nonnull ItemStack stack) {
        if (tileEntity != null && tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            IItemHandler capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            capability.extractItem(slot, 64, false);        // Clear slot
            capability.insertItem(slot, stack, false);
        } else if (tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;
            inventory.setInventorySlotContents(slot, stack);
        }
    }

    public static List<ItemStack> getOres(String name) {
        return OreDictionary.getOres(name);
    }

    public static List<ItemStack> getOres(String name, boolean alwaysCreateEntry) {
        return OreDictionary.getOres(name, alwaysCreateEntry);
    }
}
