package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        MoveDirection[] moves = OptionsParser.parse(args);
        Arrays.stream(moves)
                .forEach(animal::move);
        System.out.println(animal);
    }

//    odp na pytanie
//    mechanizm który wyklczua można rozwiązać tworząc listę z informacją na których polach znajdują się zwierzęta
//    przed każdym ruchem można byłoby sprawdzać, czy pole na które zwierzak chce przejść jest wolne i jeśli tak
//    to nanosimy poprawkę na ową listę
}
