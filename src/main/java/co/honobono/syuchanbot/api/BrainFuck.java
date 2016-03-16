package co.honobono.syuchanbot.api;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by syuchan on 2016/03/13.
 */
public class BrainFuck {
	private static final int MEM_SIZE = 30000;

	public static void main(String[] args) {
		String s = new String(Base64.getEncoder().encode("突撃！".getBytes()));
		for(int i = 0; i < 3; i++) s = new String(Base64.getEncoder().encode(("base64_decode " + s).getBytes()));
		System.out.println(s);
	}

	public static String run(String code) {
		StringBuilder sb = new StringBuilder();
		char[] mem = new char[MEM_SIZE];
		int ptr = 0;

		for (int pc = 0; pc < code.length(); pc++) {
			switch (code.charAt(pc)) {
				case '>':
					ptr++;
					if (MEM_SIZE <= ptr) {
						return "over flow";
					}
					break;

				case '<':
					ptr--;
					if (ptr < 0) {
						return "under flow";
					}
					break;

				case '+':
					mem[ptr]++;
					break;

				case '-':
					mem[ptr]--;
					break;

				case '.':
					sb.append(mem[ptr]);
					break;

				case ',':
					try {
						mem[ptr] = (char) System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case '[':
					if (mem[ptr] == 0) {
						int nonPairCount = 0;
						out:
						for (++pc; pc < code.length(); pc++) {
							switch (code.charAt(pc)) {
								case ']':
									if (nonPairCount == 0)
										break out;
									else
										nonPairCount--;
									break;
								case '[':
									nonPairCount++;
									break;
							}
						}
						if (pc == code.length()) {
							return "']' not found";
						}
						pc++;
					}
					break;

				case ']':
					if (mem[ptr] != 0) {
						int nonPairCount = 0;
						out:
						for (--pc; 0 <= pc; pc--) {
							switch (code.charAt(pc)) {
								case '[':
									if (nonPairCount == 0)
										break out;
									else
										nonPairCount--;
									break;
								case ']':
									nonPairCount++;
									break;
							}
						}
						if (pc < 0) {
							return "'[' not found";
						}
						pc--;
					}
					break;
			}
		}
		return sb.toString();
	}
}
