package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Vital_Values_Display extends JPanel {
    protected JPanel sub_display, status, type, value;
    protected Dimension overall_display_dim, sub_display_dim, value_dim;
    protected int proportion_num, proportion_den , proportion_2_num, proportion_2_den;
    protected JLabel status_msg, vital_type, vital_value;
    protected Border border;

    public Vital_Values_Display(){
        // Instantiating different panels;
        sub_display = new JPanel();
        status = new JPanel();
        type = new JPanel();
        value = new JPanel();
        sub_display.validate();

        // Instantiating different labels
        status_msg = new JLabel();
        vital_type = new JLabel();
        vital_value = new JLabel();

        // Setting Layouts of the main and sub panels
        setLayout(new GridLayout(1,2));
        sub_display.setLayout(new GridLayout(2,1));

        // Adding sub panels to main panel to construct main panel
        sub_display.add(value);
        sub_display.add(status);

        // Adding labels to corresponding Panels
        status.add(status_msg);
        value.add(vital_value);
        type.add(vital_type);

        // Instantiating border object(s)
        border = BorderFactory.createLineBorder(Color.black);

        // Setting various panels to be visible and adding contours to them
        sub_display.setVisible(true);
        sub_display.setBorder(border);
        status.setVisible(true);
        status.setBorder(border);
        type.setVisible(true);
        type.setBorder(border);
        value.setVisible(true);
        value.setBorder(border);

        // Setting the various labels to be visible
        status_msg.setVisible(true);
        vital_type.setVisible(true);
        vital_value.setVisible(true);

        setVisible(true);
        add(type);
        add(sub_display);

    }
}

