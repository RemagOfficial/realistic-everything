package com.remag.realistic.block.entity;

import com.remag.realistic.item.ModItems;
import com.remag.realistic.screen.mill.MillMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MillBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static final int INPUT_SUGAR_SLOT = 0;
    private static final int INPUT_BUCKET_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int FUEL_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int fuelTime = 0;
    private int maxFuelTime = 10;


    public MillBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MILL.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> MillBlockEntity.this.progress;
                    case 1 -> MillBlockEntity.this.maxProgress;
                    case 2 -> MillBlockEntity.this.fuelTime;
                    case 3 -> MillBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MillBlockEntity.this.progress = pValue;
                    case 1 -> MillBlockEntity.this.maxProgress = pValue;
                    case 2 -> MillBlockEntity.this.fuelTime = pValue;
                    case 3 -> MillBlockEntity.this.maxFuelTime = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.realistic.mill");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new MillMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("mill.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("mill.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipeSCJ()) {
            if (isLit()) {
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);

                if (hasProgressFinished()) {
                    reduceFuelTime();
                    craftItemSCJ();
                    resetProgress();
                }
            } else {
                if (this.itemHandler.getStackInSlot(FUEL_SLOT).getCount() > 0) {
                    useFuel();
                    setChanged(pLevel, pPos, pState);
                }
            }
        } else if(hasRecipeFlour()) {
            if (isLit()) {
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);

                if (hasProgressFinished()) {
                    reduceFuelTime();
                    craftItemFlour();
                    resetProgress();
                }
            } else {
                if (this.itemHandler.getStackInSlot(FUEL_SLOT).getCount() > 0) {
                    useFuel();
                    setChanged(pLevel, pPos, pState);
                }
            }
        } else if (!validRecipe()){
            resetProgress();
            if (isLit()) {
                reduceFuelTime();
            }
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void reduceFuelTime() {
        if(this.fuelTime > 0) {
            this.fuelTime--;
        }
    }

    private void useFuel() {
        this.itemHandler.getStackInSlot(FUEL_SLOT).shrink(1);
        this.fuelTime += this.maxFuelTime;
    }

    private boolean isLit() {
        return this.fuelTime > 0;
    }

    private void craftItemSCJ() {
        ItemStack result = new ItemStack(ModItems.SUGAR_CANE_JUICE_BUCKET.get(), 1);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
        this.itemHandler.getStackInSlot(INPUT_SUGAR_SLOT).shrink(1);
        this.itemHandler.getStackInSlot(INPUT_BUCKET_SLOT).shrink(1);
    }

    private void craftItemFlour() {
        ItemStack result = new ItemStack(ModItems.FLOUR.get(), 1);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
        this.itemHandler.getStackInSlot(INPUT_SUGAR_SLOT).shrink(1);
    }

    private boolean hasRecipeSCJ() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SUGAR_SLOT).getItem() == ModItems.CUT_WASHED_SUGAR_CANE.get();
        boolean hasFuel = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.OAK_LOG;
        boolean hasBucket = this.itemHandler.getStackInSlot(INPUT_BUCKET_SLOT).getItem() == Items.BUCKET;
        ItemStack result = new ItemStack(ModItems.SUGAR_CANE_JUICE_BUCKET.get(), 1);

        return hasCraftingItem && (hasFuel || this.fuelTime > 0) && hasBucket && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean hasRecipeFlour() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SUGAR_SLOT).getItem() == Items.WHEAT;
        boolean hasFuel = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.OAK_LOG;
        ItemStack result = new ItemStack(ModItems.FLOUR.get(), 1);

        return hasCraftingItem && (hasFuel || this.fuelTime > 0) && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean validRecipe() {
        return hasRecipeSCJ() || hasRecipeFlour();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress += 1;
    }
}
