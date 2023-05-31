import Includes.*;

public class AuthStack{
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	private static final String start_string = "2018CS50402";
	private StackNode top;

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class. 	
	*/
	public static boolean CheckStack(AuthStack current, String proof) throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		return true;
	}

	public String push(Data datainsert, String proof)throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		return null;
	}

	public String pop(String proof)throws AuthenticationFailedException, EmptyStackException{
		/*
			Implement Code here
		*/
		return null;
	}

	public StackNode GetTop(String proof)throws AuthenticationFailedException{
		/*
			Implement Code here
		*/
		return null;
	}
}