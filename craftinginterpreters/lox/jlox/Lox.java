package lox.jlox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  static boolean hadError = false;
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64); 
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  /**
   * Locates and executes file from specified path using the Lox interpreter
   * @param path location of a file that the Lox interpreter finds and executes
   * @throws IOException
   */
  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    /* If code has known error, does not execute */
    if (hadError) System.exit(65);
  }

  /**
   * Runs Lox interpreter as interactive command line prompt
   * Reads input, evaluates it, prints the result, and loops (REPL)
   * Type Control-D to kill prompt
   * @throws IOException
   */
  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) {
      System.out.print("> ");
      String line = reader.readLine(); /* Reads a line of input from System.in */
      if (line == null) break;
      run(line);
      hadError = false;
    }
  }

  /**
   * "Runs" the Lox code
   * @param source Lox source code that needs to be executed
   */
  private static void run(String source) {

    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens(); /* Scans line for  */

    /* Just prints out tokens for now */
    for (Token token: tokens) {
      System.out.println(token);
    }
  }

  /**
   * Reports error to user of language
   * @param line number that corresponds to the line of error
   * @param message string that contains error message
   */
  static void error(int line, String message) {
    report(line, "", message);
  }

  /**
   * Shows error message to user with information about the location and nature of the error
   * @param line
   * @param where
   * @param message
   */
  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }


}
