#!/usr/bin/env python3
"""
Automatic Plushie Hitbox Generator
==================================
This script automatically generates custom hitboxes for all plushies by:
1. Scanning all plushie model JSON files
2. Calculating the bounding box for each model
3. Generating VoxelShape Java code
4. Updating ModBlocks.java with the new hitboxes

Just double-click or run: python3 generate_plushie_hitboxes.py
"""

import json
import os
import sys
from pathlib import Path
from typing import Dict, Tuple, List
import re

# Terminal colors for pretty output
class Colors:
    HEADER = '\033[95m'
    BLUE = '\033[94m'
    CYAN = '\033[96m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    END = '\033[0m'
    BOLD = '\033[1m'

def print_header(msg):
    print(f"\n{Colors.BOLD}{Colors.HEADER}{'='*70}{Colors.END}")
    print(f"{Colors.BOLD}{Colors.HEADER}{msg.center(70)}{Colors.END}")
    print(f"{Colors.BOLD}{Colors.HEADER}{'='*70}{Colors.END}\n")

def print_success(msg):
    print(f"{Colors.GREEN}✓ {msg}{Colors.END}")

def print_info(msg):
    print(f"{Colors.CYAN}ℹ {msg}{Colors.END}")

def print_warning(msg):
    print(f"{Colors.YELLOW}⚠ {msg}{Colors.END}")

def print_error(msg):
    print(f"{Colors.RED}✗ {msg}{Colors.END}")

def resolve_model_parent(model_path: Path, parent_ref: str, base_dir: Path) -> Path:
    """
    Resolve a parent model reference like "charmncraft-bits:custom/baldi_plushie_eating_an_apple"
    Returns the path to the parent model file.
    """
    # Remove the namespace prefix if present
    if ':' in parent_ref:
        parent_ref = parent_ref.split(':', 1)[1]

    # Convert to file path (e.g., "custom/baldi_plushie" -> "custom/baldi_plushie.json")
    parent_path = base_dir / "src/main/resources/assets/charmncraft-bits/models" / f"{parent_ref}.json"

    return parent_path

def calculate_bounding_box(model_path: Path, base_dir: Path, visited: set = None) -> Tuple[float, float, float, float, float, float]:
    """
    Calculate the bounding box for a plushie model by analyzing all elements.
    Handles parent model references to support AMW plushies.
    Returns: (min_x, min_y, min_z, max_x, max_y, max_z)
    """
    if visited is None:
        visited = set()

    # Prevent infinite recursion
    if str(model_path) in visited:
        return None
    visited.add(str(model_path))

    try:
        with open(model_path, 'r', encoding='utf-8') as f:
            model_data = json.load(f)

        # If this model references a parent, try to get elements from the parent
        if 'parent' in model_data and 'elements' not in model_data:
            parent_path = resolve_model_parent(model_path, model_data['parent'], base_dir)
            if parent_path.exists():
                return calculate_bounding_box(parent_path, base_dir, visited)
            else:
                return None

        if 'elements' not in model_data or not model_data['elements']:
            return None

        min_x = min_y = min_z = float('inf')
        max_x = max_y = max_z = float('-inf')

        for element in model_data['elements']:
            if 'from' in element and 'to' in element:
                from_coords = element['from']
                to_coords = element['to']

                min_x = min(min_x, from_coords[0], to_coords[0])
                min_y = min(min_y, from_coords[1], to_coords[1])
                min_z = min(min_z, from_coords[2], to_coords[2])

                max_x = max(max_x, from_coords[0], to_coords[0])
                max_y = max(max_y, from_coords[1], to_coords[1])
                max_z = max(max_z, from_coords[2], to_coords[2])

        if min_x == float('inf'):
            return None

        return (min_x, min_y, min_z, max_x, max_y, max_z)

    except Exception as e:
        # Silently fail for cleaner output (we'll report skipped models at the end)
        return None

def generate_voxel_shape_code(plushie_name: str, bbox: Tuple[float, float, float, float, float, float]) -> str:
    """
    Generate Java VoxelShape code for a plushie hitbox.
    """
    min_x, min_y, min_z, max_x, max_y, max_z = bbox

    # Convert to Java constant name (uppercase with underscores)
    const_name = plushie_name.upper() + "_SHAPE"

    # Generate the VoxelShape definition with proper formatting
    code = f"    private static final VoxelShape {const_name} = VoxelShapes.cuboid(\n"
    code += f"        {min_x}/16f, {min_y}/16f, {min_z}/16f,  // min x, y, z\n"
    code += f"        {max_x}/16f, {max_y}/16f, {max_z}/16f   // max x, y, z\n"
    code += f"    );"

    return code

def find_all_plushie_models(base_dir: Path) -> Dict[str, Path]:
    """
    Find all plushie model JSON files.
    Returns dict of {plushie_name: model_path}
    """
    plushie_models = {}

    # Search in main plushies directory
    plushies_dir = base_dir / "src/main/resources/assets/charmncraft-bits/models/block/plushies"
    if plushies_dir.exists():
        for model_file in plushies_dir.glob("*.json"):
            plushie_name = model_file.stem
            plushie_models[plushie_name] = model_file

    return plushie_models

def read_plushie_list_from_java(java_file: Path) -> List[str]:
    """
    Extract the list of plushie names from ModBlocks.java PLUSHIES array.
    """
    try:
        with open(java_file, 'r', encoding='utf-8') as f:
            content = f.read()

        # Find the PLUSHIES array
        match = re.search(r'private static final String\[\] PLUSHIES = \{([^}]+)\}', content, re.DOTALL)
        if not match:
            print_error("Could not find PLUSHIES array in ModBlocks.java")
            return []

        plushies_str = match.group(1)

        # Extract all quoted strings
        plushie_names = re.findall(r'"([^"]+)"', plushies_str)

        return plushie_names

    except Exception as e:
        print_error(f"Failed to read PLUSHIES from ModBlocks.java: {e}")
        return []

def generate_all_hitboxes(base_dir: Path):
    """
    Main function to generate all hitboxes and update ModBlocks.java
    """
    print_header("Plushie Hitbox Generator")

    # Step 1: Find all plushie models
    print_info("Scanning for plushie models...")
    plushie_models = find_all_plushie_models(base_dir)
    print_success(f"Found {len(plushie_models)} plushie model files")

    # Step 2: Read the PLUSHIES list from ModBlocks.java
    java_file = base_dir / "src/main/java/com/charmed/charmncraft/bits/ModBlocks.java"
    if not java_file.exists():
        print_error(f"ModBlocks.java not found at {java_file}")
        return

    print_info("Reading plushie list from ModBlocks.java...")
    plushie_names = read_plushie_list_from_java(java_file)
    print_success(f"Found {len(plushie_names)} plushies registered in ModBlocks.java")

    # Step 3: Calculate hitboxes for all plushies
    print_info("Calculating bounding boxes for all plushies...")
    hitboxes = {}
    skipped = []

    for plushie_name in plushie_names:
        if plushie_name in plushie_models:
            bbox = calculate_bounding_box(plushie_models[plushie_name], base_dir)
            if bbox:
                hitboxes[plushie_name] = bbox
                print(f"  {Colors.CYAN}→{Colors.END} {plushie_name}: [{bbox[0]:.1f}, {bbox[1]:.1f}, {bbox[2]:.1f}] to [{bbox[3]:.1f}, {bbox[4]:.1f}, {bbox[5]:.1f}]")
            else:
                skipped.append(plushie_name)
                print_warning(f"  Skipped {plushie_name}: Could not calculate bounding box")
        else:
            skipped.append(plushie_name)
            print_warning(f"  Skipped {plushie_name}: Model file not found")

    print_success(f"Successfully calculated {len(hitboxes)} hitboxes")
    if skipped:
        print_warning(f"Skipped {len(skipped)} plushies (no model or parse error)")

    # Step 4: Generate Java code
    print_info("Generating VoxelShape Java code...")
    voxel_shapes_code = []
    for plushie_name in sorted(hitboxes.keys()):
        bbox = hitboxes[plushie_name]
        code = generate_voxel_shape_code(plushie_name, bbox)
        voxel_shapes_code.append(code)

    # Step 5: Generate the if-else chain for registerPlushies
    print_info("Generating registerPlushies() logic...")
    register_logic = []
    for plushie_name in sorted(hitboxes.keys()):
        const_name = plushie_name.upper() + "_SHAPE"
        if len(register_logic) == 0:
            register_logic.append(f'            if (plushieName.equals("{plushie_name}")) {{')
        else:
            register_logic.append(f'            }} else if (plushieName.equals("{plushie_name}")) {{')
        register_logic.append(f'                shape = {const_name};')
    if register_logic:
        register_logic.append('            }')

    # Step 6: Update ModBlocks.java
    print_info("Updating ModBlocks.java...")

    try:
        with open(java_file, 'r', encoding='utf-8') as f:
            java_content = f.read()

        # Find where to insert VoxelShape definitions (after existing shape definitions)
        # Look for the last VoxelShape definition before the static block
        insert_marker = "    static {"
        insert_pos = java_content.find(insert_marker)

        if insert_pos == -1:
            print_error("Could not find insertion point for VoxelShape definitions")
            return

        # Generate the complete VoxelShape section with header comment
        shapes_section = "\n    // ========== PLUSHIE CUSTOM HITBOXES (AUTO-GENERATED) ==========\n"
        shapes_section += "    // Generated by generate_plushie_hitboxes.py\n"
        shapes_section += "    // To regenerate, run: python3 generate_plushie_hitboxes.py\n\n"
        shapes_section += "\n\n".join(voxel_shapes_code)
        shapes_section += "\n    // ========== END PLUSHIE HITBOXES ==========\n\n"

        # Remove old auto-generated section if it exists
        old_section_pattern = r'\n    // ========== PLUSHIE CUSTOM HITBOXES \(AUTO-GENERATED\) ==========.*?// ========== END PLUSHIE HITBOXES ==========\n\n'
        java_content = re.sub(old_section_pattern, '', java_content, flags=re.DOTALL)

        # Insert new shapes section
        java_content = java_content[:insert_pos] + shapes_section + java_content[insert_pos:]

        # Update the registerPlushies() method
        # Find and replace the if-else chain for shape assignment
        register_pattern = r'(private static void registerPlushies\(\) \{[\s\S]*?VoxelShape shape = null;\n)([\s\S]*?)(\n\s+// Create plushie block)'

        register_replacement = r'\1' + '\n'.join(register_logic) + r'\3'

        java_content = re.sub(register_pattern, register_replacement, java_content)

        # Write back to file
        with open(java_file, 'w', encoding='utf-8') as f:
            f.write(java_content)

        print_success("ModBlocks.java updated successfully!")

    except Exception as e:
        print_error(f"Failed to update ModBlocks.java: {e}")
        import traceback
        traceback.print_exc()
        return

    # Step 7: Summary
    print_header("Summary")
    print_success(f"Generated {len(hitboxes)} custom hitboxes")
    print_info(f"Updated file: {java_file}")

    if skipped:
        print_warning(f"Skipped {len(skipped)} plushies:")
        for name in skipped[:10]:  # Show first 10
            print(f"  - {name}")
        if len(skipped) > 10:
            print(f"  ... and {len(skipped) - 10} more")

    print()
    print_success("All done! Your plushies now have custom hitboxes!")
    print_info("You can now test in-game to verify the hitboxes match the models.")

if __name__ == "__main__":
    # Get the base directory (where this script is located)
    script_dir = Path(__file__).parent.resolve()

    print(f"{Colors.BOLD}Starting from directory: {script_dir}{Colors.END}")

    try:
        generate_all_hitboxes(script_dir)
    except KeyboardInterrupt:
        print_error("\n\nInterrupted by user")
        sys.exit(1)
    except Exception as e:
        print_error(f"Unexpected error: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)

    # Keep window open if double-clicked
    if sys.platform.startswith('win'):
        input("\nPress Enter to exit...")
