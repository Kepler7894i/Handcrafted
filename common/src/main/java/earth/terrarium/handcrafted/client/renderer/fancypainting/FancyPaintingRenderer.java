package earth.terrarium.handcrafted.client.renderer.fancypainting;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.entities.FancyPainting;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FancyPaintingRenderer extends EntityRenderer<FancyPainting, FancyPaintingRenderer.State> {
    private static final Identifier FRAME_SMALL_TEXTURE = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/small_painting_frame.png");
    private static final Identifier FRAME_MEDIUM_TEXTURE = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/medium_painting_frame.png");
    private static final Identifier FRAME_LARGE_TEXTURE = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/large_painting_frame.png");
    private static final Identifier FRAME_TALL_TEXTURE = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/tall_painting_frame.png");
    private static final Identifier FRAME_WIDE_TEXTURE = Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/wide_painting_frame.png");

    private final ModelPart small;
    private final ModelPart medium;
    private final ModelPart large;
    private final ModelPart tall;
    private final ModelPart wide;

    private final Map<PaintingVariant, Identifier> textures = new HashMap<>();

    public FancyPaintingRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.small = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_SMALL).getChild("main");
        this.medium = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_MEDIUM).getChild("main");
        this.large = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_LARGE).getChild("main");
        this.tall = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_TALL).getChild("main");
        this.wide = context.bakeLayer(FancyPaintingModel.LAYER_LOCATION_WIDE).getChild("main");
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(FancyPainting entity, State state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.direction = entity.getDirection();
        state.variant = entity.getVariant().value();
    }

    @Override
    public void submit(State state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        PaintingVariant variant = state.variant;
        if (variant == null) return;
        Direction direction = state.direction;

        int width = variant.width() * 16;
        int height = variant.height() * 16;
        poseStack.pushPose();
        poseStack.scale(0.8f, 0.8f, 0.8f);
        poseStack.mulPose(Axis.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(0, 0.875f, 0.46125f);
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        collector.submitModelPart(getFrame(variant), poseStack, RenderTypes.entitySolid(getFrameTexture(variant)), state.lightCoords, OverlayTexture.NO_OVERLAY, null);

        Identifier texture = this.textures.computeIfAbsent(variant, v -> Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "textures/painting/" + v.assetId().getPath() + ".png"));
        poseStack.translate(width / 2f / -16f, -0.125f + 0.5 * ((32 - height) / 16f), 0.46125);
        poseStack.scale(width / 16f, height / 16f, 1);
        int light = state.lightCoords;
        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(texture), (pose, buffer) -> renderPainting(pose, buffer, direction, light));
        poseStack.popPose();

        super.submit(state, poseStack, collector, camera);
    }

    private ModelPart getFrame(PaintingVariant variant) {
        int width = variant.width();
        int height = variant.height();
        if (width == 1 && height == 1) return small;
        if (width == 2 && height == 2) return medium;
        if (width == 3 && height == 2) return large;
        if (width == 1 && height == 2) return tall;
        if (width == 2 && height == 1) return wide;
        throw new IllegalStateException("Unknown painting variant: " + variant);
    }

    private static void renderPainting(PoseStack.Pose pose, VertexConsumer consumer, Direction dir, int light) {
        Vec3i normal = dir.getUnitVec3i();
        consumer.addVertex(pose.pose(), 0, 0, 0).setColor(-1).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 0, 1, 0).setColor(-1).setUv(0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 1, 1, 0).setColor(-1).setUv(1, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
        consumer.addVertex(pose.pose(), 1, 0, 0).setColor(-1).setUv(1, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
    }

    private static Identifier getFrameTexture(PaintingVariant variant) {
        int width = variant.width();
        int height = variant.height();
        if (width == 1 && height == 1) return FRAME_SMALL_TEXTURE;
        if (width == 2 && height == 2) return FRAME_MEDIUM_TEXTURE;
        if (width == 3 && height == 2) return FRAME_LARGE_TEXTURE;
        if (width == 1 && height == 2) return FRAME_TALL_TEXTURE;
        if (width == 2 && height == 1) return FRAME_WIDE_TEXTURE;
        throw new IllegalStateException("Unknown painting variant: " + variant);
    }

    public static class State extends EntityRenderState {
        public Direction direction = Direction.NORTH;
        public @Nullable PaintingVariant variant;
    }
}
