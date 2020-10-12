package com.nick777.tgdr.common;

import com.nick777.tgdr.TheGreatDotRevamp;
import com.nick777.tgdr.client.gui.GamePanel;
import com.nick777.tgdr.common.GameElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.*;
import static com.nick777.tgdr.client.gui.GameScreen.gamePaused;

public class GameEngine {
    private static Tank player = new Tank();
    private static final GameElements.Window window = new GameElements.Window();
    private static final GameElements.Window map = new GameElements.Window();
    private static final Grid grid = new Grid();
    private static GamePanel gamePanel;
    private static Bullet newBullet;
    private static final MovementState movementState = new MovementState();
    private static final MouseState mouseState = new MouseState();
    private static final Coordinates mouse = new Coordinates();
    private static final Coordinates screenCenter = new Coordinates();

    public static void initialize(JFrame frame, GamePanel panel) {
        gamePanel = panel;

        screenCenter.x = TheGreatDotRevamp.SCREENWIDTH / 2;
        screenCenter.y = TheGreatDotRevamp.SCREENHEIGHT /2;

        //Set map boundaries
        map.setDefaultWindow(screenCenter, 2000,2000);
        window.setDefaultWindow(screenCenter,TheGreatDotRevamp.SCREENWIDTH, TheGreatDotRevamp.SCREENHEIGHT);

        grid.numHLines = (int) window.width / 20 + 2;
        grid.numVLines = (int) window.height / 20 + 2;

        InputMap im = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gamePanel.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "w-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "w-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "a-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "a-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "s-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "s-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "d-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "d-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,0,false),"mouseLeft-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,0,true),"mouseLeft-released");

        am.put("w-pressed", new YDirectionAction(movementState, -2));
        am.put("w-released", new YDirectionAction(movementState, 0));
        am.put("a-pressed", new XDirectionAction(movementState, -2));
        am.put("a-released", new XDirectionAction(movementState, 0));
        am.put("s-pressed", new YDirectionAction(movementState, 2));
        am.put("s-released", new YDirectionAction(movementState, 0));
        am.put("d-pressed", new XDirectionAction(movementState, 2));
        am.put("d-released", new XDirectionAction(movementState, 0));

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {}

