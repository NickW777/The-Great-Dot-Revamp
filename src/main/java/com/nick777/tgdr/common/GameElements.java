package com.nick777.tgdr.common;

import com.nick777.tgdr.common.GameEngine.*;
import com.nick777.tgdr.common.ai.StaticAI;

import static java.lang.Math.*;

public class GameElements {
    public static class Tank {
        public Coordinates coords = new Coordinates();
        public Barrel barrel = new Barrel();
        public Bullet[] bullets = new Bullet[0];
        public StaticAI ai = new StaticAI();

        public int totalReloadTime;
        public int timeSinceLastShot = this.totalReloadTime;
        public int radius;

        public CoordinateList getBarrelCoords(Coordinates screenCoords, boolean isPlayer, Window window) {
            int halfBarrel = this.barrel.width / 2;
            double modAngle = this.barrel.angle + PI / 2;

            Coordinates endCenter = new Coordinates();
            endCenter.x = this.barrel.length * cos(this.barrel.angle);
            endCenter.y = this.barrel.length * sin(this.barrel.angle);

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

            if (isPlayer) {
                for (int i = 0; i < 4; i++) {
                    barrelCoords.xArray[i] += screenCoords.x;
                    barrelCoords.yArray[i] += screenCoords.y;
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    barrelCoords.xArray[i] += this.coords.x - window.tl.x;
                    barrelCoords.yArray[i] += this.coords.y - window.tl.y;
                }
            }

            return barrelCoords;
        }

        public static class Barrel {
            public CoordinateList coords = new CoordinateList();
            public double angle;
            public int length;
            public int width;
        }

        public void addBullet(Bullet newBullet) {
            Bullet[] newBullets = new Bullet[this.bullets.length + 1];
            System.arraycopy(this.bullets, 0, newBullets, 0, this.bullets.length);
            newBullets[this.bullets.length] = newBullet;
            this.bullets = newBullets;
        }

        public void removeBullet(int bulletIndex) {
            Bullet[] newBullets = new Bullet[this.bullets.length - 1];
            for (int i = 0; i < this.bullets.length; i++) {
                if (i == bulletIndex) {
                } else if (i > bulletIndex) {
                    newBullets[i - 1] = this.bullets[i];
                } else {
                    newBullets[i] = this.bullets[i];
                }
            }
            this.bullets = newBullets;
        }
    }

    public static class Bullet {
        public Coordinates coords = new Coordinates();
        public Coordinates vel = new Coordinates();
        public int radius;
        public int speed;
        public int totalLifetime;
        public int lifeSpent;
    }

    public static class Window {
        public Coordinates tl = new Coordinates();
        public Coordinates tr = new Coordinates();
        public Coordinates bl = new Coordinates();
        public Coordinates br = new Coordinates();
        public Coordinates center = new Coordinates();
        public double width;
        public double height;

        public void setDefaultWindow(Coordinates screenCenter, double width, double height) {
            tl.x = 0;
            tl.y = 0;
            tr.x = width;
            tr.y = 0;
            bl.x = 0;
            bl.y = height;
            br.x = width;
            br.y = height;
            center.x = screenCenter.x;
            center.y = screenCenter.y;
            this.width = width;
            this.height = height;
        }

        public void updateWindow(Coordinates newCenter, Coordinates screenCenter, double width, double height) {
            tl.x = newCenter.x - screenCenter.x;
            tl.y = newCenter.y - screenCenter.y;
            tr.x = tl.x + width;
            tr.y = tl.y;
            bl.x = tl.x;
            bl.y = tl.y + height;
            br.x = tl.x + width;
            br.y = tl.y + height;
            center.x = newCenter.x;
            center.y = newCenter.y;
            this.width = width;
            this.height = height;
        }
    }

    public static class Grid {
        public CoordinateList hGridStart = new CoordinateList();
        public CoordinateList hGridEnd = new CoordinateList();
        public CoordinateList vGridStart = new CoordinateList();
        public CoordinateList vGridEnd = new CoordinateList();

        public int numHLines;
        public int numVLines;
    }
}
