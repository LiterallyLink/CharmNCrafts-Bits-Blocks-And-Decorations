package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.PlushieBlock;
import com.charmed.charmncraft.bits.blocks.NightLightBlock;
import com.charmed.charmncraft.bits.blocks.ConsoleBlock;
import com.charmed.charmncraft.bits.blocks.AzaleaFlowersBlock;
import com.charmed.charmncraft.bits.blocks.PottedAzaleaFlowersBlock;
import com.charmed.charmncraft.bits.blocks.MagnumTorchBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModBlocks {
    // Hitbox dimension constants (in 1/16th block units)
    private static final float FROG_MIN = 5/16f;
    private static final float FROG_MAX = 11/16f;
    private static final float FROG_HEIGHT = 6/16f;

    private static final float MUSHROOM_STEM_MIN = 5/16f;
    private static final float MUSHROOM_STEM_MAX = 11/16f;
    private static final float MUSHROOM_STEM_HEIGHT = 4/16f;
    private static final float MUSHROOM_CAP_MIN = 4/16f;
    private static final float MUSHROOM_CAP_MAX = 12/16f;
    private static final float MUSHROOM_CAP_START = 3/16f;
    private static final float MUSHROOM_CAP_END = 9/16f;

    private static final float OCTOPUS_MIN = 5/16f;
    private static final float OCTOPUS_MAX = 11/16f;
    private static final float OCTOPUS_HEIGHT = 6/16f;

    // Shared color array for all decorative blocks
    private static final String[] COLORS = {
        "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange",
        "pink", "purple", "red", "white", "yellow"
    };

    // Store blocks for creative tab registration
    private static final List<Block> COLORED_BLOCKS = new ArrayList<>();
    private static final List<Block> PLUSHIE_BLOCKS = new ArrayList<>();
    private static final List<Block> AMW_PLUSHIE_BLOCKS = new ArrayList<>();
    private static final List<Block> DELTARUNE_BLOCKS = new ArrayList<>();
    private static final List<Block> STACKED_BLOCKS = new ArrayList<>();
    private static final List<Block> CONSOLE_BLOCKS = new ArrayList<>();
    private static final List<Block> TWIGS_BLOCKS = new ArrayList<>();
    private static final List<Block> CRATE_BLOCKS = new ArrayList<>();
    private static final List<Block> MAGNUM_TORCH_BLOCKS = new ArrayList<>();

    // Set of AMW (A Man With Plushies) plushie names for efficient lookup
    private static final Set<String> AMW_PLUSHIE_NAMES = new HashSet<>(Arrays.asList(
        "abbie_plushie", "aggressive_eye_of_cthulhu_plushie", "aggressive_retinazer_plushie",
        "aggressive_spazmatism_plushie", "angry_baldi_plushie", "angry_camper_baldi_plushie",
        "angry_farmer_baldi_plushie", "animdude_plushie", "audino_plushie", "azazel_plushie",
        "badeline_plushie", "baldi_plushie", "bandage_girl_plushie", "bendy_plushie",
        "black_crewmate_plushie", "black_junimo_plushie", "blissey_almost_full_egg_holder_plushie",
        "blissey_egg_holder_plushie", "blissey_full_egg_holder_plushie", "blissey_plushie",
        "blu_heavy_plushie", "blu_spycrab_plushie", "blue_baby_plushie", "blue_crewmate_plushie",
        "blue_junimo_plushie", "blue_pikmin_plushie", "blue_royale_king_plushie", "boyfriend_plushie",
        "brown_crewmate_plushie", "cain_plushie", "camper_baldi_plushie", "candy_plushie",
        "carrot_eater_pufferfish_plushie", "casual_monika_plushie", "cc_blue_knight_plushie",
        "cc_gray_knight_plushie", "cc_green_knight_plushie", "cc_orange_knight_plushie",
        "cc_red_knight_plushie", "cindy_plushie", "cluckshroom_plushie", "coil_head_plushie",
        "companion_block_v2_plushie", "companion_block_plushie", "coreless_glados_body_plushie",
        "crabster_plushie", "cuphead_plushie", "cyan_crewmate_plushie", "employee_bee_suit_plushie",
        "employee_bunny_suit_plushie", "employee_green_suit_plushie", "employee_hazard_suit_plushie",
        "employee_orange_suit_plushie", "employee_pajama_suit_plushie", "employee_purple_suit_plushie",
        "enderman_plush_with_block_plushie", "eye_of_cthulhu_plushie", "fact_core_plushie",
        "fall_guy_plushie", "fallen_angel_monika_plushie", "farmer_baldi_plushie",
        "fedora_glados_plushie", "female_indeedee_plushie", "filter_mask_high_roller_plushie",
        "fredbear_plushie", "freddy_fazbear_plushie", "fully_puffed_pufferfish_plushie",
        "gas_mask_high_roller_plushie", "gd_cube_plushie", "gdm_cube_plushie", "gds_cube_plushie",
        "genuine_blu_spycrab_plushie", "genuine_red_spycrab_plushie", "glados_plushie",
        "golden_freddy_plushie", "golden_plushie", "golden_rambley_plushie", "goose_plushie",
        "green_crewmate_plushie", "green_shovel_knight_plushie", "guff_plushie", "happycane_plushie",
        "hat_kid_plushie", "hat_kid_raincoat_plushie", "headcrab_plushie", "heavy_plushie",
        "henry_stickmin_plushie", "henry_stickmin_toppat_leader_plushie",
        "henry_stickmin_toppat_recruit_plushie", "herobrine_plushie", "insomni_plushie",
        "isaac_plushie", "junimo_plushie", "lariat_plushie", "lime_crewmate_plushie",
        "little_lariat_plushie", "madeline_plushie", "magical_girl_abbie_plushie",
        "male_indeedee_plushie", "masked_employee_bee_suit_plushie",
        "masked_employee_bunny_suit_plushie", "masked_employee_green_suit_plushie",
        "masked_employee_hazard_suit_plushie", "masked_employee_orange_suit_plushie",
        "masked_employee_pajama_suit_plushie", "masked_employee_purple_suit_plushie",
        "meat_boy_plushie", "mind_abbie_plushie", "moai_plushie", "monika_plushie",
        "moobloom_plushie", "muddy_pig_plushie", "mugman_plushie", "ninji_plushie",
        "nyakuza_metro_hat_kid_plushie", "off_plushie", "old_cartoon_bendy_plushie",
        "omori_plushie", "open_shulker_plushie", "orange_crewmate_plushie", "orange_junimo_plushie",
        "original_glados_plushie", "peashooter_plushie", "peppino_blood_red_plushie",
        "peppino_dark_cook_plushie", "peppino_dirt_cook_plushie", "peppino_garish_cook_plushie",
        "peppino_golden_god_plushie", "peppino_money_green_plushie", "peppino_mooney_orange_plushie",
        "peppino_plushie", "peppino_sage_blue_plushie", "peppino_tv_purple_plushie",
        "peppino_unfunny_cook_plushie", "pink_crewmate_plushie", "pink_junimo_plushie",
        "popgoes_plushie", "potato_wheatley_plushie", "potatos_plushie", "purple_crewmate_plushie",
        "purple_junimo_plushie", "ragedude_plushie", "rainbow_crewmate_plushie",
        "rainbow_junimo_plushie", "rambley_plushie", "ratatin_plushie", "red_junimo_plushie",
        "red_pikmin_plushie", "red_royale_king_plushie", "red_spycrab_plushie", "repeater_plushie",
        "retinazer_plushie", "rhyth_plushie", "rick_plushie", "sackboy_plushie", "sans_plushie",
        "semi_puffed_pufferfish_plushie", "sensei_seaweed_plushie", "shadow_freddy_plushie",
        "shovel_knight_plushie", "snow_pea_plushie", "space_core_plushie", "spamton_plushie",
        "spazmatism_plushie", "split_pea_plushie", "sprint_hat_kid_plushie", "steppa_plushie",
        "steve_plushie", "sudowoodo_plushie", "summer_monika_plushie", "sunny_plushie",
        "tainted_the_keeper_plushie", "the_dealer_plushie", "the_keeper_plushie",
        "the_knight_plushie", "the_lamb_blue_cape_plushie", "the_lamb_gold_cape_plushie",
        "the_lamb_green_cape_plushie", "the_lamb_leaf_cover_plushie", "the_lamb_purple_cape_plushie",
        "the_lamb_red_cape_plushie", "the_lamb_white_cape_plushie", "true_eye_of_cthulhu_plushie",
        "ultimate_chicken_plushie", "ultimate_macaw_plushie", "v_1_knucklebuster_plushie",
        "v_1_plushie", "v_1_whiplash_plushie", "v_2_plushie", "v_2_whiplash_plushie",
        "vault_boy_plushie", "vault_boy_thumbs_up_plushie", "welding_mask_high_roller_plushie",
        "wheatley_crab_plushie", "wheatley_in_glados_body_plushie", "wheatley_plushie",
        "white_crewmate_plushie", "white_junimo_plushie", "wiglin_plushie", "wilson_plushie",
        "wobblewok_closed_plushie", "wobblewok_plushie", "yellow_crewmate_plushie",
        "yellow_junimo_plushie", "yellow_pikmin_plushie"
    ));

    // Azalea Flowers blocks
    public static final Block AZALEA_FLOWERS;
    public static final Block POTTED_AZALEA_FLOWERS;

    // List of all plushie types to register
    private static final String[] PLUSHIES = {
        "allay_plushie", "axolotl_plushie", "bat_plushie", "bee_plushie",
        "black_rabbit_plushie", "blaze_plushie", "brown_rabbit_plushie", "camel_plushie",
        "cat_plushie", "cave_spider_plushie", "chicken_plushie", "cod_plushie",
        "cold_frog_plushie", "cow_plushie", "creeper_plushie", "dolphin_plushie",
        "dragon_plushie", "drowned_plushie", "elder_guardian_plushie", "enderman_plushie",
        "endermite_plushie", "evoker_plushie", "frog_plushie", "ghast_plushie",
        "glow_squid_plushie", "goat_plushie", "guardian_plushie", "hoglin_plushie",
        "horse_plushie", "husk_plushie", "illusioner_plushie", "iron_golem_plushie",
        "killer_bunny_plushie", "llama_plushie", "magma_cube_plushie", "mooshroom_plushie",
        "nubert", "ocelot_plushie", "panda_plushie", "parrot_plushie", "phantom_plushie",
        "pig_plushie", "piglin_brute_plushie", "piglin_plushie", "pillager_plushie",
        "polar_bear_plushie", "pufferfish_plushie", "ravager_plushie", "red_fox_plushie",
        "salmon_plushie", "salt_rabbit_plushie", "sheep_plushie", "shulker_plushie",
        "silverfish_plushie", "skeleton_horse_plushie", "skeleton_plushie", "slime_plushie",
        "sniffer_plushie", "snow_golem_plushie", "spider_plushie", "squid_plushie",
        "stray_plushie", "strider_plushie", "tadpole_plushie", "tenna_statue", "tenna_pole", "toast_rabbit_plushie",
        "turtle_plushie", "vex_plushie", "villager_plushie", "vindicator_plushie",
        "wandering_trader_plushie", "warden_plushie", "warm_frog_plushie", "white_fox_plushie",
        "white_rabbit_plushie", "white_splotched_rabbit_plushie", "witch_plushie", "wither_plushie",
        "wither_skeleton_plushie", "wolf_plushie", "yellow_rabbit_plushie", "zoglin_plushie",
        "zombie_plushie", "zombie_villager_plushie", "zombiefied_piglin_plushie",
        // AMW Plushies (A Man With Plushies mod integration)
        "abbie_plushie", "aggressive_eye_of_cthulhu_plushie", "aggressive_retinazer_plushie",
        "aggressive_spazmatism_plushie", "angry_baldi_plushie", "angry_camper_baldi_plushie",
        "angry_farmer_baldi_plushie", "animdude_plushie", "audino_plushie",
        "azazel_plushie", "badeline_plushie", "baldi_plushie",
        "bandage_girl_plushie", "bendy_plushie", "black_crewmate_plushie",
        "black_junimo_plushie", "blissey_almost_full_egg_holder_plushie", "blissey_egg_holder_plushie",
        "blissey_full_egg_holder_plushie", "blissey_plushie", "blu_heavy_plushie",
        "blu_spycrab_plushie", "blue_baby_plushie", "blue_crewmate_plushie",
        "blue_junimo_plushie", "blue_pikmin_plushie", "blue_royale_king_plushie",
        "boyfriend_plushie", "brown_crewmate_plushie", "cain_plushie",
        "camper_baldi_plushie", "candy_plushie", "carrot_eater_pufferfish_plushie",
        "casual_monika_plushie", "cc_blue_knight_plushie", "cc_gray_knight_plushie",
        "cc_green_knight_plushie", "cc_orange_knight_plushie", "cc_red_knight_plushie",
        "cindy_plushie", "cluckshroom_plushie", "coil_head_plushie",
        "companion_block_v2_plushie", "companion_block_plushie", "coreless_glados_body_plushie",
        "crabster_plushie", "cuphead_plushie", "cyan_crewmate_plushie",
        "employee_bee_suit_plushie", "employee_bunny_suit_plushie", "employee_green_suit_plushie",
        "employee_hazard_suit_plushie", "employee_orange_suit_plushie", "employee_pajama_suit_plushie",
        "employee_purple_suit_plushie", "enderman_plush_with_block_plushie", "eye_of_cthulhu_plushie",
        "fact_core_plushie", "fall_guy_plushie", "fallen_angel_monika_plushie",
        "farmer_baldi_plushie", "fedora_glados_plushie", "female_indeedee_plushie",
        "filter_mask_high_roller_plushie", "fredbear_plushie", "freddy_fazbear_plushie",
        "fully_puffed_pufferfish_plushie", "gas_mask_high_roller_plushie", "gd_cube_plushie",
        "gdm_cube_plushie", "gds_cube_plushie", "genuine_blu_spycrab_plushie",
        "genuine_red_spycrab_plushie", "glados_plushie", "golden_freddy_plushie",
        "golden_plushie", "golden_rambley_plushie", "goose_plushie",
        "green_crewmate_plushie", "green_shovel_knight_plushie", "guff_plushie",
        "happycane_plushie", "hat_kid_plushie", "hat_kid_raincoat_plushie",
        "headcrab_plushie", "heavy_plushie", "henry_stickmin_plushie",
        "henry_stickmin_toppat_leader_plushie", "henry_stickmin_toppat_recruit_plushie", "herobrine_plushie",
        "insomni_plushie", "isaac_plushie", "junimo_plushie",
        "lariat_plushie", "lime_crewmate_plushie", "little_lariat_plushie",
        "madeline_plushie", "magical_girl_abbie_plushie", "male_indeedee_plushie",
        "masked_employee_bee_suit_plushie", "masked_employee_bunny_suit_plushie", "masked_employee_green_suit_plushie",
        "masked_employee_hazard_suit_plushie", "masked_employee_orange_suit_plushie", "masked_employee_pajama_suit_plushie",
        "masked_employee_purple_suit_plushie", "meat_boy_plushie", "mind_abbie_plushie",
        "moai_plushie", "monika_plushie", "moobloom_plushie",
        "muddy_pig_plushie", "mugman_plushie", "ninji_plushie",
        "nyakuza_metro_hat_kid_plushie", "off_plushie", "old_cartoon_bendy_plushie",
        "omori_plushie", "open_shulker_plushie", "orange_crewmate_plushie",
        "orange_junimo_plushie", "original_glados_plushie", "peashooter_plushie",
        "peppino_blood_red_plushie", "peppino_dark_cook_plushie", "peppino_dirt_cook_plushie",
        "peppino_garish_cook_plushie", "peppino_golden_god_plushie", "peppino_money_green_plushie",
        "peppino_mooney_orange_plushie", "peppino_plushie", "peppino_sage_blue_plushie",
        "peppino_tv_purple_plushie", "peppino_unfunny_cook_plushie", "pink_crewmate_plushie",
        "pink_junimo_plushie", "popgoes_plushie", "potato_wheatley_plushie",
        "potatos_plushie", "purple_crewmate_plushie", "purple_junimo_plushie",
        "ragedude_plushie", "rainbow_crewmate_plushie", "rainbow_junimo_plushie",
        "rambley_plushie", "ratatin_plushie", "red_junimo_plushie",
        "red_pikmin_plushie", "red_royale_king_plushie", "red_spycrab_plushie",
        "repeater_plushie", "retinazer_plushie", "rhyth_plushie",
        "rick_plushie", "sackboy_plushie", "sans_plushie",
        "semi_puffed_pufferfish_plushie", "sensei_seaweed_plushie", "shadow_freddy_plushie",
        "shovel_knight_plushie", "snow_pea_plushie", "space_core_plushie",
        "spamton_plushie", "spazmatism_plushie", "split_pea_plushie",
        "sprint_hat_kid_plushie", "steppa_plushie", "steve_plushie",
        "sudowoodo_plushie", "summer_monika_plushie", "sunny_plushie",
        "tainted_the_keeper_plushie", "the_dealer_plushie", "the_keeper_plushie",
        "the_knight_plushie", "the_lamb_blue_cape_plushie", "the_lamb_gold_cape_plushie",
        "the_lamb_green_cape_plushie", "the_lamb_leaf_cover_plushie", "the_lamb_purple_cape_plushie",
        "the_lamb_red_cape_plushie", "the_lamb_white_cape_plushie", "true_eye_of_cthulhu_plushie",
        "ultimate_chicken_plushie", "ultimate_macaw_plushie", "v_1_knucklebuster_plushie",
        "v_1_plushie", "v_1_whiplash_plushie", "v_2_plushie",
        "v_2_whiplash_plushie", "vault_boy_plushie", "vault_boy_thumbs_up_plushie",
        "welding_mask_high_roller_plushie", "wheatley_crab_plushie", "wheatley_in_glados_body_plushie",
        "wheatley_plushie", "white_crewmate_plushie", "white_junimo_plushie",
        "wiglin_plushie", "wilson_plushie", "wobblewok_closed_plushie",
        "wobblewok_plushie", "yellow_crewmate_plushie", "yellow_junimo_plushie",
        "yellow_pikmin_plushie"
    };

    // List of all crate and bag types to register
    private static final String[] CRATE_TYPES = {
        "apple_crate", "bass_crate", "beetroot_crate", "berry_crate",
        "black_berry_crate", "blueberry_crate", "brown_mushroom_crate", "carrot_crate",
        "catfish_crate", "cinder_flour_bag", "cocoabeans_bag", "cod_crate",
        "cookie_bag", "diamond_apple_crate", "duck_egg_crate", "egg_crate",
        "end_fish_crate", "ender_dust_bag", "glowberry_crate", "golden_apple_crate",
        "golden_carrot_crate", "green_berry_crate", "ground_cinnamon_bag", "gunpowder_bag",
        "kiwi_egg_crate", "kiwifruit_crate", "orange_berry_crate", "peanut_crate",
        "potato_crate", "powdered_obsidian_bag", "purple_berry_crate", "red_mushroom_crate",
        "salmon_crate", "salt_bag", "sugar_bag", "wheat_flour_bag", "yellow_berry_crate"
    };

    // List of all stacked block types to register
    private static final String[] STACKED_BLOCK_TYPES = {
        "stacked_acacia_logs", "stacked_acacia_planks", "stacked_bamboo_blocks",
        "stacked_bamboo_planks", "stacked_birch_logs", "stacked_birch_planks",
        "stacked_bricks", "stacked_cherry_logs", "stacked_cherry_planks",
        "stacked_coal_blocks", "stacked_cobblestone_blocks", "stacked_crimson_planks",
        "stacked_crimson_stems", "stacked_dark_oak_logs", "stacked_dark_oak_planks",
        "stacked_diamond_blocks", "stacked_emerald_blocks", "stacked_gold_blocks",
        "stacked_iron_blocks", "stacked_jungle_logs", "stacked_jungle_planks",
        "stacked_lapis_blocks", "stacked_mangrove_logs", "stacked_mangrove_planks",
        "stacked_melons", "stacked_netherite_blocks", "stacked_netherrack_blocks",
        "stacked_oak_logs", "stacked_oak_planks", "stacked_organic_compost",
        "stacked_pale_oak_logs", "stacked_pale_oak_planks", "stacked_pumpkins",
        "stacked_quartz_blocks", "stacked_raw_copper_blocks", "stacked_raw_gold_blocks",
        "stacked_raw_iron_blocks", "stacked_redstone_blocks", "stacked_resin_blocks",
        "stacked_resin_bricks", "stacked_spruce_logs", "stacked_spruce_planks",
        "stacked_stone_blocks", "stacked_stripped_acacia_logs", "stacked_stripped_bamboo_blocks",
        "stacked_stripped_birch_logs", "stacked_stripped_cherry_logs", "stacked_stripped_crimson_stems",
        "stacked_stripped_dark_oak_logs", "stacked_stripped_jungle_logs", "stacked_stripped_mangrove_logs",
        "stacked_stripped_oak_logs", "stacked_stripped_pale_oak_logs", "stacked_stripped_spruce_logs",
        "stacked_stripped_warped_stems", "stacked_warped_planks", "stacked_warped_stems"
    };

    // Nubert hitbox - custom shape based on the model
    private static final VoxelShape NUBERT_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(2/16f, 0, 2/16f, 14/16f, 1/16f, 14/16f),  // Base
        VoxelShapes.cuboid(3/16f, 1/16f, 3/16f, 13/16f, 8/16f, 13/16f),  // Body
        VoxelShapes.cuboid(4/16f, 8/16f, 4/16f, 12/16f, 9/16f, 12/16f)   // Top
    );

    // Tenna pole hitbox - custom shape based on the model
    private static final VoxelShape TENNA_POLE_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(7/16f, 0, 7/16f, 9/16f, 9/16f, 9/16f),  // Stick base
        VoxelShapes.cuboid(6/16f, 9/16f, 6/16f, 10/16f, 10/16f, 10/16f),  // Connection
        VoxelShapes.cuboid(5/16f, 10/16f, 6/16f, 11/16f, 14/16f, 10/16f),  // Main antenna body
        VoxelShapes.cuboid(5/16f, 14/16f, 6/16f, 11/16f, 18/16f, 10/16f)   // Antenna top
    );

    // Tenna statue hitbox - custom shape based on the model
    private static final VoxelShape TENNA_STATUE_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(3/16f, 0, 3/16f, 13/16f, 2/16f, 13/16f),  // Base platform
        VoxelShapes.cuboid(7/16f, 0, 5/16f, 10/16f, 3/16f, 11/16f),   // Feet
        VoxelShapes.cuboid(7/16f, 3/16f, 6/16f, 10/16f, 13/16f, 10/16f),  // Lower body/stick
        VoxelShapes.cuboid(0/16f, 14/16f, 1/16f, 8/16f, 18/16f, 6/16f),   // Left arm extended
        VoxelShapes.cuboid(6/16f, 13/16f, 4/16f, 11/16f, 21/16f, 12/16f), // Main body
        VoxelShapes.cuboid(6/16f, 21/16f, 4/16f, 11/16f, 29/16f, 16/16f), // Upper body and head
        VoxelShapes.cuboid(6/16f, 26/16f, 13/16f, 10/16f, 32/16f, 16/16f) // Antenna extending back
    );

    static {
        // Register Azalea Flowers first (needed for potted version)
        AZALEA_FLOWERS = registerAzaleaFlowersBlock("azalea_flowers");
        POTTED_AZALEA_FLOWERS = registerPottedAzaleaFlowersBlock("potted_azalea_flowers");

        // Register flower pot interaction for azalea flowers
        FlowerPotBlock.CONTENT_TO_POTTED.put(AZALEA_FLOWERS, POTTED_AZALEA_FLOWERS);

        // Register all night light blocks with lit property
        registerColoredBlocks("frog", COLORS);
        registerColoredBlocks("mushroom", COLORS);
        registerColoredBlocks("octopus", COLORS);

        // Register all plushie blocks
        registerPlushies();

        // Register all stacked blocks
        registerStackedBlocks();

        // Register all crate and bag blocks
        registerCrates();

        // Register console blocks (starting with NES as test)
        registerConsoles();

        // Register magnum torches
        registerMagnumTorches();

        // Register Night Lights to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NIGHT_LIGHTS_KEY)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Plushies to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.PLUSHIES_KEY)
            .register(entries -> {
                for (Block block : PLUSHIE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register A Man With Plushies to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.AMW_PLUSHIES_KEY)
            .register(entries -> {
                for (Block block : AMW_PLUSHIE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Deltarune Doodads to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.DELTARUNE_DOODADS_KEY)
            .register(entries -> {
                for (Block block : DELTARUNE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Stacked Blocks to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.STACKED_BLOCKS_KEY)
            .register(entries -> {
                for (Block block : STACKED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Consoles to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CONSOLES_KEY)
            .register(entries -> {
                for (Block block : CONSOLE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Twigs items to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.TWIGS_KEY)
            .register(entries -> {
                for (Block block : TWIGS_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Crates to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CRATES_KEY)
            .register(entries -> {
                for (Block block : CRATE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Magnum Torches to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAGNUM_TORCHES_KEY)
            .register(entries -> {
                for (Block block : MAGNUM_TORCH_BLOCKS) {
                    entries.add(block.asItem());
                }
            });
    }

    private static void registerColoredBlocks(String baseName, String[] colors) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            VoxelShape shape = getShapeForBlockType(baseName);

            // Create night light block with glowstone-level lighting (15) when lit
            Block block = new NightLightBlock(
                Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(BlockSoundGroup.WOOL)
                    .luminance(state -> state.get(NightLightBlock.LIT) ? 7 : 0),
                shape
            );

            registerBlock(blockName, block);
        }
    }

    private static VoxelShape getShapeForBlockType(String baseName) {
        switch (baseName) {
            case "frog":
                // Frog: 6x6x6 centered cube for body
                return VoxelShapes.cuboid(FROG_MIN, 0, FROG_MIN, FROG_MAX, FROG_HEIGHT, FROG_MAX);

            case "mushroom":
                // Mushroom: vertical stem + wider cap
                VoxelShape stem = VoxelShapes.cuboid(
                    MUSHROOM_STEM_MIN, 0, MUSHROOM_STEM_MIN,
                    MUSHROOM_STEM_MAX, MUSHROOM_STEM_HEIGHT, MUSHROOM_STEM_MAX
                );
                VoxelShape cap = VoxelShapes.cuboid(
                    MUSHROOM_CAP_MIN, MUSHROOM_CAP_START, MUSHROOM_CAP_MIN,
                    MUSHROOM_CAP_MAX, MUSHROOM_CAP_END, MUSHROOM_CAP_MAX
                );
                return VoxelShapes.union(stem, cap);

            case "octopus":
                // Octopus: 6x6x6 centered cube for body
                return VoxelShapes.cuboid(OCTOPUS_MIN, 0, OCTOPUS_MIN, OCTOPUS_MAX, OCTOPUS_HEIGHT, OCTOPUS_MAX);

            default:
                return VoxelShapes.fullCube();
        }
    }

    private static void registerPlushies() {
        for (String plushieName : PLUSHIES) {
            // Use custom shapes for special blocks, default for others
            VoxelShape shape = null;
            if (plushieName.equals("abbie_plushie")) {
                shape = ABBIE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("aggressive_eye_of_cthulhu_plushie")) {
                shape = AGGRESSIVE_EYE_OF_CTHULHU_PLUSHIE_SHAPE;
            } else if (plushieName.equals("aggressive_retinazer_plushie")) {
                shape = AGGRESSIVE_RETINAZER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("aggressive_spazmatism_plushie")) {
                shape = AGGRESSIVE_SPAZMATISM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("allay_plushie")) {
                shape = ALLAY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("angry_baldi_plushie")) {
                shape = ANGRY_BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("angry_camper_baldi_plushie")) {
                shape = ANGRY_CAMPER_BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("angry_farmer_baldi_plushie")) {
                shape = ANGRY_FARMER_BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("animdude_plushie")) {
                shape = ANIMDUDE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("audino_plushie")) {
                shape = AUDINO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("axolotl_plushie")) {
                shape = AXOLOTL_PLUSHIE_SHAPE;
            } else if (plushieName.equals("azazel_plushie")) {
                shape = AZAZEL_PLUSHIE_SHAPE;
            } else if (plushieName.equals("badeline_plushie")) {
                shape = BADELINE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("baldi_plushie")) {
                shape = BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("bandage_girl_plushie")) {
                shape = BANDAGE_GIRL_PLUSHIE_SHAPE;
            } else if (plushieName.equals("bat_plushie")) {
                shape = BAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("bee_plushie")) {
                shape = BEE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("bendy_plushie")) {
                shape = BENDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("black_crewmate_plushie")) {
                shape = BLACK_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("black_junimo_plushie")) {
                shape = BLACK_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("black_rabbit_plushie")) {
                shape = BLACK_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blaze_plushie")) {
                shape = BLAZE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blissey_almost_full_egg_holder_plushie")) {
                shape = BLISSEY_ALMOST_FULL_EGG_HOLDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blissey_egg_holder_plushie")) {
                shape = BLISSEY_EGG_HOLDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blissey_full_egg_holder_plushie")) {
                shape = BLISSEY_FULL_EGG_HOLDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blissey_plushie")) {
                shape = BLISSEY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blu_heavy_plushie")) {
                shape = BLU_HEAVY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blu_spycrab_plushie")) {
                shape = BLU_SPYCRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blue_baby_plushie")) {
                shape = BLUE_BABY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blue_crewmate_plushie")) {
                shape = BLUE_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blue_junimo_plushie")) {
                shape = BLUE_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blue_pikmin_plushie")) {
                shape = BLUE_PIKMIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("blue_royale_king_plushie")) {
                shape = BLUE_ROYALE_KING_PLUSHIE_SHAPE;
            } else if (plushieName.equals("boyfriend_plushie")) {
                shape = BOYFRIEND_PLUSHIE_SHAPE;
            } else if (plushieName.equals("brown_crewmate_plushie")) {
                shape = BROWN_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("brown_rabbit_plushie")) {
                shape = BROWN_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cain_plushie")) {
                shape = CAIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("camel_plushie")) {
                shape = CAMEL_PLUSHIE_SHAPE;
            } else if (plushieName.equals("camper_baldi_plushie")) {
                shape = CAMPER_BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("candy_plushie")) {
                shape = CANDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("carrot_eater_pufferfish_plushie")) {
                shape = CARROT_EATER_PUFFERFISH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("casual_monika_plushie")) {
                shape = CASUAL_MONIKA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cat_plushie")) {
                shape = CAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cave_spider_plushie")) {
                shape = CAVE_SPIDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cc_blue_knight_plushie")) {
                shape = CC_BLUE_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cc_gray_knight_plushie")) {
                shape = CC_GRAY_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cc_green_knight_plushie")) {
                shape = CC_GREEN_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cc_orange_knight_plushie")) {
                shape = CC_ORANGE_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cc_red_knight_plushie")) {
                shape = CC_RED_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("chicken_plushie")) {
                shape = CHICKEN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cindy_plushie")) {
                shape = CINDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cluckshroom_plushie")) {
                shape = CLUCKSHROOM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cod_plushie")) {
                shape = COD_PLUSHIE_SHAPE;
            } else if (plushieName.equals("coil_head_plushie")) {
                shape = COIL_HEAD_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cold_frog_plushie")) {
                shape = COLD_FROG_PLUSHIE_SHAPE;
            } else if (plushieName.equals("companion_block_plushie")) {
                shape = COMPANION_BLOCK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("companion_block_v2_plushie")) {
                shape = COMPANION_BLOCK_V2_PLUSHIE_SHAPE;
            } else if (plushieName.equals("coreless_glados_body_plushie")) {
                shape = CORELESS_GLADOS_BODY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cow_plushie")) {
                shape = COW_PLUSHIE_SHAPE;
            } else if (plushieName.equals("crabster_plushie")) {
                shape = CRABSTER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("creeper_plushie")) {
                shape = CREEPER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cuphead_plushie")) {
                shape = CUPHEAD_PLUSHIE_SHAPE;
            } else if (plushieName.equals("cyan_crewmate_plushie")) {
                shape = CYAN_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("dolphin_plushie")) {
                shape = DOLPHIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("dragon_plushie")) {
                shape = DRAGON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("drowned_plushie")) {
                shape = DROWNED_PLUSHIE_SHAPE;
            } else if (plushieName.equals("elder_guardian_plushie")) {
                shape = ELDER_GUARDIAN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_bee_suit_plushie")) {
                shape = EMPLOYEE_BEE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_bunny_suit_plushie")) {
                shape = EMPLOYEE_BUNNY_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_green_suit_plushie")) {
                shape = EMPLOYEE_GREEN_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_hazard_suit_plushie")) {
                shape = EMPLOYEE_HAZARD_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_orange_suit_plushie")) {
                shape = EMPLOYEE_ORANGE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_pajama_suit_plushie")) {
                shape = EMPLOYEE_PAJAMA_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("employee_purple_suit_plushie")) {
                shape = EMPLOYEE_PURPLE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("enderman_plush_with_block_plushie")) {
                shape = ENDERMAN_PLUSH_WITH_BLOCK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("enderman_plushie")) {
                shape = ENDERMAN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("endermite_plushie")) {
                shape = ENDERMITE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("evoker_plushie")) {
                shape = EVOKER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("eye_of_cthulhu_plushie")) {
                shape = EYE_OF_CTHULHU_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fact_core_plushie")) {
                shape = FACT_CORE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fall_guy_plushie")) {
                shape = FALL_GUY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fallen_angel_monika_plushie")) {
                shape = FALLEN_ANGEL_MONIKA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("farmer_baldi_plushie")) {
                shape = FARMER_BALDI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fedora_glados_plushie")) {
                shape = FEDORA_GLADOS_PLUSHIE_SHAPE;
            } else if (plushieName.equals("female_indeedee_plushie")) {
                shape = FEMALE_INDEEDEE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("filter_mask_high_roller_plushie")) {
                shape = FILTER_MASK_HIGH_ROLLER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fredbear_plushie")) {
                shape = FREDBEAR_PLUSHIE_SHAPE;
            } else if (plushieName.equals("freddy_fazbear_plushie")) {
                shape = FREDDY_FAZBEAR_PLUSHIE_SHAPE;
            } else if (plushieName.equals("frog_plushie")) {
                shape = FROG_PLUSHIE_SHAPE;
            } else if (plushieName.equals("fully_puffed_pufferfish_plushie")) {
                shape = FULLY_PUFFED_PUFFERFISH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("gas_mask_high_roller_plushie")) {
                shape = GAS_MASK_HIGH_ROLLER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("gd_cube_plushie")) {
                shape = GD_CUBE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("gdm_cube_plushie")) {
                shape = GDM_CUBE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("gds_cube_plushie")) {
                shape = GDS_CUBE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("genuine_blu_spycrab_plushie")) {
                shape = GENUINE_BLU_SPYCRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("genuine_red_spycrab_plushie")) {
                shape = GENUINE_RED_SPYCRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ghast_plushie")) {
                shape = GHAST_PLUSHIE_SHAPE;
            } else if (plushieName.equals("glados_plushie")) {
                shape = GLADOS_PLUSHIE_SHAPE;
            } else if (plushieName.equals("glow_squid_plushie")) {
                shape = GLOW_SQUID_PLUSHIE_SHAPE;
            } else if (plushieName.equals("goat_plushie")) {
                shape = GOAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("golden_freddy_plushie")) {
                shape = GOLDEN_FREDDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("golden_rambley_plushie")) {
                shape = GOLDEN_RAMBLEY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("goose_plushie")) {
                shape = GOOSE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("green_crewmate_plushie")) {
                shape = GREEN_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("green_shovel_knight_plushie")) {
                shape = GREEN_SHOVEL_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("guardian_plushie")) {
                shape = GUARDIAN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("guff_plushie")) {
                shape = GUFF_PLUSHIE_SHAPE;
            } else if (plushieName.equals("happycane_plushie")) {
                shape = HAPPYCANE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("hat_kid_plushie")) {
                shape = HAT_KID_PLUSHIE_SHAPE;
            } else if (plushieName.equals("hat_kid_raincoat_plushie")) {
                shape = HAT_KID_RAINCOAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("headcrab_plushie")) {
                shape = HEADCRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("heavy_plushie")) {
                shape = HEAVY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("henry_stickmin_plushie")) {
                shape = HENRY_STICKMIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("henry_stickmin_toppat_leader_plushie")) {
                shape = HENRY_STICKMIN_TOPPAT_LEADER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("henry_stickmin_toppat_recruit_plushie")) {
                shape = HENRY_STICKMIN_TOPPAT_RECRUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("herobrine_plushie")) {
                shape = HEROBRINE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("hoglin_plushie")) {
                shape = HOGLIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("horse_plushie")) {
                shape = HORSE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("husk_plushie")) {
                shape = HUSK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("illusioner_plushie")) {
                shape = ILLUSIONER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("insomni_plushie")) {
                shape = INSOMNI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("iron_golem_plushie")) {
                shape = IRON_GOLEM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("isaac_plushie")) {
                shape = ISAAC_PLUSHIE_SHAPE;
            } else if (plushieName.equals("junimo_plushie")) {
                shape = JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("killer_bunny_plushie")) {
                shape = KILLER_BUNNY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("lariat_plushie")) {
                shape = LARIAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("lime_crewmate_plushie")) {
                shape = LIME_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("little_lariat_plushie")) {
                shape = LITTLE_LARIAT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("llama_plushie")) {
                shape = LLAMA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("madeline_plushie")) {
                shape = MADELINE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("magical_girl_abbie_plushie")) {
                shape = MAGICAL_GIRL_ABBIE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("magma_cube_plushie")) {
                shape = MAGMA_CUBE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("male_indeedee_plushie")) {
                shape = MALE_INDEEDEE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_bee_suit_plushie")) {
                shape = MASKED_EMPLOYEE_BEE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_bunny_suit_plushie")) {
                shape = MASKED_EMPLOYEE_BUNNY_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_green_suit_plushie")) {
                shape = MASKED_EMPLOYEE_GREEN_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_hazard_suit_plushie")) {
                shape = MASKED_EMPLOYEE_HAZARD_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_orange_suit_plushie")) {
                shape = MASKED_EMPLOYEE_ORANGE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_pajama_suit_plushie")) {
                shape = MASKED_EMPLOYEE_PAJAMA_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("masked_employee_purple_suit_plushie")) {
                shape = MASKED_EMPLOYEE_PURPLE_SUIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("meat_boy_plushie")) {
                shape = MEAT_BOY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("mind_abbie_plushie")) {
                shape = MIND_ABBIE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("moai_plushie")) {
                shape = MOAI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("monika_plushie")) {
                shape = MONIKA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("moobloom_plushie")) {
                shape = MOOBLOOM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("mooshroom_plushie")) {
                shape = MOOSHROOM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("muddy_pig_plushie")) {
                shape = MUDDY_PIG_PLUSHIE_SHAPE;
            } else if (plushieName.equals("mugman_plushie")) {
                shape = MUGMAN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ninji_plushie")) {
                shape = NINJI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("nyakuza_metro_hat_kid_plushie")) {
                shape = NYAKUZA_METRO_HAT_KID_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ocelot_plushie")) {
                shape = OCELOT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("off_plushie")) {
                shape = OFF_PLUSHIE_SHAPE;
            } else if (plushieName.equals("old_cartoon_bendy_plushie")) {
                shape = OLD_CARTOON_BENDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("omori_plushie")) {
                shape = OMORI_PLUSHIE_SHAPE;
            } else if (plushieName.equals("open_shulker_plushie")) {
                shape = OPEN_SHULKER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("orange_crewmate_plushie")) {
                shape = ORANGE_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("orange_junimo_plushie")) {
                shape = ORANGE_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("original_glados_plushie")) {
                shape = ORIGINAL_GLADOS_PLUSHIE_SHAPE;
            } else if (plushieName.equals("panda_plushie")) {
                shape = PANDA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("parrot_plushie")) {
                shape = PARROT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peashooter_plushie")) {
                shape = PEASHOOTER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_blood_red_plushie")) {
                shape = PEPPINO_BLOOD_RED_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_dark_cook_plushie")) {
                shape = PEPPINO_DARK_COOK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_dirt_cook_plushie")) {
                shape = PEPPINO_DIRT_COOK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_garish_cook_plushie")) {
                shape = PEPPINO_GARISH_COOK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_golden_god_plushie")) {
                shape = PEPPINO_GOLDEN_GOD_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_money_green_plushie")) {
                shape = PEPPINO_MONEY_GREEN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_mooney_orange_plushie")) {
                shape = PEPPINO_MOONEY_ORANGE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_plushie")) {
                shape = PEPPINO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_sage_blue_plushie")) {
                shape = PEPPINO_SAGE_BLUE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_tv_purple_plushie")) {
                shape = PEPPINO_TV_PURPLE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("peppino_unfunny_cook_plushie")) {
                shape = PEPPINO_UNFUNNY_COOK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("phantom_plushie")) {
                shape = PHANTOM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("pig_plushie")) {
                shape = PIG_PLUSHIE_SHAPE;
            } else if (plushieName.equals("piglin_brute_plushie")) {
                shape = PIGLIN_BRUTE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("piglin_plushie")) {
                shape = PIGLIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("pillager_plushie")) {
                shape = PILLAGER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("pink_crewmate_plushie")) {
                shape = PINK_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("pink_junimo_plushie")) {
                shape = PINK_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("polar_bear_plushie")) {
                shape = POLAR_BEAR_PLUSHIE_SHAPE;
            } else if (plushieName.equals("popgoes_plushie")) {
                shape = POPGOES_PLUSHIE_SHAPE;
            } else if (plushieName.equals("potato_wheatley_plushie")) {
                shape = POTATO_WHEATLEY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("potatos_plushie")) {
                shape = POTATOS_PLUSHIE_SHAPE;
            } else if (plushieName.equals("pufferfish_plushie")) {
                shape = PUFFERFISH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("purple_crewmate_plushie")) {
                shape = PURPLE_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("purple_junimo_plushie")) {
                shape = PURPLE_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ragedude_plushie")) {
                shape = RAGEDUDE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("rainbow_crewmate_plushie")) {
                shape = RAINBOW_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("rainbow_junimo_plushie")) {
                shape = RAINBOW_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("rambley_plushie")) {
                shape = RAMBLEY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ratatin_plushie")) {
                shape = RATATIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ravager_plushie")) {
                shape = RAVAGER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("red_fox_plushie")) {
                shape = RED_FOX_PLUSHIE_SHAPE;
            } else if (plushieName.equals("red_junimo_plushie")) {
                shape = RED_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("red_pikmin_plushie")) {
                shape = RED_PIKMIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("red_royale_king_plushie")) {
                shape = RED_ROYALE_KING_PLUSHIE_SHAPE;
            } else if (plushieName.equals("red_spycrab_plushie")) {
                shape = RED_SPYCRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("repeater_plushie")) {
                shape = REPEATER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("retinazer_plushie")) {
                shape = RETINAZER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("rhyth_plushie")) {
                shape = RHYTH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("rick_plushie")) {
                shape = RICK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sackboy_plushie")) {
                shape = SACKBOY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("salmon_plushie")) {
                shape = SALMON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("salt_rabbit_plushie")) {
                shape = SALT_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sans_plushie")) {
                shape = SANS_PLUSHIE_SHAPE;
            } else if (plushieName.equals("semi_puffed_pufferfish_plushie")) {
                shape = SEMI_PUFFED_PUFFERFISH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sensei_seaweed_plushie")) {
                shape = SENSEI_SEAWEED_PLUSHIE_SHAPE;
            } else if (plushieName.equals("shadow_freddy_plushie")) {
                shape = SHADOW_FREDDY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sheep_plushie")) {
                shape = SHEEP_PLUSHIE_SHAPE;
            } else if (plushieName.equals("shovel_knight_plushie")) {
                shape = SHOVEL_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("shulker_plushie")) {
                shape = SHULKER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("silverfish_plushie")) {
                shape = SILVERFISH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("skeleton_horse_plushie")) {
                shape = SKELETON_HORSE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("skeleton_plushie")) {
                shape = SKELETON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("slime_plushie")) {
                shape = SLIME_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sniffer_plushie")) {
                shape = SNIFFER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("snow_golem_plushie")) {
                shape = SNOW_GOLEM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("snow_pea_plushie")) {
                shape = SNOW_PEA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("space_core_plushie")) {
                shape = SPACE_CORE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("spamton_plushie")) {
                shape = SPAMTON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("spazmatism_plushie")) {
                shape = SPAZMATISM_PLUSHIE_SHAPE;
            } else if (plushieName.equals("spider_plushie")) {
                shape = SPIDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("split_pea_plushie")) {
                shape = SPLIT_PEA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sprint_hat_kid_plushie")) {
                shape = SPRINT_HAT_KID_PLUSHIE_SHAPE;
            } else if (plushieName.equals("squid_plushie")) {
                shape = SQUID_PLUSHIE_SHAPE;
            } else if (plushieName.equals("steppa_plushie")) {
                shape = STEPPA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("steve_plushie")) {
                shape = STEVE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("stray_plushie")) {
                shape = STRAY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("strider_plushie")) {
                shape = STRIDER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sudowoodo_plushie")) {
                shape = SUDOWOODO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("summer_monika_plushie")) {
                shape = SUMMER_MONIKA_PLUSHIE_SHAPE;
            } else if (plushieName.equals("sunny_plushie")) {
                shape = SUNNY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("tadpole_plushie")) {
                shape = TADPOLE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("tainted_the_keeper_plushie")) {
                shape = TAINTED_THE_KEEPER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_dealer_plushie")) {
                shape = THE_DEALER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_keeper_plushie")) {
                shape = THE_KEEPER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_knight_plushie")) {
                shape = THE_KNIGHT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_blue_cape_plushie")) {
                shape = THE_LAMB_BLUE_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_gold_cape_plushie")) {
                shape = THE_LAMB_GOLD_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_green_cape_plushie")) {
                shape = THE_LAMB_GREEN_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_leaf_cover_plushie")) {
                shape = THE_LAMB_LEAF_COVER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_purple_cape_plushie")) {
                shape = THE_LAMB_PURPLE_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_red_cape_plushie")) {
                shape = THE_LAMB_RED_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("the_lamb_white_cape_plushie")) {
                shape = THE_LAMB_WHITE_CAPE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("toast_rabbit_plushie")) {
                shape = TOAST_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("true_eye_of_cthulhu_plushie")) {
                shape = TRUE_EYE_OF_CTHULHU_PLUSHIE_SHAPE;
            } else if (plushieName.equals("turtle_plushie")) {
                shape = TURTLE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ultimate_chicken_plushie")) {
                shape = ULTIMATE_CHICKEN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("ultimate_macaw_plushie")) {
                shape = ULTIMATE_MACAW_PLUSHIE_SHAPE;
            } else if (plushieName.equals("v_1_knucklebuster_plushie")) {
                shape = V_1_KNUCKLEBUSTER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("v_1_plushie")) {
                shape = V_1_PLUSHIE_SHAPE;
            } else if (plushieName.equals("v_1_whiplash_plushie")) {
                shape = V_1_WHIPLASH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("v_2_plushie")) {
                shape = V_2_PLUSHIE_SHAPE;
            } else if (plushieName.equals("v_2_whiplash_plushie")) {
                shape = V_2_WHIPLASH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("vault_boy_plushie")) {
                shape = VAULT_BOY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("vault_boy_thumbs_up_plushie")) {
                shape = VAULT_BOY_THUMBS_UP_PLUSHIE_SHAPE;
            } else if (plushieName.equals("vex_plushie")) {
                shape = VEX_PLUSHIE_SHAPE;
            } else if (plushieName.equals("villager_plushie")) {
                shape = VILLAGER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("vindicator_plushie")) {
                shape = VINDICATOR_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wandering_trader_plushie")) {
                shape = WANDERING_TRADER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("warden_plushie")) {
                shape = WARDEN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("warm_frog_plushie")) {
                shape = WARM_FROG_PLUSHIE_SHAPE;
            } else if (plushieName.equals("welding_mask_high_roller_plushie")) {
                shape = WELDING_MASK_HIGH_ROLLER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wheatley_crab_plushie")) {
                shape = WHEATLEY_CRAB_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wheatley_in_glados_body_plushie")) {
                shape = WHEATLEY_IN_GLADOS_BODY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wheatley_plushie")) {
                shape = WHEATLEY_PLUSHIE_SHAPE;
            } else if (plushieName.equals("white_crewmate_plushie")) {
                shape = WHITE_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("white_fox_plushie")) {
                shape = WHITE_FOX_PLUSHIE_SHAPE;
            } else if (plushieName.equals("white_junimo_plushie")) {
                shape = WHITE_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("white_rabbit_plushie")) {
                shape = WHITE_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("white_splotched_rabbit_plushie")) {
                shape = WHITE_SPLOTCHED_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wiglin_plushie")) {
                shape = WIGLIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wilson_plushie")) {
                shape = WILSON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("witch_plushie")) {
                shape = WITCH_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wither_plushie")) {
                shape = WITHER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wither_skeleton_plushie")) {
                shape = WITHER_SKELETON_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wobblewok_closed_plushie")) {
                shape = WOBBLEWOK_CLOSED_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wobblewok_plushie")) {
                shape = WOBBLEWOK_PLUSHIE_SHAPE;
            } else if (plushieName.equals("wolf_plushie")) {
                shape = WOLF_PLUSHIE_SHAPE;
            } else if (plushieName.equals("yellow_crewmate_plushie")) {
                shape = YELLOW_CREWMATE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("yellow_junimo_plushie")) {
                shape = YELLOW_JUNIMO_PLUSHIE_SHAPE;
            } else if (plushieName.equals("yellow_pikmin_plushie")) {
                shape = YELLOW_PIKMIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("yellow_rabbit_plushie")) {
                shape = YELLOW_RABBIT_PLUSHIE_SHAPE;
            } else if (plushieName.equals("zoglin_plushie")) {
                shape = ZOGLIN_PLUSHIE_SHAPE;
            } else if (plushieName.equals("zombie_plushie")) {
                shape = ZOMBIE_PLUSHIE_SHAPE;
            } else if (plushieName.equals("zombie_villager_plushie")) {
                shape = ZOMBIE_VILLAGER_PLUSHIE_SHAPE;
            } else if (plushieName.equals("zombiefied_piglin_plushie")) {
                shape = ZOMBIEFIED_PIGLIN_PLUSHIE_SHAPE;
            }

            // Create plushie block with wool sounds and waterlogging
            Block plushieBlock;
            if (shape != null) {
                plushieBlock = new PlushieBlock(
                    Block.Settings.create()
                        .strength(0.7f, 0.7f)
                        .sounds(BlockSoundGroup.WOOL)
                        .nonOpaque(), // Prevent face culling on blocks below
                    shape
                );
            } else {
                plushieBlock = new PlushieBlock(
                    Block.Settings.create()
                        .strength(0.7f, 0.7f)
                        .sounds(BlockSoundGroup.WOOL)
                        .nonOpaque() // Prevent face culling on blocks below
                );
            }

            registerPlushieBlock(plushieName, plushieBlock);
        }
    }

    private static void registerStackedBlocks() {
        for (String blockName : STACKED_BLOCK_TYPES) {
            // Create simple block with stone properties
            Block stackedBlock 
    // ========== PLUSHIE CUSTOM HITBOXES (AUTO-GENERATED) ==========
    // Generated by generate_plushie_hitboxes.py
    // To regenerate, run: python3 generate_plushie_hitboxes.py

    private static final VoxelShape ABBIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5.375/16f,  // min x, y, z
        15.25/16f, 11/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape AGGRESSIVE_EYE_OF_CTHULHU_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape AGGRESSIVE_RETINAZER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 0/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape AGGRESSIVE_SPAZMATISM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape ALLAY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.00672/16f, -4.5/16f, 4.8/16f,  // min x, y, z
        14.00672/16f, 8/16f, 11.7039/16f   // max x, y, z
    );

    private static final VoxelShape ANGRY_BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 16/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape ANGRY_CAMPER_BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 19/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape ANGRY_FARMER_BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 19/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape ANIMDUDE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.25/16f, 0/16f, 1.25/16f,  // min x, y, z
        14.75/16f, 15.5/16f, 14.25/16f   // max x, y, z
    );

    private static final VoxelShape AUDINO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        14.5/16f, 9/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape AXOLOTL_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2/16f, 0/16f, -0.75/16f,  // min x, y, z
        16.75/16f, 6.5/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape AZAZEL_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 5.5/16f,  // min x, y, z
        15/16f, 15/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape BADELINE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 2/16f,  // min x, y, z
        17.25/16f, 16/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BANDAGE_GIRL_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.75/16f, -2.5/16f, 6.25/16f,  // min x, y, z
        12.5/16f, 7.75/16f, 9.25/16f   // max x, y, z
    );

    private static final VoxelShape BAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, 0/16f, 5/16f,  // min x, y, z
        15.5/16f, 11/16f, 9/16f   // max x, y, z
    );

    private static final VoxelShape BEE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -0.5/16f, 0/16f, 0.4/16f,  // min x, y, z
        16.5/16f, 7.25/16f, 15.4/16f   // max x, y, z
    );

    private static final VoxelShape BENDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        18.5/16f, 16/16f, 13.25/16f   // max x, y, z
    );

    private static final VoxelShape BLACK_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape BLACK_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape BLACK_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape BLAZE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 4/16f,  // min x, y, z
        12/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape BLISSEY_ALMOST_FULL_EGG_HOLDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, -0.25/16f, 3/16f,  // min x, y, z
        13/16f, 7.25/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLISSEY_EGG_HOLDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, -0.25/16f, 3/16f,  // min x, y, z
        13/16f, 7.25/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLISSEY_FULL_EGG_HOLDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, -0.25/16f, 3/16f,  // min x, y, z
        13/16f, 7.25/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLISSEY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, -0.25/16f, 3/16f,  // min x, y, z
        13/16f, 7.25/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLU_HEAVY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 6/16f,  // min x, y, z
        13/16f, 16/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLU_SPYCRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.75/16f,  // min x, y, z
        14/16f, 10/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape BLUE_BABY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape BLUE_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape BLUE_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape BLUE_PIKMIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 12.75/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape BLUE_ROYALE_KING_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        13.5/16f, 20/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape BOYFRIEND_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 13/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape BROWN_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape BROWN_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape CAIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape CAMEL_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -3.7/16f, 0/16f, -0.35/16f,  // min x, y, z
        15.25/16f, 13.35/16f, 16.55/16f   // max x, y, z
    );

    private static final VoxelShape CAMPER_BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 19/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape CANDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.4/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 10.25/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape CARROT_EATER_PUFFERFISH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -4/16f, 0/16f, -1/16f,  // min x, y, z
        20/16f, 16/16f, 19/16f   // max x, y, z
    );

    private static final VoxelShape CASUAL_MONIKA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 14/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape CAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        6.15/16f, -4.2/16f, 0.1/16f,  // min x, y, z
        9.85/16f, 8.6/16f, 15.2/16f   // max x, y, z
    );

    private static final VoxelShape CAVE_SPIDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.6/16f, -0.05/16f, 3/16f,  // min x, y, z
        14.1/16f, 3.1/16f, 12.75/16f   // max x, y, z
    );

    private static final VoxelShape CC_BLUE_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 13/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape CC_GRAY_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 13/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape CC_GREEN_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 13/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape CC_ORANGE_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 13/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape CC_RED_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 6/16f,  // min x, y, z
        11/16f, 13/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape CHICKEN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.45/16f, 0/16f, 2/16f,  // min x, y, z
        11.55/16f, 12.5/16f, 12.7/16f   // max x, y, z
    );

    private static final VoxelShape CINDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.4/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 10.25/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape CLUCKSHROOM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0.05/16f, 0.5/16f,  // min x, y, z
        12/16f, 16/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape COD_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.75/16f, -1.9/16f, 1/16f,  // min x, y, z
        9.75/16f, 5/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape COIL_HEAD_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.25/16f, 0/16f, 6/16f,  // min x, y, z
        12.5/16f, 26/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape COLD_FROG_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, 0/16f, 2.5/16f,  // min x, y, z
        15.5/16f, 8.25/16f, 13.75/16f   // max x, y, z
    );

    private static final VoxelShape COMPANION_BLOCK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 0/16f,  // min x, y, z
        16/16f, 16/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape COMPANION_BLOCK_V2_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 0/16f,  // min x, y, z
        16/16f, 16/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape CORELESS_GLADOS_BODY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.75/16f, 1.5/16f, 4/16f,  // min x, y, z
        10.25/16f, 16/16f, 15.1/16f   // max x, y, z
    );

    private static final VoxelShape COW_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 13/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape CRABSTER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.3/16f, 0/16f, 5.99/16f,  // min x, y, z
        12.7/16f, 10/16f, 13/16f   // max x, y, z
    );

    private static final VoxelShape CREEPER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 4/16f,  // min x, y, z
        10.5/16f, 15/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape CUPHEAD_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.4/16f, 0/16f, 6/16f,  // min x, y, z
        10.6/16f, 11/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape CYAN_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape DOLPHIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 0/16f,  // min x, y, z
        11.5/16f, 6/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape DRAGON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -16/16f, -4/16f, -10/16f,  // min x, y, z
        30/16f, 10/16f, 22/16f   // max x, y, z
    );

    private static final VoxelShape DROWNED_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.25/16f, 0/16f, -1/16f,  // min x, y, z
        13/16f, 15/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape ELDER_GUARDIAN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.9/16f, -7.5/16f, -2.9/16f,  // min x, y, z
        14.2/16f, 7/16f, 15.2/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_BEE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 21.125/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_BUNNY_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 20.95/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_GREEN_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_HAZARD_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_ORANGE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_PAJAMA_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape EMPLOYEE_PURPLE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape ENDERMAN_PLUSH_WITH_BLOCK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 2.25/16f,  // min x, y, z
        11/16f, 16/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape ENDERMAN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.8/16f, 0/16f, 5.3/16f,  // min x, y, z
        11.2/16f, 16/16f, 10.6/16f   // max x, y, z
    );

    private static final VoxelShape ENDERMITE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 4/16f,  // min x, y, z
        11/16f, 4/16f, 13/16f   // max x, y, z
    );

    private static final VoxelShape EVOKER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, -0.1/16f,  // min x, y, z
        11.75/16f, 14.5/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape EYE_OF_CTHULHU_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape FACT_CORE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 0.575/16f,  // min x, y, z
        15/16f, 15/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape FALL_GUY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 6.5/16f,  // min x, y, z
        13/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape FALLEN_ANGEL_MONIKA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 5.5/16f,  // min x, y, z
        15/16f, 15/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape FARMER_BALDI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 19/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape FEDORA_GLADOS_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 1/16f, 0/16f,  // min x, y, z
        10.5/16f, 16/16f, 15.1/16f   // max x, y, z
    );

    private static final VoxelShape FEMALE_INDEEDEE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 5/16f,  // min x, y, z
        14/16f, 9.75/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape FILTER_MASK_HIGH_ROLLER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 11/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape FREDBEAR_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.7/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 16.85/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape FREDDY_FAZBEAR_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.7/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 16.85/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape FROG_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, 0/16f, 2.5/16f,  // min x, y, z
        15.5/16f, 8.25/16f, 13.75/16f   // max x, y, z
    );

    private static final VoxelShape FULLY_PUFFED_PUFFERFISH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -4/16f, 0/16f, -1/16f,  // min x, y, z
        20/16f, 16/16f, 19/16f   // max x, y, z
    );

    private static final VoxelShape GAS_MASK_HIGH_ROLLER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 11/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape GD_CUBE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        10.5/16f, 5/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape GDM_CUBE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.25/16f, 0/16f, 5.25/16f,  // min x, y, z
        10.75/16f, 5/16f, 10.75/16f   // max x, y, z
    );

    private static final VoxelShape GDS_CUBE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 5.4/16f,  // min x, y, z
        10.5/16f, 5/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape GENUINE_BLU_SPYCRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.75/16f,  // min x, y, z
        14/16f, 10/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape GENUINE_RED_SPYCRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.75/16f,  // min x, y, z
        14/16f, 10/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape GHAST_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, -0.5/16f, 4.5/16f,  // min x, y, z
        11.5/16f, 11.5/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape GLADOS_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.75/16f, 1/16f, 0.25/16f,  // min x, y, z
        10.25/16f, 16/16f, 15.1/16f   // max x, y, z
    );

    private static final VoxelShape GLOW_SQUID_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -3.3/16f, 0/16f, -8.4/16f,  // min x, y, z
        20.9/16f, 9.2/16f, 18.05/16f   // max x, y, z
    );

    private static final VoxelShape GOAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, -6.25/16f,  // min x, y, z
        11.5/16f, 15.6/16f, 15.75/16f   // max x, y, z
    );

    private static final VoxelShape GOLDEN_FREDDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.7/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 16.85/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape GOLDEN_RAMBLEY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.5/16f,  // min x, y, z
        14/16f, 13.5/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape GOOSE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 1.5/16f,  // min x, y, z
        11.5/16f, 14/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape GREEN_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape GREEN_SHOVEL_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 0/16f,  // min x, y, z
        15/16f, 19/16f, 13/16f   // max x, y, z
    );

    private static final VoxelShape GUARDIAN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.7/16f, -5.2/16f, -2.9/16f,  // min x, y, z
        14.2/16f, 10.9/16f, 15.2/16f   // max x, y, z
    );

    private static final VoxelShape GUFF_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -4/16f, 0/16f, 2/16f,  // min x, y, z
        20/16f, 21.75/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape HAPPYCANE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5/16f,  // min x, y, z
        12/16f, 16/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape HAT_KID_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 4.5/16f,  // min x, y, z
        12/16f, 19/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape HAT_KID_RAINCOAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 4.5/16f,  // min x, y, z
        12/16f, 19/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape HEADCRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 1.1/16f,  // min x, y, z
        13.5/16f, 8/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape HEAVY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 6/16f,  // min x, y, z
        13/16f, 16/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape HENRY_STICKMIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.68121/16f, 0/16f, 5.4/16f,  // min x, y, z
        10.75/16f, 16.075/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape HENRY_STICKMIN_TOPPAT_LEADER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 4.4/16f,  // min x, y, z
        11.5/16f, 20/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape HENRY_STICKMIN_TOPPAT_RECRUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        11.5/16f, 20/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape HEROBRINE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 14/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape HOGLIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.85/16f, -3.35/16f, -3.85/16f,  // min x, y, z
        14.3/16f, 12.4/16f, 15.9/16f   // max x, y, z
    );

    private static final VoxelShape HORSE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.9/16f, -1.55/16f, 4.75/16f,  // min x, y, z
        10.1/16f, 11.95/16f, 16.25/16f   // max x, y, z
    );

    private static final VoxelShape HUSK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.25/16f, 0.05/16f, -1/16f,  // min x, y, z
        13/16f, 15/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape ILLUSIONER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, -0.1/16f,  // min x, y, z
        11.75/16f, 14.6/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape INSOMNI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.71115/16f, 0/16f, 5.99/16f,  // min x, y, z
        13.28885/16f, 10.5839/16f, 12.75/16f   // max x, y, z
    );

    private static final VoxelShape IRON_GOLEM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.75/16f, 0/16f, 5.05/16f,  // min x, y, z
        13.25/16f, 16/16f, 11.05/16f   // max x, y, z
    );

    private static final VoxelShape ISAAC_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape KILLER_BUNNY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape LARIAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, -0.45/16f,  // min x, y, z
        11.5/16f, 19.25/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape LIME_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape LITTLE_LARIAT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, -0.45/16f,  // min x, y, z
        11.5/16f, 17.25/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape LLAMA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.1/16f, 0/16f, 0.95/16f,  // min x, y, z
        10.85/16f, 14.4/16f, 14.2/16f   // max x, y, z
    );

    private static final VoxelShape MADELINE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape MAGICAL_GIRL_ABBIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5.375/16f,  // min x, y, z
        13.5/16f, 13.75/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape MAGMA_CUBE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 14/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape MALE_INDEEDEE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5/16f,  // min x, y, z
        13.5/16f, 12.64836/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_BEE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 21.125/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_BUNNY_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 20.95/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_GREEN_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_HAZARD_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_ORANGE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_PAJAMA_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MASKED_EMPLOYEE_PURPLE_SUIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape MEAT_BOY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.75/16f, -2.5/16f, 7/16f,  // min x, y, z
        12.5/16f, 7.25/16f, 9.01/16f   // max x, y, z
    );

    private static final VoxelShape MIND_ABBIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 5.375/16f,  // min x, y, z
        13.5/16f, 13.75/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape MOAI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 4/16f,  // min x, y, z
        11.5/16f, 13/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape MONIKA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 14/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape MOOBLOOM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 0/16f,  // min x, y, z
        11.5/16f, 16/16f, 13/16f   // max x, y, z
    );

    private static final VoxelShape MOOSHROOM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 13/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape MUDDY_PIG_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 0/16f,  // min x, y, z
        10.5/16f, 13.5/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape MUGMAN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.4/16f, 0/16f, 6/16f,  // min x, y, z
        10.6/16f, 11/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape NINJI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 8.5/16f, 10.75/16f   // max x, y, z
    );

    private static final VoxelShape NYAKUZA_METRO_HAT_KID_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 1.75/16f,  // min x, y, z
        12/16f, 14.75/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape OCELOT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        6.15/16f, 0/16f, 0/16f,  // min x, y, z
        9.9/16f, 10.875/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape OFF_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 6/16f,  // min x, y, z
        12/16f, 12/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape OLD_CARTOON_BENDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        18.5/16f, 16/16f, 13.25/16f   // max x, y, z
    );

    private static final VoxelShape OMORI_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 3.75/16f,  // min x, y, z
        12/16f, 14/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape OPEN_SHULKER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        6/16f, 0.01/16f, 6/16f,  // min x, y, z
        10/16f, 6/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape ORANGE_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape ORANGE_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape ORIGINAL_GLADOS_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 1/16f, 0/16f,  // min x, y, z
        13/16f, 16/16f, 15.15/16f   // max x, y, z
    );

    private static final VoxelShape PANDA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 4/16f,  // min x, y, z
        12/16f, 9/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PARROT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, -3.75/16f, 0.5/16f,  // min x, y, z
        12.25/16f, 16.5/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape PEASHOOTER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.765/16f, 0/16f, -2.755/16f,  // min x, y, z
        15.025/16f, 12.5/16f, 17.992/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_BLOOD_RED_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_DARK_COOK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_DIRT_COOK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_GARISH_COOK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_GOLDEN_GOD_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_MONEY_GREEN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_MOONEY_ORANGE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_SAGE_BLUE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_TV_PURPLE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PEPPINO_UNFUNNY_COOK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.95/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.1/16f, 19/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape PHANTOM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.25/16f, 0/16f, 3.9/16f,  // min x, y, z
        15.75/16f, 1.75/16f, 13.95/16f   // max x, y, z
    );

    private static final VoxelShape PIG_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.7/16f, 0/16f, 0.1/16f,  // min x, y, z
        11.2/16f, 10.9/16f, 15.8/16f   // max x, y, z
    );

    private static final VoxelShape PIGLIN_BRUTE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -5.6/16f, 0/16f, 5.55/16f,  // min x, y, z
        12/16f, 16.9/16f, 10.4/16f   // max x, y, z
    );

    private static final VoxelShape PIGLIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -5.6/16f, 0/16f, 5.55/16f,  // min x, y, z
        12/16f, 16.9/16f, 10.4/16f   // max x, y, z
    );

    private static final VoxelShape PILLAGER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.15/16f, 0/16f, 5.4/16f,  // min x, y, z
        11.85/16f, 14.5/16f, 10.4/16f   // max x, y, z
    );

    private static final VoxelShape PINK_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape PINK_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape POLAR_BEAR_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.1/16f, 0/16f, 1.75/16f,  // min x, y, z
        10.9/16f, 6.8/16f, 14.55/16f   // max x, y, z
    );

    private static final VoxelShape POPGOES_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.7/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 16.85/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape POTATO_WHEATLEY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.5/16f, 0/16f, 3.02513/16f,  // min x, y, z
        16.5/16f, 6/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape POTATOS_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.5/16f, 0/16f, 3.02513/16f,  // min x, y, z
        16.5/16f, 6/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape PUFFERFISH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -5.7/16f, 0/16f, -0.1/16f,  // min x, y, z
        16.95/16f, 13.8/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape PURPLE_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape PURPLE_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape RAGEDUDE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.25/16f, 0/16f, 1.25/16f,  // min x, y, z
        14.75/16f, 17.5/16f, 14.25/16f   // max x, y, z
    );

    private static final VoxelShape RAINBOW_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape RAINBOW_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape RAMBLEY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.5/16f,  // min x, y, z
        14/16f, 13.5/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape RATATIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.5/16f, 0/16f, 3/16f,  // min x, y, z
        15/16f, 16.48305/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape RAVAGER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.1/16f, 0/16f, -2.03604/16f,  // min x, y, z
        10.9/16f, 8.8/16f, 13.4/16f   // max x, y, z
    );

    private static final VoxelShape RED_FOX_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -3/16f, 0/16f, 5/16f,  // min x, y, z
        12/16f, 5.25/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape RED_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape RED_PIKMIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 4/16f,  // min x, y, z
        11/16f, 12.75/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape RED_ROYALE_KING_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        13.5/16f, 20/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape RED_SPYCRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 3.75/16f,  // min x, y, z
        14/16f, 10/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape REPEATER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.765/16f, 0/16f, -2.755/16f,  // min x, y, z
        15.025/16f, 9.25/16f, 12.25/16f   // max x, y, z
    );

    private static final VoxelShape RETINAZER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape RHYTH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.75/16f, 0/16f, 6/16f,  // min x, y, z
        13.25/16f, 11/16f, 9.1/16f   // max x, y, z
    );

    private static final VoxelShape RICK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 0.575/16f,  // min x, y, z
        15/16f, 15/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape SACKBOY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 5/16f,  // min x, y, z
        11.5/16f, 14/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape SALMON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.325/16f, -4.1/16f, 0.05/16f,  // min x, y, z
        9.15/16f, 7.45/16f, 15.55/16f   // max x, y, z
    );

    private static final VoxelShape SALT_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape SANS_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 6.5/16f,  // min x, y, z
        11.5/16f, 10.5/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape SEMI_PUFFED_PUFFERFISH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2/16f, 0/16f, 3/16f,  // min x, y, z
        18/16f, 12/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape SENSEI_SEAWEED_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.75/16f, 0/16f, 6/16f,  // min x, y, z
        13.25/16f, 11/16f, 9.1/16f   // max x, y, z
    );

    private static final VoxelShape SHADOW_FREDDY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.7/16f, 0/16f, 2.75/16f,  // min x, y, z
        12.6/16f, 16.85/16f, 11.95/16f   // max x, y, z
    );

    private static final VoxelShape SHEEP_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 0.75/16f,  // min x, y, z
        11.4/16f, 11.65/16f, 12.95/16f   // max x, y, z
    );

    private static final VoxelShape SHOVEL_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 0/16f,  // min x, y, z
        15/16f, 19/16f, 13/16f   // max x, y, z
    );

    private static final VoxelShape SHULKER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 4/16f,  // min x, y, z
        12/16f, 10.25/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape SILVERFISH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.45/16f, 0/16f, 1/16f,  // min x, y, z
        12.55/16f, 6.225/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape SKELETON_HORSE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.8/16f, 0/16f, 4.55/16f,  // min x, y, z
        10.225/16f, 10.75/16f, 13.95/16f   // max x, y, z
    );

    private static final VoxelShape SKELETON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 6.4/16f,  // min x, y, z
        10.5/16f, 13.1/16f, 9.5/16f   // max x, y, z
    );

    private static final VoxelShape SLIME_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 13/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape SNIFFER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.6/16f, 0/16f, 1.4/16f,  // min x, y, z
        11.1/16f, 7.2/16f, 17.6/16f   // max x, y, z
    );

    private static final VoxelShape SNOW_GOLEM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, -7/16f, 4.6/16f,  // min x, y, z
        13.5/16f, 15/16f, 11.6/16f   // max x, y, z
    );

    private static final VoxelShape SNOW_PEA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.765/16f, 0/16f, -2.755/16f,  // min x, y, z
        15.025/16f, 9.1/16f, 12.8/16f   // max x, y, z
    );

    private static final VoxelShape SPACE_CORE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 0.575/16f,  // min x, y, z
        15/16f, 15/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape SPAMTON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 3/16f,  // min x, y, z
        12/16f, 15/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape SPAZMATISM_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape SPIDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, -0.05/16f, 2/16f,  // min x, y, z
        14.45/16f, 4.1/16f, 14.25/16f   // max x, y, z
    );

    private static final VoxelShape SPLIT_PEA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.765/16f, 0/16f, -2.755/16f,  // min x, y, z
        15.025/16f, 9.25/16f, 13.55/16f   // max x, y, z
    );

    private static final VoxelShape SPRINT_HAT_KID_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2.5/16f,  // min x, y, z
        12/16f, 16/16f, 14.5/16f   // max x, y, z
    );

    private static final VoxelShape SQUID_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -3.3/16f, 0/16f, -8.4/16f,  // min x, y, z
        20.9/16f, 9.2/16f, 18.05/16f   // max x, y, z
    );

    private static final VoxelShape STEPPA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.75/16f, 0/16f, 6/16f,  // min x, y, z
        13.25/16f, 11/16f, 9.1/16f   // max x, y, z
    );

    private static final VoxelShape STEVE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 14/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape STRAY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.975/16f, 0/16f, 6.2/16f,  // min x, y, z
        10.875/16f, 13.3/16f, 9.7/16f   // max x, y, z
    );

    private static final VoxelShape STRIDER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.1/16f, 0/16f, 4/16f,  // min x, y, z
        16.7/16f, 15.65/16f, 12.6/16f   // max x, y, z
    );

    private static final VoxelShape SUDOWOODO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 3.5/16f,  // min x, y, z
        16/16f, 11/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape SUMMER_MONIKA_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 14/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape SUNNY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 6/16f,  // min x, y, z
        12/16f, 14/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape TADPOLE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 0.25/16f,  // min x, y, z
        11/16f, 5/16f, 16/16f   // max x, y, z
    );

    private static final VoxelShape TAINTED_THE_KEEPER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_DEALER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 11/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_KEEPER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 5.5/16f,  // min x, y, z
        12/16f, 12/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_KNIGHT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, -1.75/16f, 5.5/16f,  // min x, y, z
        12.5/16f, 16/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_BLUE_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_GOLD_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_GREEN_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_LEAF_COVER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_PURPLE_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_RED_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape THE_LAMB_WHITE_CAPE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3/16f, 0/16f, 4.9/16f,  // min x, y, z
        13/16f, 19/16f, 10.5/16f   // max x, y, z
    );

    private static final VoxelShape TOAST_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape TRUE_EYE_OF_CTHULHU_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 2/16f,  // min x, y, z
        12/16f, 8/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape TURTLE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1.5/16f, 0/16f, 0/16f,  // min x, y, z
        13.5/16f, 7/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape ULTIMATE_CHICKEN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 2.5/16f,  // min x, y, z
        14/16f, 15/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape ULTIMATE_MACAW_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0/16f, 2.5/16f,  // min x, y, z
        14/16f, 14/16f, 15.5/16f   // max x, y, z
    );

    private static final VoxelShape V_1_KNUCKLEBUSTER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 5.93894/16f,  // min x, y, z
        16/16f, 16/16f, 11.25/16f   // max x, y, z
    );

    private static final VoxelShape V_1_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 5.93894/16f,  // min x, y, z
        16/16f, 16/16f, 11.25/16f   // max x, y, z
    );

    private static final VoxelShape V_1_WHIPLASH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 5.93894/16f,  // min x, y, z
        16/16f, 16/16f, 11.25/16f   // max x, y, z
    );

    private static final VoxelShape V_2_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 5.93894/16f,  // min x, y, z
        16/16f, 16/16f, 11.25/16f   // max x, y, z
    );

    private static final VoxelShape V_2_WHIPLASH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0/16f, 0/16f, 5.93894/16f,  // min x, y, z
        16/16f, 16/16f, 11.25/16f   // max x, y, z
    );

    private static final VoxelShape VAULT_BOY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 6/16f,  // min x, y, z
        12.5/16f, 17/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape VAULT_BOY_THUMBS_UP_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 17/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape VEX_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, -3/16f, 4.8/16f,  // min x, y, z
        15.5/16f, 8/16f, 11.85/16f   // max x, y, z
    );

    private static final VoxelShape VILLAGER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, -0.1/16f,  // min x, y, z
        11.75/16f, 14.5/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape VINDICATOR_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, -0.1/16f,  // min x, y, z
        11.75/16f, 14.5/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape WANDERING_TRADER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, -0.1/16f,  // min x, y, z
        11.75/16f, 14.5/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape WARDEN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.95/16f, 0/16f, 6.15/16f,  // min x, y, z
        15/16f, 16/16f, 9.9/16f   // max x, y, z
    );

    private static final VoxelShape WARM_FROG_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, 0/16f, 2.5/16f,  // min x, y, z
        15.5/16f, 8.25/16f, 13.75/16f   // max x, y, z
    );

    private static final VoxelShape WELDING_MASK_HIGH_ROLLER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 3.5/16f,  // min x, y, z
        12.5/16f, 12/16f, 12.5/16f   // max x, y, z
    );

    private static final VoxelShape WHEATLEY_CRAB_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.5/16f, 0/16f, 1/16f,  // min x, y, z
        15.5/16f, 15/16f, 11/16f   // max x, y, z
    );

    private static final VoxelShape WHEATLEY_IN_GLADOS_BODY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.5/16f, 0/16f, 0/16f,  // min x, y, z
        11.5/16f, 16/16f, 15.1/16f   // max x, y, z
    );

    private static final VoxelShape WHEATLEY_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        1/16f, 0/16f, 0.575/16f,  // min x, y, z
        15/16f, 15/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape WHITE_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape WHITE_FOX_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -3/16f, 0/16f, 5/16f,  // min x, y, z
        12/16f, 5.25/16f, 14/16f   // max x, y, z
    );

    private static final VoxelShape WHITE_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape WHITE_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape WHITE_SPLOTCHED_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape WIGLIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.75/16f, 0/16f, 6/16f,  // min x, y, z
        13.25/16f, 11/16f, 9.1/16f   // max x, y, z
    );

    private static final VoxelShape WILSON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 6.25/16f,  // min x, y, z
        10.5/16f, 9.7/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape WITCH_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.25/16f, -0.2/16f, 2.1/16f,  // min x, y, z
        12.5/16f, 13.9/16f, 13.15/16f   // max x, y, z
    );

    private static final VoxelShape WITHER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2/16f, 0.8/16f, 5.5/16f,  // min x, y, z
        14/16f, 11.15/16f, 13.65/16f   // max x, y, z
    );

    private static final VoxelShape WITHER_SKELETON_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5.5/16f, 0/16f, 6.4/16f,  // min x, y, z
        10.5/16f, 13.1/16f, 9.5/16f   // max x, y, z
    );

    private static final VoxelShape WOBBLEWOK_CLOSED_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.5/16f, 0/16f, 2.39/16f,  // min x, y, z
        13.5/16f, 5.9/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape WOBBLEWOK_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        0.4/16f, 0/16f, 2.39/16f,  // min x, y, z
        13.5/16f, 12.71091/16f, 13.5/16f   // max x, y, z
    );

    private static final VoxelShape WOLF_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.3/16f, 0/16f, -0.35/16f,  // min x, y, z
        10.5/16f, 12.35/16f, 18.2/16f   // max x, y, z
    );

    private static final VoxelShape YELLOW_CREWMATE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        5/16f, 0/16f, 5/16f,  // min x, y, z
        11/16f, 11/16f, 12/16f   // max x, y, z
    );

    private static final VoxelShape YELLOW_JUNIMO_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        3.5/16f, 0/16f, 4.5/16f,  // min x, y, z
        12.5/16f, 13/16f, 11.5/16f   // max x, y, z
    );

    private static final VoxelShape YELLOW_PIKMIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4/16f, 0/16f, 6/16f,  // min x, y, z
        12/16f, 12.75/16f, 10/16f   // max x, y, z
    );

    private static final VoxelShape YELLOW_RABBIT_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.4/16f, 0/16f, 0.8/16f,  // min x, y, z
        11.6/16f, 14.5/16f, 12.3/16f   // max x, y, z
    );

    private static final VoxelShape ZOGLIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -2.85/16f, -3.35/16f, -3.85/16f,  // min x, y, z
        14.3/16f, 10.25/16f, 15.9/16f   // max x, y, z
    );

    private static final VoxelShape ZOMBIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        2.25/16f, 0/16f, -1/16f,  // min x, y, z
        13/16f, 15/16f, 15/16f   // max x, y, z
    );

    private static final VoxelShape ZOMBIE_VILLAGER_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        4.25/16f, 0/16f, 3.9/16f,  // min x, y, z
        11.75/16f, 14.5/16f, 10.4/16f   // max x, y, z
    );

    private static final VoxelShape ZOMBIEFIED_PIGLIN_PLUSHIE_SHAPE = VoxelShapes.cuboid(
        -5.6/16f, 0/16f, 5.55/16f,  // min x, y, z
        12/16f, 13.75/16f, 10.4/16f   // max x, y, z
    );
    // ========== END PLUSHIE HITBOXES ==========

