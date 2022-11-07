package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) {
        return MoveDirection.changeStringsToEnums(moves);
    }
}
