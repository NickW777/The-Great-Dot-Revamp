package com.nick777.tgdr.common;

import com.nick777.tgdr.common.GameEngine.*;

public class GameElements {
    public static class Tank {
        public Coordinates coords = new Coordinates();
        public Barrel barrel = new Barrel();
        public Bullet[] bullets = new Bullet[0];
        public int radius;

        public static class Barrel {
            public CoordinateList coords = new CoordinateList();
            public double angle;
            public int length;
            public int width;
        }

        public Bullet[] addBullet( Bullet newBullet) {
            Bullet[] newBullets = new Bullet[this.bullets.length + 1];
            for (int i = 0; i < this.bullets.length; i++)
                newBullets[i] = this.bullets[i];
            newBullets[this.bullets.length] = newBullet;
            return newBullets;
        }
    }

    public static class Bullet {
        public Coordinates coords = new Coordinates();
        public Coordinates vel = new Coordinates();
        public int radius;
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
