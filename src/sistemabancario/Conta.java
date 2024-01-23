package sistemabancario;

import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

public class Conta implements Menu {

    private String nomeDoUsuario;
    private long cpfDoUsuario;
    private String senhaDoUsuario;
    private String numeroDaConta;
    private String tipoDaConta;
    private double saldoDaConta;
    private boolean statusDaConta;
    private boolean pacoteDeBeneficios;

    /*MÉTODO DE INICIALIZAÇÃO (CONSTRUTOR)*/
    public Conta() {
        this.numeroDaConta = "";
    }

    /*MÉTODO PÚBLICO PARA UM USUÁRIO ABRIR UMA CONTA*/
    public void abrirConta() {
        boolean aberturaDeContaValidada = false;
        do {
            //VARIÁVEIS DE SUPORTE PARA FAZER VALIDAÇÕES DENTRO DO MÉTODO abrirConta():
            boolean cpfValidado = false;
            boolean senhaValidada = false;
            boolean contaValidada = false;
            String senhaDigitada = "";

            //LINHA PARA COLETAR O NOME DO USUÁRIO QUE VAI ABRIR A CONTA:
            this.setNomeDoUsuario(JOptionPane.showInputDialog(null, "DIGITE SEU NOME COMPLETO: "));

            //BLOCO PARA COLETAR O CPF DO USUÁRIO:
            do {
                try {
                    this.setCpfDoUsuario(Long.parseLong(JOptionPane.showInputDialog(null, "DIGITE SEU CPF [somente números]: ")));
                    cpfValidado = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "DIGITE SOMENTE OS NÚMEROS DE SEU CPF!");
                }
            } while (cpfValidado == false);

            //BLOCO PARA DEFINIR A SENHA DA CONTA DO USUÁRIO:
            do {
                this.setSenhaDoUsuario(JOptionPane.showInputDialog(null, "DEFINA SUA SENHA: "));
                senhaDigitada = JOptionPane.showInputDialog(null, "DIGITE NOVAMENTE SUA SENHA: ");
                if (senhaDigitada.equals(this.getSenhaDoUsuario())) {
                    senhaValidada = true;
                } else {
                    JOptionPane.showMessageDialog(null, "info: SENHAS DIVERGENTES!");
                }
            } while (senhaValidada == false);

            //BLOCO PARA DEFINIR O TIPO DA CONTA CC == CONTA CORRENTE / CP == CONTA POUPANÇA:
            do {
                String opcao = JOptionPane.showInputDialog(null, "DIGITE [1] PARA CONTA CORRENTE OU [2] PARA CONTA POUPANÇA");
                if (opcao.equals("1")) {
                    this.setTipoDaConta("CC");
                    this.setSaldoDaConta(50.00);
                    contaValidada = true;
                } else if (opcao.equals("2")) {
                    this.setTipoDaConta("CP");
                    this.setSaldoDaConta(150.00);
                    contaValidada = true;
                } else {
                    JOptionPane.showMessageDialog(null, "ERRO! ESCOLHA UMA OPÇÃO VÁLIDA!");
                }
            } while (contaValidada == false);

            //BLOCO PARA DEFINIR O NÚMERO DA CONTA DO USUÁRIO:
            for (int contador = 1; contador <= 9; contador++) {
                int numero = ThreadLocalRandom.current().nextInt(0, 9);
                this.setNumeroDaConta(this.getNumeroDaConta() + numero);
            }
            if (cpfValidado == true && senhaValidada == true && contaValidada == true) {
                aberturaDeContaValidada = true;
                this.setStatusDaConta(true);
                JOptionPane.showMessageDialog(null, "info: PROCEDIMENTO DE ABERTURA DE CONTA REALIZADO COM SUCESSO!");
            } else {
                JOptionPane.showMessageDialog(null, "info: É NECESSÁRIO REFAZER A OPERAÇÃO!");
            }
        } while (aberturaDeContaValidada == false);

