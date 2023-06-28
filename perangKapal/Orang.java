package perangKapal;

import java.util.Scanner;
public class Orang {
    private static final char PLAYER_1_SHIP = '1'; // Output = '@'
    private static final char PLAYER1_MISSED = '*';
    private static final char PLAYER_1_SUNKEN = 'X';
    private static final char PLAYER_2_SHIP = '2'; // Output = '#'
    private static final char PLAYER2_MISSED = '~';
    private static final char PLAYER_2_SUNKEN = 'X';
    private static final char EMPTY = '\000'; // Output = ' ';
    private static final int GRID_SIZE = 50;
    private static char[][] oceanMap = new char[GRID_SIZE][GRID_SIZE];
    private static Scanner input = new Scanner(System.in);
    static int player1Deployed;
    static int player2Deployed;
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
                        System.out.print(" " + '1' + " ");
                        break;
                    case PLAYER1_MISSED:
                        System.out.print(" " + '*' + " ");
                        break;
                    case PLAYER_2_SHIP:
                        System.out.print(" " + '2' + " ");
                        break;
                    case PLAYER2_MISSED:
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
        if (player1Deployed == 0 && player2Deployed == 0) {
            System.out.println("Kondisi Sekarang, Arena Sedang Kosong!");
        }
        System.out.println("----- 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 ----");
    }
    private static void printFooter() {
        System.out.println("------ 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 ----");
        System.out.println("Kapal " + player1Name + ": " + player1Deployed + " | Kapal " + player2Name + ": " + player2Deployed);
        System.out.println();
    }

    private interface Ship {
        void deployShips();

        boolean hasShipsRemaining();

        char[][] getOceanMap();
    }

    public static class Player implements Ship {

//        private String playerName;
//
//        public Player() {
//        }
//
//        public Player(String playerName) {
//            this.playerName = playerName;
//        }

        @Override
        public void deployShips() {
            System.out.println(player1Name + " TOLONG ATUR KAPAL ANDA\n");
            while (player1Deployed < numRounds) {
                int x = assignPlayer1('X');
                int y = assignPlayer1('Y');
                if (oceanMap[y][x] != PLAYER_1_SHIP && oceanMap[y][x] != PLAYER_2_SHIP) {
                    oceanMap[y][x] = PLAYER_1_SHIP;
                    player1Deployed++;
                } else {
                    System.out.println("Koordinat (" + x + ", " + y + ") sudah digunakan. Silakan pilih koordinat lain.");
                }
            }
            System.out.println(player1Name + " telah mengatur " + player1Deployed + " kapal");
            System.out.println();

            System.out.println(player2Name + " TOLONG ATUR KAPAL ANDA\n");
            while (player2Deployed < numRounds) {
                int a = assignPlayer2('X');
                int b = assignPlayer2('Y');
                if (oceanMap[a][b] != PLAYER_1_SHIP && oceanMap[a][b] != PLAYER_2_SHIP) {
                    oceanMap[a][b] = PLAYER_2_SHIP;
                    player2Deployed++;
                } else {
                    System.out.println("Koordinat (" + a + ", " + b + ") sudah digunakan. Silakan pilih koordinat lain.");
                }
            }
            System.out.println(player2Name + " telah mengatur " + player2Deployed + " kapal");
            System.out.println();
            numRounds = player1Deployed; // Mengubah nilai numRounds sesuai dengan jumlah kapal yang ditempatkan oleh pemain 1
        }


        @Override
        public boolean hasShipsRemaining() {
            return player1Deployed > 0 && player2Deployed > 0;
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

        private int assignPlayer2(char Yx) {
            int i = -1;
            while (i == -1) {
                System.out.print("Masukkan " + Yx + " Koordinat untuk kapal ke-" + (player2Deployed + 1) + ": ");
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
                case PLAYER_2_SHIP -> {
                    enemyOceanMap[y][x] = PLAYER_2_SUNKEN;
                    player2Deployed--;
                    System.out.println("\nYEEEAH!, kamu menenggelamkan kapal lawan !");
                }
                case EMPTY -> {
                    enemyOceanMap[y][x] = PLAYER1_MISSED;
                    System.out.println("\nMaaf, kamu meleset");
                }
                default -> {
                    System.out.println("\nKoordinat sudah digunakan. Silakan pilih koordinat lain.\n");
                    attack(enemyOceanMap); // Memanggil ulang serangan pemain
                }
            }
        }

        public void attack2(char[][] enemyOceanMap) {
            int x = attack('X');
            int y = attack('Y');
            switch (enemyOceanMap[y][x]) {
                case PLAYER_2_SHIP -> {
                    enemyOceanMap[y][x] = PLAYER_2_SUNKEN;
                    player2Deployed--;
                    System.out.println("\nOh tidak, kamu menenggelamkan kapalmu sendiri !");
                }
                case PLAYER_1_SHIP -> {
                    enemyOceanMap[y][x] = PLAYER_1_SUNKEN;
                    player1Deployed--;
                    System.out.println("\nYEEEAH!, kamu menenggelamkan kapal lawan !");
                }
                case EMPTY -> {
                    enemyOceanMap[y][x] = PLAYER2_MISSED;
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
}
