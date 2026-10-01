package earth.terrarium.handcrafted.client.utils;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ClientPlatformUtils {

    @FunctionalInterface
    public interface EntityRendererRegistry {
        <T extends Entity> void register(Supplier<EntityType<T>> entity, EntityRendererProvider<T> provider);
    }

    @FunctionalInterface
    public interface BlockRendererRegistry {
        <T extends BlockEntity, S extends BlockEntityRenderState> void register(RegistryEntry<? extends BlockEntityType<? extends T>> type, BlockEntityRendererProvider<T, S> factory);
    }

    @FunctionalInterface
    public interface LayerDefinitionRegistry {
        void register(ModelLayerLocation location, Supplier<LayerDefinition> definition);
    }
}
