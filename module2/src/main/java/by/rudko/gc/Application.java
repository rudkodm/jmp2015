package by.rudko.gc;

public class Application {

	public static void main(String[] args) throws Exception{
		
		while(true) {
			System.out.println(new Bean().getId());
			Thread.sleep(1);
		}
	}
}


class Bean {
	private static int counter = 0;
	
	@SuppressWarnings("unused")
	private int[] size = new int[1000];
	
	private int id = 0;
	
	public int getId() {
		return id;
	}

	public Bean() {
		this.id = counter++;
	}
	
}