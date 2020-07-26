package creatures;

import huglife.*;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void move() {
        energy -= 0.03;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Clorus replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    public void stay() {
        energy -= 0.01;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyDirections = new ArrayDeque<>();
        Deque<Direction> plipDirections = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            String name = entry.getValue().name();
            if (name.equals("empty")) {
                emptyDirections.add(entry.getKey());
            } else if (name.equals("plip")) {
                plipDirections.add(entry.getKey());
            }
        }

        if (emptyDirections.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plipDirections.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipDirections));
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyDirections));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyDirections));
        }
    }
}
