import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Readfile {
    public static void main(String[] args){
        if (args.length < 2) {
            System.out.println("Expected usage: CopyFile <source filename> <destination filename>");
            System.exit(1);
        }
        String inputFilename = args[0];
        String outputFilename = args[1];

    }
}
