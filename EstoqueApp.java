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

    // validar a venda
    // recebe o nome e quantidade que o cliente quer
    public boolean podeVender(String nome, int qtdCliente) {
        // busca o produto
        Produto prodDisponivel = buscarProduto(nome);
        // se não existe, a venda não pode ser efetuada
        if (prodDisponivel == null) {
            return false;
        }
        if (qtdCliente <= 0) {
            return false;
        }
        // se existe, a venda só será efetuada se a quantidade do estoque cubrir a
        // quantidade pedida
        return prodDisponivel.qtd >= qtdCliente;
    }

    // mostrar disponibilidade
    // recebe o nome do produto
    public void disponProd(String nome) {
        // busca o produto
        Produto disponivel = buscarProduto(nome);
        // se não existe avisa e para
        if (disponivel == null) {
            System.out.println("Produto inexistente no estoque.");
        }
        // se a qtd for 0 mostra que tem mas acabou
        else if (disponivel.qtd == 0) {
            System.out.println("Tinha o " + disponivel.nome + " mas acabou =( !");
        }
        // se não mostra que tem mas tem pouco(ou não?)
        else {
            System.out.println(disponivel.nome + ": " + disponivel.qtd + " disponível em estoque.");
        }
    }

    // atualizar o estoque e validar antes de cada venda a disponibilidade
    public void atualizaPorVenda(String nome, int qtdCliente) {
        // receber o nome e a quantidade vendida
        // validar a venda com o metodo de disponibilidade de produto
        if (!podeVender(nome, qtdCliente)) {
            // se a venda for invalida avisa e para e não muda nada
            System.out.println("Venda não permitida: O produto não existe ou tem estoque zerado.");
            return;
        }
        // se a venda for valida busca o produto no estoque e desconta a quantidade
        Produto vendaProduto = buscarProduto(nome);
        vendaProduto.remover(qtdCliente);
        // avisa que o estoque foi atualizado
        System.out.println(
                "Venda realizada! O produto " + vendaProduto.nome + " tem " + vendaProduto.qtd + " em estoque.");

    }

    public static void main(String[] args) {
        EstoqueApp estoque = new EstoqueApp();

        // 1) Adicionar (e tentar duplicar)
        estoque.novoProduto("Caneta", 2.50, 100);
        estoque.novoProduto("Caderno", 15.00, 20);
        estoque.novoProduto("Caneta", 3.00, 50); // duplicado: deve avisar

        // 2) Consultar (existente e inexistente)
        estoque.consultarProduto("Caneta");
        estoque.consultarProduto("Lápis");

        // 3) Validar venda
        System.out.println(estoque.podeVender("Caneta", 30)); // true
        System.out.println(estoque.podeVender("Caneta", 500)); // false (passa do estoque)
        System.out.println(estoque.podeVender("Caneta", -5)); // false (quantidade inválida)
        System.out.println(estoque.podeVender("Lápis", 1)); // false (não existe)

        // 4) Vender e provar que os objetos são independentes
        estoque.atualizaPorVenda("Caneta", 30);
        estoque.disponProd("Caneta"); // 70
        estoque.disponProd("Caderno"); // continua 20
        estoque.atualizaPorVenda("Caneta", 500); // barrado
        estoque.atualizaPorVenda("Lápis", 1); // barrado

        // 5) Esgotar um produto
        estoque.atualizaPorVenda("Caderno", 20);
        estoque.disponProd("Caderno"); // esgotado
        estoque.atualizaPorVenda("Caderno", 1); // barrado

        // 6) Alterar (nome, preço e quantidade)
        estoque.alterarProduto("Caneta", "Caneta Azul", 3.00, 15);
        estoque.consultarProduto("Caneta Azul");
        estoque.consultarProduto("Caneta"); // nome antigo não existe mais

        // 7) Remover
        estoque.removerProduto("Caneta Azul");
        estoque.consultarProduto("Caneta Azul");
        estoque.disponProd("Caneta Azul");
    }
}
