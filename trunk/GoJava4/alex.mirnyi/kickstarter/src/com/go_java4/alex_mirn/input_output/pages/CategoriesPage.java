package com.go_java4.alex_mirn.input_output.pages;

import java.io.IOException;




import com.go_java4.alex_mirn.Repository;
import com.go_java4.alex_mirn.input_output.InputData;
import com.go_java4.alex_mirn.input_output.printers.ConsolePrinter;
//import com.go_java4.alex_mirn.input_output.printers.ConsolePrinter;
import com.go_java4.alex_mirn.input_output.printers.Printer;
import com.go_java4.alex_mirn.input_output.readers.ConsoleReader;
//import com.go_java4.alex_mirn.input_output.readers.ConsoleReader;
import com.go_java4.alex_mirn.input_output.readers.Reader;
import com.go_java4.alex_mirn.input_output.pages.AbstractPage;
import com.go_java4.alex_mirn.logic.CategoriesLogic;
import com.go_java4.alex_mirn.logic.ProjectsLogic;
import com.go_java4.alex_mirn.logic.ILogic;

public class CategoriesPage implements IPage{
	private Printer printer;
	private Reader reader;
	private CategoriesLogic categoriesLogic;
	private Repository repository;
	private String header;
	private String data;
	private String footer;
	private String exit;
	private String error;

	public CategoriesPage(Printer printer, Reader reader, 
			Repository repository, CategoriesLogic categoriesLogic) {
		this.printer = printer;
		this.reader = reader;
		this.categoriesLogic = categoriesLogic;
		this.repository = repository;
		header = "Please enter the number of the category you want to choose"
				+ " or press \"0\" to leave the programm:\n";
		data = "";
		footer = "---------------";
		exit = "Bye";
		error = "Invalid input number!!! Try again";
	}
	
	public void run(){}
	public int makeChoice(){
		return 0;
	}
	public void goBack(){}

	public void addQuote() {
		header = categoriesLogic.getQuote() + "\n\n" + header;
	}
	
	public void showHeader() {
		printer.println(header);
	}

	public void addData(int index, String category) {
		data += Integer.toString(index + 1) + ". " + category + "\n";
	}

	public void showData() {
		printer.println(data);
	}

	public void showFooter() {
		printer.println(footer);
	}

	public void showExit() {
		printer.println(exit);
	}
	
	public void runCategories() throws IOException {
		addQuote();
		showHeader();

		for (int index = 0; index < categoriesLogic.getSize(); index++) {
			addData(index, categoriesLogic.getIndex(index));
		}
		showData();
		showFooter();

		int categoryChoice = new InputData(printer, reader).inputData(categoriesLogic.getSize());
		if (categoryChoice == 0) {
			showExit();
			System.exit(0);
		} else {
			ProjectsLogic projectsLogic = new ProjectsLogic(repository); 
			ProjectsPage projectsPage = new ProjectsPage(new ConsolePrinter(),
					new ConsoleReader(), projectsLogic, repository);
			projectsPage.runProjects(categoryChoice);
		}
	}

}