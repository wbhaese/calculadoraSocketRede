import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cliente3 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        double num1;
        double num2;
        int operacao = 0;
        char opr;
        Scanner entrada = new Scanner(System.in);
        Socket cliente = new Socket("192.168.0.2", 12342);
        System.out.println("Você está conectado ao servidor");


        ObjectInputStream resultado = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream dados = new ObjectOutputStream(cliente.getOutputStream());


        //val1 = Double.parseDouble(JOptionPane.showInputDialog("Insira um valor: "));
        System.out.println("Digite o primeiro valor: ");
        num1 = entrada.nextDouble();
        System.out.println("Digite o segundo valor: ");
        num2 = entrada.nextDouble();
        //val2 = Double.parseDouble(JOptionPane.showInputDialog("Insira o segundo valor."));
        while (!((operacao >= 1) && (operacao <= 4))) {
            //operacao = Integer.parseInt(JOptionPane.showInputDialog("Escolha um número para a operação artmética: 1= +, 2= -,3= X,4= / "));
            System.out.println("Escolha um número de acordo com a operação: ");
            System.out.println(" 1 - Soma (+)");
            System.out.println(" 2 - Subtração (-)");
            System.out.println(" 3 - Multiplicação (*)");
            System.out.println(" 4 - Divisão (/)");
            operacao = entrada.nextInt();
            if (!((operacao >= 1) && (operacao <= 4))) {
                System.out.println("Você digitou uma operação inválida.");
            }
        }
        dados.writeInt(operacao);
        dados.writeDouble(num1);
        dados.writeDouble(num2);
        dados.flush();

        double total = resultado.readDouble();
        opr = resultado.readChar();
        System.out.println("O resultado de " + num1 +" "+ opr +" "+ num2 + " é igual a " + total);

        resultado.close();
        dados.close();
        cliente.close();
    }
}