package view;

import controller.InductionSWController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Jamie on 30/07/2017.
 */
public class ImageOutputFrame extends JFrame {

    // Constructor
    public ImageOutputFrame() throws HeadlessException {


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 0));
        ImagePanel imagePanel = new ImagePanel(InductionSWController.getInstance().getCurrentInductee().getPhoto());
        centerPanel.add(imagePanel);
        centerPanel.add(createDetailsPanel());
        return centerPanel;
    }

    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new MigLayout("wrap 1"));
        //public Inductee(String name, String phoneNum, String email, String jobTitle, String carReg, Boolean firstAidTrained, long dateOfInduction )
        JLabel lblName = new JLabel("Name:" + InductionSWController.getInstance().getCurrentInductee().getName());
        JLabel lblPhoneNum = new JLabel("Contact Number: " + InductionSWController.getInstance().getCurrentInductee().getName());
        JLabel lblEmail = new JLabel("Email: " + InductionSWController.getInstance().getCurrentInductee().getEmail());
        JLabel lblJobTitle = new JLabel("Job Title: " + InductionSWController.getInstance().getCurrentInductee().getJobTitle());
        JLabel lblCarReg = new JLabel("Vehicle Registration: " + InductionSWController.getInstance().getCurrentInductee().getCarReg());
        String s;
        if (InductionSWController.getInstance().getCurrentInductee().getFirstAidTrained()) {
            s = "Yes";
        } else {
            s = "No";
        }
        JLabel lblFirstAidTrained = new JLabel("First Aid Trained: " + s);
        Date d = new Date(InductionSWController.getInstance().getCurrentInductee().getDateOfInduction());
        JLabel lblDateTime = new JLabel("Date & Time of Induction: " + d.toString() );

        detailsPanel.add(lblName);
        detailsPanel.add(lblPhoneNum);
        detailsPanel.add(lblEmail);
        detailsPanel.add(lblJobTitle);
        detailsPanel.add(lblCarReg);
        detailsPanel.add(lblFirstAidTrained);
        detailsPanel.add(lblDateTime);

        return detailsPanel;

    }
}
