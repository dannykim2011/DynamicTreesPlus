package com.dtteam.dynamictreesplus.block;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class CactusFruit extends Fruit {

    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(CactusFruit::new);

    public CactusFruit(Identifier registryName) {
        super(registryName);
    }

    @Override
    public void place(LevelAccessor world, BlockPos pos, @Nullable Float seasonValue) {
        BlockState state = getStateForAge(0);
        world.setBlock(pos, state, Block.UPDATE_ALL);
    }

    @Override
    public void placeDuringWorldGen(LevelAccessor world, BlockPos pos, @Nullable Float seasonValue) {
        BlockState state = getStateForAge(getAgeForWorldGen(world, pos, seasonValue));
        world.setBlock(pos, state, Block.UPDATE_ALL);
    }

}
