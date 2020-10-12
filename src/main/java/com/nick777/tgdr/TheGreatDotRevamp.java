package com.nick777.tgdr;

import com.nick777.tgdr.client.gui.TitleScreen;

import javax.swing.*;
import java.awt.*;

public class TheGreatDotRevamp {

    public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREENWIDTH = SCREENSIZE.width;
    public static final int SCREENHEIGHT = SCREENSIZE.height;

    public static void main(String[] args) {
        JFrame mainFrame = createFrame("The Great Dot Revamp", false, true);

        TitleScreen.initialize(mainFrame);
    }

    public static JFrame createFrame (String title, boolean resizable, boolean visible) {
        JFrame frame = new JFrame(title);
        frame.setLayout(new CardLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(SCREENSIZE);
        frame.setResizable(resizable);

        frame.dispose();
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));
        frame.setVisible(visible);

        return frame;
    }
}
