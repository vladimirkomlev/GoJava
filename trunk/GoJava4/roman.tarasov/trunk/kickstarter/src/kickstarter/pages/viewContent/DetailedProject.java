package kickstarter.pages.viewContent;

import kickstarter.entities.ProjectComments;
import kickstarter.mvc.interfaces.iModel;
import kickstarter.repository.facade.RepositoryException;
import kickstarter.repository.facade.iRepository;

public class DetailedProject extends PageView {

	public DetailedProject(iRepository repository, iModel imodel) {
		this.repository = repository;
		this.imodel = imodel;
	}

	private ProjectComments selectCommentsOfProject(int projectID) throws RepositoryException {
		ProjectComments comments = repository.getCommentsByProjectID(projectID);
		return comments;
	}

	public String getHeader() throws RepositoryException  {

		project = repository
				.getProjectById(imodel.getModelOptions().intSelectedProject);
		StringBuilder header = new StringBuilder();
		header.append("\n________________________");
		header.append("\n|Detailed project info |");
		header.append("\n|______________________|");
		header.append("\nname     :<");
		header.append(project.name);
		header.append(">");
		header.append("\nID       :<");
		header.append(project.ID);
		header.append(">");
		header.append("\ndescription:<");
		header.append(project.description);
		header.append(">");
		header.append("\ngoal     :<");
		header.append(project.goal);
		header.append(">");
		header.append("\npledged  :<");
		header.append(project.pledged);
		header.append(">");
		header.append("\ndays to go :<");
		header.append(project.daysToGo);
		header.append(">");
		header.append("\nhistory :<");
		header.append(project.history);
		header.append(">");
		header.append("\nlink to video :<");
		header.append(project.linkToVideo);
		header.append(">");
		header.append("\ncomments :");
		header.append("\n");

		ProjectComments comments = selectCommentsOfProject(project.ID);
		if (comments != null) {
			for (int index = 0; index < comments.getCommentLength(); index++) {
				if (comments.usersID[index] != 0) {
					header.append("user ID:<");
					header.append(comments.usersID[index]);
					header.append(">  comment ID:<");
					header.append(index);
					header.append("> <");
					header.append(comments.getComment()[index]);
					header.append(">\n");
				}
			}
		}
		header.append("\n------------------------");
		header.append("\nOptions: <p> - previous page; <i>- invest to project ; <c>- comment ; <d>- donate");
		return header.toString();
	}
}
