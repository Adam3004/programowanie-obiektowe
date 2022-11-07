package agh.ics.oop;


import java.util.Arrays;

public enum MapDirection {
    NORTH("Północ", 0, new int[]{0, 1}),
    EAST("Wschód", 1, new int[]{1, 0}),
    SOUTH("Południe", 2, new int[]{0, -1}),
    WEST("Zachód", 3, new int[]{-1, 0});

    private final String message;
    private final int number;
    private final int[] unitVector;

    MapDirection(String message, int number, int[] unitVector) {
        this.message = message;
        this.number = number;
        this.unitVector = unitVector;
    }

    public String getMessage() {
        return message;
    }

    public int getNumber() {
        return number;
    }

    public int[] getUnitVector() {
        return unitVector;
    }

    public MapDirection next() {
        int nextNumber = (getNumber() + 1) % 4;
        return MapDirection.values()[nextNumber];
    }

    public MapDirection previous() {
        int nextNumber = getNumber() - 1;
        if (nextNumber < 0) {
            nextNumber = 3;
        }
        nextNumber %= 4;

        return MapDirection.values()[nextNumber];
    }

    public Vector2d toUnitVector() {
        int[] position = getUnitVector();
        return new Vector2d(position[0], position[1]);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
