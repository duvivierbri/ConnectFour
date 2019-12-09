package connectfourpackage;

public interface TextFileDelimiterInterface{

	String readDelimitedFile(String fileName);
	void readDelimitedFileDisplayOnConsole(String fileName);
	
	String readDelimitedFile(String fileName, String delimiter);
	void readDelimitedFileDisplayOnConsole(String fileName, String delimiter);
	
}
