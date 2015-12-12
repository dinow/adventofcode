package be.dno.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class CodeAdvent_01 {

	

	public static void main(String[] args) throws Exception {
		System.out.println(processList(FileUtils.readLines(new File("C:\\Temp\\input.txt"), Charset.forName("UTF-8"))));
	}
	
	public static int processList(List<String> lines) {
	   int totalSize = 0;
	   
	   for(String line : lines){
		   totalSize += calcSize(line);
	   }
	   
	   return totalSize;
			
	}

	private static int calcSize(String line) {
		int totLength = line.length();
		//remove start and end quotes
		
		String workLine = line;//line.substring(1, line.length()-1);
		/*workLine = workLine.replace("\\\\", "§");
		int indexOfSlashX = workLine.indexOf("\\x");
		while (indexOfSlashX >= 0){ //"nxzo\"hf\\xp"
			String start = workLine.substring(0, indexOfSlashX);
			if (indexOfSlashX+4 <= workLine.length()){
				workLine = start + "@" + workLine.substring(indexOfSlashX+4, workLine.length());
				indexOfSlashX = workLine.indexOf("\\x");
			}else{
				indexOfSlashX = workLine.indexOf("\\x", indexOfSlashX+1);
			}
		}
		
		indexOfSlashX = workLine.indexOf("\\");
		while (indexOfSlashX >= 0){
			String start = workLine.substring(0, indexOfSlashX);
			workLine = start + "&" + workLine.substring(indexOfSlashX+2, workLine.length());
			indexOfSlashX = workLine.indexOf("\\");
		}*/
		
		
		//reencode...
		workLine = "\""+StringEscapeUtils.escapeJava(workLine)+"\"";
		int length =  workLine.length() - totLength;
		
		System.out.println(line + " ("+totLength+" - "+workLine.length()+") ["+workLine+"] = " + length);
		return length;
	}

}