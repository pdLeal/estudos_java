import java.util.ArrayList;
import java.util.List;

public class EstoqueApp {
    // lista de objetos vinda de produtos.
    private List<Produto> produtos = new ArrayList<>();

    // busca o produto pelo nome e valida se o é o produto correto para modificar.
    private Produto buscarProduto(String nome) {
        for (Produto prod : produtos) {
            if (prod.nome.equalsIgnoreCase(nome)) {
                return prod;
            }
        }
        return null;
    }

    // adicionar um produto
    // receber o nome, preço e quantidade
    public void novoProduto(String nome, double preco, int qtd) {
        // ver se já existe um produto com esse nome
        Produto jaExisteProduto = buscarProduto(nome);
        // se existir, ele avisa e para(nada de adc)
        if (jaExisteProduto != null) {
            System.out.println("Produto já existe na base de dados");
            return;
        }
        // se não, cria um produto, coloca a quantidade inicial e guarda na lista
        else {
            Produto novoProd = new Produto(nome, preco);
            novoProd.add(qtd);
            produtos.add(novoProd);
            System.out.println("Produto adicionado: " + novoProd.nome);
        }
    }

    // consultar um produto
    // receber o nome
    // buscar o produto
    public void consultarProduto(String nome) {
        Produto consult = buscarProduto(nome);
        // se não achou aviso e paro
        if (consult == null) {
            System.out.println("Produto não encontrado");
            return;
        }
        // se achou, mostra o objeto completo
        else {
            System.out.println("Nome do produto: " + consult.nome);
            System.out.printf("Preço: R$ %.2f%n", consult.preco);
            System.out.println("Quantidade em estoque: " + consult.qtd);
        }
    }

    // remover produto
    // buscar o produto
    public void removerProduto(String nome) {
        Produto remProd = buscarProduto(nome);
        // se não achou avisa e para
        if (remProd == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        // se achou tira ele da lista e avisa que tirou
        else {
            produtos.remove(remProd);
            System.out.println("Produto removido da base de dados: " + remProd.nome);
        }
    }

    // atualizar/alterar um produto
    // receber o nome, novo preço e nova quantidade
    public void alterarProduto(String nome, String novoNome, double novoPreco, int novaQtd) {
        // buscar o produto
        Produto upProd = buscarProduto(nome);
        // se não achou, avisa e para
        if (upProd == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        // se achou faz o update
        else {
            upProd.update(novoNome, novoPreco, novaQtd);
            System.out.println("Produto atualizado: " + upProd.nome);
        }
    }

    public static void main(String[] args) {
        EstoqueApp estoque = new EstoqueApp();

        estoque.novoProduto("Caneta", 2.50, 100);
        estoque.alterarProduto("Caneta", "Caneta Azul", 3.00, 15); // adapte aos seus parâmetros
        estoque.consultarProduto("Caneta Azul"); // nome e preço novos
        estoque.consultarProduto("Caneta"); // "não encontrado"
    }
}
