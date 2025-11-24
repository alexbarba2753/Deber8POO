public class CuentaCorriente extends CuentaBancaria {

    private double limiteCredito;

    public CuentaCorriente(String numero, String titular, double saldo, double limiteCredito) {
        super(numero, titular, saldo);

        if (limiteCredito < 0)
            throw new IllegalArgumentException("Límite inválido");

        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("Monto inválido");

        if (monto > (saldo + limiteCredito))
            throw new SaldoInsuficienteException("No tiene saldo + crédito suficiente");

        saldo -= monto;
    }
}
