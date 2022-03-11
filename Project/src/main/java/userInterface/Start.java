package userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import competitionManager.Competition;
import competitionManager.CompetitionManager;

public class Start extends Application {
	static final String NAME = "Competition_Data.xlsx";
	static CompetitionManager competitionManager = new CompetitionManager();
	static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			readExcel();
			Start.primaryStage = primaryStage;
			Parent mainRoot = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(mainRoot);
			
			primaryStage.setOnCloseRequest(e -> {
				e.consume();
				Stage SaveConfirmationMessageStage = new Stage();
				SaveConfirmationMessageStage.initModality(Modality.APPLICATION_MODAL);
				Parent saveRoot = null;
				try {
					saveRoot = FXMLLoader.load(getClass().getResource("SaveConfirmationMessage.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scene SaveConfirmationMessageScene = new Scene(saveRoot);
				SaveConfirmationMessageStage.setScene(SaveConfirmationMessageScene);
				SaveConfirmationMessageStage.setTitle("Create Competition");
				SaveConfirmationMessageStage.show();
			});
			primaryStage.setScene(scene);
			primaryStage.setTitle("Competition Manager");
			primaryStage.show();

			boolean alertShown = false;
			for (int i = 0; i < competitionManager.getCompetitions().size() && !alertShown; i++) {
				Competition competition = competitionManager.getCompetitions().get(i);
				if (competition.getDueDate().compareTo(LocalDate.now()) <= 0) {
					for (int j = 0; j < competition.getParticipantCount() && !alertShown; j++) {
						if (competition.getParticipants().get(j).getRank() == 0) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Not All Winners Are Updated");
							alert.setHeaderText("Some participants' rank did not get updated");
							alert.initOwner(primaryStage);
							alert.show();
							alertShown = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getSTRCellData(int row, int column, int sheet_num) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(NAME);
		XSSFSheet sheet = workbook.getSheetAt(sheet_num);

		String value = (sheet.getRow(row).getCell(column)).getStringCellValue();
		workbook.close();
		return value;
	}

	public int getNUMCellData(int row, int column, int sheet_num) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(NAME);
		XSSFSheet sheet = workbook.getSheetAt(sheet_num);

		double value = (sheet.getRow(row).getCell(column)).getNumericCellValue();
		int IntValue = (int) Math.round(value);
		workbook.close();
		return IntValue;
	}

	public String monthGenerator(String month) {
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

	public void readExcel() throws IOException {
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(NAME);
		} catch (InvalidOperationException e) {
			return;
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum;
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

			competitionManager.createCompetition(competition, new URL(link), competetionDate, teamBased);
			rowCounter = 5;
			while (rowCounter <= rowNum) {
				Competition c = competitionManager.getCompetitions().get(sheetCount);
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

	public static void begin(String[] args) {
		launch(args);
	}
}
