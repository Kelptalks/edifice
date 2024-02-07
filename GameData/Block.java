package GameData;

import java.util.HashMap;
import java.util.Map;

public enum Block {
    Air(0, "null", 0, true),
    Stone(1, "solidBlocks", 0, false),
    Brick(2,"solidBlocks", 1, false),
    Leaf(3,"solidBlocks", 2, false),
    Grass(4,"solidBlocks", 4, false),
    Sand(5,"solidBlocks", 5, false),
    Core(6,"solidBlocks", 6, false),
    Fruit(7,"solidBlocks", 7, false),
    Magma(8,"solidBlocks", 8, false),
    LBM(9,"solidBlocks", 9, false),
    Debug(10,"solidBlocks", 10, false),
    Concrete(11,"solidBlocks", 11, false),
    Metal(12,"solidBlocks", 12, false),
    Factory(13,"animated", 0, false),
    Crate(14,"animated", 1, false);

    public int id;
    public final String spriteSheet;
    public final int spriteSheetIndex;
    public final Boolean transparent;

    Block(int id, String spriteSheet, int spriteSheetIndex, boolean transparent) {
        this.id = id;
        this.spriteSheet = spriteSheet;
        this.spriteSheetIndex = spriteSheetIndex;
        this.transparent = transparent;
    }
}
