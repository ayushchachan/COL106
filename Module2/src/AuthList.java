import Includes.*;

import java.util.LinkedList;

public class AuthList{
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	public static final String start_string = "2016BB50005";
	public Node firstNode;
	public Node lastNode;

	private CRF obj = new CRF(64);

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static boolean CheckList(AuthList current, String proof) throws AuthenticationFailedException {
		CRF obj = new CRF(64);
		Node curr = current.firstNode;
		boolean initial = true;
		while(curr != null){
			if(initial){
				String hsh = obj.Fn(AuthList.start_string + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh)) {
					throw new AuthenticationFailedException();
				}
				initial = false;
				curr = curr.next;
			}else if(curr == current.lastNode){
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh) || !curr.dgst.equals(proof)) {
					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}else{
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh))  {
					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return this.firstNode == null;
	}


	public String InsertNode(Data datainsert, String proof) throws AuthenticationFailedException {
		/*
			Implement Code here
		*/
		CheckList(this, proof);

		String dgst;
		Node newest;
		if (lastNode != null) {
			dgst = obj.Fn(lastNode.dgst + "#" + datainsert.value);
			newest = new Node(datainsert, dgst);
			newest.previous = lastNode;
			lastNode.next = newest;
			lastNode = newest;
		} else {

			dgst = obj.Fn(start_string + "#" + datainsert.value);
			newest = new Node(datainsert, dgst);
			firstNode = lastNode = newest;
		}
		return dgst;
	}

	public String DeleteFirst(String proof) throws AuthenticationFailedException, EmptyListException {
		/*
			Implement Code here
		*/
		CheckList(this, proof);
		if (firstNode == null) throw new EmptyListException();

		Node x = firstNode;
		firstNode = firstNode.next;

		if (firstNode == null) {
			lastNode = null;
			return null;
		}

		firstNode.previous = null;
		x.next = null;
		x = firstNode;

		String dgst = start_string;

		while (x != null) {
			x.dgst = obj.Fn(dgst + "#" + x.data.value);
			dgst = x.dgst;
			x = x.next;
		}
		return lastNode.dgst;
	}


	public String DeleteLast(String proof) throws AuthenticationFailedException, EmptyListException {
		/*
			Implement Code here
		*/
		CheckList(this, proof);
		if (firstNode == null) throw new EmptyListException();

		Node x = lastNode;
		lastNode = lastNode.previous;

		if (lastNode == null) {
			firstNode = null;
			return null;
		}
		lastNode.next = null;
		x.previous = null;

		return lastNode.dgst;
	}

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static Node RetrieveNode(AuthList current, String proof, Data data) throws AuthenticationFailedException, DocumentNotFoundException{
		/*
			Implement Code here
		*/
		CheckList(current, proof);

		Node x = current.firstNode;

		while (x != null) {
			if (x.data.equals(data)) return x;
			x = x.next;
		}
		throw new DocumentNotFoundException();
	}

	public void AttackList(AuthList current, String new_data)throws EmptyListException{
		/*
			Implement Code here
		*/
	}

	@Override
	public String toString() {
		LinkedList<String> L = new LinkedList<>();

		Node x = firstNode;
		while (x != null) {
			L.add(x.data.value);
			x = x.next;

		}
		return L.toString();
	}
}
