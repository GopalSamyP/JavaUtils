package com.gopal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public List<Map<Integer, Object>> readExcel(final String filePath) {

		List<Map<Integer, Object>> mapList = new ArrayList<Map<Integer, Object>>();
		try {

			FileInputStream inputStream = new FileInputStream(filePath);
			Workbook workbook = null;

			if (filePath.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (filePath.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(inputStream);
			}

			if (workbook != null) {

				int numberOfSheets = workbook.getNumberOfSheets();

				for (int sheet = 0; sheet < numberOfSheets; sheet++) {

					Sheet currentSheet = workbook.getSheetAt(sheet);

					Iterator<Row> rowIterator = currentSheet.iterator();

					while (rowIterator.hasNext()) {

						Row row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						Map<Integer, Object> dataMap = new HashMap<Integer, Object>();
						int cellnum = 0;
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								dataMap.put(cellnum, cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								dataMap.put(cellnum, cell.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								dataMap.put(cellnum, cell.getBooleanCellValue());
								break;
							default:
								break;
							}
							cellnum++;
						}

						mapList.add(dataMap);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapList;
	}

	public void writeExcel(final String filePath, final Set<String> headers, List<Map<Integer, Object>> dataMap) {

	}

}
