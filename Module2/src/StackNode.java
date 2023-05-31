import Includes.*;

public class StackNode extends Node{
	public Data data;
	
	/*
		Data has a String attribute, you can set the value of which 
		in the constructor or using some other function

		For more reference see the Data.java files
	*/

	public String dgst;

	public StackNode(Data data, String dgst) {
		this.data = data;
		this.dgst = dgst;
	}
}