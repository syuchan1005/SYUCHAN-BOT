package co.honobono.syuchanbot.api;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by syuchan on 2016/03/12.
 */
public class RPN {

	public static int calc(String input) {
		String[] stringArray = input.split("\\s");
		Deque<Integer> que = new ArrayDeque<>();
		int a, b;
		for (int i = 0; i < stringArray.length; i++) {
			switch (stringArray[i]) {
				case "+":
					a = que.pollFirst();
					b = que.pollFirst();
					que.addFirst(b + a);
					break;
				case "-":
					a = que.pollFirst();
					b = que.pollFirst();
					que.addFirst(b - a);
					break;
				case "/":
					a = que.pollFirst();
					b = que.pollFirst();
					que.addFirst(b / a);
					break;
				case "*":
					a = que.pollFirst();
					b = que.pollFirst();
					que.addFirst(b * a);
					break;
				case "%":
					a = que.pollFirst();
					b = que.pollFirst();
					que.addFirst(b % a);
					break;
				default:
					que.addFirst(Integer.parseInt(stringArray[i]));
			}
		}
		return que.pop();
	}
}
