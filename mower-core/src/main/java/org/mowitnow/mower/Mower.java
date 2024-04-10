package org.mowitnow.mower;

import java.util.List;

public class Mower {
    private Integer x;
    private Integer y;
    private Orientation orientation;

    private List<Action> actions;

    public Mower(Integer x, Integer y, Orientation orientation, List<Action> actions) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.actions = actions;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
