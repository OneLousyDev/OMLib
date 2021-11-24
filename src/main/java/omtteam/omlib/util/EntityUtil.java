package omtteam.omlib.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;

/**
 * Created by Keridos on 10/11/17.
 * This Class
 */
public class EntityUtil {
    public static Class<? extends EntityType> findClassById(String id) {
        EntityType<?> entry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
        return entry == null ? null : entry.getClass();
    }

    @SuppressWarnings("WhileLoopReplaceableByForEach")
    public static int getEntityArmor(Entity entity) {
        int armor = 0;
        Iterator<ItemStack> iter = entity.getArmorSlots().iterator();
        
        while (iter.hasNext()) {
            ItemStack itemStack = iter.next();
            armor += itemStack.getItem() instanceof ArmorItem ? ((ArmorItem) itemStack.getItem()).damageReduceAmount : 0;
        }
        return armor;
    }
}
