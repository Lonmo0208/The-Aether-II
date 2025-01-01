package com.aetherteam.aetherii.data.generators.models;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.providers.AetherIIBlockModelSubProvider;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIIBlockFamilies;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIITexturedModels;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AetherIIBlockModels extends AetherIIBlockModelSubProvider {
    public AetherIIBlockModels(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        AetherIIBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach((family) -> this.family(family.getBaseBlock()).generateFor(family));

        // Portal
//        this.createAetherPortalBlock(blockModels);

        // Surface
//        this.createAetherGrassBlocks(blockModels);
        this.createTrivialCube(AetherIIBlocks.AETHER_DIRT.get());
        this.createTrivialCube(AetherIIBlocks.COARSE_AETHER_DIRT.get());
//        this.createAetherFarmland(blockModels);
        this.createTrivialCube(AetherIIBlocks.SHIMMERING_SILT.get());
//
//        // Underground
        this.createTrivialCube(AetherIIBlocks.UNSTABLE_HOLYSTONE.get());
        this.createTrivialCube(AetherIIBlocks.UNSTABLE_UNDERSHALE.get());
        this.createTrivialCube(AetherIIBlocks.ICHORITE.get());
//        this.createSnowyCross(AetherIIBlocks.SKY_ROOTS.get());
//        this.createTranslucentCube(AetherIIBlocks.GAS.get()); //todo translcucent interior
//        this.createTranslucentCube(AetherIIBlocks.ACID.get()); //todo ???? blockstate definition
//        this.createPointedStone(AetherIIBlocks.POINTED_HOLYSTONE.get());
//        this.createPointedStone(AetherIIBlocks.POINTED_ICHORITE.get());
//
//        // Highfields
        this.createTrivialCube(AetherIIBlocks.QUICKSOIL.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.BRYALINN_MOSS_BLOCK.get(), AetherIIBlocks.BRYALINN_MOSS_CARPET.get());
//        this.createVine(AetherIIBlocks.BRYALINN_MOSS_VINES.get()); //todo
        this.createCustomFlowerBed(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.get(),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_1.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_2.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_3.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.BRYALINN_MOSS_FLOWERS_4.create(AetherIIBlocks.BRYALINN_MOSS_FLOWERS.get(), this.modelOutput));
//        this.createCutoutMippedCube(AetherIIBlocks.TANGLED_BRANCHES.get());
//
//        // Magnetic
        this.createTrivialCube(AetherIIBlocks.FERROSITE_SAND.get());
        this.createTrivialCube(AetherIIBlocks.FERROSITE_MUD.get());
        this.createTrivialCube(AetherIIBlocks.FERROSITE.get());
        this.createTrivialCube(AetherIIBlocks.RUSTED_FERROSITE.get());
//        this.createCutoutCross(AetherIIBlocks.MAGNETIC_SHROOM.get()); //todo flower pot
//
//        // Arctic
        this.createArcticSnowBlocks();
//        this.createTranslucentCube(AetherIIBlocks.ARCTIC_ICE.get());
        this.createTrivialCube(AetherIIBlocks.ARCTIC_PACKED_ICE.get());
        this.createAmethystCluster(AetherIIBlocks.LARGE_ARCTIC_ICE_CRYSTAL.get());
        this.createAmethystCluster(AetherIIBlocks.MEDIUM_ARCTIC_ICE_CRYSTAL.get());
        this.createAmethystCluster(AetherIIBlocks.SMALL_ARCTIC_ICE_CRYSTAL.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.SHAYELINN_MOSS_BLOCK.get(), AetherIIBlocks.SHAYELINN_MOSS_CARPET.get());
//        this.createVine(AetherIIBlocks.SHAYELINN_MOSS_VINES.get()); //todo
        this.createCustomFlowerBed(AetherIIBlocks.HOLPUPEA.get(),
                AetherIITexturedModels.HOLPUPEA_1.create(AetherIIBlocks.HOLPUPEA.get(), this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_2.create(AetherIIBlocks.HOLPUPEA.get(), this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_3.create(AetherIIBlocks.HOLPUPEA.get(), this.modelOutput),
                AetherIITexturedModels.HOLPUPEA_4.create(AetherIIBlocks.HOLPUPEA.get(), this.modelOutput));
//
//        // Irradiated
        this.createTrivialCube(AetherIIBlocks.IRRADIATED_DUST_BLOCK.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.AMBRELINN_MOSS_BLOCK.get(), AetherIIBlocks.AMBRELINN_MOSS_CARPET.get());
//        this.createVine(AetherIIBlocks.AMBRELINN_MOSS_VINES.get()); //todo
        this.createCustomFlowerBed(AetherIIBlocks.TARAHESP_FLOWERS.get(),
                AetherIITexturedModels.TARAHESP_FLOWERS_1.create(AetherIIBlocks.TARAHESP_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_2.create(AetherIIBlocks.TARAHESP_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_3.create(AetherIIBlocks.TARAHESP_FLOWERS.get(), this.modelOutput),
                AetherIITexturedModels.TARAHESP_FLOWERS_4.create(AetherIIBlocks.TARAHESP_FLOWERS.get(), this.modelOutput));
//
//        // Ores
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_QUARTZ_ORE.get());
        this.createTrivialCube(AetherIIBlocks.AMBROSIUM_ORE.get());
        this.createTrivialCube(AetherIIBlocks.ZANITE_ORE.get());
        this.createTrivialCube(AetherIIBlocks.GLINT_ORE.get());
        this.createTrivialCube(AetherIIBlocks.ARKENIUM_ORE.get());
        this.createTrivialCube(AetherIIBlocks.GRAVITITE_ORE.get());
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_AMBROSIUM_ORE.get());
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_ZANITE_ORE.get());
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_GLINT_ORE.get());
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_ARKENIUM_ORE.get());
        this.createTrivialCube(AetherIIBlocks.UNDERSHALE_GRAVITITE_ORE.get());
        this.createTrivialCube(AetherIIBlocks.CORROBONITE_ORE.get());
        this.createAmethystCluster(AetherIIBlocks.CORROBONITE_CLUSTER.get());
//
//        // Aerclouds
//        this.createTranslucentCube(AetherIIBlocks.COLD_AERCLOUD.get()); //todo translucent interior
//        this.createTranslucentCube(AetherIIBlocks.BLUE_AERCLOUD.get());
//        this.createTranslucentCube(AetherIIBlocks.GOLDEN_AERCLOUD.get());
//        this.createTranslucentCube(AetherIIBlocks.GREEN_AERCLOUD.get());
//        this.createTranslucentCube(AetherIIBlocks.PURPLE_AERCLOUD.get()); //todo faces
//        this.createTranslucentCube(AetherIIBlocks.STORM_AERCLOUD.get());
//
//        // Moa Nest
        this.createTrivialCube(AetherIIBlocks.WOVEN_SKYROOT_STICKS.get());
//
//        // Logs
        this.woodProvider(AetherIIBlocks.SKYROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.SKYROOT_LOG.get()).wood(AetherIIBlocks.SKYROOT_WOOD.get());
        this.woodProvider(AetherIIBlocks.STRIPPED_SKYROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.STRIPPED_SKYROOT_LOG.get()).wood(AetherIIBlocks.STRIPPED_SKYROOT_WOOD.get());
        this.woodProvider(AetherIIBlocks.GREATROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.GREATROOT_LOG.get()).wood(AetherIIBlocks.GREATROOT_WOOD.get());
        this.woodProvider(AetherIIBlocks.STRIPPED_GREATROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.STRIPPED_GREATROOT_LOG.get()).wood(AetherIIBlocks.STRIPPED_GREATROOT_WOOD.get());
        this.woodProvider(AetherIIBlocks.WISPROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.WISPROOT_LOG.get()).wood(AetherIIBlocks.WISPROOT_WOOD.get());
        this.woodProvider(AetherIIBlocks.STRIPPED_WISPROOT_LOG.get()).logWithHorizontal(AetherIIBlocks.STRIPPED_WISPROOT_LOG.get()).wood(AetherIIBlocks.STRIPPED_WISPROOT_WOOD.get());
//        this.createCustomColumn(AetherIIBlocks.MOSSY_WISPROOT_LOG.get(), AetherIIBlocks.WISPROOT_LOG.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.AMBEROOT_LOG.get(), AetherIIBlocks.SKYROOT_LOG.get()); //todo
//        this.createTrivialCube(AetherIIBlocks.AMBEROOT_WOOD.get()); //todo
//
        // Leaves
        this.createLeavesWithPiles(AetherIIBlocks.SKYROOT_LEAVES.get(), AetherIIBlocks.SKYROOT_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.SKYPLANE_LEAVES.get(), AetherIIBlocks.SKYPLANE_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.SKYBIRCH_LEAVES.get(), AetherIIBlocks.SKYBIRCH_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.SKYPINE_LEAVES.get(), AetherIIBlocks.SKYPINE_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.WISPROOT_LEAVES.get(), AetherIIBlocks.WISPROOT_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.WISPTOP_LEAVES.get(), AetherIIBlocks.WISPTOP_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.GREATROOT_LEAVES.get(), AetherIIBlocks.GREATROOT_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.GREATOAK_LEAVES.get(), AetherIIBlocks.GREATOAK_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.GREATBOA_LEAVES.get(), AetherIIBlocks.GREATBOA_LEAF_PILE.get());
        this.createLeavesWithPiles(AetherIIBlocks.AMBEROOT_LEAVES.get(), AetherIIBlocks.AMBEROOT_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYROOT_LEAVES.get(), AetherIIBlocks.IRRADIATED_SKYROOT_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYPLANE_LEAVES.get(), AetherIIBlocks.IRRADIATED_SKYPLANE_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAVES.get(), AetherIIBlocks.IRRADIATED_SKYBIRCH_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_SKYPINE_LEAVES.get(), AetherIIBlocks.IRRADIATED_SKYPINE_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_WISPROOT_LEAVES.get(), AetherIIBlocks.IRRADIATED_WISPROOT_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_WISPTOP_LEAVES.get(), AetherIIBlocks.IRRADIATED_WISPTOP_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATROOT_LEAVES.get(), AetherIIBlocks.IRRADIATED_GREATROOT_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATOAK_LEAVES.get(), AetherIIBlocks.IRRADIATED_GREATOAK_LEAF_PILE.get());
        this.createTintedLeavesWithPiles(AetherIIBlocks.IRRADIATED_GREATBOA_LEAVES.get(), AetherIIBlocks.IRRADIATED_GREATBOA_LEAF_PILE.get());

        // Saplings
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYROOT_SAPLING.get(), AetherIIBlocks.POTTED_SKYROOT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYPLANE_SAPLING.get(), AetherIIBlocks.POTTED_SKYPLANE_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYBIRCH_SAPLING.get(), AetherIIBlocks.POTTED_SKYBIRCH_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.SKYPINE_SAPLING.get(), AetherIIBlocks.POTTED_SKYPINE_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.WISPROOT_SAPLING.get(), AetherIIBlocks.POTTED_WISPROOT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.WISPTOP_SAPLING.get(), AetherIIBlocks.POTTED_WISPTOP_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATROOT_SAPLING.get(), AetherIIBlocks.POTTED_GREATROOT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATOAK_SAPLING.get(), AetherIIBlocks.POTTED_GREATOAK_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.GREATBOA_SAPLING.get(), AetherIIBlocks.POTTED_GREATBOA_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(AetherIIBlocks.AMBEROOT_SAPLING.get(), AetherIIBlocks.POTTED_AMBEROOT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//
//        // Grasses
//        this.createCrossBlock(AetherIIBlocks.AETHER_SHORT_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED); //todo tinting and frosting
//        this.createCrossBlock(AetherIIBlocks.AETHER_MEDIUM_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createCrossBlock(AetherIIBlocks.AETHER_LONG_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
////        this.createCrossBlock(AetherIIBlocks.HIGHLAND_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
////        this.createCrossBlock(AetherIIBlocks.SHIELD_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//
//        // Flowers //todo
//        this.createPlantWithDefaultItem(AetherIIBlocks.HIGHLAND_FERN.get(), AetherIIBlocks.POTTED_HIGHLAND_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.SHIELD_FERN.get(), AetherIIBlocks.POTTED_SHIELD_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.HESPEROSE.get(), AetherIIBlocks.POTTED_HESPEROSE.get());
//        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.TARABLOOM.get(), AetherIIBlocks.POTTED_TARABLOOM.get());
//        this.createSnowyPlantWithDefaultItem(AetherIIBlocks.POASPROUT.get(), AetherIIBlocks.POTTED_POASPROUT.get());
//        this.createPlantWithDefaultItem(AetherIIBlocks.SATIVAL_SHOOT.get(), AetherIIBlocks.POTTED_SATIVAL_SHOOT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.LILICHIME.get(), AetherIIBlocks.POTTED_LILICHIME.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.PLURACIAN.get(), AetherIIBlocks.POTTED_PLURACIAN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.BLADE_POA.get(), AetherIIBlocks.POTTED_BLADE_POA.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.AECHOR_CUTTING.get(), AetherIIBlocks.POTTED_AECHOR_CUTTING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//
//        // Bushes
////        this.createTrivialCube(AetherIIBlocks.HIGHLANDS_BUSH.get()); //todo custom model
//        this.createCrossBlock(AetherIIBlocks.BLUEBERRY_BUSH_STEM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
////        this.createTrivialCube(AetherIIBlocks.BLUEBERRY_BUSH.get());  //todo custom model //, AetherIIBlocks.BLUEBERRY_BUSH_STEM.get()
//
//        // Potted Bushes
//        this.createPlantWithDefaultItem(AetherIIBlocks.HIGHLANDS_BUSH.get(), AetherIIBlocks.POTTED_HIGHLANDS_BUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.BLUEBERRY_BUSH_STEM.get(), AetherIIBlocks.POTTED_BLUEBERRY_BUSH_STEM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//        this.createPlantWithDefaultItem(AetherIIBlocks.BLUEBERRY_BUSH.get(), AetherIIBlocks.POTTED_BLUEBERRY_BUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//
//        // Orange Tree
////        this.createCrossBlock(AetherIIBlocks.ORANGE_TREE.get(), BlockModelGenerators.PlantType.NOT_TINTED); //todo custom model
//
//        // Potted Orange Tree
//        this.createPlantWithDefaultItem(AetherIIBlocks.ORANGE_TREE.get(), AetherIIBlocks.POTTED_ORANGE_TREE.get(), BlockModelGenerators.PlantType.NOT_TINTED); //todo
//
//        // Surface Vegetation
//        this.createValkyrieSprout(blockModels);
//        this.createGrowingPlant(AetherIIBlocks.BRETTL_PLANT_TIP.get(), AetherIIBlocks.BRETTL_PLANT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
//
//        // Lake
//        this.createCutoutCross(AetherIIBlocks.ARILUM_SHOOT.get());
//        this.createCutoutCross(AetherIIBlocks.ARILUM.get());
//        this.createCutoutCross(AetherIIBlocks.ARILUM_PLANT.get());
//        this.createCutoutCross(AetherIIBlocks.BLOOMING_ARILUM.get());
//        this.createCutoutCross(AetherIIBlocks.BLOOMING_ARILUM_PLANT.get());
//
//        // Ground Decoration
//        this.createTrivialCube(AetherIIBlocks.SKYROOT_TWIG.get());
//        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_ROCK.get());
//        //todo
//        //        this.twig(AetherIIBlocks.SKYROOT_TWIG.get(), AetherIIBlocks.SKYROOT_LOG.get());
//        //        this.rock(AetherIIBlocks.HOLYSTONE_ROCK.get(), AetherIIBlocks.HOLYSTONE.get());
//
//        // Skyroot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.SKYROOT_FLOORBOARDS.get());
        this.createTrivialCube(AetherIIBlocks.SKYROOT_HIGHLIGHT.get());
        this.createTrivialCube(AetherIIBlocks.SKYROOT_SHINGLES.get());
        this.createTrivialCube(AetherIIBlocks.SKYROOT_SMALL_SHINGLES.get());
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_BASE_PLANKS.get(), AetherIIBlocks.SKYROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_TOP_PLANKS.get(), AetherIIBlocks.SKYROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_BASE_BEAM.get(), AetherIIBlocks.SKYROOT_HIGHLIGHT.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_TOP_BEAM.get(), AetherIIBlocks.SKYROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_BEAM.get(), AetherIIBlocks.SKYROOT_HIGHLIGHT.get());
//        this.createDoor(AetherIIBlocks.SECRET_SKYROOT_DOOR.get()); //todo
//        this.createTrapdoor(AetherIIBlocks.SECRET_SKYROOT_TRAPDOOR.get());
//
//        // Greatroot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.GREATROOT_FLOORBOARDS.get());
        this.createTrivialCube(AetherIIBlocks.GREATROOT_HIGHLIGHT.get());
        this.createTrivialCube(AetherIIBlocks.GREATROOT_SHINGLES.get());
        this.createTrivialCube(AetherIIBlocks.GREATROOT_SMALL_SHINGLES.get());
//        this.createCustomColumn(AetherIIBlocks.GREATROOT_BASE_PLANKS.get(), AetherIIBlocks.GREATROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.GREATROOT_TOP_PLANKS.get(), AetherIIBlocks.GREATROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.GREATROOT_BASE_BEAM.get(), AetherIIBlocks.GREATROOT_HIGHLIGHT.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.GREATROOT_TOP_BEAM.get(), AetherIIBlocks.GREATROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.GREATROOT_BEAM.get(), AetherIIBlocks.GREATROOT_HIGHLIGHT.get());
//        this.createDoor(AetherIIBlocks.SECRET_GREATROOT_DOOR.get()); //todo
//        this.createTrapdoor(AetherIIBlocks.SECRET_GREATROOT_TRAPDOOR.get());
//
//        // Wisproot Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.WISPROOT_FLOORBOARDS.get());
        this.createTrivialCube(AetherIIBlocks.WISPROOT_HIGHLIGHT.get());
        this.createTrivialCube(AetherIIBlocks.WISPROOT_SHINGLES.get());
        this.createTrivialCube(AetherIIBlocks.WISPROOT_SMALL_SHINGLES.get());
//        this.createCustomColumn(AetherIIBlocks.WISPROOT_BASE_PLANKS.get(), AetherIIBlocks.WISPROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.WISPROOT_TOP_PLANKS.get(), AetherIIBlocks.WISPROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.WISPROOT_BASE_BEAM.get(), AetherIIBlocks.WISPROOT_HIGHLIGHT.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.WISPROOT_TOP_BEAM.get(), AetherIIBlocks.WISPROOT_HIGHLIGHT.get());
//        this.createCustomColumn(AetherIIBlocks.WISPROOT_BEAM.get(), AetherIIBlocks.WISPROOT_HIGHLIGHT.get());
//        this.createDoor(AetherIIBlocks.SECRET_WISPROOT_DOOR.get()); //todo
//        this.createTrapdoor(AetherIIBlocks.SECRET_WISPROOT_TRAPDOOR.get());
//
//        // Holystone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_FLAGSTONES.get());
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_HEADSTONE.get());
        this.createTrivialCube(AetherIIBlocks.HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_BASE_BRICKS.get(), AetherIIBlocks.HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_CAPSTONE_BRICKS.get(), AetherIIBlocks.HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_BASE_PILLAR.get(), AetherIIBlocks.HOLYSTONE_KEYSTONE.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_CAPSTONE_PILLAR.get(), AetherIIBlocks.HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_PILLAR.get(), AetherIIBlocks.HOLYSTONE_KEYSTONE.get());
