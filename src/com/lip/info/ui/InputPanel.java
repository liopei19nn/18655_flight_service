/**
 *
 */
package com.lip.info.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class InputPanel extends JPanel {
    private static final String SUBMIT = "Submit";
    private static final String NAME_COLON = "Company Code:";
    private static final String CODE_COLON = "Airline Code";
    private static final long serialVersionUID = -7258071600712637251L;

    private static final int NUM_COLUMNS = 5;

    private final UICore model;
    private final OutputPanel outputPanel;
    private final JTextField nameField = new JTextField(NUM_COLUMNS);
    private final JLabel name = new JLabel(NAME_COLON);

    private final JTextField codeField = new JTextField(NUM_COLUMNS);
    private final JLabel code = new JLabel(CODE_COLON);

    private final JButton submitButton = new JButton(SUBMIT);

    public InputPanel(UICore gen, OutputPanel sp) {
        model = gen;
        outputPanel = sp;

        /*
         * TODO: Add the following to this JPanel: add company name label,
         * field, airline number label, field and submib button
         */

        add(name);
        add(nameField);
        add(code);
        add(codeField);
        add(submitButton);


        /*
         * TODO: Add an action listener
         *
         */
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.setName(nameField.getText(), codeField.getText());
                outputPanel.refresh();
            }
        });
    }
}
