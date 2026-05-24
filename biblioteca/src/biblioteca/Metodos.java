package biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Metodos {

    Scanner lerTeclado = new Scanner(System.in);

    public void titulo(String text) {
        System.out.println("----------------------------------------");
        System.out.println(text);
        System.out.println("----------------------------------------");
        System.out.println();
    }

    //1 ADICIONAR NOVO LIVRO
    public void novoLivro(ArrayList<String> titulos, ArrayList<String> autores, ArrayList<String> status, ArrayList<String> locatarios, ArrayList<Integer> prazos){

        System.out.println("Informe o título do livro: ");
        String tituloLivro = lerTeclado.nextLine();

        System.out.println("Informe o autor do livro: ");
        String autorLivro = lerTeclado.nextLine();


        if (!tituloLivro.isEmpty() && !autorLivro.isEmpty()) {
            titulos.add(tituloLivro);
            autores.add(autorLivro);
            status.add("Disponível");
            locatarios.add("Ninguém");
            prazos.add(0);

            System.out.println("Livro cadastrado com sucesso!\n");

        } else {
            System.out.println("ERRO: nenhum campo pode ficar vazio.\n");

        }

    }

    //2 PESQUISAR LIVRO POR TÍTULO
    public boolean pesquisarLivro(ArrayList<String> titulos, ArrayList<String> autores, ArrayList<String> status) {

        System.out.println("Informe o título do livro que deseja buscar: ");
        String pesquisarLivro = lerTeclado.nextLine();

        for( int indice = 0; indice < titulos.size(); indice++){
            if(pesquisarLivro.equalsIgnoreCase(titulos.get(indice))){
                System.out.println("Título: " + titulos.get(indice) + ", Autor: " + autores.get(indice) + ", Status: " + status.get(indice) + "\n");
                return true;

            }
        }
        System.out.println("Livro não encontrado.\n");
        return false;

    }

    // 3 REALIZAR EMPRÉSTIMO
    public boolean realizarEmprestimo(ArrayList<String> titulos, ArrayList<String> status, ArrayList<String> locatarios, ArrayList<Integer> prazos) {


        System.out.println("Informe seu nome: ");
        String nome = lerTeclado.nextLine();
        System.out.println("Informe o título do livro que deseja alugar: ");
        String alugarLivro = lerTeclado.nextLine();

        int locador = 0;

        for (int indice = 0; indice < locatarios.size(); indice++){
            if(nome.equalsIgnoreCase(locatarios.get(indice))){
                locador++;
            }
        }
        if (locador >= 3){
            System.out.println("Operação não realizada: o usuário atingiu o limite máximo de 3 livros emprestados.\n");
            return false;
        }

        for (int indice = 0; indice < titulos.size(); indice++){
            if (alugarLivro.equalsIgnoreCase(titulos.get(indice)) && status.get(indice).equals("Disponível") && locador <3){
                status.set(indice, "Emprestado");
                locatarios.set(indice, nome);
                prazos.set(indice, 7);
                System.out.println("Empréstimo realizado com sucesso para: " + nome + "\n");
                return true;
            }
        }
        System.out.println("Operação não realizada: Livro não encontrado ou já está emprestado.\n");
        return false;

    }

    //4 REALIZAR DEVOLUÇÃO
    public boolean realizarDevolucao(ArrayList<String> titulos, ArrayList<String> status, ArrayList<String> locatarios, ArrayList<Integer> prazos) {

        System.out.println("Informe seu nome: ");
        String nome = lerTeclado.nextLine();
        System.out.println("Informe o título do livro a ser devolvido: ");
        String livroDevolvido = lerTeclado.nextLine();
        System.out.println("Informe por quantos dias o livro permaneceu emprestado: ");
        int diasLivro = lerTeclado.nextInt();
        lerTeclado.nextLine();

        for (int indice = 0; indice < titulos.size(); indice++){
            if(livroDevolvido.equalsIgnoreCase(titulos.get(indice)) && nome.equalsIgnoreCase(locatarios.get(indice))){
                status.set(indice, "Disponível");
                locatarios.set(indice, "Ninguém");
                prazos.set(indice, 0);
                System.out.println("Livro devolvido com sucesso.\n");

                if (diasLivro > 7 ) {
                    double multa = (diasLivro - 7) * 2;
                    System.out.printf("Você excedeu o prazo de devolução em %d dia(s). Multa aplicada: R$ %.2f%n \n",(diasLivro - 7), multa);
                }
                return true;
            }
        }
        System.out.println("ERRO! Empréstimo não encontrado. Verifique o nome do usuário e o título do livro.\n");
        return false;

    }

    //5 LISTAR LIVROS
    public void listarLivros(ArrayList<String> titulos, ArrayList<String> autores, ArrayList<String> status) {

        titulo("LISTAR LIVROS");

        String menu = "";

        while (!menu.equals("0")) {
            System.out.println("""
                    1 TODOS
                    2 AUTOR
                    3 DISPONÍVEIS
                    0 SAIR""");

            menu = lerTeclado.nextLine();

            switch (menu) {
                case "1":
                    int contador = 1;
                    titulo("TODOS OS LIVROS");

                    for (String livros : titulos) {
                        System.out.println("ID: " + contador + " TÍTULO: " + livros + " AUTOR: " + autores.get(contador - 1) + " STATUS: " + status.get(contador - 1));
                        contador++;
                    }
                    break;
                case "2":
                    System.out.println("Informe o nome do autor: ");
                    String nomeAutor = lerTeclado.nextLine();

                    boolean autorEncontrado = false;

                    for (int indice = 0; indice < autores.size(); indice++) {
                        if (nomeAutor.equalsIgnoreCase(autores.get(indice))) {
                            System.out.println("ID: " + (indice+1) + " AUTOR: " + autores.get(indice) + " TÍTULO: " + titulos.get(indice) + " STATUS: " + status.get(indice));
                             autorEncontrado = true;
                        }

                    }
                    if (!autorEncontrado) {
                        System.out.println("Autor não encontrado.");
                    }
                    break;

                case "3":

                    for (int indice = 0; indice < status.size(); indice++) {
                        if (status.get(indice).equals("Disponível")) {
                            System.out.println("ID: " + (indice+1) + " AUTOR: " + autores.get(indice) + " TÍTULO: " + titulos.get(indice) + " STATUS: " + status.get(indice));
                        }

                    }
                    int quantidade = Collections.frequency(status, "Disponível");
                    if (quantidade == 0){
                        System.out.println("Nenhum livro disponível no momento.");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");

            }
            System.out.println();
        }
    }

    //6 EXCLUIR LIVRO
    public boolean excluirLivros(ArrayList<String> titulos, ArrayList<String> autores, ArrayList<String> status, ArrayList<String> locatarios, ArrayList<Integer> prazos) {

        System.out.println("Informe o título do livro a ser excluído: ");
        String excluir = lerTeclado.nextLine();

        System.out.println("Tem certeza de que deseja excluir este livro? [S/N]");
        String confirmarExcluir = lerTeclado.nextLine();

        if(confirmarExcluir.equalsIgnoreCase("n")) {
            System.out.println("Operação cancelada pelo usuário.\n");
            return false;
        }

        for (int indice = 0; indice < titulos.size(); indice++) {
            if (excluir.equalsIgnoreCase(titulos.get(indice)) && status.get(indice).equals("Disponível")) {
                titulos.remove(indice);
                autores.remove(indice);
                status.remove(indice);
                locatarios.remove(indice);
                prazos.remove(indice);

                System.out.println("Livro excluído com sucesso.\n");
                return true;
            }
            }

        System.out.println("ERRO: livro não encontrado ou emprestado. \n");
        return false;
    }
}



