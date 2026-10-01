package earth.terrarium.handcrafted.common.items;

import earth.terrarium.handcrafted.common.blocks.base.TooltipBlock;
import earth.terrarium.handcrafted.common.utils.TooltipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class TooltipBlockItem extends BlockItem {
    private final @Nullable Component tooltip;

    public TooltipBlockItem(Block block, Properties properties) {
        this(block, null, properties);
    }

    public TooltipBlockItem(Block block, @Nullable Component tooltip, Properties properties) {
        super(block, properties);
        this.tooltip = tooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (this.tooltip != null) {
            TooltipUtils.addDescriptionComponent(tooltipComponents, this.tooltip);
        } else if (this.getBlock() instanceof TooltipBlock block) {
            block.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        }
    }
}
