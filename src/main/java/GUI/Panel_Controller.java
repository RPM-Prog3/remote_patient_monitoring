package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel_Controller {
    private JPanel mainPanel, simulationPanel, tuningPanel, graphPanel, vitalsPanel;
    private BP_Vitals BP_panel;
    private RR_Vitals RR_panel;
    private ECG_Vitals ECG_panel;
    private HR_Vitals HR_panel;
    private Border border;

    public Panel_Controller() {
        // Instantiating main panel and the several sub panels
        mainPanel = new JPanel();
        tuningPanel = new JPanel();
        simulationPanel = new JPanel();
        vitalsPanel = new JPanel();
        graphPanel = new JPanel();

        BP_panel = new BP_Vitals();
        RR_panel = new RR_Vitals();
        ECG_panel = new ECG_Vitals();
        HR_panel = new HR_Vitals();

        // Setting the layout of the main panel and of the several sub panels
        mainPanel.setLayout(new GridLayout(2,1));
        simulationPanel.setLayout(new GridLayout(1,2));
        graphPanel.setLayout(new GridLayout(4,1));
        vitalsPanel.setLayout(new GridLayout(4,1));

        // Making the various visible
        mainPanel.setVisible(true);
        tuningPanel.setVisible(true);
        simulationPanel.setVisible(true);
        vitalsPanel.setVisible(true);
        graphPanel.setVisible(true);

        border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        simulationPanel.setBorder(border);
        tuningPanel.setBorder(border);
        vitalsPanel.setBorder(border);
        BP_panel.setBorder(border);
        RR_panel.setBorder(border);
        HR_panel.setBorder(border);
        ECG_panel.setBorder(border);

        // Adding vital sign (BP, RR, HR, ECG) panels to vitalsPanel
        vitalsPanel.add(BP_panel);
        vitalsPanel.add(HR_panel);
        vitalsPanel.add(ECG_panel);
        vitalsPanel.add(RR_panel);

        // Adding the vitalsPanel and graphPanel to simulationPanel
        simulationPanel.add(graphPanel);
        simulationPanel.add(vitalsPanel);

        // Composing the main panel
        mainPanel.add(simulationPanel);
        mainPanel.add(tuningPanel);

        System.out.println(mainPanel.getPreferredSize());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Dimension getPanelSize(){
        return vitalsPanel.getPreferredSize();
    }
}
