package org.mowitnow.mower;

public enum Orientation {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');
    private Character code;

    private Orientation(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }

    public static Orientation fromCode(Character code) {
        for (var orientation : Orientation.values()) {
            if (orientation.getCode().equals(code)) {
                return orientation;
            }
        }
        return null;
    }

}
