import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> myList = new ArrayList<String>();

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath()); //this prints in terminal
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                myList.add(parameters[1]);
                return String.format("You have added %s to the list.", parameters[1]);
            }
        }
        if(url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String mySubString = parameters[1];
                ArrayList<String> search = new ArrayList<String>();
                for(int i = 0; i < myList.size(); i++){
                    if(myList.get(i).contains(mySubString)){
                        search.add(myList.get(i));
                    }
                }
                if(search.isEmpty()){
                    return String.format("This array is empty.");
                }
                return search.toString();
            }
        }
        return "404 Not Found!";
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
