package innobytequiz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Collections.copy;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuizApplication implements ActionListener {

	JFrame f;
	JPanel p, p1;
	CardLayout card;
	JDesktopPane desktop;
	Container c;
	JButton addQuiz = new JButton("Add Question");;
	JButton updateQuiz = new JButton("Update Or Remove Question");;
	JButton viewQuiz = new JButton("View Questions");;
	JButton exit = new JButton("Exit");;
	JButton logout = new JButton("Logout");;

	QuizApplication() {
		f = new JFrame();
		p = new JPanel();
		p1 = new JPanel();

		p.add(addQuiz);
		p.add(updateQuiz);
		p.add(viewQuiz);
		p.add(exit);
		p.add(logout);
		p.setLayout(new GridLayout(5, 1));
		p1.setLayout(null);
		f.add(p, BorderLayout.LINE_START);
		addQuiz.addActionListener(this);
		updateQuiz.addActionListener(this);
		viewQuiz.addActionListener(this);
		exit.addActionListener(this);
		logout.addActionListener(this);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);

		f.setSize(300, 300);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addQuiz) {
			Thread runner = new Thread() {

				public void run() {
					AddQuiz ot = new AddQuiz();
					f.add(ot, BorderLayout.CENTER);

				}
			};
			runner.start();

		}
		if (e.getSource() == updateQuiz) {
			Thread runner = new Thread() {

				public void run() {
					EditQuiz ot = new EditQuiz();
					f.add(ot, BorderLayout.CENTER);

				}
			};
			runner.start();
		}
		if (e.getSource() == viewQuiz) {
			ViewQuiz vq = new ViewQuiz();
			f.add(vq, BorderLayout.CENTER);

		}
		if (e.getSource() == exit) {
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(false);

		}
		if (e.getSource() == logout) {
			f.setVisible(false);
			new Registration();

		}
	}

}
