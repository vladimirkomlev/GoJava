package com.sergiisavin.kickstarter.launcher;

import com.sergiisavin.kickstarter.Categories;
import com.sergiisavin.kickstarter.CategoriesContainer;
import com.sergiisavin.kickstarter.Kickstarter;
import com.sergiisavin.kickstarter.Quotes;
import com.sergiisavin.kickstarter.QuotesContainer;
import com.sergiisavin.kickstarter.UserInterface.PageDispatcher;
import com.sergiisavin.kickstarter.UserInterface.PageType;

public class Launcher {
    
	static PageDispatcher pageDispatcher;
    static Kickstarter kickstarter;
    static Quotes quotes;
    static Categories categories;
    
	public static void main(String[] args) {
		
		CreateAndInitializeClasses();
		
		injectDependencies();

		launch();

	}


	private static void CreateAndInitializeClasses() {
		pageDispatcher = new PageDispatcher();
		kickstarter = new Kickstarter();
		quotes = new QuotesContainer("Easy come - easy go", "An apple a day keeps doctors away", "A good speach"
				+ " must be as girls skirt: short enaugh to be interesting and long enaugh to cover the subject");
		categories = new CategoriesContainer("Toys", "Software", "Gadgets");
	}

	private static void injectDependencies() {
		kickstarter.setQuotes(quotes);
		kickstarter.setCategories(categories);
		pageDispatcher.setKickstarter(kickstarter);
	}
	
	private static void launch() {
		pageDispatcher.requestPage(PageType.MAIN_MENU_PAGE);
	}

}