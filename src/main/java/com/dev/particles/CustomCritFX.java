package com.dev.particles;

import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.world.World;

public class CustomCritFX extends EntityCritFX {
    public CustomCritFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.particleScale *= Configs.globalScale;
        System.out.println("Created CustomCritFX with scale: " + this.particleScale);
    }
}