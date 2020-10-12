package com.nick777.tgdr.client.render;

import com.nick777.tgdr.TheGreatDotRevamp;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Icon {
    private static Class cls = new TheGreatDotRevamp().getClass();

    private static URL url = cls.getResource("/pauseButton.png");

    public static final javax.swing.Icon pauseButtonIcon = new ImageIcon( new ImageIcon("src/main/resources/assets/pauseButton.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH));
    public static final javax.swing.Icon pauseButtonHoverIcon = new ImageIcon(new ImageIcon("src/main/resources/assets/pauseButtonHover.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH));
    public static final javax.swing.Icon pauseButtonClickIcon = new ImageIcon(new ImageIcon("src/main/resources/assets/pauseButtonClick.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH));

    public static final javax.swing.Icon playButtonIcon = new ImageIcon(new ImageIcon("src/main/resources/assets/playButton.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH));

}
