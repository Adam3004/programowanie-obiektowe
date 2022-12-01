package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) throws IllegalArgumentException {
        return MoveDirection.changeStringsToEnums(moves);

    }
}
