package sample;

import java.io.*;
import java.util.ArrayList;

public class File {
  private static FileOutputStream writeFile;
  private static FileInputStream readFile;
  private static ObjectOutputStream writeStream;
  private static ObjectInputStream readStream;

  public static void saveMoves(String fileName, ArrayList<Move> toSave) {
    try {
      writeStream.writeObject(toSave);
      writeStream.flush();
      writeStream.close();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }

  public static Options loadOptions(String fileName) {
    Options gameOptions = new Options();
    try {
      readFile = new FileInputStream(fileName);
      readStream = new ObjectInputStream(readFile);
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
      readStream.close();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return moves;
  }

  public static void saveOptions(String fileName, Options toSave) {
    try {
      writeFile = new FileOutputStream(fileName);
      writeStream = new ObjectOutputStream(writeFile);
      writeStream.writeObject(toSave);
      writeStream.flush();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
}
