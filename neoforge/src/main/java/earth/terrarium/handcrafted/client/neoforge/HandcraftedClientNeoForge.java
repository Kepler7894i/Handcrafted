package earth.terrarium.handcrafted.client.neoforge;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.handcrafted.client.HandcraftedClient;
import earth.terrarium.handcrafted.client.utils.ClientPlatformUtils;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class HandcraftedClientNeoForge {

    public static void init() {
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        HandcraftedClient.onRegisterEntityRenderers(new ClientPlatformUtils.EntityRendererRegistry() {
            @Override
            public <T extends net.minecraft.world.entity.Entity> void register(java.util.function.Supplier<net.minecraft.world.entity.EntityType<T>> entity, net.minecraft.client.renderer.entity.EntityRendererProvider<T> provider) {
                event.registerEntityRenderer(entity.get(), provider);
            }
        });
        HandcraftedClient.onRegisterBlockRenderers(new ClientPlatformUtils.BlockRendererRegistry() {
            @Override
            @SuppressWarnings("unchecked")
            public <T extends BlockEntity, S extends net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState> void register(RegistryEntry<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T, S> factory) {
                event.registerBlockEntityRenderer((BlockEntityType<T>) type.get(), factory);
            }
        });
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        HandcraftedClient.onRegisterEntityLayers(event::registerLayerDefinition);
    }
}
