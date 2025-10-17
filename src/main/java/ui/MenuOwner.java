package ui;

import model.Owner;
import service.OwnerService;
import util.VerifyUserInput;

import java.util.Scanner;

import static util.VerifyUserInput.verifyNumberInput;
import static util.VerifyUserInput.verifyStringToBooleanUserInput;

public class MenuOwner {

    // Service de donos, onde esta toda a logica de negocio
    private static final OwnerService ownerService = new OwnerService();
    private static final Scanner sc = new Scanner(System.in);

    public static Owner menuCreateOwner() throws Exception{

        System.out.println("======== CADASTRO DONO =========");
        System.out.println("Deseja cadastrar um novo dono? ( S / N )");
        String opString = sc.next();
        sc.nextLine();

        // Se o usuario escolher nao cadastrar um novo dono ele vai para a opcao de inserir o cpf de um existente
        if (!(verifyStringToBooleanUserInput(opString))){
            System.out.println("Digite o CPF do dono XXX.XXX.XXX-XX");
            String cpfInputUser = sc.nextLine();
            // Verifica se o CPF esta no modelo certo
            String cpf = VerifyUserInput.verifyCpfInput(cpfInputUser);

            System.out.println("Buscando em nosso bando de dados ...");

            // Tenta buscar na lista do Service onde esta todos os donos se o CPF ja esta cadastrado ou nao
            try{
                Owner owner = ownerService.getOwnerByCpf(cpf);

                menuShowOwnerByCpf(cpf);

                return owner;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        // Essa a parte de cadastro de um novo dono
        // Vai inserir Nome, CPF (tem verificacao para ver se segue o padrao) e numero de contato (tem verificacao para ver se segue o padrao)
        System.out.println("Digite o nome do dono do imovel");
        String name = sc.nextLine();

        System.out.println("Digite o Numero de contato do dono do imovel (XX) XXXXX-XXXX");
        String numberInputUser = sc.nextLine();
        String number = verifyNumberInput(numberInputUser);

        System.out.println("Digite o CPF do dono do imovel XXX.XXX.XXX-XX");
        String cpfInputUser = sc.nextLine();

        String cpf = VerifyUserInput.verifyCpfInput(cpfInputUser);

        // Verifica se ja existe alguem com esse CPF cadastrado
        if (!ownerService.isCpfAlreadyRegister(cpf)) {

            System.out.println("Criando dono ...");

            ownerService.addOwner(name,number,cpf);

            return ownerService.getOwnerByCpf(cpf);

        }

        System.out.println("Ja existe um usuario com esse CPF");
        return null;

    }

    // Mostra todos os Donos
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

    // Mostra o dono com base no CPF que for passado
    public static void menuShowOwnerByCpf(String cpf) throws Exception {

        Owner owner = ownerService.getOwnerByCpf(cpf);
        System.out.println("=====================");
        System.out.println("Nome do dono: " + owner.getName());
        System.out.println("Cpf do dono: " + owner.getCpf());
        System.out.println("Numero do dono: " + owner.getNumber());
        System.out.println("========================");

    }

}
