package testspark;

import static spark.Spark.get;
import static spark.Spark.port;

public class TestSpark {

		    public static void main(String[] args) {
	        port(3000);

	        get("/hello", (req, res) -> {
	            System.out.println("request made");
	            return "hi world";
	        });

	    }

	}