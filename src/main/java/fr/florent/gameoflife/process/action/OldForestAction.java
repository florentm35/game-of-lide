package fr.florent.gameoflife.process.action;

import fr.florent.gameoflife.process.EnumState;
import fr.florent.gameoflife.util.IndexUtil;

import java.util.concurrent.atomic.AtomicReference;

public class OldForestAction extends AbstactActionState {

    private static final double PRCT_COMBUSTION_1_3 = 10 / 100d;
    private static final double PRCT_COMBUSTION_2 = 20 / 100d;
    private static final double PRCT_OLD_FOREST_COMBUSTION = 0.005 / 100d;

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
            if (roll <= PRCT_OLD_FOREST_COMBUSTION) {
                return EnumState.COMBUSTION_1;
            }
        }


        return states[IndexUtil.getIndex(x, y, width)];
    }
}

