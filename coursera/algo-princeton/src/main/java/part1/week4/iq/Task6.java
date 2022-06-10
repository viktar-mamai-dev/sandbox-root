package part1.week4.iq;

public class Task6 {

    public static class User {
        private static Long nextId = 1L;
        private Long id;
        private String name;

        public User(String name) {
            this.name = name;
            this.id = nextId++;
        }

        public String getName() {
            return name;
        }
    }

    public static class Website {
        private static Long nextId = 1L;
        private Long id;
        private String url;

        public Website(String url) {
            this.url = url;
            this.id = nextId++;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Visit implements Comparable<Visit> {
        private static Long nextId = 1L;
        private Long id;
        private Long userId;
        private Long websiteId;

        public Visit(User user, Website webSite) {
            this.id = nextId++;
            this.userId = user.id;
            this.websiteId = webSite.id;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getWebsiteId() {
            return websiteId;
        }

        @Override
        public int compareTo(Visit that) {
            if (!this.getUserId().equals(that.getUserId())) {
                return Long.compare(this.getUserId(), that.getUserId());
            }
            if (!this.getWebsiteId().equals(that.getWebsiteId())) {
                return Long.compare(this.getWebsiteId(), that.getWebsiteId());
            }
            return 0;
        }
    }
}
