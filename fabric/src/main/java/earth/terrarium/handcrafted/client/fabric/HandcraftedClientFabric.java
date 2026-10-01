package earth.terrarium.handcrafted.client.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.handcrafted.client.HandcraftedClient;
import earth.terrarium.handcrafted.client.utils.ClientPlatformUtils;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class HandcraftedClientFabric {

    public static void init() {
        HandcraftedClient.onRegisterEntityRenderers(new ClientPlatformUtils.EntityRendererRegistry() {
            @Override
            public <T extends Entity> void register(Supplier<EntityType<T>> entity, EntityRendererProvider<T> provider) {
                EntityRendererRegistry.register(entity.get(), provider);
            }
        });
        HandcraftedClient.onRegisterBlockRenderers(new ClientPlatformUtils.BlockRendererRegistry() {
            @Override
            @SuppressWarnings("unchecked")
            public <T extends BlockEntity, S extends BlockEntityRenderState> void register(RegistryEntry<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T, S> factory) {
                BlockEntityRendererRegistry.register((BlockEntityType<T>) type.get(), factory);
            }
        });
        HandcraftedClient.onRegisterEntityLayers((location, definition) -> ModelLayerRegistry.registerModelLayer(location, definition::get));
    }
}
