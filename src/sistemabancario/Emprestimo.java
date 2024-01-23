package sistemabancario;

import javax.swing.JOptionPane;

public class Emprestimo extends Conta {

    private int numeroDeTentativas;
    private boolean emprestimoValidado;

    public Emprestimo() {

        numeroDeTentativas = 3;
        emprestimoValidado = false;

    }

    public void contratarEmprestimo(Conta e) {

        int opcao = 0;
        do {
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "<html>"
                        + "DIGITE [1] PARA 1.000 R$" + "<br>"
                        + "DIGITE [2] PARA 5.000 R$" + "<br>"
                        + "DIGITE [3] PARA 10.000 R$" + "<br>"
                        + "DIGITE [4] PARA 25.000 R$" + "<br>"
                        + "DIGITE [5] PARA SAIR DA OPERAÇÃO" + "<br>"
                        + "</html>"));
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "ERRO! OPÇÃO INVÁLIDA!");
            }
        } while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5);

        do {
            String senhaDigitada = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA: ");
            // SE A SENHA ESTIVER CORRETA A OPERAÇÃO DE EMPRÉSTIMO É EFETUADA UTILIZANDO O SWITCH COM BASE NA OPÇÃO ESCOLHIDA PELA USUÁRIO ANTERIORMENTE:
            if (senhaDigitada.equals(e.getSenhaDoUsuario())) {
                switch (opcao) {

                    case 1:
                        emprestimoValidado = true;
                        e.setSaldoDaConta(e.getSaldoDaConta() + 1000.00);
                        JOptionPane.showMessageDialog(null, "info: EMPRÉSTIMO CONTRATADO COM SUCESSO!");
                        e.saldo();
                        break;
                    case 2:
                        emprestimoValidado = true;
                        e.setSaldoDaConta(e.getSaldoDaConta() + 5000.00);
                        JOptionPane.showMessageDialog(null, "info: EMPRÉSTIMO CONTRATADO COM SUCESSO!");
                        e.saldo();
                        break;
                    case 3:
                        emprestimoValidado = true;
                        e.setSaldoDaConta(e.getSaldoDaConta() + 10000.00);
                        JOptionPane.showMessageDialog(null, "info: EMPRÉSTIMO CONTRATADO COM SUCESSO!");
                        e.saldo();
                        break;
                    case 4:
                        emprestimoValidado = true;
                        e.setSaldoDaConta(e.getSaldoDaConta() + 25000.00);
                        JOptionPane.showMessageDialog(null, "info: EMPRÉSTIMO CONTRATADO COM SUCESSO!");
                        e.saldo();
                    case 5:
                        emprestimoValidado = true;
                        JOptionPane.showMessageDialog(null, "ENCERRANDO OPERAÇÃO...");
                        break;

                }
            } else {
                numeroDeTentativas--;
                //ESSE IF É PARA FORMATAR O "TENTATIVA(S)" NA TELA:
                if (numeroDeTentativas > 0) {
                    JOptionPane.showMessageDialog(null, "info: SENHA INCORRETA!" + " +" + numeroDeTentativas + " TENTATIVAS!");
                } else if (numeroDeTentativas == 1) {
                    JOptionPane.showMessageDialog(null, "info: SENHA INCORRETA!" + " +" + numeroDeTentativas + " TENTATIVA!");
                } else {
                    JOptionPane.showMessageDialog(null, "info: SENHA INCORRETA!");
                }
            }
        } while (numeroDeTentativas > 0 && emprestimoValidado == false);
        
        //CASO O NÚMERO DE TENTATIVAS EXCEDA O LIMITE A OPERAÇÃO É ENCERRADA:
        if (numeroDeTentativas == 0) {
            JOptionPane.showMessageDialog(null, "info: NÚMERO DE TENTATIVAS EXCEDIDO, ENCERRANDO OPERAÇÃO...");
        }
    }
}
