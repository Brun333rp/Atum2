package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.blocks.IRenderMapper;
import com.teammetallurgy.atum.client.model.entity.ModelDesertWolf;
import com.teammetallurgy.atum.client.model.entity.ModelDustySkeleton;
import com.teammetallurgy.atum.client.model.entity.ModelNomad;
import com.teammetallurgy.atum.client.render.entity.RenderBonestorm;
import com.teammetallurgy.atum.client.render.entity.RenderDesertWolf;
import com.teammetallurgy.atum.client.render.entity.RenderGhost;
import com.teammetallurgy.atum.client.render.entity.RenderTarantula;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderBone;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderNutsCall;
import com.teammetallurgy.atum.client.render.shield.RenderAtumsProtection;
import com.teammetallurgy.atum.client.render.shield.RenderBrigandShield;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.entity.arrow.CustomArrow;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import com.teammetallurgy.atum.init.AtumItems;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void generateParticle(Particle particle) {
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        AtumItems.ATUMS_PROTECTION.setTileEntityItemStackRenderer(new RenderAtumsProtection());
        AtumItems.BRIGAND_SHIELD.setTileEntityItemStackRenderer(new RenderBrigandShield());
        ModelLoader.setCustomMeshDefinition(AtumItems.BRIGAND_SHIELD, stack -> new ModelResourceLocation(new ResourceLocation(Constants.MOD_ID, "brigand_shield"), "inventory"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, RenderTarantula::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, manager -> new RenderLiving<EntityMummy>(manager, new ModelZombie(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityMummy entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/mummy.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBrigand.class, manager -> new RenderBiped<EntityBrigand>(manager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityBrigand entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/brigand.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBarbarian.class, manager -> new RenderBiped<EntityBarbarian>(manager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityBarbarian entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/barbarian.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityNomad.class, manager -> new RenderBiped<EntityNomad>(manager, new ModelNomad(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityNomad entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/nomad.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarlord.class, manager -> new RenderBiped<EntityBanditWarlord>(manager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityBanditWarlord entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/bandit_warlord.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, manager -> new RenderBiped<EntityPharaoh>(manager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityPharaoh entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/pharaoh_blue.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityForsaken.class, manager -> new RenderBiped<EntityForsaken>(manager, new ModelDustySkeleton(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityForsaken entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/forsaken.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, manager -> new RenderGhost(manager, new ModelZombie(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityStoneguard.class, manager -> new RenderBiped<EntityStoneguard>(manager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull EntityStoneguard entity) {
                return new ResourceLocation(Constants.MOD_ID, "textures/entities/stoneguard.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDesertWolf.class, manager -> new RenderDesertWolf(manager, new ModelDesertWolf(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBonestorm.class, RenderBonestorm::new);
        RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, manager -> new RenderArrow<CustomArrow>(manager) {
            @Override
            protected ResourceLocation getEntityTexture(@Nonnull CustomArrow entity) {
                return entity.getTexture();
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallBone.class, manager -> new RenderBone(manager, 0.35F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, RenderNutsCall::new);
    }


    public static void ignoreRenderProperty(Block block) {
        if (block instanceof IRenderMapper) {
            IRenderMapper mapper = (IRenderMapper) block;

            IProperty[] nonRenderingProperties = mapper.getNonRenderingProperties();

            if (nonRenderingProperties.length != 0) {
                IStateMapper stateMapper = (new StateMap.Builder()).ignore(nonRenderingProperties).build();
                ModelLoader.setCustomStateMapper(block, stateMapper);
            }
        }
    }
}