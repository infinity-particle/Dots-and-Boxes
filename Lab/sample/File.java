package sample;

import java.io.*;
import java.util.ArrayList;

public class File {
  private static FileOutputStream writeFile;
  private static FileInputStream readFile;
  private static ObjectOutputStream writeStream;
  private static ObjectInputStream readStream;

  public static void createWriteStream(String fileName) {
    try {
      writeFile = new FileOutputStream(fileName);
      writeStream = new ObjectOutputStream(writeFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveMoves(ArrayList<Move> toSave) {
    try {
      writeStream.writeObject(toSave);
      writeStream.flush();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }

  public static void createReadStream(String fileName) {
    try {
      readFile = new FileInputStream(fileName);
      readStream = new ObjectInputStream(readFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Options loadOptions() {
    Options gameOptions = new Options();
    try {
      gameOptions = (Options) readStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return gameOptions;
  }

  public static ArrayList<Move> loadMoves() {
    ArrayList<Move> moves = new ArrayList<>();
    try {
      moves = (ArrayList<Move>) readStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return moves;
  }

  public static void saveOptions(Options toSave) {
    try {
      writeStream.writeObject(toSave);
      writeStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void closeWriteStream() {
    try {
      writeStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void closeReadStream() {
    try {
      readStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
