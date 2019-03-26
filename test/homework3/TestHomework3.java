package Label;

//public class TestHomework3 {
//
//}


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestHomework3 {
	Homework3 t = null;
	private int input;//the number will be tested.
	
	public TestHomework3(int input) {
		this.input = input;
		
	}
	//a collection of parameters.the first parameter is  the number tested 
	//and the second parameter is excepted resulted
	@Parameterized.Parameters
	public static Collection data() {
		return Arrays.asList(new Object[]{
			1,
			2,
			3,
			5,
			10,
		});
	}
	//Before testing, initialize the variable.
	@Before
	public void testBeforeTriangel() {
		t= new Homework3();
	}
	
	@Test
	public void testTriangle() {
		t.printPrimes(input);
	}

}
