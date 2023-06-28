package main;

import java.util.Scanner;

import main.*;

public class Hotel {
	static Holder ob = new Holder();
	static Scanner sc = new Scanner(System.in);

	static void CustDetails(int i, int rn) {
		String name, contact, gender; 
		String name2 = null, contact2 = null ;
		String gender2 = "";
		System.out.print("\nMasukkan nama pelanggan: ");
		name = sc.next();
		System.out.print("Masukkan nomor kontak: ");
		contact = sc.next();
		System.out.print("Masukkan jenis kelamin: ");
		gender = sc.next();
		if (i < 3) {
			System.out.print("Masukkan nama pelanggan kedua: ");
			name2 = sc.next();
			System.out.print("Masukkan nomor kontak: ");
			contact2 = sc.next();
			System.out.print("Masukkan jenis kelamin: ");
			gender2 = sc.next();
		}

		switch (i) {
		case 1:
			ob.arr1[rn] = new DoubleRoom(name, contact, gender, name2, contact2, gender2);
			break;
		case 2:
			ob.arr2[rn] = new DoubleRoom(name, contact, gender, name2, contact2, gender2);
			break;
		case 3:
			ob.arr3[rn] = new SingleRoom(name, contact, gender);
			break;
		case 4:
			ob.arr4[rn] = new SingleRoom(name, contact, gender);
			break;
		default:
			System.out.println("Pilihan tidak tersedia");
			break;
		}
	}

	static void bookroom(int i) {
		int j;
		int rn;
		System.out.println("\nPilih nomor kamar dari: ");
		switch (i) {
		case 1:
			for (j = 0; j < ob.arr1.length; j++) {
				if (ob.arr1[j] == null) {
					System.out.print(j + 1 + ",");
				}
			}
			System.out.print("\nMasukkan nomor kamar: ");
			try {
				rn = sc.nextInt();
				rn--;
				if (ob.arr1[rn] != null)
					throw new NotAvailable();
				CustDetails(i, rn);
			} catch (Exception e) {
				System.out.println("Pilihan tidak tersedia");
				return;
			}
			break;
		case 2:
			for (j = 0; j < ob.arr2.length; j++) {
				if (ob.arr2[j] == null) {
					System.out.print(j + 11 + ",");
				}
			}
			System.out.print("\nMasukkan nomor kamar: ");
			try {
				rn = sc.nextInt();
				rn = rn - 11;
				if (ob.arr2[rn] != null)
					throw new NotAvailable();
				CustDetails(i, rn);
			} catch (Exception e) {
				System.out.println("pilihan tidak tersedia");
				return;
			}
			break;
		case 3:
			for (j = 0; j < ob.arr3.length; j++) {
				if (ob.arr3[j] == null) {
					System.out.print(j + 31 + ",");
				}
			}
			System.out.print("\nMasukkan nomor kamar: ");
			try {
				rn = sc.nextInt();
				rn = rn - 31;
				if (ob.arr3[rn] != null)
					throw new NotAvailable();
				CustDetails(i, rn);
			} catch (Exception e) {
				System.out.println("pilihan tidak tersedia");
				return;
			}
			break;
		case 4:
			for (j = 0; j < ob.arr4.length; j++) {
				if (ob.arr4[j] == null) {
					System.out.print(j + 41 + ",");
				}
			}
			System.out.print("\nMasukkan nomor kamar: ");
			try {
				rn = sc.nextInt();
				rn = rn - 41;
				if (ob.arr4[rn] != null)
					throw new NotAvailable();
				CustDetails(i, rn);
			} catch (Exception e) {
				System.out.println("Pilihan tidak tersedia");
				return;
			}
			break;
		default:
			System.out.println("Masukkan nomor kamar");
			break;
		}
		System.out.println("Kamar telah dipesan");
	}

	static void features(int i) {
		switch (i) {
		case 1:
			System.out.println("Number of double beds : 1\nAC : Yes\nGratis sarapan : Yes\nBiaya per hari:4000 ");
			break;
		case 2:
			System.out.println("Number of double beds : 1\nAC : No\nGratis sarapan : Yes\nBiaya per hari:3000 ");
			break;
		case 3:
			System.out.println("Number of single beds : 1\nAC : Yes\nGratis sarapan : Yes\nBiaya per hari:2200  ");
			break;
		case 4:
			System.out.println("Number of single beds : 1\nAC : No\nGratis sarapan : Yes\nBiaya per hari:1200 ");
			break;
		default:
			System.out.println("Masukkan Pilihan");
			break;
		}
	}

	static void availability(int i) {
		int j, count = 0;
		switch (i) {
		case 1:
			for (j = 0; j < 10; j++) {
				if (ob.arr1[j] == null)
					count++;
			}
			break;
		case 2:
			for (j = 0; j < ob.arr2.length; j++) {
				if (ob.arr2[j] == null)
					count++;
			}
			break;
		case 3:
			for (j = 0; j < ob.arr3.length; j++) {
				if (ob.arr3[j] == null)
					count++;
			}
			break;
		case 4:
			for (j = 0; j < ob.arr4.length; j++) {
				if (ob.arr4[j] == null)
					count++;
			}
			break;
		default:
			System.out.println("Masukkan pilihan");
			break;
		}
		System.out.println("Jumlah kamar yang tersedia : " + count);
	}

