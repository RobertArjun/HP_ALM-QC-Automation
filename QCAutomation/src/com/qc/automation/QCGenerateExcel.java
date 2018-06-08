package com.qc.automation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QCGenerateExcel {

	/**
	 * @param args
	 */
	public XSSFWorkbook workbook;
	public XSSFSheet spreadsheet;
	public XSSFRow row;
	public XSSFFont font;
	private static final Logger logger = Logger
			.getLogger(QCGenerateExcel.class);

	public void createExcel(List<QCBug> defects, Properties prop)
			throws IOException {
		String headers = prop.getProperty("header");
		List headerList = Arrays.asList(headers.trim().split(","));
		// Create Blank Workbook
		workbook = new XSSFWorkbook();
		logger.debug("Calling Create Excel");
		font = workbook.createFont();
		spreadsheet = workbook.createSheet("QC-Defects");

		XSSFCellStyle headerStyle = getHeaderStyle();
		XSSFCellStyle normalStyle = getNormalStyle();
		int initialrow = 0;
		row = spreadsheet.createRow(initialrow);
		for (int colno = 0; colno < headerList.size(); colno++) {
			Cell cell = row.createCell(colno);

			if (initialrow == 0) {
				createHeader(cell, headerList.get(colno).toString());
				cell.setCellStyle(headerStyle);
			}
		}

		for (int rownum = 0; rownum < defects.size(); rownum++) {
			row = spreadsheet.createRow(rownum + 1);
			for (int colno = 0; colno < headerList.size(); colno++) {
				Cell cell = row.createCell(colno);
				createBody(defects, colno, cell, headerList, rownum);
				cell.setCellStyle(normalStyle);
			}

		}
		/*
		 * spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		 */
		autoResizeColumns(headerList.size());
		File file = new File(prop.getProperty("file.location").toString());

		if (!file.exists()) {
			// If directories are not available then create it
			File parent_directory = file.getParentFile();
			if (null != parent_directory) {
				parent_directory.mkdirs();
			}
			file.createNewFile();
		}
		FileOutputStream out = null;
		// Write the workbook in file system
		out = new FileOutputStream(file, false);
		workbook.write(out);
		out.close();
		logger.debug("Writesheet.xlsx written successfully");
		Desktop.getDesktop().open(file);

	}

	private void createBody(List<QCBug> defects, int colno, Cell cell,
			List headerList, int rownum) {
		switch (colno) {
		case 0:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getNo());
			break;
		case 1:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getProjectName());
			break;
		case 2:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getDefectID());
			break;
		case 3:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getDescription());
			break;
		case 4:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getAssignedName());
			break;
		case 5:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getAssingedTo());
			break;
		case 6:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getAssignedSystem());
			break;
		case 7:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getTeamAssigned());
			break;
		case 8:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getTargetRelease());
			break;
		case 9:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getOpenBy());
			break;
		case 10:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getSevierity());
			break;
		case 11:
			cell = row.createCell(colno);
			cell.setCellValue(defects.get(rownum).getOpenDate());
			break;
		default:
			logger.debug("Invalid");
			break;
		}

	}

	/*
	 * public static void setColumnValues(int colno) {
	 * 
	 * }
	 */
	private static void createHeader(Cell cell, String values) {
		cell.setCellValue(values);
	}

	private void autoResizeColumns(int i) {
		for (int colIndex = 0; colIndex < i; colIndex++) {
			if (colIndex == 3) {
				spreadsheet.getColumnHelper().setColWidth(colIndex, 50.00);
			} else {
				spreadsheet.autoSizeColumn(colIndex);
			}
		}
	}

	private XSSFCellStyle getNormalStyle() {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		return style;
	}

	private XSSFCellStyle getHeaderStyle() {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;

	}
}
