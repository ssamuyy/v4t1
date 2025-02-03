package main;

import java.io.*;
import java.util.*;

public class Cave implements Serializable {
    private static final long serialVersionUID = 1L;
    Player player;
    List<Monster> monsters;
    
    public Cave(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
    }
    
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }
    
    public void listMonsters() {
        if (monsters.isEmpty()) {
            System.out.print(" Luola on tyhjä.");
            return;
        }
        System.out.println();
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).printInfo(i + 1);
        }
    }
    
    public void attackMonster(int index) {
        Monster target = monsters.get(index - 1);
        if (!player.attack(target)) {
            monsters.remove(target);
        }
    }
    
    public void saveGame(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("\nPeli tallennettiin tiedostoon " + filename + ".");
        } catch (IOException e) {
            System.out.println("Tallennus epäonnistui.");
        }
    }
    
    public static Cave loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.print("\nPeli ladattu tiedostosta " + filename + ". ");
            return (Cave) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lataus epäonnistui.");
            return null;
        }
    }
}
