import Includes.*;

import java.lang.constant.Constable;
import java.util.*;

public class MerkleTree{
	
	// Check the TreeNode.java file for more details
	public TreeNode rootnode;
	private int two_pow_d;
	private static CRF obj = new CRF(64);
	public int numdocs;

	public String[] data;

	public String Build(String[] documents){
		// Implement Code here
		int d = (int) (Math.log(documents.length)/Math.log(2));
		two_pow_d = (int) (Math.pow(2, d));
		data = new String[2*two_pow_d - 1];
		numdocs = documents.length;

		for (int i = 0; i < numdocs; i++) {
			data[two_pow_d - 1 + i] = documents[i];
		}

		for (int i = two_pow_d - 2; i >= 0; i--) {
			int l = left(i);
			int r = right(i);
			data[i] = obj.Fn(data[l] + "#" + data[r]);
		}
		return data[0];
	}

	/* 
		Pair is a generic data structure defined in the Pair.java file
		It has two attributes - First and Second, that can be set either manually or
		using the constructor

		Edit: The constructor is added
	*/
		
	public List<Pair<String,String>> QueryDocument(int doc_idx){
		// Implement Code here
		ArrayList<Pair<String, String>> answer = new ArrayList<>();

		int i = two_pow_d - 1 + doc_idx;		// index in the heap

		while (i != 0) {
			Pair<String, String> p = siblingPair(i);
			answer.add(p);
			i = parent(i);
		}
		answer.add(new Pair<String, String>(data[i], null));
		return answer;
	}

	private Pair<String, String> siblingPair(int i) {
		if (i % 2 == 0) {
			return new Pair<>(data[i - 1], data[i]);
		} else {
			return new Pair<>(data[i], data[i + 1]);
		}
	}

	public static boolean Authenticate(List<Pair<String,String>> path, String summary){
		// Implement Code here
		Pair<String, String> x = null;

		for (Pair<String, String> y : path) {
			if (x == null) {
				x = y;
				continue;
			}
			String val = obj.Fn(x.First + "#" + x.Second);
			if (val.equals(y.First) || val.equals(y.Second)) {
				x = y;
			} else {
				return false;
			}
		}
		// x == root
		if (!x.First.equals(summary)) return false;
		return true;
	}

	public String UpdateDocument(int doc_idx, String new_document){		
		// Implement Code here
		int i = two_pow_d - 2 + doc_idx;		// index in the heap

		data[i] = new_document;
		while (i > 0) {
			Pair<String, String> p = siblingPair(i);
			data[parent(i)] = obj.Fn(p.First + "#" + p.Second);
			i = parent(i);
		}
		return data[0];
	}

	private int left(int i) {
		return 2*i + 1;
	}

	private int right(int i) {
		return 2*i + 2;
	}

	private int parent(int j) {
		return (j - 1)/2;
	}
}