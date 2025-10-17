package ui;

import model.Owner;
import service.OwnerService;

import java.util.Scanner;

import static util.VerifyUserInput.verifyStringToBooleanUserInput;

public class MenuOwner {

    private static final OwnerService ownerService = new OwnerService();
    private static final Scanner sc = new Scanner(System.in);

    public static Owner menuCreateOwner() throws Exception{

        System.out.println("======== CADASTRO DONO =========");
        System.out.println("Deseja cadastrar um novo dono? ( S / N )");
        String opString = sc.next();
        sc.nextLine();

        if (!(verifyStringToBooleanUserInput(opString))){
            System.out.println("Digite o CPF do dono");
            String cpf = sc.nextLine();

            System.out.println("Buscando em nosso bando de dados ...");

            try{
                Owner owner = ownerService.getOwnerByCpf(cpf);

                menuShowOwnerByCpf(cpf);

                return owner;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        System.out.println("Digite o nome do dono do imovel");
        String name = sc.nextLine();

        System.out.println("Digite o Numero de contato do dono do imovel");
        String number = sc.nextLine();

        System.out.println("Digite o CPF do dono do imovel");
        String cpf = sc.nextLine();

        System.out.println("Criando dono ...");

        ownerService.addOwner(name,number,cpf);

        return ownerService.getOwnerByCpf(cpf);
    }

    public static void menuShowAllOwners(){

        System.out.println("========= DONOS ============");

        for (Owner owner: ownerService.getAllOwners()){

            System.out.println("=====================");
            System.out.println("Nome do dono: " + owner.getName());
            System.out.println("Cpf do dono: " + owner.getCpf());
            System.out.println("Numero do dono: " + owner.getNumber());
            System.out.println("========================");

        }

    }

    public static void menuShowOwnerByCpf(String cpf) throws Exception {

        Owner owner = ownerService.getOwnerByCpf(cpf);
        System.out.println("=====================");
        System.out.println("Nome do dono: " + owner.getName());
        System.out.println("Cpf do dono: " + owner.getCpf());
        System.out.println("Numero do dono: " + owner.getNumber());
        System.out.println("========================");

    }

}
