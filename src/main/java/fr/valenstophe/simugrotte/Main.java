package fr.valenstophe.simugrotte;

import fr.valenstophe.simugrotte.geometry.Area;
import fr.valenstophe.simugrotte.geometry.Position2D;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator(new Area(200, 200));

        simulateTicks(simulator);

        // loop(ceiling);
    }

    private static void simulateTicks(Simulator simulator) {
        for (int i = 0; i < 10000; i++) {
            simulator.tick(i);
        }
    }

    private static void loop(Simulator simulator) {
        Scanner scanner = new Scanner(System.in);

        int currentTick = 0;
        System.out.println("Ready !");
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("q")) {
                return;
            }
            simulator.tick(currentTick);
            currentTick++;
        }
    }

    public static void testIsInside() {
        Position2D areaPosition = new Position2D(100, 500);
        Area area = new Area(areaPosition, 300, 400);

        Position2D[] positionsToTest = {
            new Position2D(99, 500), // faux
            new Position2D(100, 499), // faux
            new Position2D(100, 500), // vrai
            new Position2D(501, 800), // faux
            new Position2D(500, 801), // faux
            new Position2D(500, 800) // vrai
        };

        for (Position2D position : positionsToTest) {
            System.out.println(position + ": " + area.isInside(position));
        }
    }
}