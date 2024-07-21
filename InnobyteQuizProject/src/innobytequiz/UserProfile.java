package innobytequiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class UserProfile {

	JFrame f;
	JPanel panel, top_panel;
	JLabel l = new JLabel();
	JPanel myAttempt_panel;
	JPanel front;
	JButton myAttempt;

	UserProfile(String username) {
		f = new JFrame();
		panel = new JPanel();
		top_panel = new JPanel();
		myAttempt_panel = new JPanel();
		front = new JPanel();
		l.setText("Welcome to Quiz World , " + username);
		Font font = new Font("Arial", Font.BOLD, 24);
		l.setFont(font);
		JLabel l2 = new JLabel();
		l2.setText("My Attempts");
		l2.setFont(font);
		JButton b1 = new JButton("NORTH");

		JButton b5 = new JButton("Take Quiz");
		b5.setFont(new Font("Arial", Font.BOLD, 16));

		b5.setBackground(Color.BLACK);

		b5.setForeground(Color.WHITE);

		b5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		myAttempt = new JButton("My Attempts");
		b5.setBounds(650, 150, 150, 30);
		myAttempt.setBounds(300, 300, 150, 30);
		l.setBounds(530, 50, 400, 110);
		l2.setBounds(600, 250, 200, 30);
		panel.add(b5);
		panel.add(l);
		panel.add(l2);
		top_panel.add(b1);
		panel.setLayout(null);
		top_panel.setLayout(null);
		front.setLayout(null);
		myAttempt_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "My Attempts",
				TitledBorder.CENTER, TitledBorder.TOP));
		myAttempt_panel.setLayout(null);
		f.add(top_panel, BorderLayout.NORTH);
		f.add(panel, BorderLayout.CENTER);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Test(username);
				f.setVisible(false);
			}

		});
		myAttempt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == myAttempt) {
					JInternalFrame ff = new JInternalFrame();
					ff.add(myAttempt_panel);
					ff.setSize(600, 800);
					ff.setTitle("My Attempts");
					ff.setClosable(true);
					panel.add(ff);
					ff.setVisible(true);
				}
			}

		});
		try {
			PreparedStatement ps = null;
			long userId = 0;
			Connection con = ConnectionProvider.getConnection();
			ps = con.prepareStatement("select * from user where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getLong(1);
			}
			if (userId > 0) {
				System.out.println("getting Score");
				PreparedStatement ps1 = con.prepareStatement("select * from quizrecord where userid = ?");
				ps1.setLong(1, userId);
				ResultSet rs1 = ps1.executeQuery();
				List<List<String>> dynamicRec = new ArrayList<>();
				dynamicRec.add(Arrays.asList("Sr, No", "User Id", "Score"));
				while (rs1.next()) {
					dynamicRec.add(Arrays.asList(rs1.getLong(1) + "", rs1.getLong(3) + "", rs1.getString(4)));
				}
				int rows = dynamicRec.size();
				int cols = dynamicRec.get(0).size();
				String[][] array = new String[rows][cols];

				for (int i = 0; i < rows; i++) {
					List<String> row = dynamicRec.get(i);
					for (int j = 0; j < cols; j++) {
						array[i][j] = row.get(j);
						System.out.println(row.get(j));
					}
				}
				String header[] = { "ID", "QuizId", "Score" };
				JTable table = new JTable(array, header);
				table.setFont(new Font("Serif", Font.PLAIN, 16));
				table.setRowHeight(30);
				table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "",
						TitledBorder.CENTER, TitledBorder.TOP));
				//
				table.setBounds(550, 300, 300, 100);
				panel.add(table);
				System.out.println(array.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setTitle("Welcome " + username);
		f.setSize(600, 800);
		f.setVisible(true);
	}
}
