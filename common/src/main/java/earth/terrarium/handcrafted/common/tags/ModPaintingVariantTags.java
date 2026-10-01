package earth.terrarium.handcrafted.common.tags;

import earth.terrarium.handcrafted.Handcrafted;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

public final class ModPaintingVariantTags {
    public static final TagKey<PaintingVariant> PAINTINGS = tag("paintings");

    private static TagKey<PaintingVariant> tag(String name) {
        return TagKey.create(Registries.PAINTING_VARIANT, Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, name));
    }
}