            @Override
            public void mousePressed(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON1) {
                    mouseState.LeftButtonPressed = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON1) {
                    mouseState.LeftButtonPressed = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {}

            @Override
            public void mouseExited(MouseEvent me) {}
        });

        Timer timer = new Timer(17, actionEvent -> {
            if (!gamePaused) {
                //Update mouse coordinates
                    mouse.x = MouseInfo.getPointerInfo().getLocation().x;
                    mouse.y = MouseInfo.getPointerInfo().getLocation().y;

                //Update Server Side
                    //Update Player
                    player.coords.y += movementState.yDirection;
                    player.coords.x += movementState.xDirection;
                    player.barrel.angle = getAngle(screenCenter,mouse);
                    player.radius = 40;
                    player.barrel.length = 70;
                    player.barrel.width = 40;
                    player.barrel.coords = getBarrelCoords(player);

                    //Add new bullets
                    if (mouseState.LeftButtonPressed) {
                        newBullet = new Bullet();
                        newBullet.coords.x = player.barrel.length * cos(player.barrel.angle) + player.coords.x;
                        newBullet.coords.y = player.barrel.length * sin(player.barrel.angle) + player.coords.y;
                        newBullet.vel.x = cos(player.barrel.angle);
                        newBullet.vel.y = sin(player.barrel.angle);
                        newBullet.radius = 15;
                        player.bullets = player.addBullet(newBullet);
                    }

                    //Update Current Bullets
                    for(int i = 0; i < player.bullets.length; i++) {
                        player.bullets[i].coords.x += player.bullets[i].vel.x;
                        player.bullets[i].coords.y += player.bullets[i].vel.y;
                    }

                    //Update Window Location
                    window.updateWindow(player.coords, screenCenter,TheGreatDotRevamp.SCREENWIDTH,TheGreatDotRevamp.SCREENHEIGHT);

                    //Update Background Grid
                    double startX = -(window.tl.x % 20);
                    double startY = -(window.tl.y % 20);

                    for (int i = 0; i <= grid.numHLines; i++) {
                        grid.hGridStart.xArray[i] = (int) startX + 20 * i;
                        grid.hGridStart.yArray[i] = 0;
                        grid.hGridEnd.xArray[i] = (int) startX + 20 * i;
                        grid.hGridEnd.yArray[i] = (int) map.height;
                    }

                    for (int i = 0; i <= grid.numVLines; i++) {
                        grid.vGridStart.xArray[i] = 0;
                        grid.vGridStart.yArray[i] = (int) startY + i * 20;
                        grid.vGridEnd.xArray[i] = (int) map.width;
                        grid.vGridEnd.yArray[i] = (int) startY + i * 20;
                    }

                //Update Client Side
                    gamePanel.setPlayer(player);
                    gamePanel.setGrid(grid);
                    gamePanel.setWindow(window);

                //Update Frame
                    gamePanel.repaint();
            }
        });
        timer.start();
    }

    public static class MovementState {
        public int xDirection;
        public int yDirection;
    }

    public static class MouseState {
        public boolean LeftButtonPressed;
        public boolean RightButtonPressed;
    }

    public static abstract class AbstractDirectionAction extends AbstractAction {

        private final MovementState movementState;
        private final int value;

        public AbstractDirectionAction(MovementState movementState, int value) {
            this.movementState = movementState;
            this.value = value;
        }

        public MovementState getMovementState() {
            return movementState;
        }

        public int getValue() {
            return value;
        }

    }

    public static class YDirectionAction extends AbstractDirectionAction {

        public YDirectionAction(MovementState movementState, int value) {
            super(movementState, value);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getMovementState().yDirection = getValue();
        }

    }

    public static class XDirectionAction extends AbstractDirectionAction {

        public XDirectionAction(MovementState movementState, int value) {
            super(movementState, value);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getMovementState().xDirection = getValue();
        }

    }

    public static CoordinateList getBarrelCoords(Tank tank) {
        int halfBarrel = tank.barrel.width / 2;
        double modAngle = tank.barrel.angle + PI / 2;

        Coordinates endCenter = new Coordinates();
        endCenter.x = tank.barrel.length * cos(tank.barrel.angle);
        endCenter.y = tank.barrel.length * sin(tank.barrel.angle);

        CoordinateList barrelCoords = new CoordinateList();
        //All corners are described as if the barrel were pointing upwards on the screen

        //Bottom Left Corner
        barrelCoords.xArray[0] = (int) (halfBarrel * cos(modAngle));
        barrelCoords.yArray[0] = (int) (halfBarrel * sin(modAngle));

        //Bottom Right Corner
        barrelCoords.xArray[1] = -(barrelCoords.xArray[0]);
        barrelCoords.yArray[1] = -barrelCoords.yArray[0];

        //Top Right Corner
        barrelCoords.xArray[2] = (int) endCenter.x + barrelCoords.xArray[1];
        barrelCoords.yArray[2] = (int) endCenter.y + barrelCoords.yArray[1];

        //Top Left Corner
        barrelCoords.xArray[3] = (int) endCenter.x + barrelCoords.xArray[0];
        barrelCoords.yArray[3] = (int) endCenter.y + barrelCoords.yArray[0];

        for (int i = 0; i < 4; i++) {
            barrelCoords.xArray[i] += screenCenter.x;
            barrelCoords.yArray[i] += screenCenter.y;
        }

        return barrelCoords;
    }

    public static double getAngle(Coordinates origin, Coordinates testPoint) {
        return -atan2(testPoint.x - origin.x,testPoint.y - origin.y) + PI / 2;
    }

    public static class Coordinates {
        public double x;
        public double y;
    }

    public static class CoordinateList {
        public int[] xArray = new int[99];
        public int[] yArray = new int[99];
    }

}