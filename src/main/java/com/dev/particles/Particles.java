package com.dev.particles;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "particles", name = "Particles Customizer", version = "1.0", clientSideOnly = true)
public class Particles {
    public static final String MODID = "particles";

    @SidedProxy(clientSide = "com.dev.particles.ClientProxy", serverSide = "com.dev.particles.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configs.init(event.getSuggestedConfigurationFile().getParentFile().toPath().resolve("particles.cfg").toFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        MinecraftForge.EVENT_BUS.register(new ClientProxy()); // イベント登録
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new Commands());
    }
}