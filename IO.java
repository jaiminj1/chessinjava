
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Stacie Torres
 * @version 1.0
 * @since 1.0
 */
public class IO {

    /**
     * Used for writing to text files
     */
    private static PrintWriter fileOut;

    /**
     * Used for reading from text files
     */
    private static BufferedReader fileIn;

    /**
     * Creates an output file for writing.
     *
     * @param fileName name of the file
     */
    public static void createOutputFile(String fileName) {
        try {
            fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            System.out.println("*** Cannot create file: " + fileName + " ***");
        }
    }

    /**
     * Opens an existing output file for writing.
     *
     * @param fileName name of the file
     */
    public static void appendOutputFile(String fileName) {
        try {
            fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        } catch (IOException e) {
            System.out.println("*** Cannot open file: " + fileName + " ***");
        }
    }

    /**
     * Writes on a text file.
     *
     * @param text Characters to be printed.
     */
    public static void print(String text) {
        fileOut.print(text);
    }

    /**
     * Writes on a text file on a new line.
     *
     * @param text Characters to be printed.
     */
    public static void println(String text) {
        fileOut.println(text);
    }

    /**
     * Closes a output file.
     */
    public static void closeOutputFile() {
        fileOut.close();
    }

     /**
     * Opens an existing text file to read.
     *
     * @param fileName name of file.
     */
    public static void openInputFile(String fileName) {
        try {
            fileIn = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("***Cannot open " + fileName + "***");
        }
    }

    /**
     * Reads a line
     * 
     * @return the line
     */
    public static String readLine() throws IOException //Note: if there's an error in this method it will return IOException
    {
        return fileIn.readLine();
    }

    /**
     * Closes the reading file.
     */
    public static void closeInputFile() throws IOException //Note: if there's an error in this method it will return IOException
    {
        fileIn.close();
    }
}
