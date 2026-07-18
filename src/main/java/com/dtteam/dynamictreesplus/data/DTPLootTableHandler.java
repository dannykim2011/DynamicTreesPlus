package com.dtteam.dynamictreesplus.data;

import com.dtteam.dynamictrees.data.DTLootTableBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

/** Loot-table factories used by dynamic mushroom caps and stems. */
public final class DTPLootTableHandler {

    private DTPLootTableHandler() {
    }

    public static LootTable.Builder createMushroomBranchDrops(Block primitiveLogBlock,
                                                               HolderLookup.Provider registries) {
        return DTLootTableBuilder.createBranchDrops(primitiveLogBlock, Items.STICK, registries);
    }

    public static LootTable.Builder createCapBlockDrops(Block primitiveCapBlock, Item primitiveSapling,
                                                         int countMin, int countMax,
                                                         HolderLookup.Provider registries) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(primitiveCapBlock)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(countMin, countMax)))));
    }

    public static LootTable.Builder createCapDrops(Block primitiveCapBlock, Item primitiveSapling,
                                                    ContextKeySet parameterSet,
                                                    HolderLookup.Provider registries) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(primitiveCapBlock)));
    }
}
