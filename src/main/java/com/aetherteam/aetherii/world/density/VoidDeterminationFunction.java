package com.aetherteam.aetherii.world.density;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;

public class VoidDeterminationFunction implements DensityFunction {

    public static final KeyDispatchDataCodec<VoidDeterminationFunction> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec(instance -> instance.group(
                            DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(codec -> codec.input),
                            Codec.INT.fieldOf("y").forGetter(codec -> codec.y)
                    ).apply(instance, VoidDeterminationFunction::new)));

    private final DensityFunction input;
    private final int y;

    public VoidDeterminationFunction(DensityFunction input, int y) {
        this.input = input;
        this.y = y;
    }

    public double compute(FunctionContext context) {
        return input.compute(new DensityFunction.SinglePointContext(context.blockX(), y, context.blockY()));
    }

    @Override
    public void fillArray(double[] array, ContextProvider contextProvider) {
        contextProvider.fillAllDirectly(array, this);
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(this);
    }


    @Override
    public double minValue() {
        return -this.maxValue();
    }

    @Override
    public double maxValue() {
        return input.maxValue();
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}