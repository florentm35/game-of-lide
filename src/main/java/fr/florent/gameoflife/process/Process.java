package fr.florent.gameoflife.process;

import fr.florent.gameoflife.util.IndexUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Process {

    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private Canvas canvas;

    private int width;
    private int height;

    private EnumState[] states;

    public Process(Canvas canvas) {
        this.canvas = canvas;

        this.width = (int) canvas.getWidth();
        this.height = (int) canvas.getHeight();

        this.states = new EnumState[width * height];
    }

    public void initAshForestState() {
        for (int i = 0; i < this.width * this.height; i++) {
            this.states[i] = EnumState.ASH;
        }
    }
    public void initForestState() {
        for (int i = 0; i < this.width * this.height; i++) {
            this.states[i] = EnumState.FOREST;
        }
    }
    public void initOldForestState() {
        for (int i = 0; i < this.width * this.height; i++) {
            this.states[i] = EnumState.OLD_FOREST;
        }
    }

    public void initRandomState() {

        int stateSize = EnumState.values().length;
        Random rand = new Random(); //instance of random class
        int upperbound = stateSize;

        for (int i = 0; i < this.width * this.height; i++) {
            this.states[i] = EnumState.getStateByValue(rand.nextInt(upperbound));
        }

    }


    public void paintStates() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.width, this.height);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int index = IndexUtil.getIndex(x, y, width);

                gc.setFill(states[index].color);
                gc.fillRect(x * 4, y * 4, 4, 4);

            }
        }

    }


    public void recalculateState() {

        EnumState[] copy = Arrays.copyOf(this.states, this.states.length);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int index = IndexUtil.getIndex(x, y, width);
                this.states[index] = this.states[index].actionState.calculateState(x, y, width, copy);
            }
        }

    }

}
