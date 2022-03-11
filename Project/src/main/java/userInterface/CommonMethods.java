package userInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import competitionManager.Competition;
import competitionManager.Participant;
import competitionManager.TeamBasedCompetition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CommonMethods {

	public static Callback<DatePicker, DateCell> disablePreviousDates = new Callback<DatePicker, DateCell>() {
		@Override
		public DateCell call(final DatePicker param) {
			return new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty); // To change body of generated methods, choose Tools | Templates.
					LocalDate today = LocalDate.now();
					setDisable(empty || item.compareTo(today) < 0);
				}

			};
		}
	};

	public static void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	}

	public static void writeExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			for (int i = 0; i < Start.competitionManager.getCompetitions().size(); i++) {
				Competition c = Start.competitionManager.getCompetitions().get(i);

				XSSFSheet sheet = (XSSFSheet) workbook.createSheet(c.getName());
				URL link = c.getLink();
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

			FileOutputStream outstream = new FileOutputStream(Start.NAME);
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

}
