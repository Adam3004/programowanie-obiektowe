package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public enum MoveDirection {
    FORWARD("f", "zwierzak idzie do przodu"),
    BACKWARD("b", "zwierzak idzie do tyłu"),
    RIGHT("r", "zwierzak idzie w prawo"),
    LEFT("l", "zwierzak idzie w lewo");
//    UNKNOWN("", "not necessary");

    private final String communicate;
    private final String letter;

    MoveDirection(String letter, String communicate) {
        this.letter = letter;
        this.communicate = communicate;
    }

    public String getCommunicate() {
        return communicate;
    }

    public String getLetter() {
        return letter;
    }

    public static MoveDirection[] changeStringsToEnums(String[] moves) {
        List<String> letters = getAllLetters();
        Arrays.stream(MoveDirection.values()).forEach(moveDirection -> letters.add(moveDirection.name().toLowerCase(Locale.ROOT)));
        return Arrays.stream(moves)
                .filter(letters::contains)
                .map(MoveDirection::changeLetterToMoveDirection)
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection changeLetterToMoveDirection(String letter) {
        List<MoveDirection> newVal = Arrays.stream(MoveDirection.values())
                .filter(moveDirection -> moveDirection.getLetter().equals(letter) || moveDirection.name().toLowerCase(Locale.ROOT).equals(letter))
                .collect(Collectors.toList());
        return newVal.get(0);
    }

    private static List<String> getAllLetters() {
        List<String> letters = new ArrayList<>();
        for (MoveDirection moveDirection : MoveDirection.values()) {
            letters.add(moveDirection.getLetter());
        }
        return letters;
    }

}
