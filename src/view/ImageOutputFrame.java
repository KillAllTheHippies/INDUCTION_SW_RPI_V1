package view;

import controller.InductionSWController;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        createImageFromFrame();
    }
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10,10));
//        ImagePanel imagePanel = new ImagePanel(InductionSWController.getInstance().getCurrentInductee().getPhoto());
//        imagePanel.setSize(InductionSWController.getInstance().getCurrentInductee().getPhoto().getWidth(),InductionSWController.getInstance().getCurrentInductee().getPhoto().getHeight());
        JPanel imagePanel = new JPanel();

        BufferedImage img = InductionSWController.getInstance().getCurrentInductee().getPhoto();

        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        imagePanel.add(label);
        centerPanel.add(imagePanel,BorderLayout.CENTER);
        centerPanel.add(createDetailsPanel(),BorderLayout.EAST);
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

    private void createImageFromFrame() {
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);  //this == JComponent
        g.dispose();
        try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
    }
}
