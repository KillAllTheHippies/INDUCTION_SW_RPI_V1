package view;

import controller.InductionSWController;
import controller.interfaces.IGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamie on 07/04/16.
 */
public class PhotoTakeFrame extends JFrame implements IGui{

    public PhotoTakeFrame() throws HeadlessException {
        /* DEFAULT CONSTRUCTOR */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        // add to the layout
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        this.setUndecorated(true);
        this.add(mainPanel);
//        this.setSize(300,300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0,1));
        JButton beginInductionBtn = new JButton("Please look directly into the camera and click here when you are ready to be photographed.");
//        beginInductionBtn.setBorder(new EmptyBorder(100,100,100,100));
        beginInductionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // take the photo
                try {
                    InductionSWController.getInstance().getCurrentInductee().setPhoto(InductionSWController.getInstance().takePicture()
                    );
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (NullPointerException e2) {
                    System.out.println("No Current Inductee!!!");
                }

                PhotoConfirmFrame pcf = new PhotoConfirmFrame("PHOTOGRAPH QUALITY CONFIRMATION");
                pcf.setVisible(true);
                pcf.setExtendedState(MAXIMIZED_BOTH);
                pcf.setLocationRelativeTo(null);
                dispose();
            }
        });
//                UserInputFrame uif = new UserInputFrame("Photograph");
//                uif.setVisible(true);
//                uif.setSize(640,480);
//                uif.setLocationRelativeTo(null);
//                dispose(); // Hide the Dashboard
////                setVisible(false); // hide the dashboard
//            }
//        });


        centerPanel.add(beginInductionBtn);


        return centerPanel;
    }

    @Override
    public void refreshGUI() {

    }
}
