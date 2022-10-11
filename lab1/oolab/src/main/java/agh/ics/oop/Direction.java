package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Direction {
    FORWARD("f", "zwierzak idzie do przodu"),
    BACKWARD("b", "zwierzak idzie do tyłu"),
    RIGHT("r", "zwierzak idzie w prawo"),
    LEFT("l", "zwierzak idzie w lewo");
//    UNKNOWN("", "not necessary");

    private final String communicate;
    private final String letter;

    Direction(String letter, String communicate) {
        this.letter = letter;
        this.communicate = communicate;
    }

    public String getCommunicate() {
        return communicate;
    }

    public String getLetter() {
        return letter;
    }

    public static Direction[] changeStringsToEnums(String[] moves){
        List<String> letters = getAllLetters();
        return Arrays.stream(moves)
                .filter(letters::contains)
                .map(Direction::changeLetterToDirection)
                .toArray(Direction[]::new);
    }

    private static Direction changeLetterToDirection(String letter){
        List<Direction> newVal = Arrays.stream(Direction.values())
                .filter(direction -> direction.letter.equals(letter))
                .collect(Collectors.toList());
        return newVal.get(0);
    }

    private static List<String> getAllLetters() {
        List<String> letters = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            letters.add(direction.getLetter());
        }
        return letters;
    }

}
