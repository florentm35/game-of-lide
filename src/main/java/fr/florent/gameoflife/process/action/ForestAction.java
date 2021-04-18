package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;
import fr.florent.gameoflife.util.IndexUtil;

import java.util.concurrent.atomic.AtomicReference;

public class ForestAction extends AbstactActionState {

    private static final double PRCT_COMBUSTION_1_3 = 1 / 100d;
    private static final double PRCT_COMBUSTION_2 = 2 / 100d;
    private static final double PRCT_OLD_FOREST = 0.5 / 100d;

    @Override
    public EnumState calculateState(int x, int y, int width, EnumState[] states) {

        AtomicReference<Double> combustionPercent = new AtomicReference<>(0d);
        cellIterate(x, y, width, states, state -> {
            switch (state) {
                case COMBUSTION_1:
                case COMBUSTION_3:
                    combustionPercent.updateAndGet(v -> v + PRCT_COMBUSTION_1_3);
                    break;
                case COMBUSTION_2:
                    combustionPercent.updateAndGet(v -> v + PRCT_COMBUSTION_2);
                    break;

            }
        });

        double roll = rand.nextDouble();

        if (roll <= combustionPercent.get()) {
            return EnumState.COMBUSTION_1;
        } else {
            roll = rand.nextDouble();
            if (roll <= PRCT_OLD_FOREST) {
                return EnumState.OLD_FOREST;
            }
        }


        return states[IndexUtil.getIndex(x, y, width)];
    }
}

