package fr.valenstophe.simugrotte.ui;

import fr.valenstophe.simugrotte.Entity;
import fr.valenstophe.simugrotte.Stalactite;
import fr.valenstophe.simugrotte.WaterDrop;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DrawPoints extends JPanel {

    private List<Entity> entities;

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public DrawPoints() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(200, 200);
        affineTransform.scale(5, 5);

        g2d.setTransform(affineTransform);

        List<Entity> entitiesCopy = new ArrayList<>(entities);

        int sodaStrawCount = (int) entitiesCopy.stream().filter(entity -> entity instanceof Stalactite && !((Stalactite) entity).isSealed()).peek(entity -> {
            g2d.setColor(Color.red);
            g2d.drawLine(entity.getPosition().getX(), entity.getPosition().getY(),
                    entity.getPosition().getX() + 1, entity.getPosition().getY() + 1);
        }).count();

        int stalactiteCount = (int) entitiesCopy.stream().filter(entity -> entity instanceof Stalactite && ((Stalactite) entity).isSealed()).peek(entity -> {
            g2d.setColor(Color.yellow);
            g2d.drawLine(entity.getPosition().getX(), entity.getPosition().getY(),
                    entity.getPosition().getX() + 1, entity.getPosition().getY() + 1);
        }).count();

        int waterDropCount = (int) entitiesCopy.stream().filter(entity -> entity instanceof WaterDrop).peek(entity -> {
            g2d.setColor(Color.blue);
            g2d.drawLine(entity.getPosition().getX(), entity.getPosition().getY(),
                    entity.getPosition().getX(), entity.getPosition().getY());
        }).count();

        g2d.drawString("WaterDrop : " + waterDropCount, -35, -25);
        g2d.drawString("SodaStraw : " + sodaStrawCount, -35, -10);
        g2d.drawString("Stalactite : " + stalactiteCount, 75, -25);


//        for (int i = 0; i <= 100000; i++) {
//            Dimension size = getSize();
//            int w = size.width ;
//            int h = size.height;
//
//            Random r = new Random();
//            int x = Math.abs(r.nextInt()) % w;
//            int y = Math.abs(r.nextInt()) % h;
//            g2d.drawLine(x, y, x, y);
//        }
    }
}