//
//        // Faded Holystone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_FLAGSTONES.get());
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_HEADSTONE.get());
        this.createTrivialCube(AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.FADED_HOLYSTONE_BASE_BRICKS.get(), AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_BRICKS.get(), AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.FADED_HOLYSTONE_BASE_PILLAR.get(), AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get()); //todo
//        this.createCustomColumn(AetherIIBlocks.FADED_HOLYSTONE_CAPSTONE_PILLAR.get(), AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.FADED_HOLYSTONE_PILLAR.get(), AetherIIBlocks.FADED_HOLYSTONE_KEYSTONE.get());
//
//        // Agiosite Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.AGIOSITE_FLAGSTONES.get());
        this.createTrivialCube(AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.AGIOSITE_BASE_BRICKS.get(), AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.AGIOSITE_CAPSTONE_BRICKS.get(), AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.AGIOSITE_BASE_PILLAR.get(), AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.AGIOSITE_CAPSTONE_PILLAR.get(), AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.AGIOSITE_PILLAR.get(), AetherIIBlocks.AGIOSITE_KEYSTONE.get());
//
//        // Icestone Decorative Blocks
        this.createTrivialCube(AetherIIBlocks.ICESTONE_FLAGSTONES.get());
        this.createTrivialCube(AetherIIBlocks.ICESTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.ICESTONE_BASE_BRICKS.get(), AetherIIBlocks.ICESTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.ICESTONE_CAPSTONE_BRICKS.get(), AetherIIBlocks.ICESTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.ICESTONE_BASE_PILLAR.get(), AetherIIBlocks.ICESTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.ICESTONE_CAPSTONE_PILLAR.get(), AetherIIBlocks.ICESTONE_KEYSTONE.get());
