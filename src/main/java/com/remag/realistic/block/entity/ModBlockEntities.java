package com.remag.realistic.block.entity;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RealisticEverything.MODID);

    public static final RegistryObject<BlockEntityType<CentrifugeBlockEntity>> CENTRIFUGE =
            BLOCK_ENTITIES.register("centrifuge", () ->
                    BlockEntityType.Builder.of(CentrifugeBlockEntity::new,
                            ModBlocks.CENTRIFUGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MillBlockEntity>> MILL =
            BLOCK_ENTITIES.register("mill", () ->
                    BlockEntityType.Builder.of(MillBlockEntity::new,
                            ModBlocks.MILL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