	static void bill(int rn, int rtype) {
		double amount = 0;
		String list[] = { "Sandwich", "Pasta", "Mie", "Soda" };
		System.out.println("\n*******");
		System.out.println(" Tagihan:-");
		System.out.println("*******");

		switch (rtype) {
		case 1:
			amount += 4000;
			System.out.println("\nBiaya kamar - " + 4000);
			System.out.println("\n===============");
			System.out.println("Biaya makanan:- ");
			System.out.println("===============");
			System.out.println("Harga kuantitas barang");
			System.out.println("-------------------------");
			for (Food obb : ob.arr1[rn].food) {
				amount += obb.cost;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemNo - 1], obb.quantity, obb.cost);
			}

			break;
		case 2:
			amount += 3000;
			System.out.println("Biaya kamar - " + 3000);
			System.out.println("\nBiaya makanan:- ");
			System.out.println("===============");
			System.out.println("Harga kuantitas barang");
			System.out.println("-------------------------");
			for (Food obb : ob.arr2[rn].food) {
				amount += obb.cost;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemNo - 1], obb.quantity, obb.cost);
			}
			break;
		case 3:
			amount += 2200;
			System.out.println("Biaya kamar - " + 2200);
			System.out.println("\nBiaya makanan:- ");
			System.out.println("===============");
			System.out.println("Harga kuantitas barang");
			System.out.println("-------------------------");
			for (Food obb : ob.arr3[rn].food) {
				amount += obb.cost;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemNo - 1], obb.quantity, obb.cost);
			}
			break;
		case 4:
			amount += 1200;
			System.out.println("Biaya kamar - " + 1200);
			System.out.println("\nBiaya makanan:- ");
			System.out.println("===============");
			System.out.println("Harga kuantitas barang");
			System.out.println("-------------------------");
			for (Food obb : ob.arr4[rn].food) {
				amount += obb.cost;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemNo - 1], obb.quantity, obb.cost);
			}
			break;
		default:
			System.out.println("Pilihan tidak tersedia");
		}
		System.out.println("\nTotal jumlah- " + amount);
	}

	static void deallocate(int rn, int rtype) {
		int j;
		char w;
		switch (rtype) {
		case 1:
			if (ob.arr1[rn] != null)
				System.out.println("Ruangan yang digunakan oleh " + ob.arr1[rn].name);
			else {
				System.out.println("Sudah kosong");
				return;
			}
			System.out.println("Apakah Anda ingin memesan ?(y/n)");
			w = sc.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				bill(rn, rtype);
				ob.arr1[rn] = null;
				System.out.println("Dealokasi berhasil");
			}

			break;
		case 2:
			if (ob.arr2[rn] != null)
				System.out.println("Ruangan yang digunakan oleh " + ob.arr2[rn].name);
			else {
				System.out.println("Sudah kosong");
				return;
			}
			System.out.println("Apakah Anda ingin memesan ?(y/n)");
			w = sc.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				bill(rn, rtype);
				ob.arr2[rn] = null;
				System.out.println("dealokasi berhasil");
			}

			break;
		case 3:
			if (ob.arr3[rn] != null)
				System.out.println("Ruangan yang digunakan oleh " + ob.arr3[rn].name);
			else {
				System.out.println("Sudah kosong");
				return;
			}
			System.out.println(" Apakah Anda ingin memesan ? (y/n)");
			w = sc.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				bill(rn, rtype);
				ob.arr3[rn] = null;
				System.out.println("Dealokasi berhasil");
			}

			break;
		case 4:
			if (ob.arr4[rn] != null)
				System.out.println("Ruangan yang digunakan oleh " + ob.arr4[rn].name);
			else {
				System.out.println("Sudah kosong");
				return;
			}
			System.out.println(" Apakah Anda ingin memesan ? (y/n)");
			w = sc.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				bill(rn, rtype);
				ob.arr4[rn] = null;
				System.out.println("Dealokasi berhasil");
			}
			break;
		default:
			System.out.println("\nMasukkan pilihan anda : ");
			break;
		}
	}

	static void order(int rn, int rtype) {
		int i, q;
		char wish;
		try {
			System.out.println(
					"\n==========\n   Menu:  \n==========\n\n1. Ayam Bakar\tRp.25.0000\n2. Sate Ayam\t\tRp. 20.000\n3. Mie Bakso\tRp.15.000\n4. Ayam Geprek\t\tRp. 10.000\n");
			do {
				i = sc.nextInt();
				System.out.print("Quantity- ");
				q = sc.nextInt();

				switch (rtype) {
				case 1:
					ob.arr1[rn].food.add(new Food(i, q));
					break;
				case 2:
					ob.arr2[rn].food.add(new Food(i, q));
					break;
				case 3:
					ob.arr3[rn].food.add(new Food(i, q));
					break;
				case 4:
					ob.arr4[rn].food.add(new Food(i, q));
					break;
				}
				System.out.println("Apakah Anda ingin memesan yang lain ? (y/n)");
				wish = sc.next().charAt(0);
			} while (wish == 'y' || wish == 'Y');
		} catch (NullPointerException e) {
			System.out.println("\nkamar tidak dipesan");
		} catch (Exception e) {
			System.out.println("Tidak bisa dilakukan");
		}
	}
}
