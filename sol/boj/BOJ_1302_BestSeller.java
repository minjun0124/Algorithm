package Algorithm.sol.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BOJ_1302_BestSeller {

	static int N;
	static Map<String, Integer> soldBook;

	public static void main(String[] args) throws IOException {
		initParam();
		sol();
	}

	private static void sol() {
		List<Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(soldBook.entrySet());

		Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o2.getValue() == o1.getValue()) {
					return o1.getKey().compareTo(o2.getKey());
				}
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		System.out.println(entries.get(0).getKey());
	}

	private static void initParam() throws IOException {
		soldBook = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		String book;
		for (int i = 0; i < N; i++) {
			book = br.readLine().trim();
			if (soldBook.containsKey(book)) {
				soldBook.put(book, soldBook.get(book)+1);
			} else {
				soldBook.put(book, 1);
			}
		}
	}
}
