package fr.valenstophe.simugrotte;

import fr.valenstophe.simugrotte.geometry.Area;
import fr.valenstophe.simugrotte.geometry.Position2D;
import fr.valenstophe.simugrotte.ui.DrawPoints;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    private static final String title = "Simulation de grotte";

    public static void main(String[] args) throws InterruptedException {
        Simulator simulator = new Simulator(new Area(100, 100));

        DrawPoints drawPoints = new DrawPoints();
        drawPoints.setEntities(simulator.getEntities());

        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawPoints);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        simulateTicks(simulator, frame);

        // loop(ceiling);
    }

    private static void simulateTicks(Simulator simulator, JFrame frame) throws InterruptedException {

        int i = 0;
        while (true) {
            simulator.tick(i);

            if (i % 500 == 0) {
                frame.repaint();
            }
            // avoid crashing the i3 wm on my laptop
            Thread.sleep(1);

            frame.setTitle(title + " - Frame : " + i);
            i++;
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
