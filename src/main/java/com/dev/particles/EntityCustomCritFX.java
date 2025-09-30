package com.dev.particles;

import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityCustomCritFX extends EntityCrit2FX {
    public EntityCustomCritFX(World worldIn, double x, double y, double z,
                              double xSpeed, double ySpeed, double zSpeed) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.particleScale *= Particles.particleScale;
    }

    @Override
    public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks,
                               float rotationX, float rotationZ, float rotationYZ,
                               float rotationXY, float rotationXZ) {
        try {
            GlStateManager.pushMatrix();

            GlStateManager.translate(
                    (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX),
                    (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY),
                    (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ)
            );

            GlStateManager.scale(Particles.particleScale, Particles.particleScale, Particles.particleScale);

            super.renderParticle(worldRendererIn, entityIn, partialTicks,
                    rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

        } catch (Exception e) {
            System.out.println("[Particles] Error while rendering custom crit particle:");
            e.printStackTrace();
        } finally {
            GlStateManager.popMatrix();
        }
    }
}