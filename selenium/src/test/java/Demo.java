import org.testng.annotations.Test;

public class Demo {

	@Test(dependsOnMethods= {"Test2"})
	public void test1() {
		System.out.println("i am inside test 1");  
	}
	@Test(groups= {"sanity"})
	public void test2() {
		System.out.println("i am inside test 1");
	}
	@Test(priority =2)
	public void test3() {
		System.out.println("i am inside test 1");
	}
		
}
