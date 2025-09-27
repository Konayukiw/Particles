package com.dev.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Particles.MODID, name = "Particles Mod", version = "1.08", clientSideOnly = true)
public class Particles {
    public static final String MODID = "particles";

    @SidedProxy(clientSide = "com.dev.particles.ClientProxy", serverSide = "com.dev.particles.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configs.init(event.getSuggestedConfigurationFile().getParentFile().toPath().resolve("particles.cfg").toFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        MinecraftForge.EVENT_BUS.register(new ClientProxy());
        ClientCommandHandler.instance.registerCommand(new Commands());
    }
}