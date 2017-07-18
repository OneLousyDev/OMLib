package omtteam.omlib.compatibility.minecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CompatCreativeTabs extends CreativeTabs {

    public CompatCreativeTabs(String label) {
        super(label);
    }

    protected abstract Item getItem();

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(getItem());
    }
}