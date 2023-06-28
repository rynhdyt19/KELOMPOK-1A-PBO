package perangKapal;

import java.util.Random;
import java.util.Scanner;

public class Komputer {
    private static final char PLAYER_1_SHIP = 'P';
    private static final char PLAYER_1_MISSED = '*';
    private static final char PLAYER_1_SUNKEN = 'X';
    private static final char EMPTY = '\000';
    private static final char COMPUTER_SUNKEN = '!';
    private static final char COMPUTER_SHIP = 'C';
    private static final char COMPUTER_MISSED = '~';
    private static final int GRID_SIZE = 50;
    private static char[][] oceanMap = new char[GRID_SIZE][GRID_SIZE];
    private static Scanner input = new Scanner(System.in);
    static int player1Deployed;
    static int computerDeployed;
    static int numRounds;
    public static String player1Name;
    public static String player2Name;

    public static void printOceanMap() {
        printHeader();
        for (int i = 0; i < oceanMap.length; i++) {
            System.out.print(" " + i + " | ");
            for (char location : oceanMap[i]) {
                switch (location) {
                    case PLAYER_1_SHIP:
                        System.out.print(" " + 'P' + " ");
                        break;
                    case PLAYER_1_MISSED:
                        System.out.print(" " + '*' + " ");
                        break;
                    case COMPUTER_SHIP:
                        System.out.print(" " + 'C' + " ");
                        break;
                    case COMPUTER_MISSED:
                        System.out.print(" " + '~' + " ");
                        break;
                    case EMPTY:
                        System.out.print(" " + ' ' + " ");
                        break;
                    default:
                        System.out.print(location + " ");
                        break;
                }
            }
            System.out.print("| " + i + " ");
            System.out.println();
        }
        printFooter();
    }
    private static void printHeader() {
        System.out.println();
        System.out.println("** BATTLESHIP BATTLE ARENA **");
        if (player1Deployed == 0 && computerDeployed == 0) {
            System.out.println("Kondisi Sekarang, Arena Sedang Kosong!");
        }
        System.out.println("----- 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 ----");
    }
    private static void printFooter() {
        System.out.println("------ 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 ----");
        System.out.println("Kapal " + player1Name + " : " + player1Deployed + " | Kapal " + player2Name + " : " + computerDeployed);
        System.out.println();
    }
    private interface Ship {
        void deployShips();

        boolean hasShipsRemaining();

        char[][] getOceanMap();
    }
    public static class Player implements Ship {
        @Override
        public void deployShips() {
            while (player1Deployed < numRounds) {
                int x = assignPlayer1('X');
                int y = assignPlayer1('Y');
                if (oceanMap[y][x] != PLAYER_1_SHIP && oceanMap[y][x] != COMPUTER_SHIP) {
                    oceanMap[y][x] = PLAYER_1_SHIP;
                    player1Deployed++;
                } else {
                    System.out.println("Koordinat (" + x + ", " + y + ") sudah digunakan. Silakan pilih koordinat lain.");
                }
            }
            System.out.println(player1Name + " telah mengatur " + player1Deployed + " kapal");
            System.out.println();
        }
        @Override
        public boolean hasShipsRemaining() {
            return player1Deployed > 0 && computerDeployed > 0;
        }
        @Override
        public char[][] getOceanMap() {
            return oceanMap;
        }
        private int assignPlayer1(char Yx) {
            int i = -1;
            while (i == -1) {
                System.out.print("Masukkan " + Yx + " Koordinat untuk kapal ke-" + (player1Deployed + 1) + ": ");
                if (!input.hasNextInt()) {
                    input.nextLine();
                } else {
                    i = input.nextInt();
                    if (i < 0 || 49 < i) {
                        i = -1;
                        System.out.println("Harap masukkan angka dari 0 sampai 49");
                    }
                }
            }
            return i;
        }
        public void attack(char[][] enemyOceanMap) {
            int x = attack('X');
            int y = attack('Y');
            switch (enemyOceanMap[y][x]) {
                case PLAYER_1_SHIP -> {
                    enemyOceanMap[y][x] = PLAYER_1_SUNKEN;
                    player1Deployed--;
                    System.out.println("\nOh tidak, kamu menenggelamkan kapalmu sendiri !");
                }
                case COMPUTER_SHIP -> {
                    enemyOceanMap[y][x] = COMPUTER_SUNKEN;
                    computerDeployed--;
                    System.out.println("\nYEEEAH!, kamu menenggelamkan kapal lawan !");
                }
                case EMPTY -> {
                    enemyOceanMap[y][x] = PLAYER_1_MISSED;
                    System.out.println("\nMaaf, kamu meleset");
                }
                default -> {
                    System.out.println("\nKoordinat sudah digunakan. Silakan pilih koordinat lain.\n");
                    attack(enemyOceanMap); // Memanggil ulang serangan pemain
                }
            }
        }

        private int attack(char Yx) {
            int i = -1;
            while (i == -1) {
                System.out.print("Masukkan Koordinat " + Yx + ": ");
                if (!input.hasNextInt()) {
                    input.nextLine();
                    System.out.println("\n");
                } else {
                    i = input.nextInt();
                    if (i < 0 || 49 < i) {
                        i = -1;
                        System.out.println("Harap masukkan angka dari 0 sampai 49");
                    }
                }
            }
            return i;
        }
    }

    public static class Computer implements Ship {

        @Override
        public void deployShips() {
            System.out.println("\nKomputer mengatur kapal");
            while (computerDeployed < numRounds) {
                Random rand = new Random();
                int x = rand.nextInt(GRID_SIZE);
                int y = rand.nextInt(GRID_SIZE);
                if (oceanMap[y][x] != PLAYER_1_SHIP && oceanMap[y][x] != COMPUTER_SHIP) {
                    oceanMap[y][x] = COMPUTER_SHIP;
                    computerDeployed++;
                }
            }
            System.out.println("Komputer telah mengatur " + computerDeployed + " kapal");
            System.out.println();
        }

        @Override
        public boolean hasShipsRemaining() {
            return computerDeployed > 0;
        }

        @Override
        public char[][] getOceanMap() {
            return oceanMap;
        }

        public void attack(char[][] enemyOceanMap) {
            Random rand = new Random();
            int x = rand.nextInt(GRID_SIZE);
            int y = rand.nextInt(GRID_SIZE);
            switch (enemyOceanMap[y][x]) {
                case PLAYER_1_SHIP:
                    enemyOceanMap[y][x] = PLAYER_1_SUNKEN;
                    player1Deployed--;
                    System.out.println("Komputer menenggelamkan kapal " + player1Name + "!");
                    break;
                case EMPTY:
                    enemyOceanMap[y][x] = COMPUTER_MISSED;
                    System.out.println("Komputer meleset!");
                    break;
                default:
                    attack(enemyOceanMap); // Memanggil ulang serangan komputer
                    break;
            }
        }
    }
}
