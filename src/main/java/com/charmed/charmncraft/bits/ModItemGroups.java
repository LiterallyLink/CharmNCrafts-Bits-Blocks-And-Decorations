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

/**
 * ModItemGroups - Creative tab organization for Charm n Craft Bits
 *
 * This class defines and populates all custom creative tabs:
 * - Stacked Blocks: Decorative material blocks
 * - Crates: Food and produce storage crates
 * - Bags: Ingredient and powder bags
 * - Twigs: Nature decorations
 * - Consoles: Gaming console decorations
 * - Deltarune Doodads: Deltarune-themed blocks
 * - Magnum Torches: Anti-spawn torches
 * - Night Lights: Interactive decorative lights
 */
public class ModItemGroups {
    // Registry keys for item groups
    public static final RegistryKey<ItemGroup> DELTARUNE_DOODADS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "deltarune_doodads"));
    public static final RegistryKey<ItemGroup> NIGHT_LIGHTS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "night_lights"));
    public static final RegistryKey<ItemGroup> STACKED_BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "stacked_blocks"));
    public static final RegistryKey<ItemGroup> CONSOLES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "consoles"));
    public static final RegistryKey<ItemGroup> TWIGS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "twigs"));
    public static final RegistryKey<ItemGroup> CRATES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "crates"));
    public static final RegistryKey<ItemGroup> BAGS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "bags"));
    public static final RegistryKey<ItemGroup> MAGNUM_TORCHES_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Charmncraftbits.MOD_ID, "magnum_torches"));
    // Register custom creative tabs for each mod
    public static final ItemGroup DELTARUNE_DOODADS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "deltarune_doodads"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.deltarune_doodads"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "nubert"))))
                    .build());

    public static final ItemGroup NIGHT_LIGHTS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "night_lights"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.night_lights"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "frog_cyan"))))
                    .build());

    public static final ItemGroup STACKED_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "stacked_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.stacked_blocks"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "stacked_oak_logs"))))
                    .build());

    public static final ItemGroup CONSOLES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "consoles"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.consoles"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "nes"))))
                    .build());

    public static final ItemGroup TWIGS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "twigs"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.twigs"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "azalea_flowers"))))
                    .build());

    public static final ItemGroup CRATES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "crates"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.crates"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "apple_crate"))))
                    .build());

    public static final ItemGroup BAGS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "bags"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.bags"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "sugar_bag"))))
                    .build());

    public static final ItemGroup MAGNUM_TORCHES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "magnum_torches"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.charmncraft-bits.magnum_torches"))
                    .icon(() -> new ItemStack(Registries.BLOCK.get(new Identifier(Charmncraftbits.MOD_ID, "diamond_magnum_torch"))))
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

        // Add crate blocks to CRATES_GROUP
        ItemGroupEvents.modifyEntriesEvent(CRATES_KEY).register(entries -> {
            for (Block block : ModBlocks.CRATE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add deltarune blocks to DELTARUNE_DOODADS_GROUP
        ItemGroupEvents.modifyEntriesEvent(DELTARUNE_DOODADS_KEY).register(entries -> {
            for (Block block : ModBlocks.DELTARUNE_BLOCKS) {
                entries.add(block);
            }
        });

        // Add night lights to NIGHT_LIGHTS_GROUP
        ItemGroupEvents.modifyEntriesEvent(NIGHT_LIGHTS_KEY).register(entries -> {
            for (Block block : ModBlocks.NIGHT_LIGHT_BLOCKS) {
                entries.add(block);
            }
        });

        // Add bag blocks to BAGS_GROUP
        ItemGroupEvents.modifyEntriesEvent(BAGS_KEY).register(entries -> {
            for (Block block : ModBlocks.BAG_BLOCKS) {
                entries.add(block);
            }
        });
    }
}
