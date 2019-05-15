public class HelloNumbers {
    public static void main(String[] args) {
        for (int i = 0; i <=9 ;i++ ) {
        	int x = 0;
        	for (int j=0;j<=i ;j++ ) {
        		x = x + j;
        	}
        	System.out.print(x + " ");
        }
        System.out.println();
    }
}