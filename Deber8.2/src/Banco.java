import java.util.ArrayList;
import java.util.Random;

public class Banco {

    private ArrayList<CuentaBancaria> cuentas;
    private String nombre;

    public Banco(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre del banco inválido");

        this.nombre = nombre;
        cuentas = new ArrayList<>();
    }

    public static String generarNumeroCuenta() {
        Random r = new Random();
        String num = "";
        for (int i = 0; i < 10; i++)
            num += r.nextInt(10);
        return num;
    }

    public void abrirCuenta(CuentaBancaria cuenta) {
        if (cuenta == null)
            throw new NullPointerException("No se puede agregar una cuenta null");

        cuentas.add(cuenta);
    }

    private CuentaBancaria buscar(String numero) {
        for (CuentaBancaria c : cuentas)
            if (c.getNumeroCuenta().equals(numero))
                return c;

        return null;
    }

    public void transferir(String origen, String destino, double monto)
            throws SaldoInsuficienteException {

        if (monto <= 0)
            throw new MontoInvalidoException("Monto inválido");

        CuentaBancaria c1 = buscar(origen);
        CuentaBancaria c2 = buscar(destino);

        if (c1 == null || c2 == null)
            throw new IllegalArgumentException("Una cuenta no existe");

        c1.retirar(monto);
        c2.depositar(monto);
    }

    public void aplicarInteresesAhorros() {
        for (CuentaBancaria c : cuentas) {
            if (c instanceof CuentaAhorros) {

                if (c.getSaldo() == 0)
                    throw new CuentaInactivaException("Cuenta sin saldo");

                ((CuentaAhorros) c).aplicarInteres();
            }
        }
    }

    public double obtenerSaldoTotal() {
        if (cuentas.isEmpty())
            throw new IllegalStateException("Banco sin cuentas");

        double total = 0;
        for (CuentaBancaria c : cuentas)
            total += c.getSaldo();

        return total;
    }

    public void ordenarPorSaldo() {
        // ordenamiento manual - burbuja
        for (int i = 0; i < cuentas.size() - 1; i++) {
            for (int j = 0; j < cuentas.size() - 1 - i; j++) {
                if (cuentas.get(j).getSaldo() > cuentas.get(j + 1).getSaldo()) {
                    CuentaBancaria aux = cuentas.get(j);
                    cuentas.set(j, cuentas.get(j + 1));
                    cuentas.set(j + 1, aux);
                }
            }
        }
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
