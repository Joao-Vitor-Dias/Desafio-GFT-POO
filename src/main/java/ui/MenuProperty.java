package ui;

import model.Owner;
import model.Property;
import service.PropertyService;
import util.VerifyUserInput;

import java.util.Scanner;

import static util.VerifyUserInput.verifyStringToBooleanUserInput;

public class MenuProperty {

    /*
    * Minha service de imoveis, onde esta toda a logica de inteiracao com a lista
    * de imoveis.
    * E um atributo que faz parte da classe MenuProperty
    * */
    private static final PropertyService propertyService = new PropertyService();
    /*
     * Inicializacao de um scanner que vai servir para classe inteira.
     * */
    private static final Scanner sc = new Scanner(System.in);

    /*
    * Menu para criacao de um imovel, onde o usuario vai passar as seguintes informacoes:
    * 1 - Vai informar qual o tipo do imovel
    * 2 - Vai digitar o endereco do imovel
    * 3 - Vai digitar o numero do imovel
    * 4 - Vai digitar se ele esta disponivel para alugar
    * 5 - Valor do aluguel
    * 6 - Vai abrir o menu para vincular um dono ao imovel, ai voce pode criar um novo
    * ou pergar um que ja existe passando cpf
    * */
    public static void menuCreationProperty() throws Exception{

        System.out.println("======== CADASTRO IMOVEL =========");

        int op;
        // Quantidade maxima de opcoes
        int quantityOfOptions = PropertyService.getQuantiyOfOptions();

        // Fazendo verificacao para nao passar nem uma opcao invalida no campo
        do {
            System.out.println("Escolha qual opcao de imovel que deseja adicionar");
            System.out.println("1 - Casa");
            System.out.println("2 - Apartamento");
            String opString = sc.next();

            op = Integer.parseInt(opString);

            // Se o usuario escolheu uma opcao maior que a quantidade que existe ou menor, e um valor invalido
            if (op > quantityOfOptions || op <= 0){
                System.out.println("Opcao invalida ... ");
            }

        }while (op > quantityOfOptions || op <= 0);
        sc.nextLine();

        System.out.println("Digite qual o endereco do imovel");
        String address = sc.nextLine();

        System.out.println("Digite o numero do imovel");
        String propertyNumber = sc.next();

        System.out.println("O imovel esta disponivel para aluguel? ( S / N )");
        String isRentString = sc.next();
        boolean isRent = VerifyUserInput.verifyStringToBooleanUserInput(isRentString);

        System.out.println("Quanto custa o aluguel? (apenas valores inteiros)");
        int rentalValuePerMonth = sc.nextInt();
        sc.nextLine();

        System.out.println("================================");
        // Chama o menu de criacao de dono para ser vinculado ao imovel
        Owner owner = MenuOwner.menuCreateOwner();

        // Chama o metodo da service que adiciona um novo imovel a lista de imoveis
        propertyService.addProperty(op,address,propertyNumber,!isRent,owner,rentalValuePerMonth);

        System.out.println("Adicionando propriedade ...");

    }

    // Apenas um menu para exibir todos os imoveis que tem no sistema
    public static boolean menuOfAllProperties(){

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

    // Menu para remocao de um imovel do sistema
    public static void menuRemoveProperty(){

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

    // Menu para simular o valor de aluguel de um imovel
    public static void menuCalculateRentPropertyValue(){

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

    // Menu para realizar o aluguel de um imovel
    public static void menuRentProperty(){

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


    // Menu para desocupar um imovel
    public static void menuVacateProperty(){

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

    // Menu para mostrar apenas propriedade disponiveis para alugar
    public static boolean menuOfAvailableProperty(){

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

    // Menu para mostrar apenas propriedade indisponiveis para alugar
    public static boolean menuOfUnavailableProperty(){

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

}
