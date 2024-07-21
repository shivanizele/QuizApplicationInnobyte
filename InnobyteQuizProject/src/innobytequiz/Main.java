package innobytequiz;

public class Main {
	public static void main(String s[]) {
		System.out.println("connection" + ConnectionProvider.getConnection());

		new Registration();

	}
}
