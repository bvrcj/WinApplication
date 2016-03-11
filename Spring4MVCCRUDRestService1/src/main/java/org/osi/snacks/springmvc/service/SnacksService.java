package org.osi.snacks.springmvc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osi.snacks.springmvc.model.TodaySnacks;

public class SnacksService {

	private FileInputStream file;
	
	public String campus_1;
	
	public String campus_2;
	
	

	public TodaySnacks getSnacks(String campus, String day) {
		day = extractDate(day);
		readProperties();
		TodaySnacks snacks = new TodaySnacks();

		try {
			System.out.println("campus  >>>>>>>>>>>>"+campus);
			snacks.setCampus("Campus-" + campus);
			
			if (campus.equals("1")) {
				//file = new FileInputStream(new File("E://campus1.xlsx"));
				System.out.println("campus_1  >>>>>>>>>>>>"+campus_1);
				file = new FileInputStream(new File(campus_1));
				System.out.println(">>>>>>>>>>>>>campus1<<<<<<<<<");
			} else {
				//file = new FileInputStream(new File("E://campus2.xlsx"));
				System.out.println("campus_2  >>>>>>>>>>>>"+campus_2);
				file = new FileInputStream(new File(campus_2));
				System.out.println(">>>>>>>>>>>>>campus2<<<<<<<<<");
			}
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// System.out.print(cell.getDateCellValue());

					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						String date = cell.getStringCellValue();
						// if(date.equalsIgnoreCase("1-2-2016 - Monday")){
						// 1-2-2016 - Monday
						// System.out.println("date in cell is : "+date);
						if (date.trim().equalsIgnoreCase(day)) {
							Row pickRow = cell.getRow();
							System.out.println("Monday is here >>>>>>>>>> "
									+ pickRow.getCell(1).getStringCellValue());
							snacks.setSnacksName(pickRow.getCell(1)
									.getStringCellValue());
							snacks.setDate(day);
						}else{
							System.out.println("DATE ARE NOT MATCHING: EXCEL DATE>>"+date+"<<  day recieved >>"+day+"<<");
						}
						break;
					}
				}
				System.out.println("");
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return snacks;
	}

	private String extractDate(String day) {

		System.out.println("DATE IS : " + day);

		String dayresult[] = day.split("-");

		String monthIs = extractMe(dayresult[0]);
		System.out.println("MONTH IS : " + monthIs);

		String dayIs = extractMe(dayresult[1]);
		System.out.println("DAY IS : " + dayIs);

		String yearIs = dayresult[2];
		System.out.println("YEAR IS : " + yearIs);

		day = monthIs + "-" + dayIs + "-" + yearIs + "-" + dayresult[3];
		// 1-2-2016 - Monday
		System.out.println("Formatted date is  >>      " + day);

		return day;

	}

	private String extractMe(String string) {
		if (string.startsWith("0")) {
			return "" + string.charAt(1);
		} else {
			return string;
		}
	}
	
	
	public void readProperties(){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String propFileName = "config.properties";
 
			input = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (input != null) {
				prop.load(input);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property value and print it out
			campus_1 = prop.getProperty("campus_one_snacks_path");
			
			System.out.println("campus_1"+campus_1);
			
			campus_2 = prop.getProperty("campus_two_snacks_path");
			
			System.out.println("campus_2"+campus_2);
			

		} catch (IOException ex) {
			System.out.println("::SnacksService : IOException "+ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("::SnacksService : IOException "+e);
				}
			}
		}
	}
	
	
	

}
