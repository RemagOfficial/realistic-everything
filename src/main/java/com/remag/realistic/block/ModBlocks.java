package com.remag.realistic.block;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.fluid.ModFluids;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RealisticEverything.MODID);

    // block entities

    public static final RegistryObject<Block> CENTRIFUGE = BLOCKS.register("centrifuge",
            () -> new CentrifugeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5f, 6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MILL = BLOCKS.register("mill",
            () -> new MillBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5f, 6f).requiresCorrectToolForDrops()));

    // fluid blocks

    public static final RegistryObject<LiquidBlock> SUGAR_CANE_JUICE = BLOCKS.register("sugar_cane_juice",
            () -> new LiquidBlock(ModFluids.SOURCE_SUGAR_CANE_JUICE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> CRYSTALLIZED_SUGAR_CANE_JUICE = BLOCKS.register("crystallized_sugar_cane_juice",
            () -> new LiquidBlock(ModFluids.SOURCE_CRYSTALLIZED_SUGAR_CANE_JUICE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> MOLTEN_SUGAR = BLOCKS.register("molten_sugar",
            () -> new LiquidBlock(ModFluids.SOURCE_MOLTEN_SUGAR, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static final RegistryObject<LiquidBlock> SODIUM_CARBONATE_SOLUTION = BLOCKS.register("sodium_carbonate_solution",
            () -> new LiquidBlock(ModFluids.SOURCE_SODIUM_CARBONATE_SOLUTION, BlockBehaviour.Properties.copy(Blocks.WATER)));

    // normal blocks

    public static final RegistryObject<Block> TRONA_ORE = BLOCKS.register("trona_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
