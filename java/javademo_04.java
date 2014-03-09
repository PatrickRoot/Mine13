public class javademo_04 {

	public static void main(String[] args) {

		for (int x = 1; x <= 9; x++) {

			for (int y = 1; y <= x; y++) {
				System.out.print(y + "*" + x + "=" + y * x + "\t");
			}
			System.out.println();
		}

	}
}