package com.gopal.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		final String filePath = "/Users/gopalsamy/Desktop/utterance-sr_final.xlsx";

		readExcelFile(filePath);
	}

	private static void readExcelFile(final String filePath) {

		ExcelUtil excelUtil = new ExcelUtil();
		final Map<String, Integer> resultMap = new HashMap<String, Integer>();
		final Map<String, Integer> failedData = new HashMap<String, Integer>();
		final List<Map<Integer, Object>> dataMap = excelUtil.readExcel(filePath);
		final List<Map<Integer, Object>> failedDataMap = new ArrayList<Map<Integer,Object>>();
		resultMap.put("TOTAL", dataMap.size() - 1);
		resultMap.put("PASS", 0);
		resultMap.put("FAIL", 0);
		failedDataMap.add(dataMap.get(0));
		for (int i = 1; i < dataMap.size(); i++) {

			Map<Integer, Object> map = dataMap.get(i);

			if (((String) map.get(2)).equalsIgnoreCase((String) map.get(3))
					|| (map.get(4) == null || ((String) map.get(4)).isEmpty())) {
				resultMap.put("PASS", resultMap.get("PASS") + 1);
			} else {
				resultMap.put("FAIL", resultMap.get("FAIL") + 1);
				failedDataMap.add(map);
			}
		}

		for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		writeExcel(failedDataMap);
		System.out.println("PASS Avg: " + (resultMap.get("PASS") * 100) / resultMap.get("TOTAL"));
		System.out.println("FAIL Avg: " + (resultMap.get("FAIL") * 100) / resultMap.get("TOTAL"));

	}

	private static void writeExcel(final List<Map<Integer, Object>> failedDataMap) {
		
		final String fileName = "/Users/gopalsamy/Desktop/misclassified.xlsx";
		Workbook workbook = null;

		try {
			if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook();
			} else if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook();
			} else {
				throw new Exception("invalid file name, should be xls or xlsx");
			}

			Sheet sheet = workbook.createSheet("missclassified");

			int rowIndex = 0;
			for (int i = 0; i < failedDataMap.size(); i++) {

				Map<Integer, Object> map = failedDataMap.get(i);
				Row row = sheet.createRow(rowIndex++);
				for (Map.Entry<Integer, Object> entry : map.entrySet()) {
					 Cell cell = row.createCell(entry.getKey());
					 cell.setCellValue(String.valueOf(entry.getValue()));
				}

			}

			// lets write the excel data to file now
			FileOutputStream fos = new FileOutputStream(fileName);
			workbook.write(fos);
			fos.close();
			System.out.println(fileName + " written successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
