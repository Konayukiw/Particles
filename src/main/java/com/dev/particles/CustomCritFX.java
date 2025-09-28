package com.dev.particles;

import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.util.MathHelper;
import net.minecraft.client.particle.EntityFX; // 必要
import net.minecraft.client.particle.IParticleFactory; // 必要

public class CustomCritFX extends EntityCritFX {
    public CustomCritFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.particleScale *= Configs.globalScale;
        System.out.println("Created CustomCritFX with initial scale: " + this.particleScale);
    }

    @Override
    public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ) {
        float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);

        float tempScale = this.particleScale;
        this.particleScale *= f;
        super.renderParticle(worldRendererIn, entityIn, partialTicks, rotX, rotZ, rotYZ, rotXY, rotXZ); // スーパークラスのメソッドを呼び出し
        this.particleScale = tempScale;
    }

    public static class Factory implements IParticleFactory {
        @Override
        public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new CustomCritFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}