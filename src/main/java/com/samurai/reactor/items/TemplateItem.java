package com.samurai.reactor.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class TemplateItem extends Item {
    public TemplateItem() {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
    }
}