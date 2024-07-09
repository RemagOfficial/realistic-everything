package com.remag.realistic.item;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.block.ModBlocks;
import com.remag.realistic.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.remag.realistic.tab.ModCreativeModeTab.addToTab;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealisticEverything.MODID);

    // Block Items

    public static final RegistryObject<Item> CENTRIFUGE = addToTab(ITEMS.register("centrifuge",
            () -> new BlockItem(ModBlocks.CENTRIFUGE.get(),
                    new Item.Properties()
            )
    ));

    public static final RegistryObject<Item> SUGAR_MILL = addToTab(ITEMS.register("sugar_mill",
            () -> new BlockItem(ModBlocks.SUGAR_MILL.get(),
                    new Item.Properties()
            )
    ));

    // Items

    public static final RegistryObject<Item> CUT_SUGAR_CANE = addToTab(ITEMS.register("cut_sugar_cane",
            () -> new Item(new Item.Properties())
    ));

    public static final RegistryObject<Item> CUT_WASHED_SUGAR_CANE = addToTab(ITEMS.register("cut_washed_sugar_cane",
            () -> new Item(new Item.Properties())
    ));

    public static final RegistryObject<Item> GOLDEN_RAW_SUGAR = addToTab(ITEMS.register("golden_raw_sugar",
            () -> new Item(new Item.Properties())
    ));

    public static final RegistryObject<Item> GOLDEN_RAW_SUGAR_BUCKET = addToTab(ITEMS.register("golden_raw_sugar_bucket",
            () -> new Item(new Item.Properties())
    ));

    public static final RegistryObject<Item> KNIFE = addToTab(ITEMS.register("knife",
            () -> new Item(new Item.Properties().durability(100))
    ));

    // Buckets

    public static final RegistryObject<Item> SUGAR_CANE_JUICE_BUCKET = addToTab(ITEMS.register("sugar_cane_juice_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SUGAR_CANE_JUICE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
    ));

    public static final RegistryObject<Item> CRYSTALLIZED_SUGAR_CANE_JUICE_BUCKET = addToTab(ITEMS.register("crystallized_sugar_cane_juice_bucket",
            () -> new BucketItem(ModFluids.SOURCE_CRYSTALLIZED_SUGAR_CANE_JUICE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
    ));

    public static final RegistryObject<Item> MOLTEN_SUGAR_BUCKET = addToTab(ITEMS.register("molten_sugar_bucket",
            () -> new BucketItem(ModFluids.SOURCE_MOLTEN_SUGAR,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
