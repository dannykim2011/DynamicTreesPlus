package com.dtteam.dynamictreesplus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DynamicCactusFlowerBlock extends CactusFlowerBlock {

    public static final BooleanProperty PILLAR = BooleanProperty.create("pillar");
    public static final BooleanProperty PIPE = BooleanProperty.create("pipe");

    private static final VoxelShape DEFAULT_SHAPE = Block.box(1.0D, -4.0D, 1.0D, 15.0D, 8.0D, 15.0D);
    private static final VoxelShape PILLAR_SHAPE = Block.box(1.0D, -3.0D, 1.0D, 15.0D, 9.0D, 15.0D);
    private static final VoxelShape PIPE_SHAPE = Block.box(1.0D, -4.0D, 1.0D, 15.0D, 8.0D, 15.0D);

    public DynamicCactusFlowerBlock(final Identifier id, final BlockBehaviour.Properties properties) {
        super(properties.setId(ResourceKey.create(Registries.BLOCK, id)));
        this.registerDefaultState(this.stateDefinition.any().setValue(PILLAR, false).setValue(PIPE, false));
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.getBlock() instanceof CactusBranchBlock || super.mayPlaceOn(state, level, pos);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos,
                                  final CollisionContext context) {
        return state.getValue(PIPE) ? PIPE_SHAPE : state.getValue(PILLAR) ? PILLAR_SHAPE : DEFAULT_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PILLAR, PIPE);
    }

}
