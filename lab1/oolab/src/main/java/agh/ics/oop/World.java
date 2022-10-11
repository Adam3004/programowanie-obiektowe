package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
//        System.out.println("System wystartował");
        System.out.println("Start");
        Direction[] moves = Direction.changeStringsToEnums(args);
        run(moves);
//        System.out.println("System zakończył działanie");
        System.out.println("Stop");
    }

    private static void run(Direction[] moves) {
        Arrays.stream(moves)
                .map(Direction::getCommunicate)
                .forEach(System.out::println);
    }

//    kod poniżej tyczy się wcześniejszych poleceń, bądź prostszych sposobów których użyłem (bez streamów)

//    private static void run(String[] moves){
////        System.out.println("zwierzak idzie do przodu");
////        for(int i =0; i<moves.length-1; i++) {
////            System.out.print(moves[i]);
////            System.out.print(", ");
////        }
////        System.out.println(moves[moves.length-1]);
//
//        String currMove;
//        for(int i =0; i<moves.length; i++){
//            currMove = moves[i];
//            switch (currMove){
//                case "f" -> System.out.println("zwierzak idzie do przodu");
//                case "b" -> System.out.println("zwierzak idzie do tyłu");
//                case "l" -> System.out.println("zwierzak idzie w lewo");
//                case "r" -> System.out.println("zwierzak idzie w prawo");
//            }
//        }
//
//    }

//    private static void run(Direction[] moves){
//        for(Direction move: moves){
//            switch (move){
//                case FORWARD -> System.out.println("zwierzak idzie do przodu");
//                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
//                case LEFT -> System.out.println("zwierzak idzie w lewo");
//                case RIGHT -> System.out.println("zwierzak idzie w prawo");
//            }
//        }
//    }

//    private static String findSout(Direction curr){
//        String newValue;
//        switch (curr){
//            case FORWARD -> newValue = "zwierzak idzie do przodu";
//            case BACKWARD -> newValue = "zwierzak idzie do tyłu";
//            case LEFT -> newValue = "zwierzak idzie w lewo";
//            case RIGHT -> newValue = "zwierzak idzie w prawo";
////            dzięki filtracji element zawsze będzie jednym z 4 powyższych
//            default -> newValue = "";
//        }
//        return newValue;
//    }
//
//    private static void run(Direction[] moves){
//        Arrays.stream(moves)
//                .filter(elem -> elem != Direction.UNKNOWN)
//                .map(World::findSout)
//                .forEach(System.out::println);
//    }
//
//    private static void changeStringsToEnums(String[] moves, Direction[] newList){
//        for (int i=0; i<moves.length; i++){
//            switch (moves[i]){
//                case "f" -> newList[i]=Direction.FORWARD;
//                case "b" -> newList[i]=Direction.BACKWARD;
//                case "l" -> newList[i]=Direction.LEFT;
//                case "r" -> newList[i]=Direction.RIGHT;
//                default -> newList[i]=Direction.UNKNOWN;
//            }
//        }
//    }


}
