package be.dno.test;

public class Day11 {

	
	private static char[] currentGeneratedPassword;
	private static int currentlyAddingCell;
	private static int lastupdatedprefix;
	private static boolean passwordOK = false;
	
	public static void main(String[] args) {
		currentGeneratedPassword = "aabbrxyz".toCharArray();
		System.out.println(checkPassword(true));
		//findNextPassword("hepxcrrq");
	}
	
	
	
	public static String findNextPassword(String password){
		System.out.println(password);
		currentGeneratedPassword = password.toCharArray();
		currentlyAddingCell = currentGeneratedPassword.length-1;
		lastupdatedprefix = currentlyAddingCell -1;
		increment();
		while (!passwordOK){
			increment();
		}
		String out = new String(currentGeneratedPassword);
		System.out.println("\t"+out);
		return out;
	}
	
	public static void increment(){
		currentGeneratedPassword[currentlyAddingCell]++;
		char a = currentGeneratedPassword[currentlyAddingCell];
		if (a == 'i' || a == 'o' || a == 'l') currentGeneratedPassword[currentlyAddingCell]++;
		if (currentGeneratedPassword[currentlyAddingCell] > 'z'){
			currentGeneratedPassword[currentlyAddingCell] = 'a';
			nextStep();
			currentlyAddingCell = currentGeneratedPassword.length-1;
		}
		passwordOK = checkPassword(false);
		if (passwordOK) checkPassword(true);
	}
	
	public static void nextStep(){
		boolean ok = false;
		while (!ok){
			currentGeneratedPassword[lastupdatedprefix]++;
			char a = currentGeneratedPassword[lastupdatedprefix];
			if (a == 'i' || a == 'o' || a == 'l') currentGeneratedPassword[lastupdatedprefix]++;
			if (currentGeneratedPassword[lastupdatedprefix] > 'z'){
				currentGeneratedPassword[lastupdatedprefix] = 'a';
				lastupdatedprefix--;
			}else{
				ok = true;
				lastupdatedprefix = currentlyAddingCell -1;
			}
		}
	}
	
	/**
	 * 	Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
		Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
		Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(boolean debug){
		
		String password = new String(currentGeneratedPassword);
		//System.out.println(password);
		if (password.contains("i") || password.contains("o") || password.contains("l")){
			return false;
		}
		char[] workingChars = password.toCharArray();
		//check two pairs in a row
		int increasingCpt = 0;
		for(int i = 0; i < workingChars.length; i++){
			if (i+1 >= workingChars.length) continue; //end of string
			char a = workingChars[i];
			char b = workingChars[i+1];
			if (a == ' ' || b == ' ') continue;
			if (a == b){
				if (debug){
					System.out.println("Found pair ["+a+","+b+"] in pos ["+i+","+(i+1)+"]");
				}
				increasingCpt++;
				workingChars[i]=' ';
				workingChars[i+1]=' ';
				i++;
			}
		}
		boolean increasing = false;
		//check for increasing 3
		for(int i = 0; i < workingChars.length; i++){
			if (i+2 >= workingChars.length) continue; //end of string
			char a = workingChars[i];
			char b = workingChars[i+1];
			char c = workingChars[i+2];
			if (a == ' ' || b == ' ' || c == ' ') continue;
			if (a == b-1 && a == c-2){
				if (debug){
					System.out.println("Found increasing ["+a+","+b+","+c+"] in pos ["+i+","+(i+1)+","+(i+2)+"]");
				}
				increasing=true;
				break;
			}
		}
		return increasing && increasingCpt >= 2;
	}

}
