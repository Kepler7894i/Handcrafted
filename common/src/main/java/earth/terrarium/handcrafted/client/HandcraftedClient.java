package earth.terrarium.handcrafted.client;

import earth.terrarium.handcrafted.client.renderer.CrockeryRenderer;
import earth.terrarium.handcrafted.client.renderer.fancypainting.FancyPaintingModel;
import earth.terrarium.handcrafted.client.renderer.fancypainting.FancyPaintingRenderer;
import earth.terrarium.handcrafted.client.utils.ClientPlatformUtils;
import earth.terrarium.handcrafted.common.registry.ModBlockEntityTypes;
import earth.terrarium.handcrafted.common.registry.ModEntityTypes;
import net.minecraft.client.renderer.entity.NoopRenderer;

public class HandcraftedClient {
    public static void onRegisterEntityRenderers(ClientPlatformUtils.EntityRendererRegistry registry) {
        registry.register(ModEntityTypes.SEAT, NoopRenderer::new);
        registry.register(ModEntityTypes.FANCY_PAINTING, FancyPaintingRenderer::new);
    }

    public static void onRegisterEntityLayers(ClientPlatformUtils.LayerDefinitionRegistry consumer) {
        FancyPaintingModel.register(consumer);
    }

    public static void onRegisterBlockRenderers(ClientPlatformUtils.BlockRendererRegistry consumer) {
        consumer.register(ModBlockEntityTypes.CROCKERY, CrockeryRenderer::new);
    }
}
