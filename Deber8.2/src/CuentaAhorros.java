public class CuentaAhorros extends CuentaBancaria {

    private double tasaInteres;

    public CuentaAhorros(String numero, String titular, double saldo, double tasaInteres) {
        super(numero, titular, saldo);

        if (tasaInteres < 0 || tasaInteres > 1)
            throw new IllegalArgumentException("La tasa debe estar entre 0 y 1");

        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("Monto inválido");

        if ((saldo - monto) < 50)
            throw new SaldoInsuficienteException("La cuenta debe mantener mínimo $50");

        saldo -= monto;
    }

    public void aplicarInteres() {
        saldo += (saldo * tasaInteres);
    }
}
