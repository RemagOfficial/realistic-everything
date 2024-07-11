package com.remag.realistic.fluid;

import com.remag.realistic.RealisticEverything;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, RealisticEverything.MODID);

    public static final RegistryObject<FluidType> SUGAR_CANE_JUICE_TYPE = registerSugarCaneJuice("sugar_cane_juice",
            FluidType.Properties.create().lightLevel(2));

    public static final RegistryObject<FluidType> CRYSTALLIZED_SUGAR_CANE_JUICE_TYPE = registerCrystallizedSugarCaneJuice("crystallized_sugar_cane_juice",
            FluidType.Properties.create().lightLevel(2));

    public static final RegistryObject<FluidType> MOLTEN_SUGAR_TYPE = registerMoltenSugar("molten_sugar",
            FluidType.Properties.create().lightLevel(2).temperature(3000));

    public static final RegistryObject<FluidType> SODIUM_CARBONATE_SOLUTION_TYPE = registerSodiumCarbonateSolution("sodium_carbonate_solution",
            FluidType.Properties.create().lightLevel(2).temperature(3000));

    private static RegistryObject<FluidType> registerSugarCaneJuice(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xA19FB800, new Vector3f(0.623f, 0.720f, 0.000f), properties));
    }

    private static RegistryObject<FluidType> registerCrystallizedSugarCaneJuice(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xA1C7DE30, new Vector3f(0.780f, 0.870f, 0.188f), properties));
    }

    private static RegistryObject<FluidType> registerMoltenSugar(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xA1CECDC5, new Vector3f(0.804f, 0.803f, 0.773f), properties));
    }

    private static RegistryObject<FluidType> registerSodiumCarbonateSolution(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xA14875F7, new Vector3f(0.282f, 0.459f, 0.969f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
