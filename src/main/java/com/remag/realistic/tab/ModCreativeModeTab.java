package com.remag.realistic.tab;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealisticEverything.MODID);

    public static final List<Supplier<? extends ItemLike>> RE_TABS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> RE_TAB = TABS.register("re_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.re_tab"))
                    .icon(ModBlocks.CENTRIFUGE.get().asItem()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            RE_TABS.forEach(itemLike -> output.accept(new ItemStack(itemLike.get()))))
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        RE_TABS.add(itemLike);
        return itemLike;
    }
}
