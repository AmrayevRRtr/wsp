package ResearcherStuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import Users.*;

public class ResearchProject {
  private String topic;
  private List<ResearchPaper> publishedPapers;
  private List<Researcher> participants;

  public ResearchProject() {
    this.publishedPapers = new ArrayList<>();
    this.participants = new ArrayList<>();
  }

  public ResearchProject(String topic) {
    this();
    this.topic = topic;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public List<ResearchPaper> getPublishedPapers() {
    return new ArrayList<>(publishedPapers);
  }

  public void setPublishedPapers(List<ResearchPaper> publishedPapers) {
    this.publishedPapers = new ArrayList<>(publishedPapers);
  }

  public void publishPaper(ResearchPaper paper) {
    if (!publishedPapers.contains(paper)) {
      publishedPapers.add(paper);
    }
  }

  public List<Researcher> getParticipants() {
    return new ArrayList<>(participants);
  }

  public void addParticipant(Object participant) throws Exception{
    if (!(participant instanceof Researcher)) {
      throw new Exception("Only a Researcher can join a Research Project.");
    }
    Researcher researcher = (Researcher) participant;
    if (!participants.contains(researcher)) {
      participants.add(researcher);
      System.out.println("Researcher added: " + researcher.getResearchArea());
    }
  }

  public void removeParticipant(Researcher researcher) {
    participants.remove(researcher);
  }

  public boolean isParticipant(Researcher researcher) {
    return participants.contains(researcher);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResearchProject that = (ResearchProject) o;
    return Objects.equals(topic, that.topic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topic);
  }

  @Override
  public String toString() {
    return "ResearchProject{" +
        "topic='" + topic + '\'' +
        ", publishedPapers=" + publishedPapers.size() +
        ", participants=" + participants.size() +
        '}';
  }
}
