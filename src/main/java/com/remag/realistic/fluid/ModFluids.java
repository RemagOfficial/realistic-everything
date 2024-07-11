package com.remag.realistic.fluid;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.block.ModBlocks;
import com.remag.realistic.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, RealisticEverything.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_SUGAR_CANE_JUICE = FLUIDS.register("sugar_cane_juice",
            () -> new ForgeFlowingFluid.Source(ModFluids.SUGAR_CANE_JUICE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SUGAR_CANE_JUICE = FLUIDS.register("flowing_sugar_cane_juice",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SUGAR_CANE_JUICE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_CRYSTALLIZED_SUGAR_CANE_JUICE = FLUIDS.register("crystallized_sugar_cane_juice",
            () -> new ForgeFlowingFluid.Source(ModFluids.CRYSTALLIZED_SUGAR_CANE_JUICE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CRYSTALLIZED_SUGAR_CANE_JUICE = FLUIDS.register("flowing_crystallized_sugar_cane_juice",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.CRYSTALLIZED_SUGAR_CANE_JUICE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_MOLTEN_SUGAR = FLUIDS.register("molten_sugar",
            () -> new MoltenSugarFluid.Source(ModFluids.MOLTEN_SUGAR_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MOLTEN_SUGAR = FLUIDS.register("flowing_molten_sugar",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.MOLTEN_SUGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_SODIUM_CARBONATE_SOLUTION = FLUIDS.register("sodium_carbonate_solution",
            () -> new SodiumCarbonateSolutionFluid.Source(ModFluids.SODIUM_CARBONATE_SOLUTION_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SODIUM_CARBONATE_SOLUTION = FLUIDS.register("flowing_sodium_carbonate_solution",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SODIUM_CARBONATE_SOLUTION_PROPERTIES));



    public static final ForgeFlowingFluid.Properties SUGAR_CANE_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SUGAR_CANE_JUICE_TYPE, SOURCE_SUGAR_CANE_JUICE, FLOWING_SUGAR_CANE_JUICE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SUGAR_CANE_JUICE).bucket(ModItems.SUGAR_CANE_JUICE_BUCKET);

    public static final ForgeFlowingFluid.Properties CRYSTALLIZED_SUGAR_CANE_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.CRYSTALLIZED_SUGAR_CANE_JUICE_TYPE, SOURCE_CRYSTALLIZED_SUGAR_CANE_JUICE, FLOWING_CRYSTALLIZED_SUGAR_CANE_JUICE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.CRYSTALLIZED_SUGAR_CANE_JUICE).bucket(ModItems.CRYSTALLIZED_SUGAR_CANE_JUICE_BUCKET);

    public static final ForgeFlowingFluid.Properties MOLTEN_SUGAR_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.MOLTEN_SUGAR_TYPE, SOURCE_MOLTEN_SUGAR, FLOWING_MOLTEN_SUGAR)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.MOLTEN_SUGAR).bucket(ModItems.MOLTEN_SUGAR_BUCKET);

    public static final ForgeFlowingFluid.Properties SODIUM_CARBONATE_SOLUTION_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SODIUM_CARBONATE_SOLUTION_TYPE, SOURCE_SODIUM_CARBONATE_SOLUTION, FLOWING_SODIUM_CARBONATE_SOLUTION)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SODIUM_CARBONATE_SOLUTION).bucket(ModItems.SODIUM_CARBONATE_SOLUTION_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
