//KELOMPOK 1A :
//        RYAN HIDAYAT (60900121020)
//        SITI AISYAH (60900121013)
//        MURNI (60900121014)
//        YUNITA (60900121009)
//        ARDY (60900117046)

package perangKapal;

import java.util.Scanner;

public class BattleShipGame {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Orang.Player player1 = new Orang.Player();
        Orang.Player player2 = new Orang.Player();
        Komputer.Player pemain1 = new Komputer.Player();
        Komputer.Computer computer = new Komputer.Computer();

        int mode;
        System.out.println("Selamat datang di permainan Battleship!");
        System.out.println("Pilih mode permainan:");
        System.out.println("1. Melawan Komputer");
        System.out.println("2. Melawan Pemain Lain");
        System.out.print("Masukkan pilihan mode (1/2) : ");
        mode = input.nextInt();
        input.nextLine(); // Membuang karakter newline (\n)

        if (mode == 2) {
            System.out.print("\nMasukkan Nama Pemain 1 : ");
            Orang.player1Name = input.nextLine();

            System.out.print("Masukkan Nama Pemain 2 : ");
            Orang.player2Name = input.nextLine();

            System.out.print("\nMasukkan Jumlah Kapal : ");
            Orang.numRounds = input.nextInt();

            Orang.printOceanMap();
            player1.deployShips();
            player2.deployShips();
            Orang.printOceanMap();


        } else if (mode == 1){
            System.out.print("\nMasukkan Nama Pemain 1 : ");
            Komputer.player1Name = input.nextLine();
            Komputer.player2Name = "Komputer";

            System.out.print("\nMasukkan Jumlah Kapal : ");
            Komputer.numRounds = input.nextInt();

            Komputer.printOceanMap();
            pemain1.deployShips();
            computer.deployShips();
            Komputer.printOceanMap();

        }else{
            System.out.println("PILIHAN TIDAK TERSEDIA");
            System.exit(0);
        }

        if(mode == 1){
            computer.getOceanMap();
        }

        int currentPlayer = 1;
        boolean gameEnd = false;

        while (!gameEnd) {
            if(mode == 1){
                if (currentPlayer == 1) {
                    System.out.println("\nGILIRAN " + Komputer.player1Name + " MENEMBAK :");
                    pemain1.attack(computer.getOceanMap());
                    pemain1.attack(computer.getOceanMap());
                    Komputer.printOceanMap();
                    gameEnd = !computer.hasShipsRemaining();
                    if (Komputer.player1Deployed > 0 && Komputer.computerDeployed <= 0) {
                        System.out.println("\nSemua kapal " + Komputer.player2Name +" telah tenggelam!");
                        System.out.println(Komputer.player1Name + " MENANG!");
                    }
                }else {
                    System.out.println("\nGILIRAN KOMPUTER MENEMBAK :");
                    computer.attack(pemain1.getOceanMap());
                    computer.attack(pemain1.getOceanMap());
                    Komputer.printOceanMap();
                    gameEnd = !pemain1.hasShipsRemaining();
                    if (Komputer.player1Deployed <= 0 && Komputer.computerDeployed > 0) {
                        System.out.println("\nSemua kapal " + Komputer.player1Name +" telah tenggelam!");
                        System.out.println(Komputer.player2Name + " MENANG!");
                    }else if(!pemain1.hasShipsRemaining() && !computer.hasShipsRemaining()){
                        System.out.println("HASILNYA SERI !!!");
                    }
                }
            }else{
                if (currentPlayer == 1) {
                    System.out.println("\nGILIRAN " + Orang.player1Name + " MENEMBAK :");
                    player1.attack(player2.getOceanMap());
                    player1.attack(player2.getOceanMap());
                    Orang.printOceanMap();
                    gameEnd = !player2.hasShipsRemaining();
                    if (gameEnd && !player1.hasShipsRemaining()) {
                        System.out.println("\nSemua kapal " + Orang.player1Name +" telah tenggelam!");
                        System.out.println(Orang.player2Name + " MENANG!");
                    }
                } else {
                    System.out.println("\nGILIRAN " + Orang.player2Name + " MENEMBAK :");
                    player2.attack2(player1.getOceanMap());
                    player2.attack2(player1.getOceanMap());
                    Orang.printOceanMap();
                    gameEnd = !player1.hasShipsRemaining();
                    if (gameEnd && !player2.hasShipsRemaining()) {
                        System.out.println("\nSemua kapal " + Orang.player2Name +" telah tenggelam!");
                        System.out.println(Orang.player1Name + " MENANG!");
                    }else if(!player1.hasShipsRemaining() && !player2.hasShipsRemaining()){
                        System.out.println("HASILNYA SERI !!!");
                    }
                }

            }
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        System.out.println("* * * * * GAME OVER * * * * *");
    }
}