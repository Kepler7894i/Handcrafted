package earth.terrarium.handcrafted.mixins.common;

import com.google.gson.JsonObject;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.resources.model.cuboid.CuboidModelElement$Deserializer")
public abstract class BlockElementMixin {

    @Shadow
    private static Vector3f getVector3f(JsonObject object, String key) {
        throw new AssertionError();
    }

    // Remove size limit. Rotation angle limits no longer exist in vanilla.
    @Inject(method = "getPosition", at = @At("HEAD"), cancellable = true)
    private static void handcrafted$getPosition(JsonObject object, String key, CallbackInfoReturnable<Vector3f> cir) {
        cir.setReturnValue(getVector3f(object, key));
    }
}
