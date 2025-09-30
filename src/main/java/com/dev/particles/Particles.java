package com.dev.particles;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

@Mod(modid = Particles.MODID, version = Particles.VERSION, acceptedMinecraftVersions = "[1.8.9]")
public class Particles {
    public static final String MODID = "ParticlesCustomizer";
    public static final String VERSION = "1.12";

    public static float particleScale = 1.0F;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandParticles());
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        if (event.target instanceof EntityLivingBase) {
            World world = event.entityPlayer.worldObj;

            if (world.isRemote) {
                for (int i = 0; i < 10; i++) {
                    double dx = (world.rand.nextDouble() - 0.5) * 0.5;
                    double dy = world.rand.nextDouble() * 0.5;
                    double dz = (world.rand.nextDouble() - 0.5) * 0.5;

                    Minecraft.getMinecraft().effectRenderer.addEffect(
                            new EntityCustomCritFX(
                                    world,
                                    event.target.posX,
                                    event.target.posY + 1.0,
                                    event.target.posZ,
                                    dx, dy, dz
                            )
                    );
                }
            }
        }
    }

    public static class CommandParticles extends CommandBase {
        @Override
        public String getCommandName() {
            return "particles";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/particles <scale>";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length == 1) {
                try {
                    float value = Float.parseFloat(args[0]);
                    float[] allowed = {0.1F,0.2F,0.3F,0.4F,0.5F,0.6F,0.7F,0.8F,0.9F,
                            1F,2F,3F,4F,5F,6F,7F,8F,9F,10F,100F};
                    boolean ok = false;
                    for (float f : allowed) {
                        if (f == value) {
                            ok = true;
                            break;
                        }
                    }

                    if (ok) {
                        particleScale = value;
                        sender.addChatMessage(new ChatComponentText("Particle scale set to " + value));
                    } else {
                        sender.addChatMessage(new ChatComponentText("Invalid value!"));
                    }
                } catch (NumberFormatException e) {
                    sender.addChatMessage(new ChatComponentText("Type a number."));
                }
            } else {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }
    }
}