
import java.util.*;
class practice{
	
	public static void main (String args[]){
		String str = "I love my india";
		// second longest lengthy word need to get via streams;
		
		System.out.println(str);		
		String ans = Arrays.stream(str.split(" ")).sorted(Comparator.comparingInt(String::length).reversed()).skip(1).findFirst().orElse(null);
		System.out.println(ans);
		
	}
}