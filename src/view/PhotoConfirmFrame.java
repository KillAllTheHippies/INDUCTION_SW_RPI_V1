package view;

import controller.InductionSWController;
import controller.interfaces.IGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class PhotoConfirmFrame extends JFrame implements IGui
{
    //The "scope" of this variable is the entire
    //class. So other methods and inner classes can see it.
//    private JButton displayScoreButton;
    private JButton showPhotoButton;
//    private JButton deleteButton;
    private JButton btnYes;
    private JButton btnNo;
    //    private JButton cancelButton;
//    private JButton saveButton;
//    private JTable inducteesTable;
//    private InducteesTableModel tableModel;
    private JLabel lblConfirm;

    public PhotoConfirmFrame(String title)
    {
        super(title);
        //This is what we are going to use as the
        //content of our JFrame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Call to method to create side panel
        JPanel sidePanel = createSideButtonPanel();
        mainPanel.add(sidePanel, BorderLayout.EAST);

        //Call to method to create bottom panel
        JPanel bottomPanel = createBottomButtonPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

//        //Call to method to create view image pane
        ImagePanel imagePanel = createImagePanel();
        mainPanel.add(imagePanel, BorderLayout.CENTER);
//        JScrollPane tableScrollPane = createTableScrollPane();
//        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private ImagePanel createImagePanel() {
        ImagePanel p = new ImagePanel(InductionSWController.getInstance().getCurrentInductee().getPhoto());
        return p;
    }
    public void refreshGUI()
    {

    }



    private JPanel createBottomButtonPanel()
    {
        lblConfirm = new JLabel("Is your face clearly visible in the photograph?");
        btnYes = new JButton("YES");
        btnNo = new JButton("NO");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(lblConfirm);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(btnYes);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(btnNo);
//
        btnYes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                InductionSWController.getInstance().launchVideo();
                dispose();
            }
        });
        btnNo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                PhotoTakeFrame ptf = new PhotoTakeFrame();
                ptf.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
                ptf.setVisible(true);
                dispose();
            }
        });


        return buttonPanel;
    }

    private JPanel createSideButtonPanel()
    {
        showPhotoButton = new JButton("Show Photo");

//
//        //Create an instance of inner class
//        //SideButtonsActionListener
//        //When we create an instance of the inner class we pass
//        //it a reference to its containing class.
        ButtonsActionListener buttonListener =
                new ButtonsActionListener(this);
//
//        displayScoreButton.addActionListener(buttonListener);
        showPhotoButton.addActionListener(buttonListener);
//        deleteButton.addActionListener(buttonListener);
//
        JPanel sideButtonPanel = new JPanel();
//
        BoxLayout boxL = new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS);
        sideButtonPanel.setLayout(boxL);
//
        sideButtonPanel.add(showPhotoButton);


        return sideButtonPanel;
    }

    public void showImage(BufferedImage img, String text) {
//        JFrame frame0 = new JFrame();
//
//        //frame0.getContentPane().add(new JPanelOpenCV(img));
//        // frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame0.setTitle(text);
//        frame0.setSize(img.getWidth(), img.getHeight() + 30);
//        frame0.setLocation(x, y);
//        frame0.setVisible(true);
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame(text);
        frame.setLayout(new FlowLayout());
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Inner class implementation of ActionListener
    private class ButtonsActionListener implements ActionListener
    {
        //This is to allow this inner class to refer to its
        //containing class (i.e. ViewInducteesFrame)
        private PhotoConfirmFrame outerClass;

        public ButtonsActionListener(PhotoConfirmFrame outerClass)
        {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e)
        {

            JButton sourceButton = (JButton)e.getSource();
            //          SHOW PHOTO BUTTON
            if(sourceButton.equals(showPhotoButton))
            {

                if (InductionSWController.getInstance().getCurrentInductee().getPhoto()!=null)
                {
                    showImage(
                            InductionSWController.getInstance().getCurrentInductee().getPhoto(),
                            InductionSWController.getInstance().getCurrentInductee().getName()
                    );
                } else {
                    JOptionPane.showMessageDialog
                            (outerClass,
                                    "No photo available.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                }


            }

            else
            {
//
                }
            }

        }
    }

