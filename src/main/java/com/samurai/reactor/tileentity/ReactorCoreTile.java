package com.samurai.reactor.tileentity;

import com.samurai.reactor.ModRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ReactorCoreTile extends TileEntity implements ITickableTileEntity {
    
    // Хранилище: 100 миллиардов FE (используем long)
    public long energy = 0;
    public final long MAX_ENERGY = 100_000_000_000L;
    
    // Инвентарь на 64 вида ресурсов
    public final ItemStackHandler inventory = new ItemStackHandler(64);
    
    public ReactorCoreTile() {
        super(ModRegistry.REACTOR_TILE.get());
    }

    @Override
    public void tick() {
        if (world == null || world.isRemote) return;

        // Потребление энергии за тик (например, 1 миллион за работу)
        long costPerTick = 1_000_000L;

        if (energy >= costPerTick) {
            energy -= costPerTick;
            generateResources();
        }
    }

    private void generateResources() {
        // Логика: заполняем все 64 слота по 100,000 предметов
        // В Minecraft стак обычно 64, но мы можем имитировать поток
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack existing = inventory.getStackInSlot(i);
            
            // Если слот пустой, положим туда что-то (например, Алмаз)
            if (existing.isEmpty()) {
                inventory.setStackInSlot(i, new ItemStack(Items.DIAMOND, 64)); 
            }
            
            // Чтобы выдать 100к за тик, нам нужно, чтобы AE2 забирала их мгновенно
            // В обычном инвентаре больше 64 нельзя, поэтому логика 100к 
            // обычно реализуется через виртуальную выдачу в трубы.
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.putLong("energy", energy);
        tag.put("inv", inventory.serializeNBT());
        return super.write(tag);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.energy = tag.getLong("energy");
        inventory.deserializeNBT(tag.getCompound("inv"));
    }
}