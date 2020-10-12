package com.nick777.tgdr.client.gui;

import com.nick777.tgdr.TheGreatDotRevamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen implements ActionListener {

    private static JFrame titleScreenFrame;
    private static JPanel titleScreenPanel;

    public static void initialize(JFrame frame) {
        titleScreenFrame = frame;

        //Panel
        titleScreenPanel = new JPanel();
        titleScreenPanel.setBackground(Color.gray);
        titleScreenPanel.setBounds(0,0,TheGreatDotRevamp.SCREENWIDTH,TheGreatDotRevamp.SCREENHEIGHT);
        titleScreenFrame.add(titleScreenPanel);

        //Start Game Button
        JButton startButton = new JButton("Play Game");
        startButton.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 - 50, TheGreatDotRevamp.SCREENHEIGHT / 2 + 200, 100, 60);
        titleScreenPanel.add(startButton);
        startButton.addActionListener(new TitleScreen());

        //Settings Button
        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 + 70, TheGreatDotRevamp.SCREENHEIGHT / 2 + 200, 100, 60);
        titleScreenPanel.add(settingsButton);
        settingsButton.addActionListener(new TitleScreen());

        //Quit Button
        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 - 170, TheGreatDotRevamp.SCREENHEIGHT / 2 + 200, 100, 60);
        titleScreenPanel.add(quitButton);
        quitButton.addActionListener(new TitleScreen());

        //Title Top
        JLabel titleTop = new JLabel("THE GREAT DOT", SwingConstants.CENTER);
        titleTop.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 - 1000,50,2000,100);
        titleTop.setFont(new Font("Courier New", Font.ITALIC, 160));
        titleScreenPanel.add(titleTop);

        //Title Bottom
        JLabel titleBottom = new JLabel("REVAMP", SwingConstants.CENTER);
        titleBottom.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 - 1000,200,2000,100);
        titleBottom.setFont(new Font("Courier New", Font.ITALIC, 160));
        titleScreenPanel.add(titleBottom);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Play Game":
                titleScreenFrame.remove(titleScreenPanel);
                GameScreen.initialize(titleScreenFrame);
                break;
            case "Settings":
                titleScreenFrame.remove(titleScreenPanel);
                SettingsScreen.initialize(titleScreenFrame);
                break;
            case "Quit Game":
                System.exit(0);
                break;
        }
    }
}
