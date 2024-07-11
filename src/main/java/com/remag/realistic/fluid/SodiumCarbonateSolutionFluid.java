package com.remag.realistic.fluid;

import com.remag.realistic.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.concurrent.ThreadLocalRandom;

public class SodiumCarbonateSolutionFluid {

    public static class Source extends ForgeFlowingFluid.Source {
        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public void tick(Level level, BlockPos pos, FluidState state) {
            int numItems = ThreadLocalRandom.current().nextInt(1, 5);
            for (int j = 0; j < numItems; j++) {
                ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ModItems.BAKING_POWDER.get().getDefaultInstance());
                level.addFreshEntity(itemEntity);
            }
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            super.tick(level, pos, state);
        }
    }
}
