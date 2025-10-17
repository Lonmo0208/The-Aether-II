package com.aetherteam.aetherii.data.generators;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.data.generators.models.AetherIIBlockModels;
import com.aetherteam.aetherii.data.generators.models.AetherIIItemModels;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.client.model.generators.NeoForgeBlockStateProvider;

import java.util.concurrent.CompletableFuture;

public class AetherIIModelData implements DataProvider {
    private final PackOutput packOutput;

    public AetherIIModelData(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        NeoForgeBlockStateProvider blockStateProvider = new AetherIIBlockModels(this.packOutput, AetherII.MODID);
        ItemModelProvider itemModelProvider = new AetherIIItemModels(this.packOutput, AetherII.MODID);
        
        return CompletableFuture.allOf(
            blockStateProvider.run(output),
            itemModelProvider.run(output)
        );
    }

    @Override
    public String getName() {
        return "Aether II Models";
    }
}
