package view;

import controller.InductionSWController;
import model.Inductee;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jamie on 29/02/16.
 * <p>
 * Initial frame to be created when application is first started
 */
public class UserInputFrame extends JFrame {

    private JButton cancelButton;
    private JButton beginInductionButton;
//    private JButton addUserButton;
//    private JButton takeQuizButton;
    private JButton displayInducteesButton;
//    private JButton BackEndButton;
//    private JButton displayVideoButton;

    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfJobTitle;
    private JTextField tfCarReg;
    private JTextField tfPhoneNum;
    private JCheckBox chkFirstAidTrained;



    public UserInputFrame(String title) throws HeadlessException {

        super(title);

        // Content of our JFrame
        JPanel mainPanel = new JPanel();

        // set border layout
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // create the side and bottom panels and add them to the layout
//        JPanel sidePanel = createSideButtonPanel();
//        mainPanel.add(sidePanel, BorderLayout.EAST);
        JPanel bottomPanel = createBottomButtonPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // create the Inductee info input panel

        JPanel centerPanel = createCenterPanel();
        centerPanel.setPreferredSize(new Dimension(320,480));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // add to the layout
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        this.setUndecorated(true);
        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.setUndecorated(true);

    }


    private JPanel createCenterPanel() {

        JLabel lblName = new JLabel("Name:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblJobTitle = new JLabel("Job Title:");
        JLabel lblCarReg = new JLabel("Vehicle Registration:");
        JLabel lblPhoneNum = new JLabel("Contact Number:");
        JLabel lblFirstAidTrained = new JLabel("Check the box if you have first aid training.");


        tfName = new JTextField("", 20);
        tfName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String regex = "^[a-zA-Z' ]+$";

                if (!tfName.getText().isEmpty()) {
                    if (tfName.getText().matches(regex)) {
                        // ...
                    } else {
                        JOptionPane.showMessageDialog(null, "Letters and punctuation only allowed in name ");
                        tfName.grabFocus();
                    }
                }
            }
        });
        tfEmail = new JTextField("", 20);
        tfEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String regex = "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

                if (!tfEmail.getText().isEmpty()) {
                    if (tfEmail.getText().matches(regex)) {
                        // ...
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email address");
                        tfEmail.grabFocus();
                    }
                }
            }
        });
        tfJobTitle = new JTextField("", 20);
        tfCarReg = new JTextField("", 20);
        tfPhoneNum = new JTextField("", 20);
        tfPhoneNum.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String regex = "-?\\d+(\\.\\d+)?";

                if (!tfPhoneNum.getText().isEmpty()) {
                    if (tfPhoneNum.getText().matches(regex)) {
                        // ...
                    } else {
                        JOptionPane.showMessageDialog(null, "Only digits allowed in phone number");
                        tfPhoneNum.grabFocus();
                    }
                }
            }
        });
        chkFirstAidTrained = new JCheckBox("Yes", false);

//        JButton btnPhotographCompetencies = new JButton("Photograph Competencies");
//        btnPhotographCompetencies.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // CREATE THE INDUCTEE
//                InductionSWController.getInstance().createInductee(tfName.getText(), tfPhoneNum.getText(), tfEmail.getText(),
//                        tfJobTitle.getText(),tfCarReg.getText(), chkFirstAidTrained.isSelected(),
//                         System.currentTimeMillis());
//
//                // LAUNCH THE PHOTO TAKE FRAME
//                PhotoTakeFrame ptf = new PhotoTakeFrame();
//                ptf.setVisible(true);
////                ptf.setSize(640,480);
//                ptf.setLocationRelativeTo(null);
////                dispose(); // Hide the Dashboard
////                setVisible(false); // hide the dashboard
//                // ADD THE PHOTO
//                // TODO: This goes in next frame
////                try {
////                    InductionSWController.getInstance().getCurrentInductee().setPhoto(InductionSWController.getInstance().takePicture()
////                    );
////                } catch (InterruptedException e1) {
////                    e1.printStackTrace();
////                }
//            }
//        });

        JPanel panel = new JPanel(new MigLayout("wrap 3"));
//        panel.setSize(640,480);


        panel.add(lblName, "cell 0 0");
        panel.add(tfName, "span 2");
        panel.add(lblEmail, "cell 0 1");
        panel.add(tfEmail, "span 2");
        panel.add(lblJobTitle, "cell 0 2");
        panel.add(tfJobTitle, "span 2");
        panel.add(lblPhoneNum, "cell 0 3");
        panel.add(tfPhoneNum, "span 2");
        panel.add(lblCarReg, "cell 0 4");
        panel.add(tfCarReg, "span 2");
        panel.add(lblFirstAidTrained, "cell 0 5");
        panel.add(chkFirstAidTrained, "span 2");
