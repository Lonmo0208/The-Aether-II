package com.aetherteam.aetherii.data.providers;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.block.natural.AetherLeafPileBlock;
import com.aetherteam.aetherii.block.natural.AetherLeavesBlock;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIIModelTemplates;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIITextureMappings;
import com.aetherteam.aetherii.data.resources.builders.models.AetherIITexturedModels;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AetherIIBlockModelSubProvider extends BlockModelGenerators {
    public AetherIIBlockModelSubProvider(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void createCrossBlock(Block block, PlantType type, TextureMapping mapping) {
        ResourceLocation resourcelocation = type.getCross().extend().renderType(ResourceLocation.withDefaultNamespace("cutout")).build().create(block, mapping, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
    }

    @Override
    public void createPlant(Block plant, Block pot, PlantType type) {
        this.createCrossBlock(plant, type);
        TextureMapping texturemapping = type.getPlantTextureMapping(plant);
        ResourceLocation resourcelocation = type.getCrossPot().extend().renderType(ResourceLocation.withDefaultNamespace("cutout")).build().create(pot, texturemapping, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(pot, resourcelocation));
    }

//    public void createCutoutMippedCube(BlockModelGenerators blockModels, Block block) {
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, AetherIIModelTemplates.TEMPLATE_CUTOUT_MIPPED_CUBE.create(block, TextureMapping.cube(block), blockModels.modelOutput)));
//    }
//
//    public void createTranslucentCube(BlockModelGenerators blockModels, Block block) {
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, AetherIIModelTemplates.TEMPLATE_TRANSLUCENT_CUBE.create(block, TextureMapping.cube(block), blockModels.modelOutput)));
//    }
//
//    public void createCustomColumn(BlockModelGenerators blockModels, Block side, Block top) {
//        TextureMapping mapping = TextureMapping.column(
//                TextureMapping.getBlockTexture(side), TextureMapping.getBlockTexture(top)
//        );
//        ResourceLocation resourcelocation = ModelTemplates.CUBE_COLUMN.create(side, mapping, blockModels.modelOutput);
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(side, resourcelocation));
//    }
//
//    public void createAetherPortalBlock(BlockModelGenerators blockModels) {
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(AetherIIBlocks.AETHER_PORTAL.get()).with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
//                .select(Direction.Axis.X, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(AetherIIBlocks.AETHER_PORTAL.get(), "_ns")))
//                .select(Direction.Axis.Z, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(AetherIIBlocks.AETHER_PORTAL.get(), "_ew")))));
//    }
//
//    public void createAetherGrassBlocks(BlockModelGenerators blockModels) {
//        ResourceLocation dirtLocation = TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_DIRT.get());
//        TextureMapping snowMapping = new TextureMapping()
//                .put(TextureSlot.BOTTOM, dirtLocation)
//                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
//                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_GRASS_BLOCK.get(), "_top"))
//                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_GRASS_BLOCK.get(), "_snow"));
//        Variant snowVariant = Variant.variant().with(VariantProperties.MODEL, ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(AetherIIBlocks.AETHER_GRASS_BLOCK.get(), "_snow", snowMapping, blockModels.modelOutput));
//
//        this.createTintedGrassBlock(blockModels, AetherIIBlocks.AETHER_GRASS_BLOCK.get(), snowVariant);
//
//        ResourceLocation enchantedGrassLocation = TexturedModel.CUBE_TOP_BOTTOM.get(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get())
//                .updateTextures((mapping) -> mapping.put(TextureSlot.BOTTOM, dirtLocation)).create(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get(), blockModels.modelOutput);
//        blockModels.createGrassLikeBlock(AetherIIBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get(), enchantedGrassLocation, snowVariant);
//
//        ResourceLocation dirtPathLocation = AetherIIModelTemplates.DIRT_PATH.create(AetherIIBlocks.AETHER_DIRT_PATH.get(), AetherIITextureMappings.dirtPath(AetherIIBlocks.AETHER_DIRT_PATH.get(), AetherIIBlocks.AETHER_DIRT.get()), blockModels.modelOutput);
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(AetherIIBlocks.AETHER_DIRT_PATH.get(), dirtPathLocation));
//    }
//
//    public void createTintedGrassBlock(BlockModelGenerators blockModels, Block block, Variant snowyVariant) {
//        ResourceLocation model = AetherIIModelTemplates.TINTED_GRASS.create(
//                AetherIIBlocks.AETHER_GRASS_BLOCK.get(),
//                AetherIITextureMappings.tintedGrass(AetherIIBlocks.AETHER_GRASS_BLOCK.get(), AetherIIBlocks.AETHER_DIRT.get()),
//                blockModels.modelOutput
//        );
//        List<Variant> list = Arrays.asList(BlockModelGenerators.createRotatedVariants(model));
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
//                .with(PropertyDispatch.property(BlockStateProperties.SNOWY).select(true, snowyVariant).select(false, list))
//        );
//        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.tintedModel(model,
//                new AetherGrassColorSource(0, AetherIIColorResolvers.AETHER_GRASS_COLOR, 5.0F, 6.0F),
//                new AetherGrassColorSource(1, AetherIIColorResolvers.AETHER_GRASS_COLOR, 5.0F, 6.0F),
//                new AetherGrassColorSource(2, AetherIIColorResolvers.AETHER_GRASS_COLOR, 5.0F, 6.0F)
//        ));
//    }
//
//    public void createAetherFarmland(BlockModelGenerators blockModels) {
//        TextureMapping mapping = new TextureMapping().put(TextureSlot.DIRT, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_DIRT.get())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_FARMLAND.get()));
//        TextureMapping mappingMoist = new TextureMapping().put(TextureSlot.DIRT, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_DIRT.get())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_FARMLAND.get(), "_moist"));
//        ResourceLocation location = ModelTemplates.FARMLAND.create(AetherIIBlocks.AETHER_FARMLAND.get(), mapping, blockModels.modelOutput);
//        ResourceLocation locationMoist = ModelTemplates.FARMLAND.create(TextureMapping.getBlockTexture(AetherIIBlocks.AETHER_FARMLAND.get(), "_moist"), mappingMoist, blockModels.modelOutput);
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(AetherIIBlocks.AETHER_FARMLAND.get()).with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, locationMoist, location)));
//    }
//
//    public void createSnowyCross(BlockModelGenerators blockModels, Block block) {
//        ResourceLocation defaultLocation = AetherIIModelTemplates.TEMPLATE_CUTOUT_CROSS.create(block, TextureMapping.cross(block), blockModels.modelOutput);
//        ResourceLocation snowyLocation = blockModels.createSuffixedVariant(block, "_snowy", AetherIIModelTemplates.TEMPLATE_CUTOUT_CROSS, TextureMapping::cross);
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block.asItem())))
//                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.SNOWY, snowyLocation, defaultLocation)));
//        blockModels.registerSimpleFlatItemModel(block);
//    }
//
//    public void createGlassBlocks(BlockModelGenerators blockModels, Block glass, Block pane) {
//        this.createTranslucentCube(blockModels, glass);
//        TextureMapping mapping = TextureMapping.pane(glass, pane);
//        ResourceLocation post = ModelTemplates.STAINED_GLASS_PANE_POST.extend().renderType("translucent").build().create(pane, mapping, blockModels.modelOutput);
//        ResourceLocation side = ModelTemplates.STAINED_GLASS_PANE_SIDE.extend().renderType("translucent").build().create(pane, mapping, blockModels.modelOutput);
//        ResourceLocation sideAlt = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.extend().renderType("translucent").build().create(pane, mapping, blockModels.modelOutput);
//        ResourceLocation noSide = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.extend().renderType("translucent").build().create(pane, mapping, blockModels.modelOutput);
//        ResourceLocation noSideAlt = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.extend().renderType("translucent").build().create(pane, mapping, blockModels.modelOutput);
//        Item item = pane.asItem();
//        blockModels.registerSimpleItemModel(item, blockModels.createFlatItemModelWithBlockTexture(item, glass));
//        blockModels.blockStateOutput.accept(MultiPartGenerator.multiPart(pane).with(Variant.variant().with(VariantProperties.MODEL, post)).with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, side)).with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, side).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, sideAlt)).with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, sideAlt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, noSide)).with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(VariantProperties.MODEL, noSideAlt)).with(Condition.condition().term(BlockStateProperties.SOUTH, false), Variant.variant().with(VariantProperties.MODEL, noSideAlt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, noSide).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
//    }
//
//    public void createPointedStone(BlockModelGenerators blockModels, Block block) {
//        PropertyDispatch.C2<Direction, DripstoneThickness> c2 = PropertyDispatch.properties(
//                BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS
//        );
//
//        for (DripstoneThickness thicknessUp : DripstoneThickness.values()) {
//            c2.select(Direction.UP, thicknessUp, this.createPointedStoneVariant(blockModels, block, Direction.UP, thicknessUp));
//        }
//
//        for (DripstoneThickness thicknessDown : DripstoneThickness.values()) {
//            c2.select(Direction.DOWN, thicknessDown, this.createPointedStoneVariant(blockModels, block, Direction.DOWN, thicknessDown));
//        }
//
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(c2));
//        blockModels.registerSimpleFlatItemModel(block);
//    }
//
//    public Variant createPointedStoneVariant(BlockModelGenerators blockModels, Block block, Direction direction, DripstoneThickness thickness) {
//        String name = "_" + direction.getSerializedName() + "_" + thickness.getSerializedName();
//        TextureMapping mapping = TextureMapping.cross(TextureMapping.getBlockTexture(block, name));
//        return Variant.variant()
//                .with(VariantProperties.MODEL, AetherIIModelTemplates.TEMPLATE_CUTOUT_CROSS.createWithSuffix(block, name, mapping, blockModels.modelOutput));
//    }
//

    @Override
    public void createAmethystCluster(Block block) {
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL,
                AetherIIModelTemplates.TEMPLATE_CUTOUT_CROSS.create(block, TextureMapping.cross(block), this.modelOutput))).with(this.createColumnWithFacing()));
        this.registerSimpleFlatItemModel(block);
    }

