package com.nick777.tgdr.client.gui;

import com.nick777.tgdr.TheGreatDotRevamp;
import com.nick777.tgdr.common.GameElements;
import com.nick777.tgdr.common.GameElements.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Tank player = new Tank();
    Grid grid = new Grid();
    GameElements.Window window = new GameElements.Window();

    public void setPlayer(Tank updatedPlayer) { player = updatedPlayer; }
    public void setGrid(Grid updatedGrid) { grid = updatedGrid; }
    public void setWindow(GameElements.Window updatedWindow) { window = updatedWindow; }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw Background Grid
        g.setColor(new Color(169,169,169, 80));

        //Horizontal
        for (int i = 0; i < grid.numHLines; i++)
            g.drawLine(grid.hGridStart.xArray[i], grid.hGridStart.yArray[i], grid.hGridEnd.xArray[i], grid.hGridEnd.yArray[i]);

        //Vertical
        for (int i = 0; i < grid.numVLines; i++)
            g.drawLine(grid.vGridStart.xArray[i], grid.vGridStart.yArray[i], grid.vGridEnd.xArray[i], grid.vGridEnd.yArray[i]);

        //Draw Player Bullets
        g.setColor(Color.RED);
        for (int i = 0; i < player.bullets.length; i++)
            g.fillOval((int) player.bullets[i].coords.x - (int) window.tl.x - player.bullets[i].radius,(int) player.bullets[i].coords.y - (int) window.tl.y - player.bullets[i].radius,player.bullets[i].radius * 2, player.bullets[i].radius * 2);

        //Draw Player Barrel
        g.setColor(Color.gray);
        g.fillPolygon(player.barrel.coords.xArray, player.barrel.coords.yArray, 4);

        //Draw Player Body
        g.setColor(new Color(0, 176, 225));
        g.fillOval(TheGreatDotRevamp.SCREENWIDTH / 2 - player.radius,TheGreatDotRevamp.SCREENHEIGHT / 2 - player.radius,player.radius * 2,player.radius * 2);
    }
}
