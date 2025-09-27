package com.dev.particles;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.TextureManager;
import java.lang.reflect.Field;

public class Renderer extends EffectRenderer {
    private static Field particleScaleField;

    static {
        try {
            particleScaleField = EntityFX.class.getDeclaredField("particleScale");
            particleScaleField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Renderer(World worldIn, TextureManager renderer) {
        super(worldIn, renderer);
    }

    @Override
    public void addEffect(EntityFX effect) {
        if (effect != null && particleScaleField != null) {
            try {
                float currentScale = particleScaleField.getFloat(effect);
                particleScaleField.setFloat(effect, currentScale * Configs.globalScale); // ParticleConfigを使用
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        super.addEffect(effect);
    }
}