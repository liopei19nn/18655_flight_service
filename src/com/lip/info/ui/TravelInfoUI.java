package com.lip.info.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class TravelInfoUI {
    private static final String TITLE = "Travel Assistant";

    private final JFrame frame;
    private final UICore model;

    private final OutputPanel outputPanel;
    private final InputPanel inputPanel;
    private final JPanel quotePanel = new JPanel();

    /**
     * @Param input the ui core into system
     */
    public TravelInfoUI(UICore gen) {
        model = gen;

        outputPanel = new OutputPanel(model);
        inputPanel = new InputPanel(model, outputPanel);

        quotePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        frame = new JFrame(TITLE);
        frame.setResizable(true);

        addElements(frame.getContentPane()); // get panel for output
        frame.pack();

        // When this window's "exit" button is clicked, the program ends
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // add all panel in the frame
    private void addElements(Container container) {
        container.setLayout(new BorderLayout()); // set up a layout manager

        container.add(inputPanel, BorderLayout.NORTH);
        container.add(outputPanel, BorderLayout.SOUTH);

        // Creates a panel with two buttons on it and adds it to the top
        JPanel buttonPanel = new JPanel();
        inputPanel.add(buttonPanel);

    }

    // show the UI
    public void show() {
        // dislay the UI interface
        frame.setVisible(true);
    }

    // create UI
    public static void createAndShowGUI() {
        // create the UI
        UICore generator = new UICore();
        TravelInfoUI main = new TravelInfoUI(generator);
        main.show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
