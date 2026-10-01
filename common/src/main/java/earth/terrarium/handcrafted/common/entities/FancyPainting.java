package earth.terrarium.handcrafted.common.entities;

import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.registry.ModEntityTypes;
import earth.terrarium.handcrafted.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.painting.Painting;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import org.jspecify.annotations.Nullable;

public class FancyPainting extends Painting {

    public FancyPainting(EntityType<? extends Painting> type, Level level) {
        super(type, level);
    }

    public FancyPainting(Level level, BlockPos pos) {
        super(ModEntityTypes.FANCY_PAINTING.get(), level);
        this.pos = pos;
    }

    @Override
    public void dropItem(ServerLevel level, @Nullable Entity causedBy) {
        if (level.getGameRules().get(GameRules.ENTITY_DROPS)) {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (!(causedBy instanceof Player player && player.hasInfiniteMaterials())) {
                this.spawnAtLocation(level, ModItems.FANCY_PAINTING.get());
            }
        }
    }

    @Override
    public ItemStack getPickResult() {
        return ModItems.FANCY_PAINTING.get().getDefaultInstance();
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
    }

    public void setVariant(Holder<PaintingVariant> variant) {
        this.applyImplicitComponent(DataComponents.PAINTING_VARIANT, variant);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        // Worlds saved by older versions stored the variant as a bare path inside the handcrafted namespace.
        input.getString("variant").filter(variant -> variant.indexOf(':') < 0).ifPresent(path -> {
            Identifier id = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, path);
            this.registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT)
                .get(ResourceKey.create(Registries.PAINTING_VARIANT, id))
                .ifPresent(this::setVariant);
        });
    }
}
