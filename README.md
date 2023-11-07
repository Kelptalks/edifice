# Edifice: Infinite Isometric Voxel Rendering Engine

Edifice isometric voxel rendering engine capable of generating and managing an infinite world. Utilising Java AWT for custom rendering processes, it enables world building and dynamic rendering.

## World Storage

### Description
Block data within Edifice is efficiently managed by an octree structure optimized for extreme depths.

### Specifics

- **Nodes**: There are two types of nodes within the engine's octree:
  - **Branch Node**: Acts as a linking structure to other branch nodes and forms the higher-level framework of the octree.
  - **Leaf Node**: When a branch node reaches a depth of 4, it transitions to a leaf node, which consists of a 1D array with 512 long integers. Each long integer encapsulates 8 bit-packed blocks, with each block represented by 8 bits corresponding to the block ID.

- **Keys**: Keys are represented as long integers, divided into 3-bit sections, each signifying an octree index at a specific depth. The `Keymod` class allows manipulation of keys based on location through a specialized bit adder, which can adjust key axes.

## Rendering

### Description
Custom rendering within Edifice is achieved using Java AWT, where textures are initially split into triangles. An isometric raycasting technique is then employed to render the first non-transparent block on the screen.

### Specifics

- **Texture Manager**: Textures are divided into six distinct triangular textures. These are stored in a sub-array of six elements, which is itself part of a larger array containing all block textures. This arrangement allows the first index to act as the block ID and the second as the corresponding texture dimension.

- **Raycaster**: The algorithm is designed to identify every possible block that could occupy a triangular segment on the screen. A dedicated loop continues until it encounters a non-transparent block, at which point it determines the appropriate triangle to draw.
