package ui;

import model.Owner;
import model.Property;
import service.OwnerService;
import service.PropertyService;

import java.util.Scanner;

import static util.VerifyUserInput.verifyStringToBooleanUserInput;

public class Menu {

    private final PropertyService propertyService = new PropertyService();
    private final OwnerService ownerService = new OwnerService();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu(){

        System.out.println("==== BEM-VINDO(A) A NOSSA IMOBILIARIA ===");
        System.out.println("Oque deseja? ");
        System.out.println("1 - Cadastrar um Imovel");
        System.out.println("2 - Cadastrar um Dono de Imovel");
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
                menuCreationProperty();
                break;
            case 2:
                menuCreateOwner();
                break;
            case 3:
                menuOfAllProperties();
                break;
            case 4:
                menuRemoveProperty();
                break;
            case 5:
                menuCalculateRentPropertyValue();
                break;
            case 6:
                menuRentProperty();
                break;
            case 7:
                menuVacateProperty();
                break;
            case 8:
                menuShowAllOwners();
            default:
                System.out.println("Opcao invalida");
                break;

        }

        System.out.println("Deseja continuar? ( S / N )");
        String wantContinueString = sc.next();

        if (verifyStringToBooleanUserInput(wantContinueString)) {
            mainMenu();
        }

        sc.close();

    }

    public void menuCreationProperty(){

        System.out.println("======== CADASTRO IMOVEL =========");

        System.out.println("Escolha qual opcao de imovel que deseja adicionar");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        String opString = sc.next();
        sc.nextLine();
        int op = Integer.parseInt(opString);

        System.out.println("Digite qual o endereco do imovel");
        String address = sc.nextLine();

        System.out.println("Digite o numero do imovel");
        String propertyNumber = sc.next();

        System.out.println("O imovel esta disponivel para aluguel? ( S / N )");
        String isRent = sc.next();

        System.out.println("Quanto custa o aluguel? (apenas valores inteiros)");
        int rentalValuePerMonth = sc.nextInt();
        sc.nextLine();

        System.out.println("================================");
        Owner owner = menuCreateOwner();

        propertyService.addProperty(op,address,propertyNumber,!(verifyStringToBooleanUserInput(isRent)),owner,rentalValuePerMonth);

        System.out.println("Adicionando propriedade ...");


    }

    public Owner menuCreateOwner(){

        System.out.println("======== CADASTRO DONO =========");
        System.out.println("Deseja cadastrar um novo dono? ( S / N )");
        String opString = sc.next();
        sc.nextLine();

        if (!(verifyStringToBooleanUserInput(opString))){
            System.out.println("Digite o CPF do dono");
            String cpf = sc.nextLine();

            System.out.println("Buscando em nosso bando de dados ...");
            return ownerService.getOwnerByCpf(cpf);
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

    public boolean menuOfAllProperties(){

        int cont = 0;
        boolean isFoundOne = false;

        System.out.println("============= IMOVEIS =============");

        for (Property property : PropertyService.getProperties()){

            System.out.println("=======================");
            System.out.println("Propriedade: " + cont);
            System.out.println("Endereco do imovel: " + property.getAddress());
            System.out.println("Numero do imovel: " + property.getPropertyNumber());
            System.out.println(property.isRented());
            System.out.println("Valor do aluguel: R$ " + property.getRentalValuePerMonth());
            System.out.println("Nome do dono do imovel: " + property.getOwner().getName());
            System.out.println("Numero de contato do dono: " + property.getOwnerContact());
            System.out.println("========================");
            isFoundOne = true;
            cont++;

        }

        return isFoundOne;

    }

    public void menuRemoveProperty(){

        boolean isContinued = menuOfAllProperties();

        if (!isContinued){
            System.out.println("Nao tem nem um imovel disponivel para remover");
            return;
        }

        System.out.println("Digite o ID da imovel");
        int id = sc.nextInt();
        sc.nextLine();

        propertyService.removeProperty(id);

        System.out.println("Removendo imovel ...");

    }

    public void menuCalculateRentPropertyValue(){

        boolean isContinued = menuOfAvailableProperty();

        if (!isContinued){
            System.out.println("Nao tem nem um imovel disponivel para alugar");
            return;
        }

        System.out.println("Insira o ID da imovel a qual deseja alugar: ");
        int propertyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira quantos meses planeja ficar na casa: ");
        int months = sc.nextInt();
        sc.nextLine();

        Property property = propertyService.findPropertyById(propertyId);
        double discountValue = property.calculateDiscount(months);
        int totalValueOfRental = property.calculateRental(months);

        System.out.println("Calculando aluguel ...");
        System.out.println("O valor de aluguel do imovel foi de " + totalValueOfRental);
        System.out.println("O valor do disconto foi de " + discountValue * 100 + " %");
        System.out.printf("A simulacao fica em R$ %.2f\n", totalValueOfRental - (totalValueOfRental * discountValue));

    }

    public void menuRentProperty(){

        boolean isContinued = menuOfAvailableProperty();

        if (!isContinued){
            System.out.println("Nao tem nem um imovel disponivel para alugar");
            return;
        }

        System.out.println("Insira o ID da imovel a qual deseja alugar: ");
        int propertyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira quantos meses planeja ficar na casa: ");
        int months = sc.nextInt();
        sc.nextLine();

        System.out.println("Alugando imovel ...");

        Property property = propertyService.findPropertyById(propertyId);
        property.setRent(true);

        double discountValue = property.calculateDiscount(months);
        int totalValueOfRental = property.calculateRental(months);

        System.out.println("Parabens !!! O imovel foi alugado com sucesso");
        System.out.println("O valor de aluguel do imovel foi de " + totalValueOfRental);
        System.out.println("O valor do disconto foi de " + discountValue * 100 + " %");
        System.out.println("O imovel foi alugado ao valor de R$ " + (totalValueOfRental - (totalValueOfRental * discountValue)) + ". Pelo total de " + months + " meses.");

    }

    public void menuVacateProperty(){

        boolean isContinued = menuOfUnavailableProperty();

        if (!isContinued){
            System.out.println("Nao foi alugado nem um imovel ate agora para poder ser desoculpado...");
            return;
        }

        System.out.println("Insira o ID do imovel que deseja desoculpar");
        int propertyId = sc.nextInt();
        sc.nextLine();

        Property property = propertyService.findPropertyById(propertyId);
        property.setRent(false);

        System.out.println("O imovel foi desoculpado ...");

    }

    public boolean menuOfAvailableProperty(){

        int cont = 0;
        boolean isFoundOne = false;

        for (Property property : PropertyService.getProperties()){

            if (!(property.isRent())) {
                System.out.println("=======================");
                System.out.println("Propriedade: " + cont);
                System.out.println("Endereco do imovel: " + property.getAddress());
                System.out.println("Numero do imovel: " + property.getPropertyNumber());
                System.out.println(property.isRented());
                System.out.println("Valor do aluguel: R$ " + property.getRentalValuePerMonth());
                System.out.println("Nome do dono do imovel: " + property.getOwner().getName());
                System.out.println("Numero de contato do dono: " + property.getOwnerContact());
                System.out.println("========================");
                isFoundOne = true;
            }
            cont++;

        }

        return isFoundOne;

    }

    public boolean menuOfUnavailableProperty(){

        int cont = 0;
        boolean isFoundOne = false;

        for (Property property : PropertyService.getProperties()){


            if (property.isRent()) {
                System.out.println("=======================");
                System.out.println("Propriedade: " + cont);
                System.out.println("Endereco do imovel: " + property.getAddress());
                System.out.println("Numero do imovel: " + property.getPropertyNumber());
                System.out.println(property.isRented());
                System.out.println("Valor do aluguel: R$ " + property.getRentalValuePerMonth());
                System.out.println("Nome do dono do imovel: " + property.getOwner().getName());
                System.out.println("Numero de contato do dono: " + property.getOwnerContact());
                System.out.println("========================");
                isFoundOne = true;
            }
            cont++;

        }

        return isFoundOne;

    }

    public void menuShowAllOwners(){

        System.out.println("========= DONOS ============");

        for (Owner owner: ownerService.getAllOwners()){

            System.out.println("=====================");
            System.out.println("Nome do dono: " + owner.getName());
            System.out.println("Cpf do dono: " + owner.getCpf());
            System.out.println("Numero do dono: " + owner.getNumber());
            System.out.println("========================");

        }

    }

}
