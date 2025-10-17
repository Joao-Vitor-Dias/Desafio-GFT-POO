package util;

import java.util.Scanner;

public class VerifyUserInput {

    /*
    * Recebe uma String como parametro sendo ela o S(true) ou N(false) e retorna o boolean de acordo com o input
    * que foi recebido.
    * */
    public static boolean verifyStringToBooleanUserInput(String isRent){

        Scanner sc = new Scanner(System.in);

        while (!(isRent.equalsIgnoreCase("S") || isRent.equalsIgnoreCase("N"))){

            System.out.println("Opcao invalida!!!");
            System.out.println("Insira novamente ... (S/N)");
            isRent = sc.next();

        }

        return isRent.equalsIgnoreCase("S");

    }

}
