package com.dtteam.dynamictreesplus.worldgen.canceller;

import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

/**
 * Cancels block-column features that place the configured cactus block type.
 *
 * @author Harley O'Connor
 */
public class CactusFeatureCanceller<T extends Block> extends FeatureCanceller {

    private static final RandomSource PLACEHOLDER_RANDOM = RandomSource.create(0L);

    private final Class<T> cactusBlockClass;

    public CactusFeatureCanceller(final Identifier registryName, Class<T> cactusBlockClass) {
        super(registryName);
        this.cactusBlockClass = cactusBlockClass;
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        Identifier featureResLoc = BuiltInRegistries.FEATURE.getKey(configuredFeature.feature());
        if (featureResLoc == null)
            return false;

        FeatureConfiguration featureConfig = configuredFeature.config();

        if (!(featureConfig instanceof BlockColumnConfiguration blockColumnConfiguration) || !featureCancellations.shouldCancelNamespace(featureResLoc.getNamespace())) {
            return false;
        }

        for (BlockColumnConfiguration.Layer layer : blockColumnConfiguration.layers()) {
            final BlockStateProvider stateProvider = layer.state();
            if (!(stateProvider instanceof SimpleStateProvider simpleStateProvider)) {
                continue;
            }

            if (this.cactusBlockClass.isInstance(simpleStateProvider.getState(null, PLACEHOLDER_RANDOM, BlockPos.ZERO).getBlock())) {
                return true;
            }
        }

        return false;
    }
}
