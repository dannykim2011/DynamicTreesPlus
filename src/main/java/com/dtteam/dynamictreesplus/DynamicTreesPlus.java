package com.dtteam.dynamictreesplus;

import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictreesplus.init.DTPConfigs;
import com.dtteam.dynamictreesplus.init.DTPRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(DynamicTreesPlus.MOD_ID)
public class DynamicTreesPlus {

    public static final String MOD_ID = "dynamictreesplus";
    public static final Identifier CACTUS = DynamicTreesPlus.location("cactus");
    public static final Identifier MUSHROOM = DynamicTreesPlus.location("mushroom");

    public DynamicTreesPlus(IEventBus modBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.SERVER, DTPConfigs.SERVER_CONFIG);
        modContainer.registerConfig(ModConfig.Type.COMMON, DTPConfigs.COMMON_CONFIG);

        modBus.register(DTPRegistries.class);
        NeoForgeRegistryHandler.setup(MOD_ID, modBus);

        DTPRegistries.setup();
    }

    public static Identifier location(final String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

}
