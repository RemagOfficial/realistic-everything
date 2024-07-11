package com.remag.realistic.screen;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.screen.centrifuge.CentrifugeMenu;
import com.remag.realistic.screen.mill.MillMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RealisticEverything.MODID);

    public static final RegistryObject<MenuType<CentrifugeMenu>> CENTRIFUGE_MENU =
            registerMenuType("centrifuge_menu", CentrifugeMenu::new);

    public static final RegistryObject<MenuType<MillMenu>> MILL_MENU =
            registerMenuType("mill_menu", MillMenu::new);


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
