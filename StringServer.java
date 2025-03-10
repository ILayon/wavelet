import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a string that will be manipulated by
    // /add-message.
    String myString = "";

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath()); //this prints in terminal
        if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                if(myString.equals("")){
                    return myString = parameters[1];
                }
                return myString = myString + '\n' + parameters[1];
            }
        }
        return "404 Not Found!";
    }
}


class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
