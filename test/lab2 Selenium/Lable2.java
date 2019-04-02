package Label;
//package cn.tjucic.selenium;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import Label.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Lable2 {
private WebDriver driver;
private String baseUrl;
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();
public String[] wrong_info = new String[200];
public int wrong_count = 0;
@Before
public void setUp() throws Exception {
	  //String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
	  String driverPath = System.getProperty("user.dir") + "/src/Label/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	  baseUrl = "http://121.193.130.195:8800/login";
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

public void check(String order,String id, String name, String git) {
	  String password = id.substring(4, 10);
	  driver.findElement(By.name("id")).clear();
	  driver.findElement(By.name("id")).sendKeys(id);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.id("btn_login")).click();
	  //get the information on the website
	  String web_id = driver.findElement(By.id("student-id")).getText();
	  String web_name = driver.findElement(By.id("student-name")).getText();
	  String web_git = driver.findElement(By.id("student-git")).getText().toString();

	  if(!id.equals(web_id)) {
		  System.out.print("the order:" + order+ "  ");
		  wrong_info[wrong_count] = order;
		  wrong_count++;
		  System.out.println("the id " + id + " is different from " + "the web_id " + web_id +".");
	  }  
	  else if(!name.equals(web_name)) {
		  System.out.print("the order:" + order+ "  ");
		  wrong_info[wrong_count] = order;
		  wrong_count++;
		  System.out.println("the name " + name + " is different from " + "the web_name " + web_name + ".");
	  }	  
	  else if(!git.equals(web_git)) {
		  System.out.print("the order:" + order+ "  ");
		  wrong_info[wrong_count] = order;
		  wrong_count++;
		  System.out.println("the git " + git + " is different from " + "the web_git " + web_git + ".");
	  }
		   
	  
}
//check 函数
	public void check_excel(String filepath) throws Exception{
		try {
			//File excelFile = new File("E:\\课内学习\\上课用ppt\\大三下\\软件测试\\第二次lab\\软件测试名单.xlsx");//创建文件对象
			File excelFile = new File(filepath);
			FileInputStream in = new FileInputStream(excelFile);// 文件流
			
			Excel.checkExcelVaild(excelFile);//检查是否为excel文件
			Workbook workbook = Excel.getWorkbok(in, excelFile); //获得workbook
			
			int sheetCount = workbook.getNumberOfSheets();//sheet 的数量
			Sheet sheet = workbook.getSheetAt(0);//遍历第一个sheet
			
			//
			int count = 0;
			for(Row row : sheet) {
				try{
					if(count < 2) {
						count++;
						continue;
					}//跳过第一、二行的目录
					
					if(row.getCell(0).toString().equals("")) {
						return;
					}

					String order = (String) Excel.getValue(row.getCell(0));
					String id = (String) Excel.getValue(row.getCell(1));
					String name = (String) Excel.getValue(row.getCell(2));
					String git = new String();
					Cell cell = row.getCell(3);
					git = (String)Excel.getValue(cell);
					System.out.println(cell);
					if(git == null) {
						git = "";
					}	
					 
						
					System.out.println("order:" + order + " id:" + id + " name:" + name + " git:" + git);
					//wrong_info[wrong_count] = order;
					//wrong_count++;
					check(order,id, name, git); //对比 excel中的数据与web中的数据。
					driver.findElement(By.id("btn_logout")).click();
					driver.findElement(By.id("btn_return")).click();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

//@Test
//public void testBaidu() throws Exception {
//  driver.get(baseUrl + "/");
// // WebElement we = driver.findElement(By.id("kw"));
// // we.click();
////  driver.findElement(By.id("kw")).click();
//  String order = "1";
//  String id = "3016218055";
//  String name = "霍欣芷";
////  String id = "3014218120";
////  String name = "李明";
//  String git = "";
//  check(order, id, name, git);
//}
@Test
public void testCheck_excel() throws Exception{
	driver.get(baseUrl + "/");
	String filepath = "E:\\课内学习\\上课用ppt\\大三下\\软件测试\\第二次lab\\软件测试名单.xlsx";
	System.out.println("excute the function");
	check_excel(filepath);
	System.out.println("excute end");
	for(int i = 0; i < wrong_count; i++) {
		System.out.println("the order of information is  " + wrong_info[i]);
	}
}
@After
public void tearDown() throws Exception {
//  driver.quit();
//  String verificationErrorString = verificationErrors.toString();
//  if (!"".equals(verificationErrorString)) {
//    fail(verificationErrorString);
//  }
}

private boolean isElementPresent(By by) {
  try {
    driver.findElement(by);
    return true;
  } catch (NoSuchElementException e) {
    return false;
  }
}

private boolean isAlertPresent() {
  try {
    driver.switchTo().alert();
    return true;
  } catch (NoAlertPresentException e) {
    return false;
  }
}

private String closeAlertAndGetItsText() {
  try {
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    if (acceptNextAlert) {
      alert.accept();
    } else {
      alert.dismiss();
    }
    return alertText;
  } finally {
    acceptNextAlert = true;
  }
}
}


//package Label;
//
//public class TestBaidu {
//
//}

//public class Lable2 {
//
//}
