package com.kevin.base.enums;

/**
 * @author kevin
 */

public enum AbstractEnumTest {
    RED(30) {
        public AbstractEnumTest newxtLamp() {
            return GREEN;
        }
    },
    GREEN(45) {
        public AbstractEnumTest newxtLamp() {
            return YELLOW;
        }
    },
    YELLOW(5) {
        public AbstractEnumTest newxtLamp() {
            return RED;
        }
    };

    public abstract AbstractEnumTest newxtLamp();

    private int time;

    private AbstractEnumTest(int time) {
        this.time = time;
    }
}
