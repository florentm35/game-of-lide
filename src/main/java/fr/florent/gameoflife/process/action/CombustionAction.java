package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;
import fr.florent.gameoflife.util.IndexUtil;

public class CombustionAction extends AbstactActionState {
    private static final double PRCT_UPPER = 10 / 100d;

    @Override
    public EnumState calculateState(int x, int y, int width, EnumState[] states) {


        double roll = rand.nextDouble();

        if (roll <= PRCT_UPPER) {
            int index = IndexUtil.getIndex(x, y, width);
            EnumState currentState = states[index];

            switch (currentState) {
                case COMBUSTION_1:
                    return EnumState.COMBUSTION_2;
                case COMBUSTION_2:
                    return EnumState.COMBUSTION_3;
                case COMBUSTION_3:
                    return EnumState.ASH;
            }

        }
        return states[IndexUtil.getIndex(x, y, width)];


    }
}

