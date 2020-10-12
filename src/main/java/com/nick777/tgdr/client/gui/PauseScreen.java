package com.nick777.tgdr.client.gui;

import com.nick777.tgdr.TheGreatDotRevamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static com.nick777.tgdr.client.gui.GameScreen.pauseButton;
import static com.nick777.tgdr.client.gui.GameScreen.pauseGame;
import static com.nick777.tgdr.client.render.Icon.*;

public class PauseScreen implements ActionListener {

    private static JPanel pausePanel;
    private static JLayeredPane layeredPane;
    private static JFrame gameFrame;

    public static void initialize(JFrame frame, JLayeredPane pane) {
        layeredPane = pane;
        gameFrame = frame;

        //Panel
        pausePanel = new JPanel();
        pausePanel.setBackground(new Color(0,0,0,80));
        pausePanel.setBounds(0,0, TheGreatDotRevamp.SCREENWIDTH, TheGreatDotRevamp.SCREENHEIGHT);
        layeredPane.add(pausePanel, 3);

        //Play Button
        JButton playButton = new JButton(playButtonIcon);
        playButton.setBounds(10,10,70,70);
        playButton.setActionCommand("Play");
        playButton.addActionListener(new PauseScreen());
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setRolloverIcon(playButtonIcon);
        playButton.setPressedIcon(playButtonIcon);
        pausePanel.add(playButton);

        //Title Screen Button
        JButton titleButton = new JButton("Title Screen");
        titleButton.setBounds(10, TheGreatDotRevamp.SCREENHEIGHT - 70, 140, 60);
        titleButton.addActionListener(new PauseScreen());
        pausePanel.add(titleButton);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Play":
                pauseGame(false);
                pauseButton.setEnabled(true);
                layeredPane.remove(pausePanel);
                layeredPane.repaint();
                break;
            case "Title Screen":
                gameFrame.remove(layeredPane);
                TitleScreen.initialize(gameFrame);
                break;
        }
    }
}
