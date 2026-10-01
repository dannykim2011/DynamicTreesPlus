package com.dtteam.dynamictreesplus.systems.featuregen;

import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.dtteam.dynamictreesplus.block.CactusBranchBlock;
import com.dtteam.dynamictreesplus.block.DynamicCactusFlowerBlock;
import com.dtteam.dynamictreesplus.init.DTPRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class CactusFlowerGenFeature extends GenFeature {

    private final boolean pillar;
    private final boolean pipe;

    public CactusFlowerGenFeature(final Identifier registryName, final boolean pillar, final boolean pipe) {
        super(registryName);
        this.pillar = pillar;
        this.pipe = pipe;
    }

    @Override
    protected void registerProperties() {
        this.register(PLACE_CHANCE);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration().with(PLACE_CHANCE, 0.25F);
    }

    @Override
    protected boolean postGenerate(final GenFeatureConfiguration configuration, final PostGenerationContext context) {
        if (!context.isWorldGen() || !context.level().getBiome(context.pos()).is(Biomes.DESERT)) {
            return false;
        }

        final LevelAccessor level = context.level();
        final Block flower = DTPRegistries.DYNAMIC_CACTUS_FLOWER.get();
        boolean placed = false;

        for (final BlockPos endPoint : context.endPoints()) {
            final BlockState endState = level.getBlockState(endPoint);
            if (!(endState.getBlock() instanceof CactusBranchBlock)
                    || context.random().nextFloat() > configuration.get(PLACE_CHANCE)) {
                continue;
            }

            final BlockPos flowerPos = endPoint.above();
            final BlockState flowerState = flower.defaultBlockState()
                    .setValue(DynamicCactusFlowerBlock.PILLAR,
                            this.pillar && endState.getValue(CactusBranchBlock.TRUNK_TYPE) != CactusBranchBlock.CactusThickness.BRANCH)
                    .setValue(DynamicCactusFlowerBlock.PIPE, this.pipe);

            if (level.getBlockState(flowerPos).isAir() && flowerState.canSurvive(level, flowerPos)) {
                level.setBlock(flowerPos, flowerState, Block.UPDATE_ALL);
                placed = true;
            }
        }

        return placed;
    }

}
