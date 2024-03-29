import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String [] inputs =ReadInputFile.readFile(args[0]);
        ArrayList<Command> commands= new ArrayList<Command>();//a container holds commands
        Library library = new Library();
        createCommands(library,inputs,commands);//this method created commands and add them the commands array list
        runSystem(commands);//execute the commands in the lists
        try {
            FileWriter writer = new FileWriter(args[1]);
            writer.write(library.getSystemOutput());
            writer.close();
        }catch (IOException e){

        }
    }



    public static void runSystem(ArrayList<Command> commands){
        for (Command i:commands){
            i.execute();//execute commands
        }
    }
    public static void createCommands(Library library,String [] inputs,ArrayList<Command> commands){
        for (String Line : inputs){
            String[] command=Line.split("\t");
            switch (command[0]) {//switch case that manage which command will be created
                case "addBook":
                    commands.add(new AddBook(library, command[1]));
                    break;
                case "addMember":
                    commands.add(new AddMember(library, command[1]));
                    break;
                case "borrowBook":
                    commands.add(new BorrowBook(library, command[1], command[2], command[3]));
                    break;
                case "returnBook":
                    commands.add(new ReturnBook(library, command[1], command[2], command[3]));
                    break;
                case "extendBook":
                    commands.add(new ExtendBook(library, command[1], command[2], command[3]));
                    break;
                case "readInLibrary":
                    commands.add(new ReadInLibrary(library, command[1], command[2], command[3]));
                    break;
                case "getTheHistory":
                    commands.add(new getHistory(library));
                    break;
                default:
                    commands.add(new ErroneousCommand(library));
            }
        }
    }
}
