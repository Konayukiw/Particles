package com.dev.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.effectRenderer = new Renderer(mc.theWorld, mc.getTextureManager());
        System.out.println("EffectRenderer set to: " + mc.effectRenderer.getClass().getSimpleName());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player != null && player.ticksExisted % 10 == 0) {
                double x = player.posX + (player.worldObj.rand.nextDouble() - 0.5D) * 2.0D;
                double y = player.posY + player.height * player.worldObj.rand.nextDouble();
                double z = player.posZ + (player.worldObj.rand.nextDouble() - 0.5D) * 2.0D;
                double motionX = (player.worldObj.rand.nextDouble() - 0.5D) * 0.1D;
                double motionY = player.worldObj.rand.nextDouble() * 0.1D;
                double motionZ = (player.worldObj.rand.nextDouble() - 0.5D) * 0.1D;

                player.worldObj.spawnParticle(EnumParticleTypes.FLAME, x, y, z, motionX, motionY, motionZ, new int[0]);
            }
        }
    }
}