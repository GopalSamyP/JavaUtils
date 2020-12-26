package com.gopal.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		final String filePath = "/Users/gopalsamy/Downloads/automation/out/{YOUR_FILE_NAME}";

		readExcelFile(filePath);
	}

	private static void readExcelFile(final String filePath) {

		ExcelUtil excelUtil = new ExcelUtil();
		final Map<String, Integer> resultMap = new HashMap<String, Integer>();
		final List<Map<Integer, Object>> dataMap = excelUtil.readExcel(filePath);

		resultMap.put("TOTAL", dataMap.size() - 1);
		resultMap.put("PASS", 0);
		resultMap.put("FAIL", 0);

		for (int i = 1; i < dataMap.size(); i++) {

			Map<Integer, Object> map = dataMap.get(i);

			if (((String) map.get(2)).equalsIgnoreCase((String) map.get(3))) {
				resultMap.put("PASS", resultMap.get("PASS") + 1);
			} else {
				resultMap.put("FAIL", resultMap.get("FAIL") + 1);
			}
		}

		for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		System.out.println("PASS Avg: " + (resultMap.get("PASS") * 100) / resultMap.get("TOTAL"));
		System.out.println("FAIL Avg: " + (resultMap.get("FAIL") * 100) / resultMap.get("TOTAL"));

	}
}
