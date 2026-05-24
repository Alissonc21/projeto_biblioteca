package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class MainBiblioteca {
    static void main(String[] args) {

        Metodos biblioteca = new Metodos();
        biblioteca.titulo("SISTEMA DE GERECIAMENTO DE BIBLIOTECA");

        ArrayList<String> titulos = new ArrayList<>();
        ArrayList<String> autores = new ArrayList<>();
        ArrayList<String> status = new ArrayList<>();
        ArrayList<String> locatarios = new ArrayList<>();
        ArrayList<Integer> prazos = new ArrayList<>();

        titulos.add("Grande Sertão: Veredas");
        autores.add("João Guimarães Rosa");
        status.add("Disponível");
        locatarios.add("Ninguém");
        prazos.add(0);

        titulos.add("Dom Casmurro");
        autores.add("Machado de Assis");
        status.add("Disponível");
        locatarios.add("Ninguém");
        prazos.add(0);

        titulos.add("Memórias Póstumas de Brás Cubas");
        autores.add("Machado de Assis");
        status.add("Emprestado");
        locatarios.add("Ninguém");
        prazos.add(0);

        titulos.add("O Cortiço");
        autores.add("Aluísio Azevedo");
        status.add("Disponível");
        locatarios.add("Ninguém");
        prazos.add(0);

        titulos.add("Vidas Secas");
        autores.add("Graciliano Ramos");
        status.add("Disponível");
        locatarios.add("Ninguém");
        prazos.add(0);

        Scanner lerTeclado = new Scanner(System.in);
        String menu = "";

        while (!menu.equals("0")){
            System.out.println("""
                1 ADICIONAR NOVO LIVRO
                2 PESQUISAR LIVRO POR TÍTULO
                3 REALIZAR EMPRÉSTIMO
                4 REALIZAR DEVOLUÇÃO
                5 LISTAR LIVROS
                6 EXCLUIR LIVRO
                0 SAIR""");

            menu = lerTeclado.nextLine();

            if ((menu.equals("2") || menu.equals("3") || menu.equals("4") || menu.equals("5") || menu.equals("6")) && titulos.size() == 0){
                System.out.println("Ainda não existem livros cadastrados no sistema. \n");
                continue;
            }

            switch (menu){
                case "1":
                    biblioteca.novoLivro(titulos, autores, status, locatarios, prazos);
                    break;
                case "2":
                    biblioteca.pesquisarLivro(titulos, autores, status);
                    break;
                case "3":
                    biblioteca.realizarEmprestimo(titulos, status, locatarios, prazos);
                    break;
                case "4":
                    biblioteca.realizarDevolucao(titulos, status, locatarios, prazos);
                    break;
                case "5":
                    biblioteca.listarLivros(titulos, autores, status);
                    break;
                case "6":
                    biblioteca.excluirLivros(titulos, autores, status, locatarios, prazos);
                    break;
                case "0":
                    System.out.println("OBRIGADO POR UTILIZAR NOSSO SISTEMA!");
                    break;
                default:
                    System.out.println("OPÇÃO INVALIDA. TENTE NOVAMENTE.\n");

            }
            }
        lerTeclado.close();

        }

    }