//        this.createCustomColumn(AetherIIBlocks.ICESTONE_PILLAR.get(), AetherIIBlocks.ICESTONE_KEYSTONE.get());
//
//        // Glass
//        this.createGlassBlocks(AetherIIBlocks.QUICKSOIL_GLASS.get(), AetherIIBlocks.QUICKSOIL_GLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.SKYROOT_FRAMED_QUICKSOIL_GLASS.get(), AetherIIBlocks.SKYROOT_FRAMED_QUICKSOIL_GLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.ARKENIUM_FRAMED_QUICKSOIL_GLASS.get(), AetherIIBlocks.ARKENIUM_FRAMED_QUICKSOIL_GLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.CRUDE_SCATTERGLASS.get(), AetherIIBlocks.CRUDE_SCATTERGLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS.get(), AetherIIBlocks.SKYROOT_FRAMED_CRUDE_SCATTERGLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS.get(), AetherIIBlocks.ARKENIUM_FRAMED_CRUDE_SCATTERGLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.SCATTERGLASS.get(), AetherIIBlocks.SCATTERGLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS.get(), AetherIIBlocks.SKYROOT_FRAMED_SCATTERGLASS_PANE.get());
//        this.createGlassBlocks(AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS.get(), AetherIIBlocks.ARKENIUM_FRAMED_SCATTERGLASS_PANE.get());

        // Wool
        this.createFullAndCarpetBlocks(AetherIIBlocks.CLOUDWOOL.get(), AetherIIBlocks.CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.WHITE_CLOUDWOOL.get(), AetherIIBlocks.WHITE_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.ORANGE_CLOUDWOOL.get(), AetherIIBlocks.ORANGE_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.MAGENTA_CLOUDWOOL.get(), AetherIIBlocks.MAGENTA_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL.get(), AetherIIBlocks.LIGHT_BLUE_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.YELLOW_CLOUDWOOL.get(), AetherIIBlocks.YELLOW_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIME_CLOUDWOOL.get(), AetherIIBlocks.LIME_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.PINK_CLOUDWOOL.get(), AetherIIBlocks.PINK_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.GRAY_CLOUDWOOL.get(), AetherIIBlocks.GRAY_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL.get(), AetherIIBlocks.LIGHT_GRAY_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.CYAN_CLOUDWOOL.get(), AetherIIBlocks.CYAN_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.PURPLE_CLOUDWOOL.get(), AetherIIBlocks.PURPLE_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.BLUE_CLOUDWOOL.get(), AetherIIBlocks.BLUE_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.BROWN_CLOUDWOOL.get(), AetherIIBlocks.BROWN_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.GREEN_CLOUDWOOL.get(), AetherIIBlocks.GREEN_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.RED_CLOUDWOOL.get(), AetherIIBlocks.RED_CLOUDWOOL_CARPET.get());
        this.createFullAndCarpetBlocks(AetherIIBlocks.BLACK_CLOUDWOOL.get(), AetherIIBlocks.BLACK_CLOUDWOOL_CARPET.get());
