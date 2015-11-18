package nthu.cs.excelsior.model;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;


import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Video;
import nthu.cs.excelsior.recommender.document.Document;
import nthu.cs.excelsior.recommender.document.Field;
import nthu.cs.excelsior.recommender.document.RamField;
import nthu.cs.excelsior.recommender.index.IndexReader;
import nthu.cs.excelsior.recommender.index.IndexWriter;
import nthu.cs.excelsior.recommender.index.TrieIndexReader;
import nthu.cs.excelsior.recommender.index.TrieIndexWriter;
import nthu.cs.excelsior.recommender.search.IndexSearcher;
import nthu.cs.excelsior.recommender.search.Query;
import nthu.cs.excelsior.recommender.search.TermQuery;
import nthu.cs.excelsior.recommender.search.TopDocs;
import nthu.cs.excelsior.recommender.search.ScoreDoc;
import nthu.cs.excelsior.recommender.store.Directory;
import nthu.cs.excelsior.recommender.store.RamDirectory;

@SuppressWarnings("serial")
public class SearchDao extends ModelAwareServlet<Video>{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		try {
			String queryString = URLDecoder.decode(
					req.getParameter("queryString"), "UTF-8");
			List<Video> vl = ObjectifyService.begin().query(Video.class).list();
			if (queryString.equalsIgnoreCase("show_all")) {
				setModel(req, vl);
			}
			else{
				List<Video> result = new ArrayList<Video>();
				Directory dir = createIndex(vl);
				IndexReader reader = new TrieIndexReader(dir);
				IndexSearcher searcher = new IndexSearcher(reader);
				Query query = new TermQuery(queryString);
				TopDocs tops = searcher.search(query, 10);
				for (ScoreDoc sd : tops.scoreDocs())
					result.add(vl.get(sd.docNum()));
				searcher.close();
				setModel(req, result);
			}
		}catch(Exception e){
			return;
		}
	}
	private static Directory createIndex(List<Video> vl) throws Exception {
		Directory dir = new RamDirectory();
		IndexWriter writer = new TrieIndexWriter(dir, new DefAnalyzer());
		for (Video video : vl) {
			writer.addDocument(createDefDocument(
					URLDecoder.decode(video.getTitle(), "UTF-8"),
					URLDecoder.decode(video.getDescription(), "UTF-8")));
		}
		writer.close();
		return dir;
	}

	public static Document createDefDocument(String title, String description) {
		Document doc = new Document();
		// boosts the heading field
		Field f1 = new RamField("title", true, 2.0, title);
		doc.add(f1);
		Field f2 = new RamField("description", description);
		doc.add(f2);
		return doc;
	}
}
