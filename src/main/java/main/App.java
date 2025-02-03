package main;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Syötä pelaajan nimi: ");
        Cave cave = new Cave(new Player(scanner.nextLine()));
        
        boolean running = true;
        while (running) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Anna hirviön tyyppi: ");
                    String type = scanner.nextLine();
                    System.out.print("Anna hirviön elämän määrä numerona: ");
                    int health = scanner.nextInt();
                    cave.addMonster(new Monster(type, health));
                    break;
                case 2:
                    System.out.println("Luolan hirviöt:");
                    cave.listMonsters();
                    break;
                case 3:
                    System.out.print("Valitse hirviö, johon hyökätä:\n");
                    cave.listMonsters();
                    int index = scanner.nextInt();
                    cave.attackMonster(index);
                    break;
                case 4:
                    System.out.print("Anna tiedoston nimi, johon peli tallentaa: ");
                    String saveFile = scanner.next();
                    cave.saveGame(saveFile);
                    break;
                case 5:
                    System.out.print("Anna tiedoston nimi, josta peli ladataan: ");
                    String loadFile = scanner.next();
                    Cave loadedCave = Cave.loadGame(loadFile);
                    if (loadedCave != null) {
                        cave = loadedCave;
                        System.out.println("Tervetuloa takaisin, " + cave.player.name + ".");
                    }
                    break;
                case 0:
                    System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                    running = false;
                    break;
                default:
                    System.out.println("Virheellinen valinta.");
                    break;
            }
        }
        scanner.close();
    }
}
