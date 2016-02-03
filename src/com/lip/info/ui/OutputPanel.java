/**
 *
 */
package com.lip.info.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.lip.info.client.TravelInfoClient;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class OutputPanel extends JPanel {
    private static final long serialVersionUID = 4052155298523697692L;

    private static final Border PADDING = BorderFactory.createEmptyBorder(100, 100, 100, 100);

    private final JTextArea greeting = new JTextArea("Enter Company Name and Airline Code Above");
    private final UICore model;

    public OutputPanel(UICore gen) {
        model = gen;

        setBorder(PADDING); // Adds padding

        add(greeting);
    }

    // refresh and display the company code and flight number
    public void refresh() {
        String comCode = model.getName();
        String number = model.getCode();
        String rawReturn = TravelInfoClient.getAllInfoResponse(comCode, number);
        greeting.setText(parseRawReturn(rawReturn));
    }

    // parse the XML information
    private String parseRawReturn(String raw) {
        String[] s = raw.split("<[/a-zA-Z]+>");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > 1) {
                ret.append(s[i] + "\n");
            }
        }

        return ret.toString();

    }

}
