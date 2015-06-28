package com.zline.zlogistics.biz.util;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * 整形数据样式
	 */
	private static HSSFCellStyle integerStyle = null;
	/**
	 * 双精度数据样式
	 */
	private static HSSFCellStyle doubleStyle = null;
	/**
	 * 百分比形式的数据样式
	 */
	private static HSSFCellStyle bigdecimalStyle = null;
	/**
	 * 长整形数据样式
	 */
	private static HSSFCellStyle longStyle = null;
	/**
	 * 浮点数据样式
	 */
	private static HSSFCellStyle floatStyle = null;

	/**
	 * 将HSSFWorkbook写进文件
	 * 
	 * @param wb
	 * @param fileName
	 */
	private static void writeWorkbook(HSSFWorkbook wb, String fileName)
			throws Exception {
		FileOutputStream fos = null;
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.flush();
		fos.close();
	}

	@SuppressWarnings("deprecation")
	public static String[] getSheelName(String fileName) throws Exception {
		Workbook wb = null;
		if (fileName.endsWith(".xlsx")) {
			wb = new XSSFWorkbook(fileName);
		} else {
			wb = new HSSFWorkbook(new FileInputStream(fileName));
		}
		String[] sheelNames = new String[wb.getNumberOfSheets()];
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheelNames[i] = wb.getSheetName(i);
		}
		return sheelNames;
	}

	public static Object[][] insertTableValue() throws Exception {
		// String[] sheetNames =
		// getSheelName("E:\\03-工作资料\\01-需求\\17-权限管理和采购单\\02-模块权限整理信息\\权限整理_丽丽.xls");
		Object[][] objs = readExcel("E:\\tmp.xls");
		for (Object[] obj : objs) {
			for (Object o : obj) {
				System.out.print(o);
			}
		}
		return objs;
	}

	/**
	 * 创建sheet
	 * 
	 * @param wb
	 * @param sheetName
	 * @return
	 */
	private static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		sheet.setGridsPrinted(true);
		sheet.setDisplayGridlines(true);
		return sheet;
	}

	/**
	 * 创建HSSFRow
	 * 
	 * @param sheet
	 * @param rownum
	 * @param height
	 * @return
	 */
	private static HSSFRow createRow(HSSFSheet sheet, int rownum, int height) {
		HSSFRow row = sheet.createRow(rownum);
		row.setHeightInPoints(height);
		return row;
	}

	/**
	 * 创建HSSCell
	 * 
	 * @param row
	 * @param cellnum
	 * @return
	 */
	private static HSSFCell createCell(HSSFRow row, int cellnum) {
		HSSFCell cell = row.createCell(cellnum);
		return cell;
	}

	private static void setCellVal(HSSFCell cell, Object obj, HSSFWorkbook wb) {
		/**
		 * 如果是数字类型的，按数值保存
		 */
		// = wb.createCellStyle();
		if (obj == null) {

		} else if (obj instanceof String) {
			cell.setCellValue(obj.toString());
		} else if (obj instanceof BigDecimal) {
			cell.setCellValue(Double.valueOf(obj.toString()));
			cell.setCellStyle(bigdecimalStyle);
		} else if (obj instanceof Double) {
			cell.setCellValue(Double.valueOf(obj.toString()));
			cell.setCellStyle(doubleStyle);
		} else if (obj instanceof Integer) {
			cell.setCellValue(Integer.valueOf(obj.toString()));
			cell.setCellStyle(integerStyle);
		} else if (obj instanceof Long) {
			cell.setCellValue(Long.valueOf(obj.toString()));
			cell.setCellStyle(longStyle);
		} else if (obj instanceof Float) {
			cell.setCellValue(Float.valueOf(obj.toString()));
			cell.setCellStyle(floatStyle);
		} else if (obj.toString().matches("^-?\\d*\\.?\\d*$")) {
			cell.setCellValue(Double.valueOf(obj.toString()));
		} else {
			cell.setCellValue(obj.toString());
		}
	}

	private static HSSFWorkbook createWorkBook() {
		HSSFWorkbook wb = new HSSFWorkbook();
		bigdecimalStyle = wb.createCellStyle();
		bigdecimalStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		floatStyle = wb.createCellStyle();
		floatStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		longStyle = wb.createCellStyle();
		longStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		integerStyle = wb.createCellStyle();
		integerStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("###0"));
		doubleStyle = wb.createCellStyle();
		doubleStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		return wb;
	}

	/**
	 * 创建excel
	 * 
	 * @param datas
	 *            数据
	 * @param headers
	 *            表头
	 * @param fileName
	 *            保存的文件名称带路径
	 * @param sheetName
	 *            sheet名称
	 * @param rowheight
	 *            row高度
	 * @return
	 */
	public static boolean createExcel(Object[][] datas, Object[] headers,
			String fileName, String sheetName, int rowheight) {
		try {
			// 新建工作薄
			HSSFWorkbook wb = createWorkBook();
			// 新建sheet
			HSSFSheet sheet = null;
			if (sheetName == null || sheetName.isEmpty()) {
				sheet = createSheet(wb, "Sheet1");
			} else {
				sheet = createSheet(wb, sheetName);
			}
			// 新建表头row
			HSSFRow row = null;
			if (rowheight == 0) {
				row = createRow(sheet, 0, 24);
			} else {
				row = createRow(sheet, 0, rowheight);
			}
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 表头设置居中
			style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//
			// style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//
			// 设置前景色
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//
			style.setFont(font);
			// 新建表头列
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = createCell(row, i);
				if (headers[i] != null) {
					cell.setCellValue(headers[i].toString());
					cell.setCellStyle(style);
				}
			}
			// 新建表内容行
			for (int i = 0; i < datas.length; i++) {
				HSSFRow dataRow = null;
				if (rowheight == 0) {
					dataRow = createRow(sheet, i + 1, 24);
				} else {
					dataRow = createRow(sheet, i + 1, rowheight);
				}
				for (int j = 0; j < datas[i].length; j++) {
					HSSFCell dataCell = createCell(dataRow, j);
					setCellVal(dataCell, datas[i][j], wb);
				}
			}
			// 保存文件
			writeWorkbook(wb, fileName);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 按默认的行高生成excel文件
	 * 
	 * @param datas
	 *            数据
	 * @param headers
	 *            excel头
	 * @param fileName
	 *            文件名称
	 * @param sheetName
	 *            sheet名称
	 * @return
	 */
	public static boolean createExcel(Object[][] datas, String[] headers,
			String fileName, String sheetName) {
		return createExcel(datas, headers, fileName, sheetName, 0);
	}

	/**
	 * 按默认的行高与sheet名称生成excel文件
	 * 
	 * @param datas
	 * @param headers
	 * @param fileName
	 * @return
	 */
	public static boolean createExcel(Object[][] datas, String[] headers,
			String fileName) {
		return createExcel(datas, headers, fileName, null, 0);
	}

	/**
	 * 按照对象，及要保存到xml里面的字段与中文描述来保存对应的xml文档
	 * 
	 * @param list
	 *            对象
	 * @param properties
	 *            要保存的属性
	 * @param headerDescs
	 *            文件头
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean createExcel(List list, List<String> properties,
			List<String> headerDescs, String fileName) {
		try {
			if (list == null || list.size() < 1 || fileName == null
					|| fileName.isEmpty()) {
				return false;
			} else {
				String[] headers = new String[headerDescs.size()];
				Class clazz = list.get(0).getClass();
				Object[][] objs = new Object[list.size()][properties.size()];
				for (int k = 0; k < properties.size(); k++) {
					headers[k] = headerDescs.get(k);
					PropertyDescriptor pd = new PropertyDescriptor(
							properties.get(k), clazz);
					Method m = pd.getReadMethod();
					for (int i = 0; i < list.size(); i++) {
						objs[i][k] = m.invoke(list.get(i));
					}
				}
				return createExcel(objs, headers, fileName);
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 从excel里面读取数据保存到数组里面
	 * 
	 * @param fileName
	 *            文件名称
	 * @param sheetName
	 *            sheet名称
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Object[][] readExcel(String fileName, String sheetName) {
		try {
			if (fileName.endsWith(".xlsx")) {
				XSSFWorkbook wb = new XSSFWorkbook(fileName);
				if (sheetName == null || sheetName.isEmpty()) {
					sheetName = "Sheet1";
				}
				XSSFSheet sheet = wb.getSheet(sheetName);
				XSSFRow row = null;
				XSSFCell cell = null;
				int length = sheet.getRow(sheet.getFirstRowNum())
						.getPhysicalNumberOfCells();
				Object[][] rows = new Object[sheet.getPhysicalNumberOfRows() - 1][length];
				for (int i = sheet.getFirstRowNum() + 1; i < sheet
						.getPhysicalNumberOfRows(); i++) {
					row = sheet.getRow(i);
					Object[] cells = new Object[length];
					for (int k = row.getFirstCellNum(); k < length; k++) {
						cell = row.getCell(k);
						if (cell != null) {
							if (HSSFCell.CELL_TYPE_NUMERIC == cell
									.getCellType()) {
								cells[k] = cell.getNumericCellValue();
							} else if (HSSFCell.CELL_TYPE_STRING == cell
									.getCellType()) {
								cells[k] = row.getCell(k).getStringCellValue();
							} else if (HSSFCell.CELL_TYPE_FORMULA == cell
									.getCellType()) {
								cells[k] = cell.getNumericCellValue();
							}
						}
					}
					rows[i - 1] = cells;
				}
				cell = null;
				row = null;
				sheet = null;
				wb = null;
				return rows;
			} else if (fileName.endsWith(".xls")) {
				HSSFWorkbook wb = new HSSFWorkbook(
						new FileInputStream(fileName));
				if (sheetName == null || sheetName.isEmpty()) {
					sheetName = "Sheet1";
				}
				HSSFSheet sheet = wb.getSheet(sheetName);
				HSSFRow row = null;
				HSSFCell cell = null;
				HSSFRow temp = sheet.getRow(sheet.getFirstRowNum());
				if (temp == null) {
					return null;
				}
				int length = sheet.getRow(sheet.getFirstRowNum())
						.getPhysicalNumberOfCells();
				Object[][] rows = new Object[sheet.getPhysicalNumberOfRows() - 1][length];
				for (int i = sheet.getFirstRowNum() + 1; i < sheet
						.getPhysicalNumberOfRows(); i++) {
					row = sheet.getRow(i);
					Object[] cells = new Object[length];
					if (row == null)
						continue;
					for (int k = row.getFirstCellNum(); k < length; k++) {
						cell = row.getCell(k);
						if (cell != null) {
							if (HSSFCell.CELL_TYPE_NUMERIC == cell
									.getCellType()) {
								cells[k] = cell.getNumericCellValue();
							} else if (HSSFCell.CELL_TYPE_STRING == cell
									.getCellType()) {
								cells[k] = row.getCell(k).getStringCellValue();
							}
						}
					}
					rows[i - 1] = cells;
				}
				cell = null;
				row = null;
				sheet = null;
				wb = null;
				return rows;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 从excel文件中读取数据返回
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static Object[][] readExcel(String fileName) {
		return readExcel(fileName, null);
	}

	/**
	 * 从excel文件中读取数据，并生成指定类的对象
	 * 
	 * @param fileName
	 *            文件名称
	 * @param sheetName
	 * @param properties
	 *            赋值的属性
	 * @param clazz
	 *            类
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readExcel(String fileName, String sheetName,
			List<String> properties, Class clazz) {
		try {
			Object[][] results = readExcel(fileName, sheetName);
			Object[] rs = new Object[properties.size()];
			List list = new ArrayList();
			for (int i = 0; i < results.length; i++) {
				list.add(clazz.newInstance());
			}
			for (int k = 0; k < rs.length; k++) {
				PropertyDescriptor pd = new PropertyDescriptor(
						properties.get(k), clazz);
				Method m = pd.getWriteMethod();
				for (int i = 0; i < results.length; i++) {
					if (pd.getPropertyType().getName().endsWith("lang.String")) {
						m.invoke(list.get(i), results[i][k].toString());
					} else if (pd.getPropertyType().getName()
							.endsWith("lang.Integer")) {
						String str = results[i][k].toString();
						if (str.indexOf(".") > 0) {
							str = str.substring(0, str.indexOf('.'));
						}
						m.invoke(list.get(i), Integer.valueOf(str));
					} else if (pd.getPropertyType().getName()
							.endsWith("lang.Double")) {
						m.invoke(list.get(i), (Double) results[i][k]);
					} else if (pd.getPropertyType().getName()
							.endsWith("BigDecimal")) {
						m.invoke(list.get(i),
								BigDecimal.valueOf((Double) results[i][k]));
					} else if (pd.getPropertyType().getName()
							.endsWith("lang.Long")) {
						String str = results[i][k].toString();
						if (str.indexOf(".") > 0) {
							str = str.substring(0, str.indexOf('.'));
						}
						m.invoke(list.get(i), Long.valueOf(str));
					}
				}
			}
			return list;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 针对对不固定的对象生成对应的excel文档，即对象中包含有类型为一维数组的情况
	 * 
	 * @param list
	 *            对象集合，生成的时候会默认把第一个对象看做表头。
	 * @param properties
	 *            要保存的属性
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean createExcel(List list, List<String> properties,
			String fileName) {
		/**
		 * 如果对象不存在，或者小于2个 返回false
		 */
		if (list == null || list.size() < 2) {
			return false;
		}
		try {
			/**
			 * 获取第1个对象，用来生成表头数据
			 */
			Object obj = list.get(0);
			Object[] headers = null;
			int k = 0;
			for (String property : properties) {
				PropertyDescriptor pd = new PropertyDescriptor(property,
						obj.getClass());
				Method m = pd.getReadMethod();
				String typeName = pd.getPropertyType().toString();
				if (typeName.indexOf("[L") != -1) {
					k++;
					Object[] objs = (Object[]) m.invoke(obj);
					if (objs == null)
						continue;
					int oldLength = headers == null ? 0 : headers.length;
					if (oldLength == 0) {
						headers = new Object[objs.length];
					} else {
						headers = Arrays
								.copyOf(headers, oldLength + properties.size()
										+ objs.length - k);
					}
					for (int i = 0; i < objs.length; i++) {
						headers[i + oldLength] = objs[i];
					}
				} else {
					Object o = m.invoke(obj);
					int oldLength = headers == null ? 0 : headers.length;
					if (oldLength == 0) {
						headers = new Object[1];
					} else {
						headers = Arrays.copyOf(headers, oldLength + 1);
					}
					headers[oldLength] = o;
				}
			}
			/**
			 * 生成数据对象
			 */
			Object[][] datas = new Object[list.size() - 1][headers.length];
			for (int i = 1; i < list.size(); i++) {
				Object data = list.get(i);
				int j = 0;
				for (String property : properties) {
					PropertyDescriptor pd = new PropertyDescriptor(property,
							data.getClass());
					Method m = pd.getReadMethod();
					String typeName = pd.getPropertyType().toString();
					if (typeName.indexOf("[L") != -1) {
						Object[] objs = (Object[]) m.invoke(data);
						if (objs == null)
							continue;
						for (Object o : objs) {
							datas[i - 1][j++] = o;
						}
					} else {
						Object o = m.invoke(data);
						datas[i - 1][j++] = o;
					}
				}
			}
			return createExcel(datas, headers, fileName, null, 0);
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 从excel文件中读取数据，并生成指定类的对象
	 * 
	 * @param fileName
	 *            文件名称
	 * @param properties
	 *            属性
	 * @param clazz
	 *            类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List readExcel(String fileName, List<String> properties,
			Class clazz) {
		return readExcel(fileName, null, properties, clazz);
	}

	public static Float floatTwo(Float f) {
		BigDecimal bd = new BigDecimal(f);
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	
	public static boolean createExcel(Object[][] datas, Object[] headers,
			String fileName, String sheetName, int rowheight,Object[] cellColors) {
		try {
			// 新建工作薄
			HSSFWorkbook wb = createWorkBook();
			// 新建sheet
			HSSFSheet sheet = null;
			if (sheetName == null || sheetName.isEmpty()) {
				sheet = createSheet(wb, "Sheet1");
			} else {
				sheet = createSheet(wb, sheetName);
			}
			// 新建表头row
			HSSFRow row = null;
			if (rowheight == 0) {
				row = createRow(sheet, 0, 24);
			} else {
				row = createRow(sheet, 0, rowheight);
			}
			HSSFCellStyle style;
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 新建表头列
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = createCell(row, i);
				if(headers[i] != null) {
					style = wb.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 表头设置居中
					style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
					style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					if(cellColors != null){
						style.setFillForegroundColor((Short) cellColors[i]);
					}else{
						style.setFillForegroundColor(HSSFColor.YELLOW.index);
					}
					style.setFont(font);
					
					cell.setCellValue(headers[i].toString());
					cell.setCellStyle(style);
				}
			}
			// 新建表内容行
			for (int i = 0; i < datas.length; i++) {
				HSSFRow dataRow = null;
				if (rowheight == 0) {
					dataRow = createRow(sheet, i + 1, 24);
				} else {
					dataRow = createRow(sheet, i + 1, rowheight);
				}
				for (int j = 0; j < datas[i].length; j++) {
					HSSFCell dataCell = createCell(dataRow, j);
					setCellVal(dataCell, datas[i][j], wb);
				}
			}
			// 保存文件
			writeWorkbook(wb, fileName);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
