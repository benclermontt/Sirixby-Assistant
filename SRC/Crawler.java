
public class Crawler {
	
	private static final int MAX_PAGES = 5;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
	public void search(String URL, String keyword) {
		while(this.pagesVisited.size() < MAX_PAGES) {
			String currentURL;
			CrawlerLeg leg = new CrawlerLeg();
			if(this.pagesVisited.isEmpty()) {
				currentURL = URL;
				this.pagesVisited.add(URL);
			}
			else {
				currentURL = this.nextURL();
			}
			leg.crawl(currentURL);
			boolean success = leg.searchKeyword(searchKeyword);
			if(success) {
				System.out.println("Keyword %s is found at %s", searchKeyword, currentURL);
				break;
			}
			this.pagesToVisit.addAll(leg.getLinks());
		}
		System.out.println(String.format("Visited %s web pages", this.pagesVisited.size()));
	}
}
