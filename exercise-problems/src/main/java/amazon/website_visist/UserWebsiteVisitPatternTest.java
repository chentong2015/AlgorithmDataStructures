package amazon.website_visist;

public class UserWebsiteVisitPatternTest {

    public static void main(String[] args) {
        String[] usernames = {"joe", "joe", "joe",
                "james", "james", "james", "james",
                "mary", "mary", "mary",
                "tong", "tong",
                "test", "test", "test",
                "chen", "chen", "chen"};
        String[] websites = {"home", "about", "career",
                "home", "cart", "maps", "home",
                "home", "about", "career",
                "home", "about",
                "home", "about", "about",
                "home", "about", "about"};

        UserWebsiteVisitPattern visitPattern = new UserWebsiteVisitPattern();
        System.out.println(visitPattern.find3SequenceWebsiteVisited(usernames, websites));
    }
}
