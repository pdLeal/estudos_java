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
    public void consulta(String nome) {
        Produto consult = buscarProduto(nome);
        // se não achou aviso e paro
        if (consult == null) {
            System.out.println("Produto não encontrado");
            return;
        }
        // se achou, mostra o objeto completo
        else {
            System.out.println("Nome do produto: " + consult.nome);
            System.out.println("Preço: R$ " + consult.preco);
            System.out.println("Quantidade em estoque: " + consult.qtd);
        }
    }

    // remover produto
    // buscar o produto
    public void removerProduto(String nome) {
        Produto removerProduto = buscarProduto(nome);
        // se não achou avisa e para
        if (removerProduto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        // se achou tira ele da lista e avisa que tirou
        else {
            produtos.remove(removerProduto);
            System.out.println("Produto removido da base de dados: " + removerProduto.nome);
        }
    }

    public static void main(String[] args) {
        EstoqueApp estoque = new EstoqueApp();

        estoque.novoProduto("Caneta", 2.50, 100);
        estoque.consulta("Caneta"); // deve mostrar os dados
        estoque.removerProduto("Caneta"); // deve avisar que removeu
        estoque.consulta("Caneta");
    }
}
