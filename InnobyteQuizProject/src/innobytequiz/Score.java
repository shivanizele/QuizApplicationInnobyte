package innobytequiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Score extends JFrame implements ActionListener {

	Score(String username, int score) {
		setBounds(600, 150, 750, 550);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		PreparedStatement ps = null;
		long userId = 0;
		try {
			System.out.println("Updating score");
			Connection con = ConnectionProvider.getConnection();
			ps = con.prepareStatement("select * from user where username = ?");
			ps.setString(1, username);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getLong(1);
			}
			System.out.println(userId);
			if (userId > 0) {
				PreparedStatement ps1 = con
						.prepareStatement("insert into quizrecord (quizid,userid,marks) values(?,?,?)");
				ps1.setLong(1, 0);
				ps1.setLong(2, userId);
				ps1.setString(3, score + "");
				ps1.executeUpdate();
				System.out.println("Score updated successfully...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JLabel l2 = new JLabel("Thank you " + username + " for Attempting Quiz");
		l2.setBounds(45, 30, 700, 30);
		l2.setFont(new Font("RALEWAY", Font.PLAIN, 26));
		add(l2);

		JLabel l3 = new JLabel("Your Score is " + score);
		l3.setBounds(350, 200, 300, 30);
		l3.setFont(new Font("Jokerman", Font.PLAIN, 26));
		l3.setForeground(new Color(199, 21, 133));
		add(l3);

		JButton b1 = new JButton("Play Again");
		b1.setBackground(Color.BLUE);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);

		b1.setBounds(400, 270, 120, 30);
		add(b1);
	}

	public void actionPerformed(ActionEvent ae) {
		this.setVisible(false);
		new Test("User");
	}

	public static void main(String[] args) {
		new Score("", 0).setVisible(true);
	}
}
