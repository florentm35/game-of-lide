package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;
import fr.florent.gameoflife.util.IndexUtil;

public class AshAction extends AbstactActionState {

    private static final double PRCT_UPPER = 0.1 / 100d;


    @Override
    public EnumState calculateState(int x, int y, int width, EnumState[] states) {
        double roll = rand.nextDouble();
        if (roll <= PRCT_UPPER) {
            return EnumState.FOREST;
        }
        return states[IndexUtil.getIndex(x, y, width)];
    }
}

