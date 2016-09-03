package crc;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入原始报文,用回车结尾");
		CRC crc = new CRC(sc.nextLine());
		sc.close();
		crc.incode(crc.getData());
	}
}
