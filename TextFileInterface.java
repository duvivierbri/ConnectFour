package connectfourpackage;

public interface TextFileInterface {

	void createNewFile(String fileName);
	void writeToNewFile(String fileName, String text);
	void appendToFile(String fileName, String text);
	String readFile(String fileName);
	void readFileDisplayOnConsole(String fileName);
	boolean deleteFile(String fileName);
	boolean copyFile(String fileName);
	boolean copyFile(String fileNameOrig, String fileNameCopiedVersion);
	void writeToFileFromKeyboard(String fileName);
	boolean findAndReplaceFileContent(String fileName, String textOrig, String textReplacement);
	
}
