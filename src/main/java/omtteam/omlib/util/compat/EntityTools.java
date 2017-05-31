package omtteam.omlib.util.compat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class EntityTools {

    /**
     * This method attempts to fix an old-style (1.10.2) entity Id and convert it to the
     * string representation of the new ResourceLocation. The 1.10 version of this function will just return
     * the given id
     * This does not work for modded entities.
     * @param id an old-style entity id as used in 1.10
     * @return
     */
    public static String fixEntityId(String id) {
        return id;
    }


    /**
     * On 1.11: return the string representation of the ResourceLocation that belongs with the entity class
     * On 1.10: return the entity name
     */
    public static String findEntityIdByClass(Class<? extends Entity> clazz) {
        return EntityList.CLASS_TO_NAME.get(clazz);
    }

    /**
     * Return the unlocalized name based by class.
     */
    public static String findEntityUnlocNameByClass(Class<? extends Entity> clazz) {
        return EntityList.CLASS_TO_NAME.get(clazz);
    }

    /**
     * Return a stream of all registered entities. The strings returned are string representations
     * of the resource location on 1.11 and entity names on 1.10.
     */
    public static Stream<String> getEntities() {
        return EntityList.NAME_TO_CLASS.keySet().stream();
    }

    /**
     * Get the localized name of an entity based on class
     */
    public static String findEntityLocNameByClass(Class<? extends Entity> clazz) {
        return I18n.translateToLocal(EntityList.CLASS_TO_NAME.get(clazz));
    }

    /**
     * Return the entity name (localized name)
     */
    public static String getEntityName(Entity entity) {
        if (entity instanceof EntitySkeleton && ((EntitySkeleton) entity).func_189771_df() == SkeletonType.WITHER) {
            return "Wither Skeleton";
        }
        if (entity instanceof EntitySkeleton && ((EntitySkeleton) entity).func_189771_df() == SkeletonType.STRAY) {
            return "Stray Skeleton";
        }
        if (entity instanceof EntityPigZombie) {
            return "Zombie Pigman";
        }
        return entity.getName();
    }


    /**
     * Find the entity class based on the given ID. On 1.11 this ID should be
     * a string representation of the ResourceLocation for that entity. On 1.10 it
     * should be the name of that entity. In any case, the resuilt of fixEntityID()
     * should work as input.
     */
    public static Class<? extends Entity> findClassById(String id) {
        return EntityList.NAME_TO_CLASS.get(id);
    }

    /**
     * Create an entity given a mobId. On 1.11 this should be the string representation of
     * the ResourceLocation for that entity. On 1.10 the entity name. In either case the
     * result of fixEntityId will work.
     */
    public static EntityLiving createEntity(World world, String mobId) {
        Class<? extends Entity> clazz;
        if ("WitherSkeleton".equals(mobId)) {
            clazz = EntitySkeleton.class;
        } else if ("StraySkeleton".equals(mobId)) {
            clazz = EntitySkeleton.class;
        } else {
            clazz = findClassById(mobId);
        }
        EntityLiving entityLiving = null;
        try {
            entityLiving = (EntityLiving) clazz.getConstructor(World.class).newInstance(world);
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
        if ("WitherSkeleton".equals(mobId)) {
            ((EntitySkeleton) entityLiving).func_189768_a(SkeletonType.WITHER);
        } else if ("StraySkeleton".equals(mobId)) {
            ((EntitySkeleton) entityLiving).func_189768_a(SkeletonType.STRAY);
        } else if (entityLiving instanceof EntitySkeleton) {
            // Force non-wither otherwise
            ((EntitySkeleton) entityLiving).func_189768_a(SkeletonType.NORMAL);
        }
        return entityLiving;
    }

    /**
     * Given the class of an entity returns the mobId. On 1.10 this correctly
     * works with wither and stray skeletons by using the provided entity
     */
    public static String findId(Class<? extends Entity> clazz, Entity entity) {
        if (entity instanceof EntitySkeleton) {
            EntitySkeleton skeleton = (EntitySkeleton) entity;
            if (skeleton.func_189771_df() == SkeletonType.WITHER) {
                return "WitherSkeleton";
            } else if (skeleton.func_189771_df() == SkeletonType.STRAY) {
                return "StraySkeleton";
            }
        }
        return EntityList.CLASS_TO_NAME.get(clazz);
    }

    /**
     * Set the type of mob on a spawner. The 1.10 version uses entityName. The 1.11 version uses the resource location
     * @param resourceLocation
     * @param entityName
     */
    public static void setSpawnerEntity(@Nonnull World world, @Nonnull TileEntityMobSpawner spawner, @Nonnull ResourceLocation resourceLocation, @Nonnull String entityName) {
        MobSpawnerBaseLogic mobspawnerbaselogic = spawner.getSpawnerBaseLogic();
        mobspawnerbaselogic.setEntityName(entityName);
        spawner.markDirty();
    }

    public static void moveEntity(Entity entity, double x, double y, double z) {
        entity.moveEntity(x, y, z);
    }

    @SuppressWarnings("SameParameterValue")
    public static void registerModEntity(ResourceLocation resourceLocation, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public static void registerModEntity(ResourceLocation resourceLocation, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
    }
}
