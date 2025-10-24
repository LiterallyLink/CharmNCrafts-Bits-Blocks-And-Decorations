package com.charmed.charmncraft.bits;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    // Registry keys for item groups
    public static final RegistryKey<ItemGroup> DELTARUNE_DOODADS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "deltarune_doodads"));
    public static final RegistryKey<ItemGroup> NIGHT_LIGHTS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "night_lights"));
    public static final RegistryKey<ItemGroup> PLUSHIES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "plushies"));
    // Register custom creative tabs for each mod
    public static final ItemGroup DELTARUNE_DOODADS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "deltarune_doodads"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.deltarune_doodads"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "nubert"))))
                    .build());

    public static final ItemGroup NIGHT_LIGHTS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "night_lights"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.night_lights"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "frog_cyan"))))
                    .build());

    public static final ItemGroup PLUSHIES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "plushies"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.plushies"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "creeper_plushie"))))
                    .build());

    public static void initialize() {
        // This method is called to trigger the static initializer
    }
}
