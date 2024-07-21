package innobytequiz;

import java.sql.*;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.corba.se.impl.naming.pcosnaming.NameServer;

public class AddQuiz extends JInternalFrame {
	NumberOfQuestions numberOfQuestions = new NumberOfQuestions();
	int i;
	int numberofQuestion = numberOfQuestions.getCount();

	JPanel panel = new JPanel();
	JLabel question = new JLabel("Question");
	JPanel[] quizes = new JPanel[10];
	JLabel[] questions = new JLabel[10];

	JLabel options1 = new JLabel("Option1");
	JLabel options2 = new JLabel("Option2");
	JLabel options3 = new JLabel("Option3");
	JLabel options4 = new JLabel("Option4");
	JLabel answer = new JLabel("Answer");
	JTextField tf_question = new JTextField();
	JTextField tf_option1 = new JTextField();
	JTextField tf_option2 = new JTextField();
	JTextField tf_option3 = new JTextField();
	JTextField tf_option4 = new JTextField();
	JTextField tf_answer = new JTextField();
	JSeparator sep[] = new JSeparator[10];
	JButton btn_add_question = new JButton("Add Question");
	String Quiz[] = new String[10];

	JComboBox cb_quiz;

	AddQuiz() {

		ArrayList<String> names = new ArrayList<String>();
		names.add("Low");
		names.add("Intermediate");
		names.add("Advance");
		AddQuizes();
		cb_quiz = new JComboBox(names.toArray());
		question.setBounds(10, 10, 80, 30);
		options1.setBounds(10, 40, 80, 30);
		options2.setBounds(10, 70, 80, 30);
		options3.setBounds(10, 110, 80, 30);
		options4.setBounds(10, 150, 80, 30);
		answer.setBounds(10, 190, 80, 30);
		tf_question.setBounds(100, 10, 200, 30);
		tf_option1.setBounds(100, 40, 80, 30);
		tf_option2.setBounds(100, 70, 80, 30);
		tf_option3.setBounds(100, 110, 80, 30);
		tf_option4.setBounds(100, 150, 80, 30);
		tf_answer.setBounds(100, 190, 80, 30);
		cb_quiz.setBounds(40, 230, 100, 40);
		btn_add_question.setBounds(40, 280, 80, 30);
		panel.add(question);
		panel.add(options1);
		panel.add(options2);
		panel.add(options3);
		panel.add(options4);
		panel.add(answer);
		panel.add(tf_question);
		panel.add(tf_option1);
		panel.add(tf_option2);
		panel.add(tf_option3);
		panel.add(tf_option4);
		panel.add(tf_answer);
		panel.add(btn_add_question);
		panel.add(cb_quiz);

		btn_add_question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddQuestion();
			}

		});
		panel.setLayout(null);

		add(panel);
		setSize(600, 600);

		setClosable(true);
		setVisible(true);
		setTitle("Add New Question");
		pack();
	}

	private void AddQuestion() {
		int x = 0;
		String Question = tf_question.getText();
		String Option1 = tf_option1.getText();
		String Option2 = tf_option2.getText();
		String Option3 = tf_option3.getText();
		String Option4 = tf_option4.getText();
		String Answer = tf_answer.getText();

		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into question (Name,Option1,Option2,Option3,Option4,Answer,QuizID) values(?,?,?,?,?,?,?)");
			ps.setString(1, Question);
			ps.setString(2, Option1);
			ps.setString(3, Option2);
			ps.setString(4, Option3);
			ps.setString(5, Option4);
			ps.setString(6, Answer);
			ps.setInt(7, 1);

			ps.executeUpdate();
			x++;
			if (x > 0) {
				JOptionPane.showMessageDialog(btn_add_question, "Question Saved Successfully");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void AddQuizes() {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from quiz");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Quiz[i] = rs.getString("Name");
				System.out.println(Quiz[i]);
				i++;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}