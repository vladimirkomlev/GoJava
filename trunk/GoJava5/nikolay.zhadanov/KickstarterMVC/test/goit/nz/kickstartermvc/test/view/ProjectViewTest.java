package goit.nz.kickstartermvc.test.view;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import goit.nz.kickstartermvc.model.ProjectModel;
import goit.nz.kickstartermvc.output.Output;
import goit.nz.kickstartermvc.test.MockStorage;
import goit.nz.kickstartermvc.view.ProjectView;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ProjectViewTest {
	@Mock
	private ProjectModel model;
	@Mock
	private Output output;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenPrintProjectThenProjectPrinted() {
		MockStorage storage = new MockStorage();
		storage.init();

		when(model.getProject()).thenReturn(storage.getProjects().get(0));

		final List<String> view = new ArrayList<>();

		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				String str = (String) arguments[0];
				view.add(str);
				return null;
			}
		}).when(output).println(anyString());

		ProjectView projectView = new ProjectView(output);
		projectView.printProject(model);
		String[] expectedResult = { 
				"", 
				"Overview of project: \"NAME1\"",
				"-------------------", 
				"     Description: desc1",
				"     Goal: 1000",
				"     Pledged: 0",
				"     Days to go: 10",
				"     Project Events:",
				"We have almost finished!\nWe are going to start!",
				"     Link to video: http://www.youtube.com/jrgri74ht3h97",
				"     Project FAQ:",
				"How can you imagine such idea?\nBecause of whisky, babe!",
				"",
				"(0 - back)" };
		assertArrayEquals("Wrong project layout", expectedResult,
				view.toArray());
	}
	
	@Test
	public void whenProjectIsNullThenWarningPrinted() {
		
		when(model.getProject()).thenReturn(null);
		
		final List<String> view = new ArrayList<>();

		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				String str = (String) arguments[0];
				view.add(str);
				return null;
			}
		}).when(output).println(anyString());

		ProjectView projectView = new ProjectView(output);
		projectView.printProject(model);
		String[] expectedResult = { 
				"", 
				"NPE case - model haven't returned the project",
				"",
				"(0 - back)" };
		assertArrayEquals("Wrong null project warning", expectedResult,
				view.toArray());
	}

}
