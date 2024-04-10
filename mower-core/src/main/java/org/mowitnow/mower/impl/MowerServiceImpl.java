package org.mowitnow.mower.impl;

import org.mowitnow.mower.*;
import org.springframework.stereotype.Component;

@Component
public class MowerServiceImpl implements MowerService {

    @Override
    public void doAction(Mower mower, Action action, Lawn lawn) {
        switch (action) {
            case TURN_LEFT -> turnLeft(mower);
            case TURN_RIGHT -> turnRight(mower);
            case MOVE_FORWARD -> moveForward(mower, lawn);
        }
    }

    private void turnLeft(Mower mower) {
        switch (mower.getOrientation()) {
            case EAST -> mower.setOrientation(Orientation.NORTH);
            case NORTH -> mower.setOrientation(Orientation.WEST);
            case WEST -> mower.setOrientation(Orientation.SOUTH);
            case SOUTH -> mower.setOrientation(Orientation.EAST);
        }
    }

    private void turnRight(Mower mower) {
        switch (mower.getOrientation()) {
            case EAST -> mower.setOrientation(Orientation.SOUTH);
            case NORTH -> mower.setOrientation(Orientation.EAST);
            case WEST -> mower.setOrientation(Orientation.NORTH);
            case SOUTH -> mower.setOrientation(Orientation.WEST);
        }
    }

    private void moveForward(Mower mower, Lawn lawn) {
        switch (mower.getOrientation()) {
            case EAST -> mower.setX(Math.min(mower.getX() + 1, lawn.getxMax()));
            case NORTH -> mower.setY(Math.min(mower.getY() + 1, lawn.getyMax()));
            case WEST -> mower.setX(Math.max(mower.getX() - 1, 0));
            case SOUTH -> mower.setY(Math.max(mower.getY() - 1, 0));
        }
    }
}
