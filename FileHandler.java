package connectfourpackage;
import java.io.File; //So we can deal with files in the first place (lol)
import java.io.FileNotFoundException; //So we can make our own errors
import java.io.FileOutputStream; //So we can write data to a file
import java.io.PrintWriter; //So we can deal with the PrintWriter (Like a scanner but with io)
import java.util.Scanner;

public class FileHandler implements TextFileInterface, TextFileDelimiterInterface{

	@Override
	public void createNewFile(String fileName) {
		PrintWriter outstream = null; //starts with nothing
		
		try {
			outstream = new PrintWriter(fileName);
		} catch (FileNotFoundException couldntCreateFile) {
			System.err.print("Couldn't create the file " + fileName);
			couldntCreateFile.printStackTrace();
		}
		finally {
			if (outstream != null) {
				outstream.close();
			}
		}
		
	}

	@Override
	public void writeToNewFile(String fileName, String text) {
	PrintWriter outStream = null;
		
		try {
			outStream = new PrintWriter(fileName);
			outStream.println(text);//print the line of text to the file
		} catch (FileNotFoundException e) {
			System.err.println("Could not write to the file "+fileName);
		}
		finally{
			if(outStream !=null){
				outStream.close();
			}
		}
	}

	@Override
	public void appendToFile(String fileName, String text) {
		PrintWriter outStream = null;
		try {
//			outStream = new PrintWriter(fileName);
			outStream = new PrintWriter(new FileOutputStream(fileName, true));
			outStream.println(text);//print the line of text to the file
		} catch (FileNotFoundException e) {
			System.err.println("Could not write to the file "+fileName);
		}
		finally{
			if(outStream !=null){
				outStream.close();
			}
		}
		
	}

	@Override
	public String readFile(String fileName) {
		Scanner inStream = null;
		String fileContents = "";
		try {

			File theFileObject = new File(fileName);
			inStream = new Scanner(theFileObject);
			while(inStream.hasNextLine()){
				fileContents += inStream.nextLine()+"\n";
			}
			
		} catch (FileNotFoundException e) {
			return (fileContents + e.getMessage());
		}
		finally{
			if(inStream !=null){
				inStream.close();
			}
			System.out.println("All Done with file "+fileName);
		}
		return fileContents;
	}

	@Override
	public void readFileDisplayOnConsole(String fileName) {
		Scanner inStream = null;
		try {

			File theFileObject = new File(fileName);
			inStream = new Scanner(theFileObject);
			while(inStream.hasNextLine()){
				System.out.println(inStream.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Probelm in readFileDisplayOnConsole " +  e.getMessage());
		}
		finally{
			if(inStream !=null){
				inStream.close();
			}
//			System.out.println("All Done with file "+fileName);
		}
	}

	@Override
	public boolean deleteFile(String fileName) {
		File theFile = new File(fileName);
		if(theFile.exists()){
			if(theFile.delete()){
				System.out.println("The file "+fileName+ " was successfully deleted");
				return true;
			}
			else{
				System.err.println("The file "+fileName+ " COULD NOT be deleted");
				return false;
			}
		}
		System.err.println("The file "+fileName+ " Does NOT exist");
		return false;
	}

	@Override
	public boolean copyFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean copyFile(String fileNameOrig, String fileNameCopiedVersion) {
		Scanner inStream = null;
		PrintWriter outStream = null;
		boolean status = false;
		try {
			inStream = new Scanner(new File(fileNameOrig)); //connect to original file
			outStream = new PrintWriter(new FileOutputStream(fileNameCopiedVersion, false));
			while(inStream.hasNextLine()){
				
				outStream.println(   inStream.nextLine()   ); //write the read line to the output file
			}
			status =  true;
		} catch (FileNotFoundException e) {
			System.err.println("Could not copy file from "+
					fileNameOrig+" to "+ fileNameCopiedVersion);
			status = false;
		}
		finally{
			if(inStream !=null){
				inStream.close();
			}
			if(outStream !=null){
				outStream.close();
			}
		}
		return status;
	}

	@Override
	public void writeToFileFromKeyboard(String fileName) {
		Scanner kb = new Scanner(System.in);
		PrintWriter outStream = null;
		String lineIn = "";
		
		try {
			outStream = new PrintWriter( new FileOutputStream(fileName , true)  );
			do{
				lineIn = kb.nextLine();
				outStream.println(lineIn);
				outStream.flush();
			}while(!lineIn.equalsIgnoreCase("STOP"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(kb != null){
				kb.close();
			}
			if(outStream !=null){
				outStream.close();
			}
		}
		
	}

	@Override
	public boolean findAndReplaceFileContent(String fileName, String textOrig, String textReplacement) {
		return false;
	}

	@Override
	public String readDelimitedFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readDelimitedFileDisplayOnConsole(String fileName) {
		Scanner inStream = null;
		String token = "";
		File theFile = new File(fileName);

		try {
			if(theFile.exists() && theFile.canRead()){
					inStream = new Scanner(theFile);
					inStream.useDelimiter(",");
					while(inStream.hasNext()){
						token = inStream.next();
						System.out.println(token);
					}
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inStream !=null){
			inStream.close();
		}
		
	}


	public void readDelimitedFileDisplayColumnOnConsole(String fileName) {
		Scanner inStream = null;
		String token = "";
		File theFile = new File(fileName);

		try {
			if(theFile.exists() && theFile.canRead()){
					inStream = new Scanner(theFile);
//					inStream.useDelimiter(",");
					while(inStream.hasNextLine()){
						String [] tokens = inStream.nextLine().split(",");
						System.out.println(tokens[0]);
					}
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(inStream !=null){
			inStream.close();
		}
		
	}
	public void readDelimitedFileDisplayColumnsOnConsole(String fileName) {
		Scanner inStream = null;
		String token = "";
		File theFile = new File(fileName);

		try {
			if(theFile.exists() && theFile.canRead()){
					inStream = new Scanner(theFile);
//					inStream.useDelimiter(",");
					int lineNum = 0;
					while(inStream.hasNextLine()){
						lineNum++;
						String [] tokens = inStream.nextLine().split(",");
						for(int i=0; i<tokens.length; i++){
							if(i == 1  && lineNum > 1){
								tokens[i] = ""+(Integer.parseInt(tokens[i]) * 100);
							}
							System.out.printf("%20s", tokens[i]);
//							System.out.print(tokens[i]+"   ");
						}
						System.out.println();
					}
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inStream !=null){
			inStream.close();
		}
		
	}
	
	@Override
	public String readDelimitedFile(String fileName, String delimiter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readDelimitedFileDisplayOnConsole(String fileName,
			String delimiter) {
		// TODO Auto-generated method stub
		
	}

}