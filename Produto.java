public class Produto {
    String nome;
    double preco;
    int qtd = 0;

    public Produto(String nome, double preco) {
        this.nome = nome;

        if (preco > 0) {
            this.preco = preco;

        } else {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }
    }

    public void add(int add_qtd) {
        if (add_qtd > 0) {
            this.qtd += add_qtd;
        } else {
            System.err.println("Quantidade inválida! Nenhuma alteração feita.");
        }
    }

    public void remover(int remover_qtd) {
        if (this.qtd == 0) {
            throw new IllegalStateException("Produto indisponível no momento (estoque zerado).");
        }

        if (remover_qtd > this.qtd) {
            throw new IllegalArgumentException("Quantidade solicitada maior que o saldo disponível em estoque.");
        }

        this.qtd -= remover_qtd;
    }

    public void alterar_preco(double novo_preco) {
        if (novo_preco > 0) {
            this.preco = novo_preco;

        } else {
            throw new IllegalArgumentException("Novo preço deve ser maior que 0");
        }
    }

    public void update(String novo_nome, double novo_preco, int nova_qtd) {
        this.nome = novo_nome;

        this.alterar_preco(novo_preco);

        if (nova_qtd >= 0) {
            this.preco = nova_qtd;

        } else {
            throw new IllegalArgumentException("Nova quantidade deve ser maior ou igual a 0");
        }

    }

    public static void main(String[] args) {
        System.out.println("Apenas p/ testes");

        // Produto test = new Produto("Testando", 13);
        Produto chocolate = new Produto("Chocolate", 1.33);

        System.out.println(chocolate.nome);
        System.out.println(chocolate.preco);
        System.out.println(chocolate.qtd);
        chocolate.add(3);
        System.out.println(chocolate.qtd);

    }
}
