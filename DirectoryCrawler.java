/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package directorycrawler;

/**
*
* @author DELL
*/
import java.util.*;
import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class DirectoryCrawler{
	public static void main(String[] args)throws Exception{
		//Starting directory for parsing
		File aStartingDir = new File("C:\\Users\\DELL\\Documents\\NetBeansProjects\\DirectoryCrawler");
		System.out.println("Working Directory = " +
			System.getProperty("user.dir"));


		directoryCrawler(aStartingDir);



		System.out.println();
		System.out.println();
		List<String> ab = new ArrayList<String>();



		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter string to search: ");
		String n = reader.nextLine(); // Scans the next token of the input as an int.

		ab = index.get(n);

		System.out.println("Your string present in : ");
		for (int i = 0; i < ab.size(); i++){
			System.out.println(ab.get(i));

		}
		System.out.println("Parsing complete");
	}
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else return "";
	}
	static Map<String, List<String>> index = new HashMap<String, List<String>>();

	//Crawls and indexes directories and files, also inside of text files
	public static int directoryCrawler(File aStartingDir)throws Exception{
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> dir = new ArrayList<File>();
		//Current Directory being traversed
		System.out.
			println("------------------------------------------------");
		System.out.println(aStartingDir.toString());
		System.out.
			println("------------------------------------------------");
		//List all files in directory
		for (File file : filesAndDirs){
			if (file.isFile()) {
				//Display file names
				//System.out.println(file.getName());
				//System.out.println(getFileExtension(file));

				List<String> tempList = new ArrayList<String>();
				tempList.add(aStartingDir.toString() + "\\" + file.getName());
				index.put(file.getName(), tempList);
				if (getFileExtension(file).equals("txt")){
					List<String> tempLst = new ArrayList<String>();
					index.put(file.getName(), tempList);

					//Index content of text files
					BufferedReader read = new BufferedReader(new FileReader(aStartingDir.toString() + "\\" + file.getName()));
					String line = null;
					while ((line = read.readLine()) != null) {
						StringTokenizer tokens = new StringTokenizer(line);
						while (tokens.hasMoreElements()){
							String firstname = tokens.nextToken();
							tempLst.add(aStartingDir.toString() + "\\" + file.getName());
							System.out.println(tempLst.size());

							index.put(firstname, tempLst);






						}
					}



				}
			}


			else {
				//Add first level directory to a list
				dir.add(file);
			}
		}
		if (dir.isEmpty())
			return 0;
		else {
			/*Traverse directory structure recursively
			by calling directory crawler */
			for (File file : dir){
				directoryCrawler(file);
			}
		}
                return 1;
	}
}
