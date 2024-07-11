package com.remag.realistic;

import com.mojang.logging.LogUtils;
import com.remag.realistic.block.ModBlocks;
import com.remag.realistic.block.entity.ModBlockEntities;
import com.remag.realistic.events.ModEvents;
import com.remag.realistic.fluid.ModFluidTypes;
import com.remag.realistic.fluid.ModFluids;
import com.remag.realistic.item.ModItems;
import com.remag.realistic.screen.centrifuge.CentrifugeScreen;
import com.remag.realistic.screen.ModMenuTypes;
import com.remag.realistic.screen.mill.MillScreen;
import com.remag.realistic.tab.ModCreativeModeTab;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RealisticEverything.MODID)
public class RealisticEverything
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "realistic";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public RealisticEverything()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.register(new ModEvents());

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModCreativeModeTab.TABS.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::init);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    public void init(FMLConstructModEvent event) {
        MixinBootstrap.init();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.CENTRIFUGE_MENU.get(), CentrifugeScreen::new);
            MenuScreens.register(ModMenuTypes.MILL_MENU.get(), MillScreen::new);

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SUGAR_CANE_JUICE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SUGAR_CANE_JUICE.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_CRYSTALLIZED_SUGAR_CANE_JUICE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_CRYSTALLIZED_SUGAR_CANE_JUICE.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_MOLTEN_SUGAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MOLTEN_SUGAR.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SODIUM_CARBONATE_SOLUTION.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SODIUM_CARBONATE_SOLUTION.get(), RenderType.translucent());
        }
    }
}
