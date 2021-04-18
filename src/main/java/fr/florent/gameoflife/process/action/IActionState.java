package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;

public interface IActionState {

    EnumState calculateState(int x, int y, int width, EnumState[] states);
}
