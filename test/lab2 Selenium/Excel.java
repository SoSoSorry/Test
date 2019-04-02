package Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
public class Excel{
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	//判断excel的版本，获取workbook
	public static Workbook getWorkbok(InputStream in, File file) throws IOException{
		Workbook wb = null;
		if(file.getName().endsWith(EXCEL_XLS)) {
			wb = new HSSFWorkbook(in);
			
		}else if(file.getName().endsWith(EXCEL_XLSX)) {
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
	
	//判断文件是否是excel
	public static void checkExcelVaild(File file) throws Exception{
		if(!file.exists()) {
			throw new Exception("不存在");
		}
		if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
			throw new Exception("文件不是excel");
		}
	}

	public static void check_excel(String filepath) throws Exception{
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
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

					String order = Excel.getValue(row.getCell(0)).toString();
					String id =  Excel.getValue(row.getCell(1)).toString();
					String name = (String) Excel.getValue(row.getCell(2));
					String git = "";
					if(row.getCell(3) != null)
						git= (String) Excel.getValue(row.getCell(3));
					System.out.println("order:" + order  + " id:" + id + " name:" + name + " git:" + git);
					//wrong_info[wrong_count] = order;
					//wrong_count++;
					//check(order,id, name, git); //对比 excel中的数据与web中的数据。
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String args[]) throws Exception {
//		check_excel("E:\\课内学习\\上课用ppt\\大三下\\软件测试\\第二次lab\\软件测试名单.xlsx");
//	}
	//获取总列数（空格不计）
//	int columnTotalNum = row.getPhysicalNumberOfCells();
////	System.out.println("总列数：" + columnTotalNum);
////  
////  System.out.println("最大列数：" + row.getLastCellNum());
//  int end = row.getLastCellNum();
//  for(int i = 1; i < end; i++) {
//  	Cell cell = row.getCell(i);
//  	if(cell == null) {
//  		//System.out.print("null" + "\t");
//  		continue;
//  	}
//  	
//  	Object obj = getValue(cell);
//  	//System.out.print(obj + "\t");
//  }
	
	@SuppressWarnings("deprecation")
	public static Object getValue(Cell cell) {
    	Object obj = null;
    	switch (cell.getCellTypeEnum()) {
	        case BOOLEAN:
	            obj = cell.getBooleanCellValue(); 
	            break;
	        case ERROR:
	            obj = cell.getErrorCellValue(); 
	            break;
	        case NUMERIC:
	        	DecimalFormat df = new DecimalFormat("0");
				obj = df.format(cell.getNumericCellValue());
				break;
	            
				
	            
	        case STRING:
	            obj = cell.getStringCellValue(); 
	            break;
	        default:
	            break;
    	}
    	return obj;
    }

}
//public class Excel {
//	//*************xlsx文件读取函数************************
//		//excel_name为文件名，arg为需要查询的列号
//		//返回二维字符串数组
//		@SuppressWarnings({ "resource", "unused" })
//		public ArrayList<ArrayList<String>> xlsx_reader(String excel_url,int ... args) throws IOException {
//	 
//	        //读取xlsx文件
//	        XSSFWorkbook xssfWorkbook = null;
//	        //寻找目录读取文件
//	        File excelFile = new File(excel_url); 
//	        InputStream is = new FileInputStream(excelFile);
//	        xssfWorkbook = new XSSFWorkbook(is);
//	      
//	        if(xssfWorkbook==null){
//	        	System.out.println("未读取到内容,请检查路径！");
//	        	return null;
//	        }
//	        
//	        ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
//	        //遍历xlsx中的sheet
//	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
//	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
//	            if (xssfSheet == null) {
//	                continue;
//	            }
//	            // 对于每个sheet，读取其中的每一行
//	            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//	                if (xssfRow == null) continue; 
//	                ArrayList<String> curarr=new ArrayList<String>();
//	                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
//	                	XSSFCell cell = xssfRow.getCell(args[columnNum]);
//	                	
//	                	curarr.add( Trim_str( getValue(cell) ) );
//	                }
//	                ans.add(curarr);
//	            }
//	        }
//	        return ans;
//	    }
//
//		//判断后缀为xlsx的excel文件的数据类
//	    @SuppressWarnings("deprecation")
//		private static String getValue(XSSFCell xssfRow) {
//	    	if(xssfRow==null){
//	    		return "---";
//	    	}
//	        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
//	            return String.valueOf(xssfRow.getBooleanCellValue());
//	        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
//	        	double cur=xssfRow.getNumericCellValue();
//	        	long longVal = Math.round(cur);
//	        	Object inputValue = null;
//	        	if(Double.parseDouble(longVal + ".0") == cur)  
//	        	        inputValue = longVal;  
//	        	else  
//	        	        inputValue = cur; 
//	            return String.valueOf(inputValue);
//	        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK || xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR){
//	        	return "---";
//	        }
//	        else {
//	            return String.valueOf(xssfRow.getStringCellValue());
//	        }
//	    }
//
//	  //判断后缀为xls的excel文件的数据类型
//	    @SuppressWarnings("deprecation")
//		private static String getValue(HSSFCell hssfCell) {
//	    	if(hssfCell==null){
//	    		return "---";
//	    	}
//	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//	            return String.valueOf(hssfCell.getBooleanCellValue());
//	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
//	        	double cur=hssfCell.getNumericCellValue();
//	        	long longVal = Math.round(cur);
//	        	Object inputValue = null;
//	        	if(Double.parseDouble(longVal + ".0") == cur)  
//	        	        inputValue = longVal;  
//	        	else  
//	        	        inputValue = cur; 
//	            return String.valueOf(inputValue);
//	        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK || hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR){
//	        	return "---";
//	        }
//	        else {
//	            return String.valueOf(hssfCell.getStringCellValue());
//	        }
//	    }
//
//	  //字符串修剪  去除所有空白符号 ， 问号 ， 中文空格
//	    static private String Trim_str(String str){
//	        if(str==null)
//	            return null;
//	        return str.replaceAll("[\\s\\?]", "").replace("　", "");
//	    }
//	
//}
