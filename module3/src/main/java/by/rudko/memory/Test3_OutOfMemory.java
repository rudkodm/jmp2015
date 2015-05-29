package by.rudko.memory;

import java.util.LinkedList;
import java.util.Queue;

public class Test3_OutOfMemory {

	public static void main(String[] args) {
		Queue<Object> queue = new LinkedList<Object>();
		while (true) {
			queue.add(new long[1000]);
		}
	}
	
}
