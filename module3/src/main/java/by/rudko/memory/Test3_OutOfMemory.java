package by.rudko.memory;

import java.util.LinkedList;
import java.util.Queue;

public class Test3_OutOfMemory {

	private static final int SIZE = 1000;

	public static void main(String[] args) {
		Queue<Object> queue = new LinkedList<Object>();
		while (true) {
			queue.add(new long[SIZE]);
		}
	}
	
}