//
//    public void createVine(BlockModelGenerators blockModels, Block block) {
//        blockModels.createMultifaceBlockStates(block);
//        ResourceLocation resourcelocation = blockModels.createFlatItemModelWithBlockTexture(block.asItem(), block);
////        blockModels.registerSimpleFlatItemModel(block);
//    }
//
    protected void createArcticSnowBlocks() {
        TextureMapping mapping = TextureMapping.cube(AetherIIBlocks.ARCTIC_SNOW.get());
        ResourceLocation snowBlockLocation = ModelTemplates.CUBE_ALL.create(AetherIIBlocks.ARCTIC_SNOW_BLOCK.get(), mapping, this.modelOutput);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(AetherIIBlocks.ARCTIC_SNOW.get()).with(PropertyDispatch.property(BlockStateProperties.LAYERS).generate((i) -> {
            Variant variant = Variant.variant();
            VariantProperty<ResourceLocation> property = VariantProperties.MODEL;
            ResourceLocation location;
            if (i < 8) {
                Block block = AetherIIBlocks.ARCTIC_SNOW.get();
                int layers = i * 2;
                location = ModelLocationUtils.getModelLocation(block, "_height" + layers);
                AetherIIModelTemplates.THIN.extend()
                        .element(elementBuilder -> elementBuilder.from(0.0F, 0.0F, 0.0F).to(16.0F, (float) layers, 16.0F)
                                .face(Direction.DOWN, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.UP, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.NORTH, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.SOUTH, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.EAST, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.WEST, faceBuilder -> faceBuilder.texture(TextureSlot.ALL)))
                        .build().create(location, AetherIITextureMappings.particle(mapping), this.modelOutput);
            } else {
                location = snowBlockLocation;
            }
            return variant.with(property, location);
        })));
        this.registerSimpleItemModel(AetherIIBlocks.ARCTIC_SNOW.get(), ModelLocationUtils.getModelLocation(AetherIIBlocks.ARCTIC_SNOW.get(), "_height2"));
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(AetherIIBlocks.ARCTIC_SNOW_BLOCK.get(), snowBlockLocation));
    }

    public void createCustomFlowerBed(Block block, ResourceLocation flowerbed1, ResourceLocation flowerbed2, ResourceLocation flowerbed3, ResourceLocation flowerbed4) {
        this.registerSimpleFlatItemModel(block.asItem());
        this.blockStateOutput.accept(MultiPartGenerator.multiPart(block)
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), Variant.variant().with(VariantProperties.MODEL, flowerbed1))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), Variant.variant().with(VariantProperties.MODEL, flowerbed1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), Variant.variant().with(VariantProperties.MODEL, flowerbed1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), Variant.variant().with(VariantProperties.MODEL, flowerbed1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), Variant.variant().with(VariantProperties.MODEL, flowerbed2))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), Variant.variant().with(VariantProperties.MODEL, flowerbed2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), Variant.variant().with(VariantProperties.MODEL, flowerbed2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), Variant.variant().with(VariantProperties.MODEL, flowerbed2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), Variant.variant().with(VariantProperties.MODEL, flowerbed3))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), Variant.variant().with(VariantProperties.MODEL, flowerbed3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), Variant.variant().with(VariantProperties.MODEL, flowerbed3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 3, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), Variant.variant().with(VariantProperties.MODEL, flowerbed3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), Variant.variant().with(VariantProperties.MODEL, flowerbed4))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), Variant.variant().with(VariantProperties.MODEL, flowerbed4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), Variant.variant().with(VariantProperties.MODEL, flowerbed4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(BlockStateProperties.FLOWER_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), Variant.variant().with(VariantProperties.MODEL, flowerbed4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
    }

    public void createLeavesWithPiles(Block leaves, Block piles) {
        ResourceLocation defaultLocation = AetherIITexturedModels.LEAVES.create(leaves, this.modelOutput);
        ResourceLocation snowyLocation = this.createSuffixedVariant(leaves, "_snowy", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(leaves).with(BlockModelGenerators.createBooleanModelDispatch(AetherLeavesBlock.SNOWY, snowyLocation, defaultLocation)));
        this.createPiles(piles, leaves);
    }

    public void createTintedLeavesWithPiles(Block leaves, Block piles) {
        this.createTrivialBlock(leaves, AetherIITexturedModels.TINTED_LEAVES);
        this.createPiles(piles, leaves);
    }

    public void createPiles(Block piles, Block leaves) {
        TextureMapping textureMapping = AetherIITextureMappings.particle(TextureMapping.cube(leaves));
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(piles).with(PropertyDispatch.property(AetherLeafPileBlock.PILES).generate((i) -> {
            Variant variant = Variant.variant();
            VariantProperty<ResourceLocation> property = VariantProperties.MODEL;
            ResourceLocation location;
            if (i < 16) {
                int layers = i;
                location = ModelLocationUtils.getModelLocation(piles, "_height" + layers);
                AetherIIModelTemplates.THIN.extend()
                        .ambientOcclusion(layers == 1)
                        .renderType(ResourceLocation.withDefaultNamespace("cutout_mipped"))
                        .element(elementBuilder -> elementBuilder.from(0.0F, 0.0F, 0.0F).to(16.0F, (float) layers, 16.0F)
                                .face(Direction.DOWN, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.UP, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.NORTH, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.SOUTH, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.EAST, faceBuilder -> faceBuilder.texture(TextureSlot.ALL))
                                .face(Direction.WEST, faceBuilder -> faceBuilder.texture(TextureSlot.ALL)))
                        .build().create(location, textureMapping, this.modelOutput);
            } else {
                location = ModelLocationUtils.getModelLocation(leaves);
            }
            return variant.with(property, location);
        })));
        this.registerSimpleItemModel(piles, ModelLocationUtils.getModelLocation(piles, "_height1"));
    }
