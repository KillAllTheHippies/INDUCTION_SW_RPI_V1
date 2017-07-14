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
//        //This "wakes up" the table model and tells it
//        //that the data model it is connected to has changed.
//        this.tableModel =
//                new InducteesTableModel(InductionSWController.getInstance().getPersistor().read());
//        inducteesTable.setModel(tableModel);
//        this.tableModel.fireTableDataChanged();
    }

//    private JScrollPane createTableScrollPane()
//    {
//        inducteesTable = new JTable();
//
//        //In future weeks we'll have a call to the
//        //controller here to get us the list of players which
//        //it manages as data model objects
//        tableModel =
//                new InducteesTableModel(InductionSWController.getInstance().getPersistor().read());
//
//        inducteesTable.setModel(tableModel);
//
//        JScrollPane tableScrollPane = new JScrollPane(inducteesTable);
//        //Can also be done this way
//        //JScrollPane tableScrollPane = new JScrollPane();
//        //tableScrollPane.add(inducteesTable)
//        return tableScrollPane;
//    }

    private JPanel createBottomButtonPanel()
    {
        lblConfirm = new JLabel("Is your face clearly visible in the photograph?");
        btnYes = new JButton("YES");
        btnNo = new JButton("NO");
//        cancelButton = new JButton("Cancel");
//        saveButton = new JButton("Save");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(lblConfirm);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(btnYes);
        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(btnNo);
//        buttonPanel.add(cancelButton);
//        buttonPanel.add(saveButton);

//        saveButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e)
//            {
//                InductionSWController.getInstance().save();
//                JOptionPane.showMessageDialog(ViewInducteesFrame.this, "Inductees saved");
//            }
//        });
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
//        displayScoreButton = new JButton("Display Score");
        showPhotoButton = new JButton("Show Photo");
//        deleteButton = new JButton("Delete");
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
//// Can also be written like this in one line.
////		sideButtonPanel.setLayout(
////		new BoxLayout(sideButtonPanel, BoxLayout.X_AXIS));
//        sideButtonPanel.add(displayScoreButton);
//        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(showPhotoButton);
//        sideButtonPanel.add(Box.createVerticalStrut(5));
//        sideButtonPanel.add(deleteButton);

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
            //We know that the source of any ActionEvent
            //in this program MUST be a JButton seeing as
            //we only added an instance of this listener to
            //JButtons
            JButton sourceButton = (JButton)e.getSource();
            //          SHOW PHOTO BUTTON
            if(sourceButton.equals(showPhotoButton))
            {
                //Check if row is selected
//                if(inducteesTable.getSelectedRow() == -1)
//                {
//                    JOptionPane.showMessageDialog
//                            (outerClass,
//                                    "You need to select a row in the table",
//                                    "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                else
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
            //              DELETE BUTTON
            else
            {
//                //Check if row is selected
//                if(inducteesTable.getSelectedRow() == -1)
//                {
//                    JOptionPane.showMessageDialog
//                            (outerClass,
//                                    "You need to select a row in the table",
//                                    "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                else
//                {
//                    String message = "WARNING: Are you sure you want to delete this inductee ? \nTHIS OPERATION IS UNRECOVERABLE!!";
//                    int answer =
//                            JOptionPane.showConfirmDialog(outerClass, message);
//                    if(answer == JOptionPane.YES_OPTION)
//                    {
//                        String fileName = InductionSWController.getInstance().getPersistor().read().get(inducteesTable.getSelectedRow()).getUUID();
//                        boolean success = (new File
//                                ("Inductees/" + fileName)).delete();
//                        if (success) {
//                            System.out.println("The file has been successfully deleted");
//                            JOptionPane.showMessageDialog
//                                    (outerClass,
//                                            "File deleted",
//                                            "Success!", JOptionPane.INFORMATION_MESSAGE);
//
//                            InductionSWController.getInstance().setGuiReference(outerClass);
//                            InductionSWController.getInstance().getGuiReference().refreshGUI();
//                        }
//
////                        ArrayList<Inductee> inductees = InductionSWController.getInstance().getDataModel().getInductees();
////                        inductees.remove(inducteesTable.getSelectedRow());
////                        InductionSWController.getInstance().getDataModel().setInductees(inductees);
////                        InductionSWController.getInstance().save();
////                        refreshGUI();
//                    }
//                    else if (answer == JOptionPane.NO_OPTION)
//                    {
//
//                    }
//                    else
//                    {
//
//                    }
                }
            }

        }
    }

