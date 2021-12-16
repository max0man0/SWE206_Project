
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class WriteAndReadExcel {

	static CompetitionManager cm = new CompetitionManager();
	static String path = "C:/Users/omar_/Desktop/Universiry/semester 211/SWE 206/Lab/project/pro-testing/Excel-Training-Data-ISE2019.xlsx";

	public static void main(String[] args) throws Exception {

//		cm.createCompetition("1", new URL("http://google.com/%22"), LocalDate.of(2021, 12, 1), false);
//		cm.createCompetition("2", new URL("http://google.com/%22"), LocalDate.of(2021, 11, 1), true);
//		cm.createCompetition("3", new URL("http://google.com/%22"), LocalDate.of(2021, 10, 1), false);
//
//		HashMap<String, String> hashmap = new HashMap<String, String>();
//		hashmap.put("Name", "name0");
//		hashmap.put("Id", "201911111");
//		hashmap.put("Major", "ICS");
//		cm.getCompetitions().get(0).addParticipant(hashmap);
//		hashmap.put("Name", "name1");
//		hashmap.put("Id", "201911111");
//		hashmap.put("Major", "ICS");
//		hashmap.put("TeamName", "team ics");
//		cm.getCompetitions().get(1).addParticipant(hashmap);
//		hashmap.put("Name", "name2");
//		hashmap.put("Id", "201900444");
//		hashmap.put("Major", "ICS");
//		hashmap.put("TeamName", "team ics");
//		cm.getCompetitions().get(1).addParticipant(hashmap);
//
//		writeExcel();
		readExcel();
		for (int i = 0; i < cm.getCompetitions().size(); i++) {
			System.out.println(cm.getCompetitions().get(i));
			System.out.println(cm.getCompetitions().get(i).getParticipants()+ "\n");
			
		}

	}

	public static void writeExcel() throws Exception {

		

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			for (int i = 0; i < cm.getCompetitions().size(); i++) {
				Competition c = cm.getCompetitions().get(i);

				XSSFSheet sheet = (XSSFSheet) workbook.createSheet(c.getName());
				URL link = c.getLink();
				LocalDate Localdate = c.getDueDate();
				String date = c.getDueDate().toString();

				XSSFRow row = sheet.createRow(0);
				XSSFCell cell = row.createCell(0);

				cell.setCellValue("Competition Name");
				cell = row.createCell(1);
				cell.setCellValue(c.getName());// all these data |
				row = sheet.createRow(1); // |
				cell = row.createCell(0);
				cell.setCellValue("Competition link");
				cell = row.createCell(1);
				cell.setCellValue(link.toString());// are from the |
				row = sheet.createRow(2); // |
				cell = row.createCell(0);
				cell.setCellValue("Competition date");
				cell = row.createCell(1);
				cell.setCellValue(date);// competition class|

				boolean teamBased = c instanceof TeamBasedCompetition;

				row = sheet.createRow(4);

				cell = row.createCell(1);
				cell.setCellValue("Student ID");
				cell = row.createCell(2);
				cell.setCellValue("Student Name");
				cell = row.createCell(3);
				cell.setCellValue("Major");

				if (teamBased) {

					cell = row.createCell(4);
					cell.setCellValue("Team");
					cell = row.createCell(5);
					cell.setCellValue("Team Name");
					cell = row.createCell(6);
					cell.setCellValue("Rank");
				}

				else {

					cell = row.createCell(4);
					cell.setCellValue("Rank");

				}

				int row_counter = 5;
				int student_counter = 1;

				for (Participant p : c.getParticipants()) {
					row = sheet.createRow(row_counter);
					cell = row.createCell(0);
					cell.setCellValue(String.valueOf(student_counter));
					cell = row.createCell(1);
					cell.setCellValue(p.getId());
					cell = row.createCell(2);
					cell.setCellValue(p.getName());
					cell = row.createCell(3);
					cell.setCellValue(p.getMajor());

					if (teamBased) {
						cell = row.createCell(4);
						cell.setCellValue(p.getTeamNumber());
						cell = row.createCell(5);
						cell.setCellValue(p.getTeam().getName());
						cell = row.createCell(6);
						cell.setCellValue(p.getRank());
					} else {
						cell = row.createCell(4);
						cell.setCellValue(p.getRank());
					}

					row_counter++;
					student_counter++;

				}
			}

			FileOutputStream outstream = new FileOutputStream(path);
			workbook.write(outstream);

			outstream.close();
			workbook.close();
		}

		catch (IOException ex) {
			ex.printStackTrace();
		} catch (EncryptedDocumentException ex) {
			ex.printStackTrace();
		}

	}

	public static void type(int row, int column, int sheet_num) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		XSSFSheet sheet = workbook.getSheetAt(sheet_num);

		if ((sheet.getRow(row).getCell(column)).getCellType() == CellType.STRING)
			getSTRCellData(row, column, sheet_num);
		else
			getNUMCellData(row, column, sheet_num);
		return;
	}

	public static String getSTRCellData(int row, int column, int sheet_num) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		XSSFSheet sheet = workbook.getSheetAt(sheet_num);

		String value = (sheet.getRow(row).getCell(column)).getStringCellValue();
		return value;
	}

	public static int getNUMCellData(int row, int column, int sheet_num) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		XSSFSheet sheet = workbook.getSheetAt(sheet_num);

		double value = (sheet.getRow(row).getCell(column)).getNumericCellValue();
		int IntValue = (int) Math.round(value);
		return IntValue;
	}

	public static String monthGenerator(String month) {
		switch (month) {
		case "Jan":
			month = "1";
			break;
		case "Feb":
			month = "2";
			break;
		case "Mar":
			month = "3";
			break;
		case "Apr":
			month = "4";
			break;
		case "May":
			month = "5";
			break;
		case "June":
			month = "6";
			break;
		case "July":
			month = "7";
			break;
		case "Aug":
			month = "8";
			break;
		case "Sept":
			month = "9";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		}
		return month;
	}

	public static void readExcel() throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook(path);
