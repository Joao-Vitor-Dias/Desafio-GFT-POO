package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String verifyCpfInput(String cpf){

        Scanner sc = new Scanner(System.in);

        String regex = "^(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(cpf);

        while (!(pattern.matcher(cpf).matches())){

            System.out.println("CPF invalido ...");
            System.out.println("Insira outro: ");
            cpf = sc.nextLine();

        }



        return cpf;
    }

}
