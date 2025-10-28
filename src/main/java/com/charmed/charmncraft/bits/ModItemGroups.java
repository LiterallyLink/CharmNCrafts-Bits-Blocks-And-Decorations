package com.charmed.charmncraft.bits;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
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
    public static final RegistryKey<ItemGroup> STACKED_BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "stacked_blocks"));
    public static final RegistryKey<ItemGroup> CONSOLES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "consoles"));
    public static final RegistryKey<ItemGroup> TWIGS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "twigs"));
    public static final RegistryKey<ItemGroup> CRATES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "crates"));
    public static final RegistryKey<ItemGroup> MAGNUM_TORCHES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "magnum_torches"));
    public static final RegistryKey<ItemGroup> AMW_PLUSHIES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Charmncraftbits.MOD_ID, "amw_plushies"));
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

    public static final ItemGroup STACKED_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "stacked_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.stacked_blocks"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "stacked_oak_logs"))))
                    .build());

    public static final ItemGroup CONSOLES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "consoles"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.consoles"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "nes"))))
                    .build());

    public static final ItemGroup TWIGS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "twigs"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.twigs"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "azalea_flowers"))))
                    .build());

    public static final ItemGroup CRATES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "crates"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.crates"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "apple_crate"))))
                    .build());

    public static final ItemGroup MAGNUM_TORCHES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "magnum_torches"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.magnum_torches"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "diamond_magnum_torch"))))
                    .build());

    public static final ItemGroup AMW_PLUSHIES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Charmncraftbits.MOD_ID, "amw_plushies"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.amw_plushies"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "baldi_plushie"))))
                    .build());

    public static void initialize() {
        // This method is called to trigger the static initializer
    }

    public static void populateItemGroups() {
        // Add stacked blocks to STACKED_BLOCKS_GROUP
        ItemGroupEvents.modifyEntriesEvent(STACKED_BLOCKS_KEY).register(entries -> {
            for (Block block : ModBlocks.STACKED_BLOCKS) {
                entries.add(block);
            }
        });

        // Add crates to CRATES_GROUP
        ItemGroupEvents.modifyEntriesEvent(CRATES_KEY).register(entries -> {
            for (Block block : ModBlocks.CRATE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add colored blocks to appropriate group (using STACKED_BLOCKS for now)
        ItemGroupEvents.modifyEntriesEvent(STACKED_BLOCKS_KEY).register(entries -> {
            for (Block block : ModBlocks.COLORED_BLOCKS) {
                entries.add(block);
            }
        });

        // Add magnum torches to MAGNUM_TORCHES_GROUP
        ItemGroupEvents.modifyEntriesEvent(MAGNUM_TORCHES_KEY).register(entries -> {
            for (Block block : ModBlocks.MAGNUM_TORCH_BLOCKS) {
                entries.add(block);
            }
        });

        // Add console blocks to CONSOLES_GROUP
        ItemGroupEvents.modifyEntriesEvent(CONSOLES_KEY).register(entries -> {
            for (Block block : ModBlocks.CONSOLE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add twigs blocks to TWIGS_GROUP
        ItemGroupEvents.modifyEntriesEvent(TWIGS_KEY).register(entries -> {
            for (Block block : ModBlocks.TWIGS_BLOCKS) {
                entries.add(block);
            }
        });

        // Add deltarune blocks to DELTARUNE_DOODADS_GROUP
        ItemGroupEvents.modifyEntriesEvent(DELTARUNE_DOODADS_KEY).register(entries -> {
            for (Block block : ModBlocks.DELTARUNE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add AMW plushies to AMW_PLUSHIES_GROUP
        ItemGroupEvents.modifyEntriesEvent(AMW_PLUSHIES_KEY).register(entries -> {
            // Get the registered AMW plushie blocks
            entries.add(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "abbie_plushie")));
            entries.add(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "maddie_plushie")));
            entries.add(Registries.BLOCK.get(Identifier.of(Charmncraftbits.MOD_ID, "willow_plushie")));
        });

        // Add all plushies to PLUSHIES_GROUP
        ItemGroupEvents.modifyEntriesEvent(PLUSHIES_KEY).register(entries -> {
            for (Block block : ModBlocks.PLUSHIE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add night lights to NIGHT_LIGHTS_GROUP
        ItemGroupEvents.modifyEntriesEvent(NIGHT_LIGHTS_KEY).register(entries -> {
            for (Block block : ModBlocks.NIGHT_LIGHT_BLOCKS) {
                entries.add(block);
            }
        });

        // Add bag blocks to TWIGS_GROUP (or appropriate group)
        ItemGroupEvents.modifyEntriesEvent(TWIGS_KEY).register(entries -> {
            for (Block block : ModBlocks.BAG_BLOCKS) {
                entries.add(block);
            }
        });
    }
}
