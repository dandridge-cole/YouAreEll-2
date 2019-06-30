package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll(new MessageController(), new IdController());
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine()+" "; // space added to ensure either 0 or greater than one single qupte

            //if the user entered a return, just loop again
            if (commandLine.equals(" "))
                continue;
            if (commandLine.equals("exit ")) {
                System.out.println("bye!");
                break;
            }
            List<String> list = parseInput(commandLine);
            if (list == null) continue;

            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                if (executeCommand(webber, list)) continue;

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

    private static boolean executeCommand(YouAreEll webber, List<String> list) {
        // ids
        if (list.contains("ids")) return idCommands(webber, list);

        // messages
        if (list.contains("messages"))  return getMsgCmds(webber, list);

        if (list.contains("send")) {
            return sendMsgCmds(webber, list);
        }
        // you need to add a bunch more.
        return false;
    }

    private static boolean sendMsgCmds(YouAreEll webber, List<String> list) {
        String results;
        switch (list.size()){
            case 3:{
                list.add("to");
                list.add("");
            }
            case 5:{
                results = webber.get_messages("POST",
                String.format("{\"message\": \"%s\",\n",list.get(2).substring(1,list.get(2).length()-1)) +
                        String.format("\t\"fromid\": \"%s\",\n",list.get(1)) +
                        String.format("\t\"toid\": \"%s\",\n",list.get(4)) +
                        "\t\"sequence\": \"-\"\n}");
                SimpleShell.prettyPrint(results);
                return true;
            }
            default:{
                System.out.println("Invalid command syntax.  To send a message, type either:\n" +
                        "'send' followed by your github id, followed by your message in single quotes (to send to all),\n" +
                        "\t-OR-\n" +
                        "'send' followed by your github id, followed by your message, followed by 'to' followed by recipient's github id.");
                return true;
            }
        }
    }

    private static boolean getMsgCmds(YouAreEll webber, List<String> list) {
        switch(list.size()){
            case 1: {
                String results = webber.get_messages("GET","");
                SimpleShell.prettyPrint(results);
                return true;
            }
            case 2:{
                String results = webber.get_messages("GET",
            "{\"userid\": \"-\",\n" +
                    "\t\"name\": \"-\",\n" +
                    String.format("\t\"github\": \"%s\"}\n",list.get(1)));
                    SimpleShell.prettyPrint(results);
                return true;
            }
            default:{
                String results = webber.get_messages("GET",
            "{\"userid\": \"-\",\n" +
                    "\t\"name\": \"-\",\n" +
                    String.format("\t\"github\": \"%s\"},\n",list.get(1))+
                    "{\"userid\": \"-\",\n" +
                    "\t\"name\": \"-\",\n" +
                    String.format("\t\"github\": \"%s\"}",list.get(2)));
                    SimpleShell.prettyPrint(results);
                return true;
            }
        }
    }

    private static boolean idCommands(YouAreEll webber, List<String> list) {
        switch (list.size()){
            case 1:{
                String results = webber.get_ids("GET","");
                SimpleShell.prettyPrint(results);
                return true;
            }
            case 3:{
                String results = webber.get_ids("POST","{\"userid\": \"-\",\n" +
                        String.format("\t\"name\": \"%s\",\n",list.get(1)) +
                        String.format("\t\"github\": \"%s\"}",list.get(2)));
                SimpleShell.prettyPrint(results);
                return true;
            }
            default:{
                System.out.println("For list of Ids, simply type 'ids'.\n" +
                        "To create or update, type 'ids' followed by the username and github id, separated by spaces.");
                return true;
            }
        }
    }

    private static List<String> parseInput(String commandLine) {
        //input parsed into array of strings(command and arguments)
        String[] firstSplit = commandLine.split("'");
        if(firstSplit.length==2){
            System.out.println("Invalid command syntax, please try again.");
            return null;
        }

        String[] commands = firstSplit[0].split(" ");

        //loop through to see if parsing worked
        //System.out.println(commands[i]); //***check to see if parsing/split worked***
        List<String> list = new ArrayList<>(Arrays.asList(commands));

        StringBuilder builder =  new StringBuilder();

        if(firstSplit.length>1){
            builder.append("'");
            for (int i = 1; i < firstSplit.length-1; i++) builder.append(firstSplit[i]).append("'");
            list.add(builder.toString());
            String[] after = firstSplit[firstSplit.length-1].split(" ");
            for (String s : after) {
                if (!s.equals("")) list.add(s);
            }
        }
        System.out.println(list); //***check to see if list was added correctly***
        return list;
    }

}