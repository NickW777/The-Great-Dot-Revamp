package com.nick777.tgdr.client.gui;

import com.nick777.tgdr.TheGreatDotRevamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsScreen implements ActionListener {

    private static JFrame settingsScreenFrame;
    private static JPanel settingsScreenPanel;

    public static void initialize(JFrame frame) {

        settingsScreenFrame = frame;
        settingsScreenFrame.setVisible(true);

        //Panel
        settingsScreenPanel = new JPanel();
        settingsScreenPanel.setBackground(Color.gray);
        settingsScreenPanel.setBounds(0,0, TheGreatDotRevamp.SCREENWIDTH,TheGreatDotRevamp.SCREENHEIGHT);
        settingsScreenFrame.add(settingsScreenPanel);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 100, 60);
        settingsScreenPanel.add(backButton);
        backButton.addActionListener(new SettingsScreen());

        //Title
        JLabel title = new JLabel("SETTINGS", SwingConstants.CENTER);
        title.setBounds(TheGreatDotRevamp.SCREENWIDTH / 2 - 500,50,1000,100);
        title.setFont(new Font("Courier New", Font.ITALIC, 160));
        settingsScreenPanel.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Back":
                settingsScreenFrame.remove(settingsScreenPanel);
                TitleScreen.initialize(settingsScreenFrame);
                break;
        }
    }
}
