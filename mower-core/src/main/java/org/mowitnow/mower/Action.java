package org.mowitnow.mower;

public enum Action {
    TURN_LEFT('G'),
    TURN_RIGHT('D'),
    MOVE_FORWARD('A');
    private Character code;

    private Action(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }

    public static Action fromCode(Character code) {
        for (var action : Action.values()) {
            if (action.getCode().equals(code)) {
                return action;
            }
        }
        return null;
    }
}
