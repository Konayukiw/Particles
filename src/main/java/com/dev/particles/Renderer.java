package com.dev.particles;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.TextureManager;
import java.lang.reflect.Field;

public class Renderer extends EffectRenderer {
    private static Field particleScaleField;
    private static boolean loggedFailure = false;

    static {
        try {
            particleScaleField = EntityFX.class.getDeclaredField("field_70544_f");
            particleScaleField.setAccessible(true);
            System.out.println("particleScale field initialized successfully: field_70544_f");
        } catch (NoSuchFieldException e) {
            System.out.println("Failed to find particleScale field. Available fields:");
            for (Field field : EntityFX.class.getDeclaredFields()) {
                System.out.println(field.getName() + " (" + field.getType().getSimpleName() + ")");
            }
            e.printStackTrace();
        }
    }

    public Renderer(World worldIn, TextureManager renderer) {
        super(worldIn, renderer);
        System.out.println("Renderer initialized for world: " + worldIn);
    }

    @Override
    public void addEffect(EntityFX effect) {
        if (effect != null && particleScaleField != null) {
            try {
                float currentScale = particleScaleField.getFloat(effect);
                float newScale = currentScale * Configs.globalScale;
                particleScaleField.setFloat(effect, newScale);
                float finalScale = particleScaleField.getFloat(effect);
                System.out.println("Scaling " + effect.getClass().getSimpleName() +
                        ": Current=" + currentScale +
                        ", Global=" + Configs.globalScale +
                        ", New=" + newScale +
                        ", Final=" + finalScale);

                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                System.out.println("Stack trace for scaling " + effect.getClass().getSimpleName() + ":");
                for (StackTraceElement element : stackTrace) {
                    System.out.println(element.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (!loggedFailure) {
            System.out.println("Failed to scale particle: effect=" + (effect != null ? effect.getClass().getSimpleName() : "null") +
                    ", particleScaleField=" + particleScaleField);
            loggedFailure = true;
        }
        super.addEffect(effect);
    }

    @Override
    public void renderParticles(Entity viewer, float partialTicks) {
        System.out.println("Rendering particles with globalScale: " + Configs.globalScale);
        super.renderParticles(viewer, partialTicks);
    }
}