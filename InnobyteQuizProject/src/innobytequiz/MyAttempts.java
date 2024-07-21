package innobytequiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class MyAttempts implements ActionListener {

	
	JFrame frame;
	JLabel myAttempt;
	JButton cancel;
	String username;
	JPanel panel;

	public MyAttempts(String uname) {
		this.username = uname;
		frame = new JFrame();
		cancel = new JButton("Cancel");
		panel = new JPanel();
		String[] header = { "Sr.No", "Quiz Id", "Marks" };
		String[][] array = {
		         { "1", "Steve", "AUS" },
		         { "2", "Virat", "IND" },
		         { "3", "Kane", "NZ" },
		         { "4", "David", "AUS" },
		         { "5", "Ben", "ENG" },
		         { "6", "Eion", "ENG" },
		      };
		JTable table = new JTable(array, header);
		table.setBounds(300, 300, 180, 100);
		panel.add(table);
		frame.add(panel);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setTitle("Test is Running");
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
