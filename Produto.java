public class Produto {
    String name; 
    double price;
    int amount; 

    public Produto(String name, double price) {
        this.name = name;

        if (price > 0) {
            this.price = price;

        } else {
            throw new IllegalArgumentException("Price must be a positive number greater than 0");
        }
    }

    public static void main(String[] args) {
        System.out.println("Apenas p/ testes");

        Produto test = new Produto("Testando", 13);
        
        System.out.println(test.name);
        System.out.println(test.price);
    }
}
