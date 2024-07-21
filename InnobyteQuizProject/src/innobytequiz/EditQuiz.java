package innobytequiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class EditQuiz extends JInternalFrame {
	NumberOfQuestions numberOfQuestions = new NumberOfQuestions();
	int numberofQuestion = numberOfQuestions.getCount();

	JPanel panel = new JPanel();
	JLabel question = new JLabel("Question");
	JLabel questionID = new JLabel("Question ID : ");
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
	JTextField tf_questionID = new JTextField();
	JButton btn_edit_question = new JButton("Update Question");
	JButton btn_search_question = new JButton("Search");
	JButton btn_delete_question = new JButton("Delete");

	EditQuiz() {
		questionID.setBounds(10, 10, 80, 30);
		btn_search_question.setBounds(200, 10, 80, 30);
		question.setBounds(10, 60, 80, 30);
		options1.setBounds(10, 100, 80, 30);
		options2.setBounds(10, 140, 80, 30);
		options3.setBounds(10, 180, 80, 30);
		options4.setBounds(10, 220, 80, 30);
		answer.setBounds(10, 260, 80, 30);

		tf_questionID.setBounds(100, 10, 80, 30);
		tf_question.setBounds(100, 60, 80, 30);
		tf_option1.setBounds(100, 100, 80, 30);
		tf_option2.setBounds(100, 140, 80, 30);
		tf_option3.setBounds(100, 180, 80, 30);
		tf_option4.setBounds(100, 220, 80, 30);
		tf_answer.setBounds(100, 260, 80, 30);
		btn_edit_question.setBounds(40, 300, 80, 30);
		btn_delete_question.setBounds(140, 300, 80, 30);
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
		panel.add(btn_edit_question);
		panel.add(btn_search_question);
		panel.add(btn_delete_question);
		panel.add(questionID);
		panel.add(tf_questionID);

		btn_search_question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditQuestion();
			}

		});
		btn_edit_question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateQuestion();
			}

		});
		btn_delete_question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteQuestion();
			}

		});
		panel.setLayout(null);

		add(panel);
		setSize(300, 300);
		setClosable(true);

		setVisible(true);
		setTitle("Update Questions");
	}

	private void EditQuestion() {
		int x = 0;
		String QuestionID = tf_questionID.getText();
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from question where QuestionID = ?");
			ps.setString(1, QuestionID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tf_question.setText(rs.getString(2));
				tf_option1.setText(rs.getString(3));
				tf_option2.setText(rs.getString(4));
				tf_option3.setText(rs.getString(5));
				tf_option4.setText(rs.getString(6));
				tf_answer.setText(rs.getString(7));

			} else {

				JOptionPane.showMessageDialog(null, "Question Id is wrong");

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private void UpdateQuestion() {
		int x = 0;
		String Question = tf_question.getText();
		String option1 = tf_option1.getText();
		String option2 = tf_option2.getText();
		String option3 = tf_option3.getText();
		String option4 = tf_option4.getText();
		String answer = tf_answer.getText();

		Connection con = ConnectionProvider.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE question SET Name = '" + Question + "',Option1 = '"
					+ option1 + "',Option2 = '" + option2 + "',Option3 = '" + option3 + "',Option4 = '" + option4
					+ "',Answer = '" + answer + "' WHERE QuestionID =" + tf_questionID.getText());

			ps.executeUpdate();
			x++;
			if (x > 0) {
				JOptionPane.showMessageDialog(btn_edit_question, "Question Upadate Successfully");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void DeleteQuestion() {
		int x = 0;
		String QuestionID = tf_questionID.getText();
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("Delete from question where QuestionID =" + tf_questionID.getText());

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Deleted Successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}

		throw new UnsupportedOperationException("Not supported yet.");

	}

	public static void main(String s[]) {

		new EditQuiz();

	}
}
