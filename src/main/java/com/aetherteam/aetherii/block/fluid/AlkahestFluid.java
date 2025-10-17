package com.aetherteam.aetherii.block.fluid;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.BlockDestructionProgress;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class AlkahestFluid extends BaseFlowingFluid implements CanisterFluid {
    public AlkahestFluid(Properties properties) {
        super(properties);
    }

    protected void randomTick(ServerLevel level, BlockPos pos, FluidState state, RandomSource random) {
        // super.randomTick(level, pos, state, random);
        if (level.getBlockState(pos.above()).isEmpty() && state.isSource()) {
            this.createHestveil(level, pos);
        }
    }

    public void tick(Level level, BlockPos pos, FluidState fluidState) {
        super.tick(level, pos, fluidState);
        if (level instanceof ServerLevel serverLevel) {
            this.applyGravity(serverLevel, pos, fluidState);
            this.corrodeNeighbors(serverLevel, pos);
            this.destroyBelow(serverLevel, pos, fluidState);
        }
    }

    private void applyGravity(ServerLevel level, BlockPos pos, FluidState fluidState) {
        BlockState blockState = level.getBlockState(pos);
        if (fluidState.isSource()) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            FluidState belowFluid = level.getFluidState(belowPos);
            if (belowState.isAir() || (belowState.is(this.createLegacyBlock(fluidState).getBlock()) && !belowFluid.isSource())) {
                level.setBlock(belowPos, blockState, 3);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    private void corrodeNeighbors(ServerLevel level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos offsetPos = pos.relative(direction);
            BlockState offsetState = level.getBlockState(offsetPos);
            // Temporarily commented out due to missing recipe system
            // for (RecipeHolder<AlkahestCorrosionRecipe> recipe : level.getRecipeManager().getAllRecipesFor(AetherIIRecipeTypes.ALKAHEST_CORROSION.get())) {
            //     if (recipe != null) {
            //         BlockState newState = recipe.value().getResultState(offsetState);
            //         // if (recipe.value().matches(null, level, offsetPos, null, offsetState, newState, AetherIIRecipeTypes.ALKAHEST_CORROSION.get())) {
            //             if (recipe.value().convert(level, offsetPos, newState, recipe.value().getFunction())) {
            //                 PacketDistributor.sendToPlayersInDimension(level, new AlkahestFizzPacket(pos, direction.getOpposite()));
            //             }
            //         // }
            //     }
            // }
        }
    }

    private void destroyBelow(Level level, BlockPos pos, FluidState fluidState) {
        if (fluidState.isSource()) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (!belowState.isAir() && !belowState.is(this.createLegacyBlock(fluidState).getBlock())) { // && !belowState.is(AetherIITags.Blocks.ALKAHEST_RESISTANT)) {
                int destroySpeed = 0;
                // Temporarily set destroy speed to 0 due to missing tags
                // if (belowState.is(AetherIITags.Blocks.ALKAHEST_INSTANTLY_DESTROYS)) {
                //     destroySpeed = 9;
                // } else if (belowState.is(AetherIITags.Blocks.ALKAHEST_QUICKLY_DESTROYS)) {
                //     destroySpeed = 3;
                // } else if (belowState.is(AetherIITags.Blocks.ALKAHEST_SLOWLY_DESTROYS)) {
                //     destroySpeed = 1;
                // }
                if (destroySpeed != 0 && level instanceof ServerLevel) {
                    // Temporarily commented out due to missing packet class
                    // PacketDistributor.sendToPlayersInDimension((ServerLevel) level, new AlkahestDamageBlockPacket(belowPos, destroySpeed, false));
                    level.scheduleTick(pos, this, this.getTickDelay(level) + 10);
                }
            }
        }
    }

    public static void progressivelyDestroyBlock(Level level, BlockPos belowPos, int speed, boolean drop) {
        int id = belowPos.hashCode();
        BlockDestructionProgress progress = ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).aether_ii$getDestroyingBlocks().get(id);
        if (progress != null) {
            int destroyProgress = progress.getProgress();
            level.destroyBlockProgress(belowPos.hashCode(), belowPos, destroyProgress + speed);
            if (destroyProgress >= 9) {
                // ClientPacketDistributor.sendToServer(new AlkahestBreakBlockPacket(belowPos, drop));
            }
        } else {
            level.destroyBlockProgress(belowPos.hashCode(), belowPos,  speed);
        }
        ParticleUtils.spawnParticlesOnBlockFace(level, belowPos.above(), ParticleTypes.WHITE_SMOKE, UniformInt.of(10, 20), Direction.DOWN, () -> new Vec3(0, 0.5, 0), 0.5);
    }

    private boolean fullyDestroyBlock(ServerLevel level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
        level.removeBlock(pos, false);
        level.levelEvent(2001, pos, Block.getId(state));
        return true;
    }

    public void animateTick(Level level, BlockPos pos, FluidState fluidState, RandomSource random) {
        // if (!fluidState.isSource() && !fluidState.get(FALLING)) {
            if (random.nextInt(64) == 0) {
                // if (AlkahestFluid.canConvertToSource(fluidState)) {
                //     level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, AetherIISoundEvents.ALKAHEST_FIZZ.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
                // }
            }
        // } else if (random.nextInt(10) == 0) {
            // Use DRIPPING_WATER instead of WATER
            level.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        // }

        if (level.random.nextFloat() < 0.05F && fluidState.isSource()) {
            for (int i = 0; i < 5; i++) {
                double d0 = (double) pos.getX() + level.random.nextDouble();
                double d1 = (double) pos.getY() + level.random.nextDouble();
                double d2 = (double) pos.getZ() + level.random.nextDouble();
                // Use DRIPPING_WATER instead of WATER
                level.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private boolean createHestveil(ServerLevel level, BlockPos pos) {
        // if (level.getRandom().nextInt(100000) == 0) {
        //     BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(pos);
        //     for (int y = 0; y < 5; y++) {
        //         mutablePos.set(pos.getX(), pos.getY() + y, pos.getZ());
        //         if (level.isEmptyBlock(mutablePos)) {
        //             level.setBlockAndUpdate(mutablePos, AetherIIBlocks.THICK_ALKAHEST.get().defaultBlockState());
        //             return true;
        //         }
        //     }
        // }
        return false;
    }

    public void entityInside(Level level, BlockPos blockPos, Entity entity) {
        RandomSource random = level.getRandom();
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack itemStack = itemEntity.getItem().copy();
            // if (!itemStack.is(AetherIITags.Items.ALKAHEST_RESISTANT_ITEM) && !itemStack.has(AetherIIDataComponents.REINFORCEMENT_TIER)) {
            itemEntity.lifespan -= 15;
            if (entity.level().isClientSide()) {
                for (int i = 0; i < 2; ++i) {
                    double d0 = random.nextGaussian() * 0.02;
                    double d1 = random.nextGaussian() * 0.02;
                    double d2 = random.nextGaussian() * 0.02;
                    level.addParticle(ParticleTypes.WHITE_SMOKE, itemEntity.getX(), (itemEntity.getY() + itemEntity.getBoundingBox().getYsize()), itemEntity.getZ(), d0, d1, d2);
                }
            }
            // Temporarily commented out due to missing recipe system
            // if (itemEntity.lifespan <= 500) {
            //     // for (RecipeHolder<AlkahestPurificationRecipe> recipe : level.getRecipeManager().getAllRecipesFor(AetherIIRecipeTypes.ALKAHEST_PURIFICATION.get())) {
            //     //     if (recipe != null) {
            //     //         SingleRecipeInputWithRandom input = new SingleRecipeInputWithRandom(itemStack, level.getRandom());
            //     //         if (recipe.value().matches(input, level)) {
            //     //             itemEntity.discard();
            //     //             ItemStack result = recipe.value().assemble(input, level.registryAccess());
            //     //             result.setDamageValue((result.getMaxDamage() / 3) + (random.nextInt(8) * (random.nextBoolean() ? 1 : -1)));
            //     //             ItemEntity cleansedItemEntity = new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), result);
            //     //             level.addFreshEntity(cleansedItemEntity);
            //     //         }
            //     //     }
            //     // }
            // }
            // }
        } else if (entity instanceof LivingEntity livingEntity) {
            if (entity.tickCount % 20 == 0) {
                // livingEntity.hurt(AetherIIDamageTypes.damageSource(level, AetherIIDamageTypes.ALKAHEST), 3.0F);

                if (!livingEntity.level().isClientSide() && livingEntity.level() instanceof ServerLevel serverLevel) {
                    ItemStack mainhandItem = livingEntity.getMainHandItem();
                    // if (!mainhandItem.is(AetherIITags.Items.ALKAHEST_RESISTANT_ITEM) && !mainhandItem.has(AetherIIDataComponents.REINFORCEMENT_TIER)) {
                    // mainhandItem.hurtAndBreak(1, livingEntity, EquipmentSlot.MAINHAND);
                    // }

                    ItemStack offhandItem = livingEntity.getOffhandItem();
                    // if (!offhandItem.is(AetherIITags.Items.ALKAHEST_RESISTANT_ITEM) && !offhandItem.has(AetherIIDataComponents.REINFORCEMENT_TIER)) {
                    // offhandItem.hurtAndBreak(1, livingEntity, EquipmentSlot.OFFHAND);
                    // }

                    // Temporarily commented out due to missing AccessoryUtil
                    // AccessoryUtil.getFirst(livingEntity, AccessoryContainer.SlotType.HANDWEAR).ifPresent((stack) -> {
                    //     // if (!stack.is(AetherIITags.Items.ALKAHEST_RESISTANT_ITEM) && !stack.has(AetherIIDataComponents.REINFORCEMENT_TIER)) {
                    //         if (livingEntity instanceof ServerPlayer serverPlayer) {
                    //             stack.hurtAndBreak(1, serverPlayer, EquipmentSlot.BODY);
                    //         }
                    //     // }
                    // });
                }
            }
        }
    }

    @Override
    public boolean canBeReplacedWith(FluidState fluidState, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER); //todo water interaction
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity blockentity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockentity);
    }

    @Override
    public BlockState createLegacyBlock(FluidState fluidState) {
        // Temporarily return AIR block state due to missing AetherIIBlocks
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean isSame(Fluid fluid) {
        // Simplified isSame check
        return fluid.getClass() == this.getClass();
    }

    @Override
    public Fluid getFlowing() {
        // Temporarily return this due to missing AetherIIFluids
        return new Flowing();
    }

    @Override
    public Fluid getSource() {
        // Temporarily return this due to missing AetherIIFluids
        return new Source();
    }

    @Override
    protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
        super.createFluidStateDefinition(builder);
        builder.add(LEVEL);
    }

    @Override
    public Item getBucket() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public Item getCanister() {
        return ItemStack.EMPTY.getItem(); // Temporarily return empty item
    }

    @Nullable
    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER; // Temporarily use water drip particle
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        // Temporarily return empty due to missing sound events
        return Optional.empty();
    }

    protected boolean canConvertToSource() {
        return false;
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    @Override
    public int getDropOff(LevelReader level) {
        return 3;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 3;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    public static class Source extends AlkahestFluid {
        public Source() {
            super(new Properties());
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public Item getBucket() {
            // Temporarily return empty item due to missing AetherIIItems
            return ItemStack.EMPTY.getItem();
        }
    }

    public static class Flowing extends AlkahestFluid {
        public Flowing() {
            super(new Properties());
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return false;
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public Item getBucket() {
            // Temporarily return empty item due to missing AetherIIItems
            return ItemStack.EMPTY.getItem();
        }
    }
}