package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.RecipePrioritiesProvider;

import java.util.concurrent.CompletableFuture;

public class AetherIIRecipePrioritiesData extends RecipePrioritiesProvider {
    public AetherIIRecipePrioritiesData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AetherII.MODID);
    }

    @Override
    protected void addPriorities() {
        // 设置各种物品的配方优先级
        priority(AetherII.MODID, "skyroot_stick", 5);
        priority(AetherII.MODID, "skyroot_sword", 5);
        priority(AetherII.MODID, "skyroot_pickaxe", 5);
        priority(AetherII.MODID, "skyroot_axe", 5);
        priority(AetherII.MODID, "skyroot_shovel", 5);
        priority(AetherII.MODID, "skyroot_trowel", 5);
        priority(AetherII.MODID, "skyroot_hammer", 5);
        priority(AetherII.MODID, "skyroot_spear", 5);
        priority(AetherII.MODID, "skyroot_crafting_table", 5);
        priority(AetherII.MODID, "skyroot_chest", 5);
        priority(AetherII.MODID, "skyroot_ladder", 5);
        priority(AetherII.MODID, "skyroot_bucket", 5);

        priority(AetherII.MODID, "beast_pelt_helmet", 5);
        priority(AetherII.MODID, "beast_pelt_chestplate", 5);
        priority(AetherII.MODID, "beast_pelt_leggings", 5);
        priority(AetherII.MODID, "beast_pelt_boots", 5);
        priority(AetherII.MODID, "beast_pelt_gloves", 5);
        priority(AetherII.MODID, "burrukai_plate_helmet", 5);
        priority(AetherII.MODID, "burrukai_plate_chestplate", 5);
        priority(AetherII.MODID, "burrukai_plate_leggings", 5);
        priority(AetherII.MODID, "burrukai_plate_boots", 5);
        priority(AetherII.MODID, "burrukai_plate_gloves", 5);
        priority(AetherII.MODID, "hide_bundle", 5);

        priority(AetherII.MODID, "cloudwool", 5);

        priority(AetherII.MODID, "holystone_furnace", 5);
        priority(AetherII.MODID, "holystone_pickaxe", 5);
        priority(AetherII.MODID, "holystone_axe", 5);
        priority(AetherII.MODID, "holystone_shovel", 5);
        priority(AetherII.MODID, "holystone_trowel", 5);
        priority(AetherII.MODID, "holystone_shortsword", 5);
        priority(AetherII.MODID, "holystone_hammer", 5);
        priority(AetherII.MODID, "holystone_spear", 5);

        priority(AetherII.MODID, "aether_portal_frame", 5);
    }
}
