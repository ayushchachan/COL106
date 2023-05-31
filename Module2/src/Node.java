import Includes.*;

public class Node{
	public Data data;
	/*
		Data has a String attribute, you can set the value of which 
		in the constructor or using some other function

		For more reference see the Data.java file
	*/
	public Node previous;
	public Node next;
	public String dgst;

	public Node(Data data, String dgst) {
		this.data = data;
		this.dgst = dgst;
	}

	public Node(Data data) {
		this(data, null);
	}
}
