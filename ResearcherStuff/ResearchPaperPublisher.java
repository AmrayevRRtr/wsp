package ResearcherStuff;

import Users.User;

import java.util.ArrayList;
import java.util.List;


public class ResearchPaperPublisher {
    private List<User> subscribers = new ArrayList<>();

    public void subscribe(User user) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
        }
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    public void publishPaper(ResearchPaper paper) {
        System.out.println("Publishing paper: " + paper.getTitle());
        notifySubscribers(paper);
    }

    private void notifySubscribers(ResearchPaper paper) {
        for (User subscriber : subscribers) {
            subscriber.notifySubscription(paper);
        }

    }
}
