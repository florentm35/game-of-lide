package fr.florent.gameoflife.process;

import fr.florent.gameoflife.process.action.*;
import javafx.scene.paint.Color;

public enum EnumState {
    FOREST(0, Color.LIGHTGREEN, new ForestAction()), OLD_FOREST(1, Color.GREEN, new OldForestAction()),
    COMBUSTION_1(2, Color.YELLOW, new CombustionAction()), COMBUSTION_2(3, Color.ORANGE, new CombustionAction()),
    COMBUSTION_3(4, Color.RED, new CombustionAction()), ASH(5, Color.BLACK, new AshAction());

    EnumState(int value, Color color, IActionState action) {
        this.value = value;
        this.color = color;
        this.actionState = action;
    }

    public int value;

    public Color color;

    public IActionState actionState;

    public static EnumState getStateByValue(int value) {

        for (EnumState state : EnumState.values()) {
            if (state.value == value) {
                return state;
            }
        }

        throw new RuntimeException(String.format("State with %d value does not exists", value));

    }

}
