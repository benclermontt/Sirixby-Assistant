
public class Crawler {
	
	private static final int MAX_PAGES = 5;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
	public void search(String URL, String keyword) {
		while(this.pagesVisited.size() < MAX_PAGES) {
			String currentURL;
			CrawlerLeg leg = new CrawlerLeg();
			if(this.pagesVisited.isEmpty()) {
				
			}
		}
	}
}
