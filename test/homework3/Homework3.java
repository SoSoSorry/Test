package Label;

public class Homework3 {
	static int MAXPRIMES = 100;
	public static void printPrimes(int n) {
		int curPrime;  //目前考虑的素数的值，
		int numPrimes; //目前找到的素数数目,
		boolean isPrime; //是素数吗
		int [] primes = new int[MAXPRIMES]; //素数列表
		
		//将素数列表的第一个元素初始化为2
		primes[0] = 2;
		numPrimes = 1;
		curPrime = 2;
		while(numPrimes < n) {
			curPrime++; //下面一个要考虑的数
			isPrime = true;
			for(int i = 0; i <= numPrimes-1; i++) {
				//对于之前的每一个素数
				if(isDivisible(primes[i], curPrime)) {
					//找到一个约数，curPrime不是素数
					isPrime = false;
					break;//退出素数循环
				}
			}
			if(isPrime) {
				//保存
				primes[numPrimes] = curPrime;
				numPrimes++;
			}
		}//end up while
		
		//print all of the primes.
		for(int i = 0; i <= numPrimes-1; i++) {
			System.out.println("Prime:" + primes[i]);
		}
	}//end up the function printPrimes
	
	private static boolean isDivisible(int num1, int num2) {
		if(num2 % num1 == 0)
			return true;
		else 
			return false;
	}
}
