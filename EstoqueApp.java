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

    public static void main(String[] args) {
        EstoqueApp estoque = new EstoqueApp();

        estoque.novoProduto("Caneta", 2.50, 100);
        estoque.novoProduto("Caneta", 3.00, 50);
    }
}
