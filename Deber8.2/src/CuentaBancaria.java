public class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty())
            throw new IllegalArgumentException("Número de cuenta inválido");

        if (titular == null || titular.trim().isEmpty())
            throw new IllegalArgumentException("Titular inválido");

        if (saldo < 0)
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");

        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public void depositar(double monto) {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto debe ser mayor a 0");
        saldo += monto;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("Monto inválido");

        if (monto > saldo)
            throw new SaldoInsuficienteException("Saldo insuficiente");

        saldo -= monto;
    }

    @Override
    public String toString() {
        return numeroCuenta + " - " + titular + " - Saldo: $" + saldo;
    }
}
