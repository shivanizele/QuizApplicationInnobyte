package innobytequiz;

import static innobytequiz.Test.i;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class ViewQuiz extends JInternalFrame {
	NumberOfQuestions numberOfQuestions = new NumberOfQuestions();
	public int numberofQuestion = numberOfQuestions.getCount();
	String Questions[][] = new String[numberofQuestion][5];
	String Answers[][] = new String[numberofQuestion][1];
	JLabel[] question, option1, option2, option3, option4;

	public ViewQuiz() {
		Test test = new Test();
		question = new JLabel[numberofQuestion];
		option1 = new JLabel[numberofQuestion];
		option2 = new JLabel[numberofQuestion];
		option3 = new JLabel[numberofQuestion];
		option4 = new JLabel[numberofQuestion];

		Connection con = ConnectionProvider.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement("select * from question");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.print("i " + i);
				Questions[i][0] = rs.getString(2);
				Questions[i][1] = rs.getString(3);
				Questions[i][2] = rs.getString(4);
				Questions[i][3] = rs.getString(5);
				Questions[i][4] = rs.getString(6);
				Answers[i][0] = rs.getString(7);
				i = i + 1;

			}

		} catch (Exception ex) {

			System.out.println(ex);

		}
		for (int i = 0; i < numberofQuestion; i++) {

			add(question[i] = new JLabel(Questions[i][0]));
			add(option1[i] = new JLabel(Questions[i][1]));
			add(option2[i] = new JLabel(Questions[i][2]));
			add(option3[i] = new JLabel(Questions[i][3]));
			add(option4[i] = new JLabel(Questions[i][4]));
		}
		setSize(500, 500);
		setLayout(new GridLayout(numberofQuestion, 1));
		setClosable(true);

		setVisible(true);
		setTitle("All Questions");
	}

	public static void main(String args[]) {
		new ViewQuiz();

	}

}