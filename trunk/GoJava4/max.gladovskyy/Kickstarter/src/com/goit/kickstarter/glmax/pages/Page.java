package com.goit.kickstarter.glmax.pages;

import java.util.ArrayList;

import com.goit.kickstarter.glmax.controller.Position;
import com.goit.kickstarter.glmax.enteties.Entetie;
import com.goit.kickstarter.glmax.view.Output;

public abstract class Page {

	protected static final int parentPage = 0;
	protected Entetie entetie;
	protected Position currentMenuLevel;
	protected ArrayList<Page> relatedPages = new ArrayList<Page>();
	protected ArrayList<String> formatedPage = new ArrayList<String>();
	
	public Page(Entetie entetie) {
		this.entetie = entetie;
	}
	
	abstract protected void prepareFormatedPage();
	
	public void show(Output printer) {
		prepareFormatedPage();
		printer.print(formatedPage);
	}
	
	protected void fillMenu() {
		for (int index = 1; index < relatedPages.size(); index++) {
			formatedPage.add(index+") "+ relatedPages.get(index).getName());
		}
	}
	
	public Page getParentPage() {
		return relatedPages.get(parentPage);
	}
	
	public Page getChildPage(int i) {
		return relatedPages.get(i);
	}

	public void setParentPage(Page page) {
		this.relatedPages.add(parentPage, page);
	}

	public void addChildPages(ArrayList<Page> childPages) {
		this.relatedPages.addAll(childPages);
	}

	public int getId() {
		return this.entetie.getId();
	}

	public String getName() {
		return this.entetie.getName();
	}

	public int getMenuVariantsAmount() {
		return relatedPages.size();
	}

	public Position getCurrentMenuLevel() {
		return currentMenuLevel;
	}
	
	

}
