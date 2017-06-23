package testspark;

import static spark.Spark.*;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.FileOutputStream;
import java.util.ArrayList;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class SparkExample {

    public static void main(String[] args) {
        staticFiles.location("/");
        port(3000);

        get("/hello", (req, res) -> {
            System.out.println("request made");

            ArrayList<CD> cdlist = new ArrayList<CD>();
    		CD cd1 = new CD("Prince", "Purple Rain", 1);
    		cdlist.add(cd1);
    		cdlist.add(new CD("ToPoc", "Calif", 2));
    		cdlist.add(new CD("Coldplay", "Ghost Story", 3));
    		cdlist.add(new CD("Kelly Clarkson", "Piece by Piece", 3));
    		cdlist.add(new CD("Pharrel Williams","Happy", 12));
    		cdlist.add(new CD("Alica Keys,","Girl is on Fire",13));
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/example.twig");
            JtwigModel model = JtwigModel.newModel().with("cdlist", cdlist);

            return template.render(model);
        });

        get("/albums", (req, res) -> {
        	ArrayList<CD> cdlist = new ArrayList<CD>();
    		CD cd1 = new CD("Prince", "Purple Rain", 1);
    		cdlist.add(cd1);
    		cdlist.add(new CD("ToPoc", "Calif", 2));
    		cdlist.add(new CD("Coldplay", "Ghost Story", 3));
    		cdlist.add(new CD("Kelly Clarkson", "Piece by Piece", 4));
    		cdlist.add(new CD("Pharrel Williams","Happy", 12));
    		cdlist.add(new CD("Alica Keys,","Girl is on Fire",13));
    		
        	Gson gson = new Gson();
        	return gson.toJson(cdlist);
        });

    }
}

class Cat {
    public int age;
    public String name;

    public Cat(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return age + " " + name;
    }
}