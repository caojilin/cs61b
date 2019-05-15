public class LeapYear {
	public static boolean isLeapYear(int year){
		/** check a year is a leap year or not 
			@source me
		*/
		if (year % 400 == 0) {
			return true;
		}else if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		int year =Integer.parseInt(args[0]);
		//Integer.parseInt
		if (isLeapYear(year)) {
			System.out.println( year + " is a leap year." );
		}else {
			System.out.println( year + " is not a leap year." );
		}
		}
}