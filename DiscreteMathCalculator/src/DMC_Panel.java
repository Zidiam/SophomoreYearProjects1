/*
 * DMC_Panel -- The main GUI that holds all the other panels
 * Jason Melnik
 * 9/19/2019
 * Version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DMC_Panel extends JPanel {
	private JComponent panel1, panel2, panel3;
	public static JTabbedPane tabbedPane = new JTabbedPane();
	private ImageIcon icon;
	private JLabel author, version;
	private double versions = 1.0;
    public DMC_Panel() {
    	setPreferredSize(new Dimension(750, 505));
    	setLayout(null);
    	tabbedPane.setBounds(5, 0, 750, 500);
        //tabbedPane.setLayout(new GridLayout(1, 1));
        icon = new ImageIcon();
         
        setupTabPanel1();
        setupTabPanel2();
        setupTabPanel3();
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        setBackground(Color.ORANGE);
        tabbedPane.setBackground(Color.CYAN);
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        author = new JLabel("Made by Jason Melnik");
        version = new JLabel("Version: " + versions);
        
        author.setBounds(625, 495, 200, 25);
        version.setBounds(675, 0, 200, 25);
        
        add(version);
        add(author);
    }
    
    public void setupTabPanel1() {
    	panel1 = new PermutationsPanel();
  
        tabbedPane.addTab("Permutations", icon, panel1, "Calculates Permutations");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    }
    
    public void setupTabPanel2() {
    	panel2 = new BitsPanel();
        tabbedPane.addTab("Bit Strings", icon, panel2, "Calculates Bit Strings");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    }
    
    public void setupTabPanel3() {
    	panel3 = new rComboPanel();
        panel3.setPreferredSize(new Dimension(750, 500));
        tabbedPane.addTab("R Combinations", icon, panel3, "Calculates R combinations");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }
     
}