package ui;

import java.util.Scanner;

import static util.VerifyUserInput.verifyStringToBooleanUserInput;

public class MenuMain {

    private final Scanner sc = new Scanner(System.in);

    // Menu principal
    public void openMenu() throws Exception{

        System.out.println("==== BEM-VINDO(A) A NOSSA IMOBILIARIA ===");
        System.out.println("Oque deseja? ");
        System.out.println("1 - Cadastrar um Imovel");
        System.out.println("2 - Cadastrar um Dono de Imovel / Consultar um dono de Imovel");
        System.out.println("3 - Ver todos os imoveis");
        System.out.println("4 - Remover um Imovel");
        System.out.println("5 - Calcular aluguel de um Imovel");
        System.out.println("6 - Alugar um imovel");
        System.out.println("7 - Desoculpar um imovel");
        System.out.println("8 - Ver todos os donos de Imovel");
        System.out.println("0 - Sair");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op){

            case 1:
                MenuProperty.menuCreationProperty();
                break;
            case 2:
                MenuOwner.menuCreateOwner();
                break;
            case 3:
                MenuProperty.menuOfAllProperties();
                break;
            case 4:
                MenuProperty.menuRemoveProperty();
                break;
            case 5:
                MenuProperty.menuCalculateRentPropertyValue();
                break;
            case 6:
                MenuProperty.menuRentProperty();
                break;
            case 7:
                MenuProperty.menuVacateProperty();
                break;
            case 8:
                MenuOwner.menuShowAllOwners();
            default:
                System.out.println("Opcao invalida");
                break;

        }

        System.out.println("Deseja continuar? ( S / N )");
        String wantContinueString = sc.next();

        if (verifyStringToBooleanUserInput(wantContinueString)) {

            openMenu();
        }

        sc.close();

    }

}