//        panel.add(Box.createVerticalStrut(20), "cell 0 6");
//        panel.add(btnPhotographCompetencies, "cell 1 7");
        return panel;
    }

    /**
     * This method creates the bottom JPanel, puts buttons on it and returns the fully constructed JPanel
     */
    private JPanel createBottomButtonPanel() {
        // Instantiate panel, button & listener, add listener to button,
        // add button to panel, return panel.
        JPanel buttonPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        beginInductionButton = new JButton("BEGIN INDUCTION");
        ButtonsActionListener buttonListener =
                new ButtonsActionListener(this);

        cancelButton.addActionListener(buttonListener);
        beginInductionButton.addActionListener(buttonListener);

        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(beginInductionButton);


        return buttonPanel;
    }

//    private JPanel createSideButtonPanel() {
//        // instantiate the buttons
////        this.addUserButton = new JButton("Add User");
//        this.displayInducteesButton = new JButton("Display Inductees");
////        this.BackEndButton = new JButton("Launch Backend");
////        this.takeQuizButton = new JButton("Take Quiz");
////        this.displayVideoButton = new JButton("Display Video");
//
//
//        // Instantiate the listener for the buttons,
//        // passing it in a reference to this class (TwitterFrame)
//        // and assign it to the buttons
//        ButtonsActionListener buttonListener = new ButtonsActionListener(this);
////        addUserButton.addActionListener(buttonListener);
//        displayInducteesButton.addActionListener(buttonListener);
////        takeQuizButton.addActionListener(buttonListener);
////        BackEndButton.addActionListener(buttonListener);
////        displayVideoButton.addActionListener(buttonListener);
//
//        // Create panel, assign layout, add components.
//        JPanel sideButtonPanel = new JPanel();
//        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS));
////        sideButtonPanel.add(addUserButton);
//        sideButtonPanel.add(Box.createVerticalStrut(50));
//        sideButtonPanel.add(displayInducteesButton);
////        sideButtonPanel.add(Box.createVerticalStrut(5));
////        sideButtonPanel.add(BackEndButton);
////        sideButtonPanel.add(Box.createVerticalStrut(20));
////        sideButtonPanel.add(displayVideoButton);
////        sideButtonPanel.add(Box.createVerticalStrut(5));
////        sideButtonPanel.add(takeQuizButton);
//
//
//        return sideButtonPanel;
//    }


    //Inner class implementation of ActionListener
    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. UserInputFrame)
        private UserInputFrame outerClass;

        public ButtonsActionListener(UserInputFrame outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //Listener for button clicks.
            JButton sourceButton = (JButton) e.getSource();

            // ------------------ADD USER BUTTON------------------
//            if (sourceButton.equals(addUserButton)) {
//                InductionSWController.getInstance().createInductee(tfName.getText(), tfEmail.getText(),
//                        tfJobTitle.getText(), tfPhoneNum.getText(), tfCarReg.getText(),
//                        tfCompetencies.getText(), System.currentTimeMillis());
//                System.out.println("user added");
//        }
        // ------------------CANCEL BUTTON------------------
        if (sourceButton.equals(cancelButton)) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MainDashBoardFrame();
                    }
                });
                dispose();


            }
            // ------------------BEGIN INDUCTION BUTTON------------------
            else if (sourceButton.equals(beginInductionButton)) {

                // Create the Inductee
                Inductee i = InductionSWController.getInstance().createInductee(tfName.getText(), tfPhoneNum.getText(), tfEmail.getText(),
                        tfJobTitle.getText(),tfCarReg.getText(), chkFirstAidTrained.isSelected(),
                        System.currentTimeMillis());
                InductionSWController.getInstance().setCurrentInductee(i);

                // Dispose the frame
            dispose();
                // Launch the Photo taking Dialogue
            PhotoTakeFrame ptf = new PhotoTakeFrame();
            ptf.setVisible(true);
//                ptf.setSize(640,480);
            ptf.setLocationRelativeTo(null);

            }
//            // ------------------TAKE QUIZ BUTTON------------------
//            else if (sourceButton.equals(takeQuizButton)) {
//                QuizFrame qf = new QuizFrame("Quiz");
//
//                qf.setSize(400, 300);
//            }
//            // ------------------Launch Backend BUTTON------------------
//            else if (sourceButton.equals(BackEndButton)) {
//                BackEndFrame bef = new BackEndFrame("Back End");
//                bef.setSize(400, 300);
//                bef.setVisible(true);
//            }
//            // ------------------DISPLAY VIDEO BUTTON------------------
//            else if (sourceButton.equals(displayVideoButton)) {
//                InductionSWController.getInstance().launchVideo();
//            }
            // ------------------DISPLAY INDUCTEES BUTTON------------------
            else {
//                ViewInducteesFrame dif = new ViewInducteesFrame("View Inductees");
//                dif.setSize(800, 300);
//
//                dif.setVisible(true);

            } // end else

        }// end actionperformed

    }
}
