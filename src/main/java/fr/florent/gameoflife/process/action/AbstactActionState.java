package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;
import fr.florent.gameoflife.util.IndexUtil;
import org.apache.log4j.Logger;

import java.util.Random;

public abstract class AbstactActionState implements IActionState {

    Logger LOGGER = Logger.getLogger(AbstactActionState.class.getName());

    protected static Random rand = new Random();

    public interface IActionDoubleIterator {
        void action(EnumState state);
    }

    /**
     * Iterate au neigboard cell
     * @param x
     * @param y
     * @param width
     * @param states
     * @param action
     */
    public void cellIterate(int x, int y, int width, EnumState[] states, IActionDoubleIterator action) {
        for (int i = x - 1; i <= x + 1; i++) {
            if (i > 0) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j > 0
                            && IndexUtil.getIndex(i, j, width) < states.length
                            && (x != i || y != j)) {
                        EnumState state = states[IndexUtil.getIndex(i, j, width)];
                        action.action(state);
                    }
                }
            }
        }

    }
}