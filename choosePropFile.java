package GeeBeeCleanHTML;

import javax.swing.filechooser.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class choosePropFile implements ActionListener {	
	public static final String PROP_FILE_FILTER_TITLE = "All Properties files (*.properties)";
	public static final String[] PROP_FILE_TYPE = new String[] { "properties" };
	public static JFileChooser propFileChooser;
	private static final String PROP_OPEN_BTN_INACTIVE_TEXT= "Parsing properties file...";
	private static final String PROP_OPEN_BTN_ACTIVE_TEXT="Open Properties File";
	public  static File f1;
	public JButton propopenBtn;
	public JPanel proppanel;
	public JFrame propframe;
	
	choosePropFile()
	{
		proppanel = new JPanel();
		propFileChooser = new JFileChooser();
		propFileChooser.setAcceptAllFileFilterUsed(false);
		propFileChooser.setFileFilter(new FileNameExtensionFilter(
				PROP_FILE_FILTER_TITLE, PROP_FILE_TYPE));
		propopenBtn = new JButton("Open Properties File");
		propopenBtn.addActionListener(this);
		proppanel.add(propopenBtn);
	                 propframe = new JFrame("Choose Properties File");
		propframe.getContentPane().add(proppanel,"Center");
	  	propframe.setSize(500, 90);
		propframe.setResizable (true);
		propframe.setVisible(true);
		propframe.setLocationRelativeTo(null);
		propframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		propframe.pack();
		
	}
	public void setpropOpenBtnEnabled(boolean isEnabled) {
		propopenBtn.setEnabled(isEnabled);
		propopenBtn.setText(isEnabled ? PROP_OPEN_BTN_ACTIVE_TEXT
				: PROP_OPEN_BTN_INACTIVE_TEXT);	
	}
	private void parseproperties() {
		this.setpropOpenBtnEnabled(false);
		f1 = propFileChooser.getSelectedFile();
		new Thread().start();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource()==propopenBtn)
		{
			int propVal = propFileChooser.showOpenDialog(propframe);
			if (propVal == JFileChooser.APPROVE_OPTION) {
				this.parseproperties();
			}
		}
	}
}
