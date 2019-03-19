package lab1;

public class Triangle {
	public boolean triangle(int n) {
		if(n == 50)
			return true;
		else if(n > 50)
			n = n - 50;
		if(n == 20)
			return true;
		else if(n > 20)
			n = n - 20;
		if(n == 10)
			return true;
		else if(n > 10)
			n = n - 10;
		else if(n == 5)
			return true;
		else if(n > 5)
			n = n - 5;
		if(n <= 3)
			return true;
		else
			return false;
	}
	
//	public static void main(String a[]) {
//		Triangle t = new Triangle();
//		if(t.triangle(500) == true)
//			System.out.println("true");
//		else
//			System.out.println("False");
//			
//	}
}