= new Block(
                Block.Settings.create()
                    .strength(2.0f, 6.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .requiresTool()
            );

            registerStackedBlock(blockName, stackedBlock);
        }
    }

    private static void registerCrates() {
        for (String crateName : CRATE_TYPES) {
            // Create crate block with wood properties (similar to vanilla crates)
            Block crateBlock = new Block(
                Block.Settings.create()
                    .strength(2.0f, 3.0f)
                    .sounds(BlockSoundGroup.WOOD)
            );

            registerCrateBlock(crateName, crateBlock);
        }
    }

    private static void registerBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to our list for later creative tab registration
        COLORED_BLOCKS.add(block);
    }

    private static void registerPlushieBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to appropriate list for creative tab registration
        if (name.equals("nubert") || name.equals("tenna_pole") || name.equals("tenna_statue")) {
            DELTARUNE_BLOCKS.add(block);
        } else if (AMW_PLUSHIE_NAMES.contains(name)) {
            AMW_PLUSHIE_BLOCKS.add(block);
        } else {
            PLUSHIE_BLOCKS.add(block);
        }
    }

    private static void registerStackedBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to stacked blocks list for creative tab registration
        STACKED_BLOCKS.add(block);
    }

    private static void registerCrateBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to crate blocks list for creative tab registration
        CRATE_BLOCKS.add(block);
    }

    private static void registerConsoles() {
        // NES console hitbox (14x4x11 pixels)
        registerConsoleBlock("nes", VoxelShapes.cuboid(1/16f, 0, 2/16f, 15/16f, 4/16f, 13/16f));

        // Dreamcast (13x4x12 pixels)
        registerConsoleBlock("dreamcast", VoxelShapes.cuboid(1.5f/16f, 0, 2/16f, 14.5f/16f, 4/16f, 14/16f));

        // DS (9x1x6 pixels)
        registerConsoleBlock("ds", VoxelShapes.cuboid(5/16f, 0, 2.8f/16f, 14/16f, 1/16f, 8.8f/16f));

        // Gameboys (2 gameboys - compound shape)
        VoxelShape gameboysShape = VoxelShapes.union(
            VoxelShapes.cuboid(9/16f, 0, 1.8f/16f, 15/16f, 2/16f, 11.8f/16f),      // Right gameboy
            VoxelShapes.cuboid(1/16f, 0, 1.8f/16f, 8/16f, 2/16f, 12.8f/16f)        // Left gameboy
        );
        registerConsoleBlock("gameboys", gameboysShape);

        // GameCube (11x8x11 pixels)
        registerConsoleBlock("gamecube", VoxelShapes.cuboid(2.5f/16f, 0, 2.5f/16f, 13.5f/16f, 8/16f, 13.5f/16f));

        // Switch Dock (13x9x1 pixels)
        registerConsoleBlock("dock", VoxelShapes.cuboid(1.5f/16f, 0, 6/16f, 14.5f/16f, 9/16f, 7/16f));

        // N64 (4 parts: left controller + console body + right controller + top)
        VoxelShape n64Shape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 1.49f/16f, 3.01f/16f, 2.01f/16f, 5.51f/16f),       // Left controller (clamped from -0.01)
            VoxelShapes.cuboid(1.5f/16f, 0, 3/16f, 14.5f/16f, 3/16f, 14/16f),           // Main console body
            VoxelShapes.cuboid(12.99f/16f, 0, 1.49f/16f, 1.0f, 2.01f/16f, 5.51f/16f),   // Right controller (clamped from 16.01)
            VoxelShapes.cuboid(4.5f/16f, 3/16f, 9/16f, 11.5f/16f, 6/16f, 11/16f)        // Top part
        );
        registerConsoleBlock("n_64", n64Shape);

        // PS1 (13x2x9 pixels)
        registerConsoleBlock("ps_1", VoxelShapes.cuboid(1.5f/16f, 0, 3.5f/16f, 14.5f/16f, 2/16f, 12.5f/16f));

        // PS2 (15x2x10 pixels)
        registerConsoleBlock("ps_2", VoxelShapes.cuboid(0.5f/16f, 2/16f, 3/16f, 15.5f/16f, 4/16f, 13/16f));

        // PS4 (15x1x12 pixels)
        registerConsoleBlock("ps_4", VoxelShapes.cuboid(0.5f/16f, 3/16f, 2/16f, 15.5f/16f, 4/16f, 14/16f));

        // PS5 (4 parts - tall vertical console - extends above one block!)
        VoxelShape ps5Shape = VoxelShapes.union(
            VoxelShapes.cuboid(6.4848f/16f, 1.4467f/16f, 1.2322f/16f, 9.8384f/16f, 24.0835f/16f, 13.8082f/16f),   // Center
            VoxelShapes.cuboid(5.6464f/16f, 1.2371f/16f, 0.3938f/16f, 6.4848f/16f, 25.5507f/16f, 14.6466f/16f),   // Left side (tallest)
            VoxelShapes.cuboid(9.8384f/16f, 1.2371f/16f, 0.3938f/16f, 10.6768f/16f, 25.5507f/16f, 14.6466f/16f),  // Right side (tallest)
            VoxelShapes.cuboid(4.808f/16f, 0.3987f/16f, 4.5858f/16f, 11.5152f/16f, 2.0755f/16f, 11.293f/16f)      // Base
        );
        registerConsoleBlock("ps_5", ps5Shape);

        // PSP (10x2x6 pixels)
        registerConsoleBlock("psp", VoxelShapes.cuboid(3/16f, 0, 5/16f, 13/16f, 2/16f, 11/16f));

        // Sega Genesis (12x2x10 pixels)
        registerConsoleBlock("sega_genesis", VoxelShapes.cuboid(2/16f, 0, 3/16f, 14/16f, 2/16f, 13/16f));

        // SNES (10x3x12 pixels)
        registerConsoleBlock("snes", VoxelShapes.cuboid(3.5f/16f, 0, 2/16f, 13.5f/16f, 3/16f, 14/16f));

        // Switch In Dock (6 parts: dock base + switch tablet + various components)
        VoxelShape switchInDockShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 2/16f, 7/16f, 1/16f, 10/16f, 8/16f),               // Left side (clamped from -2)
            VoxelShapes.cuboid(1/16f, 0, 6/16f, 14/16f, 9/16f, 7/16f),               // Dock base front
            VoxelShapes.cuboid(14/16f, 2/16f, 7/16f, 1.0f, 10/16f, 8/16f),           // Right side (clamped from 17)
            VoxelShapes.cuboid(1/16f, 2/16f, 7/16f, 14/16f, 10/16f, 8/16f),          // Center tablet
            VoxelShapes.cuboid(1/16f, 0, 8/16f, 14/16f, 9/16f, 10/16f),              // Dock base back
            VoxelShapes.cuboid(1/16f, 0, 7/16f, 14/16f, 2/16f, 8/16f)                // Bottom connector
        );
        registerConsoleBlock("switch_in_dock", switchInDockShape);

        // Switch (3 parts: left joy-con + center tablet + right joy-con)
        VoxelShape switchShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 6/16f, 2/16f, 1/16f, 14/16f),           // Left joy-con (clamped from -1)
            VoxelShapes.cuboid(2/16f, 0, 6/16f, 14/16f, 1/16f, 14/16f),      // Center tablet
            VoxelShapes.cuboid(14/16f, 0, 6/16f, 1.0f, 1/16f, 14/16f)        // Right joy-con (clamped from 17)
        );
        registerConsoleBlock("switcsh", switchShape);

        // TV (15x11x8 pixels)
        registerConsoleBlock("tv", VoxelShapes.cuboid(0.5f/16f, 0, 4/16f, 15.5f/16f, 11/16f, 12/16f));

        // Wii (base + vertical stand - compound shape)
        VoxelShape wiiShape = VoxelShapes.union(
            VoxelShapes.cuboid(6.5f/16f, 0, 0.75f/16f, 11.5f/16f, 3/16f, 13.75f/16f),      // Base
            VoxelShapes.cuboid(7/16f, 2.4f/16f, 1.7f/16f, 11/16f, 13.4f/16f, 12.7f/16f)   // Vertical stand
        );
        registerConsoleBlock("wii", wiiShape);

        // Original Xbox (16x6x15 pixels, clamped)
        registerConsoleBlock("xbox", VoxelShapes.cuboid(0, 0, 0.5f/16f, 1.0f, 6/16f, 15.5f/16f));

        // Xbox One (2 parts: top + base)
        VoxelShape xboxOneShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 1/16f, 2/16f, 1.0f, 4/16f, 15/16f),        // Top (clamped from 17)
            VoxelShapes.cuboid(0, 0, 2/16f, 1.0f, 1/16f, 14/16f)             // Base (clamped from 16)
        );
        registerConsoleBlock("xbox_1", xboxOneShape);

        // Xbox Series S (4x19x11 pixels - tall vertical tower)
        registerConsoleBlock("xbox_series_s", VoxelShapes.cuboid(1/16f, 0, 2.5f/16f, 5/16f, 19/16f, 13.5f/16f));

        // Xbox Series X (2 parts - tall vertical tower with top vent - extends above one block!)
        VoxelShape xboxSeriesXShape = VoxelShapes.union(
            VoxelShapes.cuboid(2.5f/16f, 0, 2.5f/16f, 13.5f/16f, 21/16f, 13.5f/16f),         // Main tower
            VoxelShapes.cuboid(2.6f/16f, 20.05f/16f, 2.6f/16f, 13.4f/16f, 20.85f/16f, 13.4f/16f)  // Top vent
        );
        registerConsoleBlock("xbox_series_x", xboxSeriesXShape);
    }

    private static void registerConsoleBlock(String name, VoxelShape shape) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Create console block with custom hitbox
        Block block = new ConsoleBlock(
            Block.Settings.create()
                .strength(1.5f, 6.0f)
                .sounds(BlockSoundGroup.METAL)
                .nonOpaque(),
            shape
        );

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to console blocks list for creative tab registration
        CONSOLE_BLOCKS.add(block);
    }

    private static Block registerAzaleaFlowersBlock(String name) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Create azalea flowers block
        Block block = new AzaleaFlowersBlock(
            Block.Settings.create()
                .strength(0.0f)
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .noCollision()
        );

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to Twigs blocks list for creative tab registration
        TWIGS_BLOCKS.add(block);

        return block;
    }

    private static Block registerPottedAzaleaFlowersBlock(String name) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Create potted azalea flowers block
        Block block = new PottedAzaleaFlowersBlock(
            AZALEA_FLOWERS,
            Block.Settings.create()
                .strength(0.0f)
                .sounds(BlockSoundGroup.STONE)
                .nonOpaque()
        );

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to Twigs blocks list for creative tab registration
        TWIGS_BLOCKS.add(block);

        return block;
    }

    private static void registerMagnumTorches() {
        // Amethyst Magnum Torch - light level 14 (same as regular torch)
        registerMagnumTorchBlock("amethyst_magnum_torch", 14);

        // Diamond Magnum Torch - light level 14 (same as regular torch)
        registerMagnumTorchBlock("diamond_magnum_torch", 14);

        // Emerald Magnum Torch - light level 15 (max light, rarest material)
        registerMagnumTorchBlock("emerald_magnum_torch", 15);
    }

    private static void registerMagnumTorchBlock(String name, int lightLevel) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Create magnum torch block with wood sounds (it's made of logs)
        Block block = new MagnumTorchBlock(
            Block.Settings.create()
                .strength(1.0f, 1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .luminance(state -> lightLevel)
                .nonOpaque()
        );

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to magnum torch blocks list for creative tab registration
        MAGNUM_TORCH_BLOCKS.add(block);
    }

    public static void initialize() {
        // This method is called to trigger the static block initializer
    }
}
