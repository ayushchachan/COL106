import Includes.*;

public class AuthStack extends AuthList {
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	private static final String start_string = "2016BB50005";

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/
	public AuthStack() {

	}
	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class. 	
	*/
	public static boolean CheckStack(AuthStack current, String proof) throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		return AuthList.CheckList(current, proof);
	}

	public String push(Data datainsert, String proof)throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		return this.InsertNode(datainsert, proof);
	}

	public String pop(String proof) throws AuthenticationFailedException, EmptyStackException, EmptyListException {
		/*
			Implement Code here
		*/
		if (this.isEmpty()) throw new EmptyStackException();
		return this.DeleteLast(proof);
	}

	public StackNode GetTop(String proof)throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		AuthList.CheckList(this, proof);
		if (isEmpty()) return null;
		Node top = this.lastNode;
		return new StackNode(top.data, top.dgst);

	}
}