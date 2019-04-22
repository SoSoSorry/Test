import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_function{
	private BackPack bp;
	private BubbleSort bs;
	@Before
	public void setUp() throws Exception{
		bp = new BackPack();
		bs = new BubbleSort();
	}

	@After
	public void tearDown() throws Exception{
		bp = null;
		bs = null;
	}

	@Test
	public void testBackPack(){
		int m = 10;
        int n = 3;
        int[] w = { 3, 4, 5 };
        int[] p = { 4, 5, 6 };
        int[][] c = bp.BackPack_Solution( m, n, w, p );
	}

	@Test
	public void testBubbleSort(){
		int arr[] = new int[]{1,6,2,2,5};
        bs.BubbleSort(arr);
	}
}