        this.dadosCadastrais();
    }

    /*MÉTODOS ACESSORES E MODIFICADORES DOS ATRIBUTOS DA CLASSE CONTA (GETTERS/SETTERS)*/
    public String getNomeDoUsuario() {
        return this.nomeDoUsuario;
    }

    public void setNomeDoUsuario(String ndu) {
        this.nomeDoUsuario = ndu;
    }

    public long getCpfDoUsuario() {
        return this.cpfDoUsuario;
    }

    public void setCpfDoUsuario(long cpf) {
        this.cpfDoUsuario = cpf;
    }

    public String getSenhaDoUsuario() {
        return this.senhaDoUsuario;
    }

    public void setSenhaDoUsuario(String sdu) {
        this.senhaDoUsuario = sdu;
    }

    public String getNumeroDaConta() {
        return this.numeroDaConta;
    }

    public void setNumeroDaConta(String ndc) {
        this.numeroDaConta = ndc;
    }

    public String getTipoDaConta() {
        return this.tipoDaConta;
    }

    public void setTipoDaConta(String tdc) {
        this.tipoDaConta = tdc;
    }

    public Double getSaldoDaConta() {
        return this.saldoDaConta;
    }

    public void setSaldoDaConta(double sdc) {
        this.saldoDaConta = sdc;
    }

    public boolean getStatusDaConta() {
        return this.statusDaConta;
    }

    public void setStatusDaConta(boolean stdc) {
        this.statusDaConta = stdc;
    }

    public boolean getPacoteDeBeneficios() {
        return this.pacoteDeBeneficios;
    }

    public void setPacoteDeBeneficios(boolean pdb) {
        this.pacoteDeBeneficios = pdb;
    }

    /*MÉTODOS ABSTRATOS DA INTERFACE MENU*/
    @Override
    //NESSE MÉTODO MOSTRA ALGUMAS INFORMAÇÕES BÁSICAS DO USUÁRIO DA CONTA:
    public void dadosCadastrais() {
        if (this.getStatusDaConta() == true) {
            String mensagem = "<html>"
                    + "NOME: " + this.getNomeDoUsuario() + "<br>"
                    + "NÚMERO DA CONTA: " + this.getNumeroDaConta() + "<br>"
                    + "TIPO: " + this.getTipoDaConta() + "<br>"
                    + "STATUS: " + this.getStatusDaConta() + "<br>"
                    + "</html>";

            JOptionPane.showMessageDialog(
                    null,
                    mensagem,
                    "DADOS CADASTRAIS",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
        }
    }

    @Override
    public void saldo() {
        if (this.getStatusDaConta() == true) {
            JOptionPane.showMessageDialog(null, "SALDO: " + this.getSaldoDaConta() + " R$");
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
        }

    }

    @Override
    //MÉTODO PARA EFETUAR UM SAQUE NA CONTA DO USUÁRIO:
    public void saque() {
        //VARIÁVEIS DE SUPORTE E VALIDAÇÃO DO MÉTODO saque():
        boolean saqueValidado = false;
        double valorDoSaque = 0.00;

        if (this.getStatusDaConta() == true) {
            String senhaDigitada = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA: ");
            if (senhaDigitada.equals(this.getSenhaDoUsuario())) {
                do {
                    try {
                        valorDoSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "VALOR DO SAQUE: "));
                        saqueValidado = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "info: VALOR INVÁLIDO!");
                    }
                } while (saqueValidado == false);
                if (valorDoSaque <= this.getSaldoDaConta() && valorDoSaque > 0) {
                    this.setSaldoDaConta(this.getSaldoDaConta() - valorDoSaque);
                    JOptionPane.showMessageDialog(null, "info: SAQUE EFETUADO COM SUCESSO!");
                    this.saldo();
                } else if (valorDoSaque > this.getSaldoDaConta()) {
                    JOptionPane.showMessageDialog(null, "info: SALDO INSUFICIENTE!");
                    this.saldo();
                    JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                } else {
                    JOptionPane.showMessageDialog(null, "info: VALOR INVÁLIDO!");
                    JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                }
            } else {
                JOptionPane.showMessageDialog(null, "info: SENHA INCORRETA!");
                JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
            }
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
        }
    }

    @Override
    //MÉTODO PARA FAZER UM DEPÓSITO:
    public void deposito() {
        if (this.getStatusDaConta() == true) {
            //VARIÁVEIS DE SUPORTE E VALIDAÇÃO DO MÉTODO deposito():
            boolean contaValidada = false;
            boolean valorValidado = false;
            double valorDoDeposito = 0.00;
            String contaDigitada = "";
            do {
                try {
                    contaDigitada = JOptionPane.showInputDialog(null, "DIGITE O NÚMERO DA SUA CONTA PARA DEPOSITAR: ");
                    if (contaDigitada.equals(this.getNumeroDaConta())) {
                        valorDoDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "VALOR DO DEPÓSITO: "));
                    }
                    if (contaDigitada.equals(this.getNumeroDaConta()) && valorDoDeposito > 0) {
                        this.setSaldoDaConta(this.getSaldoDaConta() + valorDoDeposito);
                        contaValidada = true;
                        valorValidado = true;
                        JOptionPane.showMessageDialog(null, "info: DEPÓSTO EFETUADO COM SUCESSO!");
                        this.saldo();
                    } else if (!contaDigitada.equals(this.getNumeroDaConta())) {
                        JOptionPane.showMessageDialog(null, "info: NÚMERO DA CONTA INCORRETO!");
                    } else {
                        JOptionPane.showMessageDialog(null, "info: O VALOR DO DEPÓSITO DEVE SER MAIOR QUE ZERO");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ERRO! VALOR INVÁLIDO!");
                }
            } while (contaValidada == false && valorValidado == false);

        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
        }
    }

    @Override
    //MÉTODO PARA O USUÁRIO FAZER UMA TRANSFERÊNCIA VIA PIX PARA OUTRA CONTA:
    public void pix() {
        if (this.getStatusDaConta() == true) {
            String senhaDigitada = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA: ");
            if (senhaDigitada.equals(this.getSenhaDoUsuario())) {
                double valorDoPix = Double.parseDouble(JOptionPane.showInputDialog(null, "VALOR DO PIX: "));
                if (valorDoPix <= this.getSaldoDaConta()) {
                    this.setSaldoDaConta(this.getSaldoDaConta() - valorDoPix);
                    JOptionPane.showMessageDialog(null, "info: TRANSFERÊNCIA REALIZADA COM SUCESSO!");
                    this.saldo();
                } else {
                    JOptionPane.showMessageDialog(null, "info: SALDO INSUFICIENTE!");
                    this.saldo();
                    JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                }
            } else {
                JOptionPane.showMessageDialog(null, "SENHA INCORRETA!");
                JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
            }
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
        }
    }

    @Override
    //MÉTODO PARA VENDER UM PACOTE DE BENFÍCIOS PARA O USUÁRIO:
    public void beneficios() {
        if (this.getStatusDaConta() == true) {

            String mensagem = "<html>"
                    + "*****PACOTE DE BENEFÍCIOS*****" + "<br>"
                    + "<br>"
                    + "*SAQUES ILIMITADOS" + "<br>"
                    + "*OPÇÕES DE EMPRÉSTIMO" + "<br>"
                    + "*CARTÃO DE CRÉDITO" + "<br>"
                    + "*2 SORTEIOS A CADA QUINZENA" + "<br>"
                    + "</html>";

            JOptionPane.showMessageDialog(
                    null,
                    mensagem,
                    "PACOTE",
                    JOptionPane.INFORMATION_MESSAGE);

            String opcao = "";
            do {
                opcao = JOptionPane.showInputDialog(null, "<html>" + "DIGITE [1] PARA ADERIR O PACOTE DE BENEFÍCIOS POR APENAS 27.90 MENSAIS" + "<br>"
                        + "DIGITE [2] PARA CANCELAR" + "</html>");
                if (opcao.equals("1") && this.getSaldoDaConta() >= 27.90) {
                    this.setPacoteDeBeneficios(true);
                    this.setSaldoDaConta(this.getSaldoDaConta() - 27.90);
                    JOptionPane.showMessageDialog(null, "info: ADESÃO DO PACOTE REALIZADA COM SUCESSO!");
                } else if (opcao.equals("2")) {
                    JOptionPane.showMessageDialog(null, "ENCERRANDO SESSÃO...");
                } else if (opcao.equals("1") && this.getSaldoDaConta() < 27.90) {
                    JOptionPane.showMessageDialog(null, "info: SALDO INSUFICIENTE!");
                    this.saldo();
                } else {
                    JOptionPane.showMessageDialog(null, "info: OPÇÃO INVÁLIDA!");
                }
            } while (!opcao.equals("1") && !opcao.equals("2"));
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
        }
    }

    @Override
    //MÉTODO PARA VERIFICAR A DISPONIPILIDADE DE EMPRÉSTIMO PARA O USUÁRIO COM BASE NO PACOTE DE BENEFICIOS SE ESTÁ ATIVADO OU NÃO:
    public void emprestimo() {
        if (this.getPacoteDeBeneficios() == true) {
            String opcao = "";
            do {
                opcao = JOptionPane.showInputDialog(null, "<html>" + "info: Há Opções de empréstimo disponíveis! " + "<br>"
                        + "DIGITE [1] PARA VERIFICAR OU [2] PARA SAIR" + "</html>");
                if (opcao.equals("1")) {
                    Emprestimo e = new Emprestimo();
                    e.contratarEmprestimo(this);
                } else if (opcao.equals("2")) {
                    JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                } else {
                    JOptionPane.showMessageDialog(null, "info: OPÇÃO INVÁLIDA!");
                }
            } while (!opcao.equals("1") && !opcao.equals("2"));
        } else {
            JOptionPane.showMessageDialog(null, "info: No momento não há opções de empréstimo disponíveis para você!");
        }
    }

    @Override
    //MÉTODO PARA CANCELAR A CONTA:
    public void cancelarConta() {
        if (this.getStatusDaConta() == true) {
            boolean cancelamentoValidado = false;
            do {
                String opcao = JOptionPane.showInputDialog(null, "DESEJA EXCLUIR SUA CONTA? DIGITE [1] PARA SIM OU [2] PARA NÃO: ");
                if (opcao.equals("1")) {
                    try {
                        long cpfDigitado = Long.parseLong(JOptionPane.showInputDialog(null, "DIGITE SEU CPF: "));
                        String senhaDigitada = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA: ");
                        if (cpfDigitado == this.getCpfDoUsuario() && this.getSaldoDaConta() == 0 && senhaDigitada.equals(this.getSenhaDoUsuario())) {
                            this.setNomeDoUsuario("");
                            this.setCpfDoUsuario(0);
                            this.setNumeroDaConta("");
                            this.setSenhaDoUsuario("");
                            this.setStatusDaConta(false);
                            this.setTipoDaConta("");
                            this.setPacoteDeBeneficios(false);
                            JOptionPane.showMessageDialog(null, "info: SUA CONTA E TODOS SEUS DADOS CADASTRAIS FORAM EXCLUÍDOS!");
                            cancelamentoValidado = true;
                        } else if (cpfDigitado != this.getCpfDoUsuario()) {
                            JOptionPane.showMessageDialog(null, "info: CPF INCORRETO!");
                            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                            cancelamentoValidado = true;
                            break;
                        } else if (!senhaDigitada.equals(this.getSenhaDoUsuario())) {
                            JOptionPane.showMessageDialog(null, "info: SENHA INCORRETA!");
                            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                            cancelamentoValidado = true;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "info: IMPOSSÍVEL PROSSEGUIR COM A OPERAÇÃO, SALDO PENDENTE DE RETIRADA!");
                            this.saldo();
                            cancelamentoValidado = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ERRO! DADOS INCORRETOS!");
                    }

                } else if (opcao.equals("2")) {
                    JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
                    cancelamentoValidado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "info: OPÇÃO INVÁLIDA!");
                }
            } while (cancelamentoValidado == false);
        } else {
            JOptionPane.showMessageDialog(null, "info: CONTA NÃO LOCALIZADA!");
            JOptionPane.showMessageDialog(null, "info: ENCERRANDO SESSÃO...");
        }

    }

}
