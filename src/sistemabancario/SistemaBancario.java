package sistemabancario;

import javax.swing.JOptionPane;

public class SistemaBancario {
    
    public static void main(String[] args) {
        
        JOptionPane.showMessageDialog(null, "WELCOME TO PROGRAM!");
        
        Conta c1 = new Conta();
        c1.abrirConta();
        c1.deposito();
        c1.beneficios();
        c1.emprestimo();
        
    }
    
}