//		int sheet_num = workbook.getNumberOfSheets();
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum;
//		int column_num = sheet.getDefaultColumnWidth();
		Iterator<Sheet> sheets = workbook.sheetIterator();

		int sheetCount = 0;
		int rowCounter = 5;
		String month = "";
		boolean teamBased;
		HashMap<String, String> hashmap = new HashMap<String, String>();
		int rank;
		
		
		while (sheets.hasNext()) {
			sheet = (XSSFSheet) sheets.next();
			rowNum = sheet.getPhysicalNumberOfRows();
			System.out.println(rowNum);
			month = getSTRCellData(2, 1, sheetCount);
			String[] date = month.split("-");
			date[1] = monthGenerator(date[1]);
			LocalDate competetionDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
					Integer.parseInt(date[2]));
			String competition = getSTRCellData(0, 1, sheetCount);
			String link = getSTRCellData(1, 1, sheetCount);

			if (!getSTRCellData(4, 4, sheetCount).equals("Team"))
				teamBased = false;
			else
				teamBased = true;

			cm.createCompetition(competition, new URL(link), competetionDate, teamBased);
			rowCounter = 5;
			while (rowCounter <= rowNum) {
				Competition c = cm.getCompetitions().get(sheetCount);
				hashmap.put("Name", getSTRCellData(rowCounter, 2, sheetCount));
				hashmap.put("Id", (getSTRCellData(rowCounter, 1, sheetCount)));
				hashmap.put("Major", getSTRCellData(rowCounter, 3, sheetCount));
				if (teamBased) {
					hashmap.put("TeamName", getSTRCellData(rowCounter, 5, sheetCount));
					rank = getNUMCellData(rowCounter, 6, sheetCount);
				} else {
					rank = getNUMCellData(rowCounter, 4, sheetCount);
				}
				
				c.addParticipant(hashmap);
				c.getParticipants().get(c.getParticipantCount()-1).setRank(rank);
				
				rowCounter++;
				hashmap.clear();
			}

			sheetCount++;
		}
		workbook.close();

	}

}
