package sample;

import java.io.*;
import java.util.ArrayList;

public class File {
  private FileOutputStream writeFile;
  private FileInputStream readFile;
  private ObjectOutputStream writeStream;
  private ObjectInputStream readStream;
  private String fileName;

  File(String fileName) {
    this.fileName = fileName;
  }

  public void createWriteStream() {
    try {
      writeFile = new FileOutputStream(fileName);
      writeStream = new ObjectOutputStream(writeFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveMoves(ArrayList<Move> toSave) {
    try {
      writeStream.writeObject(toSave);
      writeStream.flush();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }

  public void createReadStream() {
    try {
      readFile = new FileInputStream(fileName);
      readStream = new ObjectInputStream(readFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Options loadOptions() {
    Options gameOptions = new Options();
    try {
      gameOptions = (Options) readStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return gameOptions;
  }

  public ArrayList<Move> loadMoves() {
    ArrayList<Move> moves = new ArrayList<>();
    try {
      moves = (ArrayList<Move>) readStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return moves;
  }

  public void saveOptions(Options toSave) {
    try {
      writeStream.writeObject(toSave);
      writeStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void closeWriteStream() {
    try {
      writeStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeReadStream() {
    try {
      readStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
