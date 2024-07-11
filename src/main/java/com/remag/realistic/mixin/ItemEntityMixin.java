package com.remag.realistic.mixin;

import com.remag.realistic.block.ModBlocks;
import com.remag.realistic.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow
    public abstract ItemStack getItem();

    @Shadow public abstract void setItem(ItemStack p_32046_);

    @Unique
    private int realisticEverything$landParticleDelay = 0;

    @Unique
    private int realisticEverything$waterParticleDelay = 0;

    @Unique
    private int realisticEverything$washingDelay = 0;

    @Inject(at = @At("RETURN"), method = "tick")
    void handleItemEntities(CallbackInfo ci) {
        if (this.isRemoved()) {
            return;
        }
        if (this.getItem().getItem() == ModItems.CUT_WASHED_SUGAR_CANE.get() && !this.isInFluidType(Fluids.WATER.getFluidType())) {
            if (level().isClientSide()) {
                realisticEverything$landParticleDelay++;
                if (realisticEverything$landParticleDelay > 10) {
                    realisticEverything$landParticleDelay = 0;
                    level().addParticle(ParticleTypes.DRIPPING_WATER, this.getX(), this.getY() + 0.2f, this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
        if (this.getItem().getItem() == ModItems.TRONA_SHARD.get() && this.isInFluidType(Fluids.WATER.getFluidType())) {
            level().setBlock(this.blockPosition(), ModBlocks.SODIUM_CARBONATE_SOLUTION.get().defaultBlockState(), 3);
            this.setItem(new ItemStack(ModItems.TRONA_SHARD.get(), this.getItem().getCount() - 1));
        }
        if (this.getItem().getItem() == ModItems.CUT_SUGAR_CANE.get() && this.isInFluidType(Fluids.WATER.getFluidType())) {
            int itemCount = this.getItem().getCount();
            realisticEverything$washingDelay++;
            if (level().isClientSide()) {
                realisticEverything$waterParticleDelay++;
                if (realisticEverything$waterParticleDelay > 10) {
                    realisticEverything$waterParticleDelay = 0;
                    level().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.4f, this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
            if (realisticEverything$washingDelay > 80) {
                realisticEverything$washingDelay = 0;
                this.setItem(new ItemStack(ModItems.CUT_WASHED_SUGAR_CANE.get(), itemCount));
            }
        }
    }
}
