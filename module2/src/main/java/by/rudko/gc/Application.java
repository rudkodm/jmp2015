package by.rudko.gc;

public class Application {

	public static void main(String[] args) throws Exception{
		int i = 0;
		while(true) {
			System.out.println(i++);
			Thread.sleep(1000);
		}
	}
}
