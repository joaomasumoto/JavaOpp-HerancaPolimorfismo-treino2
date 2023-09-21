package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> lista = new ArrayList<>();

		System.out.print("Enter the number of products: ");
		int n = sc.nextInt();

		for (int j = 1; j <= n; j++) {

			System.out.println("Product #" + j + " data:");
			char productType;

			do {
				System.out.print("Common, used or imported (c/u/i)? ");
				productType = sc.next().charAt(0);
				
				if (productType != 'c' && productType != 'u' && productType != 'i') {
					System.out.println("Tipo não identificado, utilizar o padrão (c/u/i). Tente novamente.");
				}
			} while (productType != 'c' && productType != 'u' && productType != 'i');

			System.out.print("Nome: ");
			sc.nextLine();
			String nome = sc.nextLine();
			System.out.print("Preço: ");
			Double preco = sc.nextDouble();

			if (productType == 'c') {
				lista.add(new Product(nome, preco));

			} else if (productType == 'u') {
				System.out.print("Data de fabricação: (DD/MM/YYYY)");
				LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				lista.add(new UsedProduct(nome, preco, date));

			} else {
				System.out.print("Customs fee: ");
				Double customsFee = sc.nextDouble();
				lista.add(new ImportedProduct(nome, preco, customsFee));

			}
		}
		
		System.out.println();
		System.out.println("ETIQUETAS DE PREÇO:");
		
		for (Product produto: lista) {
			System.out.println(produto.priceTag());
		}

		sc.close();
	}

}