//
//        // Arkenium Blocks
//        this.createDoor(AetherIIBlocks.ARKENIUM_DOOR.get());
//        this.createOrientableTrapdoor(AetherIIBlocks.ARKENIUM_TRAPDOOR.get());

        // Mineral Blocks
        this.createTrivialCube(AetherIIBlocks.AMBROSIUM_BLOCK.get());
        this.createTrivialCube(AetherIIBlocks.ZANITE_BLOCK.get());
        this.createTrivialCube(AetherIIBlocks.ARKENIUM_BLOCK.get());
        this.createTrivialCube(AetherIIBlocks.GRAVITITE_BLOCK.get());

        // Arilum Lantern
        this.createTrivialCube(AetherIIBlocks.GREEN_ARILUM_LANTERN.get());
        this.createTrivialCube(AetherIIBlocks.BLUE_ARILUM_LANTERN.get());
        this.createTrivialCube(AetherIIBlocks.PURPLE_ARILUM_LANTERN.get());
        this.createTrivialCube(AetherIIBlocks.GOLDEN_ARILUM_LANTERN.get());
        this.createTrivialCube(AetherIIBlocks.WHITE_ARILUM_LANTERN.get());

//        // Utility
//        this.createAmbrosiumTorch(blockModels);
//        this.createCraftingTableLike(AetherIIBlocks.SKYROOT_CRAFTING_TABLE.get(), AetherIIBlocks.SKYROOT_PLANKS.get(), TextureMapping::craftingTable);
//        this.createFurnace(AetherIIBlocks.HOLYSTONE_FURNACE.get(), TexturedModel.ORIENTABLE_ONLY_TOP);
//        this.createAltar(blockModels); //todo
//        this.createArtisansBench(blockModels);
//        this.createArkeniumForge(blockModels);
//        this.createChest(AetherIIBlocks.SKYROOT_CHEST.get(), AetherIIBlocks.SKYROOT_PLANKS.get(), ResourceLocation.withDefaultNamespace("skyroot"), true);
//        this.createNonTemplateHorizontalBlock(AetherIIBlocks.SKYROOT_LADDER.get());
//        this.createTrivialCube(AetherIIBlocks.SKYROOT_BED.get()); //todo
//
//        this.createHangingSign(AetherIIBlocks.STRIPPED_SKYROOT_LOG.get(), AetherIIBlocks.SKYROOT_HANGING_SIGN.get(), AetherIIBlocks.SKYROOT_WALL_HANGING_SIGN.get());
//        this.createHangingSign(AetherIIBlocks.STRIPPED_GREATROOT_LOG.get(), AetherIIBlocks.GREATROOT_HANGING_SIGN.get(), AetherIIBlocks.GREATROOT_WALL_HANGING_SIGN.get());
//        this.createHangingSign(AetherIIBlocks.STRIPPED_WISPROOT_LOG.get(), AetherIIBlocks.WISPROOT_HANGING_SIGN.get(), AetherIIBlocks.WISPROOT_WALL_HANGING_SIGN.get());
//
//        // Moa Egg
//        this.createTrivialCube(AetherIIBlocks.MOA_EGG.get()); //todo
//
//        // Bookshelves
//        this.createCustomColumn(AetherIIBlocks.SKYROOT_BOOKSHELF.get(), AetherIIBlocks.SKYROOT_PLANKS.get());
//        this.createCustomColumn(AetherIIBlocks.HOLYSTONE_BOOKSHELF.get(), AetherIIBlocks.HOLYSTONE_BRICKS.get());
//
//        // Furniture
//        this.createTrivialCube(AetherIIBlocks.OUTPOST_CAMPFIRE.get()); //todo
    }
}