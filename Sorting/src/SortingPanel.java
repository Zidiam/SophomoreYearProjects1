/*
 * SortingPanel.java -- The main GUI that holds all the other panels
 * Jason Melnik
 * 9/19/2019
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SortingPanel extends JPanel {
	private JComponent panel1, panel2, panel3, panel4, panel5,panel6;
	public static JTabbedPane tabbedPane = new JTabbedPane();
	private ImageIcon icon;
	private JLabel author, version, update;
	private double versions = 2.0;
    public SortingPanel() {
    	setPreferredSize(new Dimension(1000, 605));
    	setLayout(null);
    	tabbedPane.setBounds(5, 0, 1000, 600);
        //tabbedPane.setLayout(new GridLayout(1, 1));
        icon = new ImageIcon();
         
        setupTabPanel1();
        setupTabPanel2();
        setupTabPanel3();
        setupTabPanel4();
        setupTabPanel5();
        setupTabPanel6();
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        setBackground(Color.ORANGE);
        tabbedPane.setBackground(Color.CYAN);
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        author = new JLabel("Made by Jason Melnik");
        version = new JLabel("Version: " + versions);
        update = new JLabel("Next update: Adding more Color");
        
        author.setBounds(875, 595, 200, 25);
        version.setBounds(925, 0, 200, 25);
        update.setBounds(0, 595, 800, 25);
        
        add(update);
        add(version);
        add(author);
    }
    
    public void setupTabPanel1() {
    	panel1 = new InsertionSortPanel();
  
        tabbedPane.addTab("Insertion", icon, panel1, "Sorts an array of elements using Insertion Sort");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    }
    
    public void setupTabPanel2() {
    	panel2 = new QuickSortPanel();
        tabbedPane.addTab("Quick", icon, panel2, "Sorts an array of elements using Quick Sort");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    }
    
    public void setupTabPanel3() {
    	panel3 = new BubbleSortPanel();
        tabbedPane.addTab("Bubble", icon, panel3, "Sorts an array of elements using Bubble Sort");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }
    
    public void setupTabPanel4() {
    	panel4 = new MergeSortPanel();
        tabbedPane.addTab("Merge", icon, panel4, "Sorts an array of elements using Merge Sort");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
    }
    
    public void setupTabPanel5() {
    	panel5 = new SelectionSortPanel();
        tabbedPane.addTab("Selection", icon, panel5, "Sorts an array of elements using Selection Sort");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
    }
    
    public void setupTabPanel6() {
    	panel6 = new AllSortingPanel();
        panel6.setPreferredSize(new Dimension(750, 500));
        tabbedPane.addTab("All Sorts", icon, panel6, "Testing all Arrays at once");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
    }
     
}