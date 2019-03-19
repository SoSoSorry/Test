package lab1;

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
public class TestTriangle {
	Triangle t = null;
	private int input;//the number will be tested.
	private boolean result;//the result of triangle
	public TestTriangle(int input, boolean result) {
		this.input = input;
		this.result = result;
	}
	//a collection of parameters.the first parameter is  the number tested 
	//and the second parameter is excepted resulted
	@Parameterized.Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][]{
			{1, true},
			{3, true},
			{5, true},
			{6, true},
			{10,true},
			{11,true},
			{20, true},
			{21, true},
			{50, true},
			{51, true},
			{54, false}
		});
	}
	//Before testing, initialize the variable.
	@Before
	public void testBeforeTriangel() {
		t= new Triangle();
	}
	
	@Test
	public void testTriangle() {
		assertEquals(result, t.triangle(input));
	}

}
