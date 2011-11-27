package crossword;

public class TooLongEntryRequestedException extends Exception {
	public TooLongEntryRequestedException(int _l) {
		super("W bazie nie istnieje hasło o długości "+_l+" liter. Wygenerowanie krzyżówki nie powiodło się");
	}
}
