import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
	public LocalDate OverdueDate(String date) {
		LocalDate ldate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate OverdueDate = ldate.plusDays(7);
		return OverdueDate;
	}
	
	public long RemainDays(String date) {
		long RemainDays = ChronoUnit.DAYS.between(LocalDate.now(), OverdueDate(date));
		return RemainDays;
	}
}