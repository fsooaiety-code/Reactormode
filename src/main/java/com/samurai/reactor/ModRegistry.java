package com.samurai.reactor;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "samuraireactor");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "samuraireactor");
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "samuraireactor");

    // Блоки
    public static final RegistryObject<Block> REACTOR_CORE = BLOCKS.register("reactor_core", 
        () -> new ReactorCoreBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(10f).notSolid()));
    
    public static final RegistryObject<Block> STABILIZER = BLOCKS.register("stabilizer", 
        () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5f)));

    // Предметы для блоков
    public static final RegistryObject<Item> REACTOR_CORE_ITEM = ITEMS.register("reactor_core", 
        () -> new BlockItem(REACTOR_CORE.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    // TileEntity (Мозг)
    public static final RegistryObject<TileEntityType<ReactorCoreTile>> REACTOR_TILE = TILES.register("reactor_core",
        () -> TileEntityType.Builder.create(ReactorCoreTile::new, REACTOR_CORE.get()).build(null));
}