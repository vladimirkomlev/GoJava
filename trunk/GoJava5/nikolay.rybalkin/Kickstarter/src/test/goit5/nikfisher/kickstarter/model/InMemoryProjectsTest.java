package goit5.nikfisher.kickstarter.model;

public class InMemoryProjectsTest extends ProjectsTest {

    @Override
    Projects getProjects() {
        return new InMemoryProjects();
    }
}