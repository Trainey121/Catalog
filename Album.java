package testspark;

import static spark.Spark.port;

import java.util.ArrayList;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import static spark.Spark.get;

public class Album {

	public static void main(String[] args) {
		port(3000);

		ArrayList<CD> cdlist = new ArrayList<CD>();
		CD cd1 = new CD("Prince", "Purple Rain", 1);
		cdlist.add(cd1);
		cdlist.add(new CD("ToPoc", "Calif", 2));
		cdlist.add(new CD("Coldplay", "Ghost Story", 3));
		cdlist.add(new CD("Kelly Clarksony", "Piece by Piece", 3));

		get("/album/:id", (req, res) -> {
			System.out.println("request made");
			int id = Integer.parseInt(req.params(":id"));

			int searchListLength = cdlist.size();
			for (int i = 0; i < searchListLength; i++) {
				CD cd = cdlist.get(i);
				if (cd.getID() == id) {
					return cd.toString();
					// Do whatever you want here
				}
			}

			return "not found";

		});
		
		get("/albums", (req, res) -> {
			JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/example.twig");
            JtwigModel model = JtwigModel.newModel().with("albums", cdlist);

            return template.render(model);
		});
		
		get("/create", (req, res) -> {
			String name = req.queryParams("name");
			String artist = req.queryParams("artist");
			CD cd = new CD(artist, name, cdlist.size());
			cdlist.add(cd);
			return "success";
		});
		  get("/", (req, res) -> {
	            // convert list of albums into html
	            String body = "";
	            int searchListLength = cdlist.size();
				for (int i = 0; i < searchListLength; i++) {
					CD cd = cdlist.get(i);
					body += cd.getArtist()+" " + cd.getName()+ "<br> ";
				}			
	            String html = "<!DOCTYPE html><html><head></head><body>" + body + "</body></html>";
	            return html;
	        });
	}
}

class CD {
	private String artist;
	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CD(String artist, String name, int id) {
		super();
		this.artist = artist;
		this.name = name;
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

	public String toString() {
		return this.id + " " + this.name + " " + this.artist;

	}

}