//
//    public void createSnowyPlantWithDefaultItem(BlockModelGenerators blockModels, Block plant, Block pot) {
//        this.createSnowyCross(blockModels, plant);
//
//        TextureMapping plantMapping = TextureMapping.plant(plant);
//        ResourceLocation potLocation = ModelTemplates.FLOWER_POT_CROSS.create(pot, plantMapping, blockModels.modelOutput);
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pot, potLocation));
//    }
//
//    public void createValkyrieSprout(BlockModelGenerators blockModels) {
//        Integer[] list = new Integer[]{0, 1, 2};
//
//        Int2ObjectMap<ResourceLocation> map = new Int2ObjectOpenHashMap<>();
//        PropertyDispatch propertyDispatch = PropertyDispatch.property(ValkyrieSproutBlock.AGE)
//                .generate(
//                        age -> {
//                            int i = list[age];
//                            ResourceLocation location = map.computeIfAbsent(
//                                    i, j -> blockModels.createSuffixedVariant(AetherIIBlocks.VALKYRIE_SPROUT.get(), "_stage" + i, AetherIIModelTemplates.TEMPLATE_CUTOUT_CROSS, TextureMapping::cross)
//                            );
//                            return Variant.variant().with(VariantProperties.MODEL, location);
//                        }
//                );
//        blockModels.registerSimpleFlatItemModel(AetherIIBlocks.VALKYRIE_SPROUT.get().asItem());
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(AetherIIBlocks.VALKYRIE_SPROUT.get()).with(propertyDispatch));
//    }
//
//    public void createAmbrosiumTorch(BlockModelGenerators blockModels) {
//        TextureMapping mapping = TextureMapping.torch(AetherIIBlocks.AMBROSIUM_TORCH.get());
//        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(AetherIIBlocks.AMBROSIUM_TORCH.get(), AetherIIModelTemplates.TALL_TORCH.create(AetherIIBlocks.AMBROSIUM_TORCH.get(), mapping, blockModels.modelOutput)));
//        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(AetherIIBlocks.AMBROSIUM_WALL_TORCH.get(), Variant.variant().with(VariantProperties.MODEL, AetherIIModelTemplates.TALL_WALL_TORCH.create(AetherIIBlocks.AMBROSIUM_WALL_TORCH.get(), mapping, blockModels.modelOutput)))
//                .with(BlockModelGenerators.createTorchHorizontalDispatch()));
//        blockModels.registerSimpleFlatItemModel(AetherIIBlocks.AMBROSIUM_TORCH.get());
//    }
//
//    public void createAltar(BlockModelGenerators blockModels) {
//        ResourceLocation location = AetherIITexturedModels.ALTAR.create(AetherIIBlocks.ALTAR.get(), blockModels.modelOutput);
//        ResourceLocation location_charging = blockModels.createSuffixedVariant(AetherIIBlocks.ALTAR.get(), "_charging", AetherIIModelTemplates.ALTAR, TextureMapping::cube);
//        ResourceLocation location_blasting = blockModels.createSuffixedVariant(AetherIIBlocks.ALTAR.get(), "_blasting", AetherIIModelTemplates.ALTAR, TextureMapping::cube);
//        blockModels.blockStateOutput
//                .accept(
//                        MultiVariantGenerator.multiVariant(AetherIIBlocks.ALTAR.get())
//                                .with(BlockModelGenerators.createHorizontalFacingDispatch())
//                                .with(BlockModelGenerators.createBooleanModelDispatch(AltarBlock.CHARGING, location_charging, location))
//                                .with(BlockModelGenerators.createBooleanModelDispatch(AltarBlock.BLASTING, location_blasting, location))
//                );
//    }
//
//    public void createArtisansBench(BlockModelGenerators blockModels) {
//        blockModels.blockStateOutput
//                .accept(
//                        MultiVariantGenerator.multiVariant(
//                                        AetherIIBlocks.ARTISANS_BENCH.get(), Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(AetherIIBlocks.ARTISANS_BENCH.get()))
//                                )
//                                .with(BlockModelGenerators.createHorizontalFacingDispatch())
//                );
//    }
//
//    public void createArkeniumForge(BlockModelGenerators blockModels) {
//        ResourceLocation location = AetherIITexturedModels.ARKENIUM_FORGE.create(AetherIIBlocks.ARKENIUM_FORGE.get(), blockModels.modelOutput);
//        ResourceLocation location_charged = blockModels.createSuffixedVariant(AetherIIBlocks.ARKENIUM_FORGE.get(), "_charged", AetherIIModelTemplates.ARKENIUM_FORGE, TextureMapping::cube);
//        blockModels.blockStateOutput
//                .accept(
//                        MultiVariantGenerator.multiVariant(AetherIIBlocks.ARKENIUM_FORGE.get())
//                                .with(BlockModelGenerators.createHorizontalFacingDispatch())
//                                .with(BlockModelGenerators.createBooleanModelDispatch(ArkeniumForgeBlock.CHARGED, location_charged, location))
//                );
//    }
}