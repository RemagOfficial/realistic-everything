package com.remag.realistic.datagen;

import com.remag.realistic.RealisticEverything;
import com.remag.realistic.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RealisticEverything.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TRONA_ORE.get(),
                        ModBlocks.CENTRIFUGE.get(),
                        ModBlocks.MILL.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TRONA_ORE.get());
    }
}
