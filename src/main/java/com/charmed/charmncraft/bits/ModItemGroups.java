package com.charmed.charmncraft.bits;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final Identifier MORE_SLAB_VARIANTS_ID = Identifier.of(Charmncraftbits.MOD_ID, "more_slab_variants");
    public static final RegistryKey<ItemGroup> MORE_SLAB_VARIANTS_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), MORE_SLAB_VARIANTS_ID);
    
    public static final ItemGroup MORE_SLAB_VARIANTS = FabricItemGroup.builder()
        .displayName(Text.literal("More Slab Variants"))
        .icon(() -> new ItemStack(Registries.ITEM.get(Identifier.of(Charmncraftbits.MOD_ID, "stone_slab"))))
        .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, MORE_SLAB_VARIANTS_ID, MORE_SLAB_VARIANTS);
    }
}
