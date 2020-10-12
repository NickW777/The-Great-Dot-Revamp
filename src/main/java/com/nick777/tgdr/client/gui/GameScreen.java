package com.nick777.tgdr.client.gui;

import com.nick777.tgdr.TheGreatDotRevamp;
import com.nick777.tgdr.common.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.nick777.tgdr.client.render.Icon.*;

public class GameScreen implements ActionListener {

    private static JFrame gameFrame;
    private static GamePanel gamePanel;
    private static JLayeredPane layeredPane;
    public static JButton pauseButton;
    public static boolean gamePaused;

    public static void initialize(JFrame frame) {
        gameFrame = frame;

        //Layered Pane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,TheGreatDotRevamp.SCREENWIDTH, TheGreatDotRevamp.SCREENHEIGHT);
        gameFrame.add(layeredPane);

        //Panel
        gamePanel = new GamePanel();

        gamePanel.setBackground(Color.white);
        gamePanel.setBounds(0,0, TheGreatDotRevamp.SCREENWIDTH, TheGreatDotRevamp.SCREENHEIGHT);
        layeredPane.add(gamePanel, 1);

        //Pause Button
        pauseButton = new JButton(pauseButtonIcon);
        pauseButton.setBounds(TheGreatDotRevamp.SCREENWIDTH - 80,10,70,70);
        pauseButton.setActionCommand("Pause");
        pauseButton.addActionListener(new GameScreen());
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(new Color(0,0,0, 255) );
        pauseButton.setRolloverIcon(pauseButtonHoverIcon);
        pauseButton.setPressedIcon(pauseButtonClickIcon);
        gamePanel.add(pauseButton);

        GameEngine.initialize(gameFrame, gamePanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Pause":
                pauseGame(true);
                pauseButton.setEnabled(false);
                PauseScreen.initialize(gameFrame, layeredPane);
                break;
        }
    }

    public static void pauseGame(boolean isPaused) {
        gamePaused = isPaused;
    }
}
