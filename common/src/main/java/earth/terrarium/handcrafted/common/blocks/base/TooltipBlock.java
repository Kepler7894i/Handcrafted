package earth.terrarium.handcrafted.common.blocks.base;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

/**
 * Blocks no longer have a tooltip hook of their own, so blocks that want one implement this
 * and their {@link net.minecraft.world.item.BlockItem} (see {@code TooltipBlockItem}) forwards to it.
 */
public interface TooltipBlock {

    void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag);
}
