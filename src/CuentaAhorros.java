public class CuentaAhorros extends Cuenta{

    private static final double SALDO_MINIMO = 100000;

    //Constructor
    public  CuentaAhorros(String numeroCuenta, String titular, double saldoActual, String fechaApertura){

        //Llamamos al constructor de la clase principal (Cuenta)
        super(numeroCuenta, titular, saldoActual, fechaApertura);

    }

    //MÉTODOS
    //Retirar
    @Override
    public void retirar(double cantidad){

        //Validar estado de cuenta
        if (!getEstadoCuenta().equals("ACTIVA")){

            throw new IllegalArgumentException("No se puede retirar. La cuenta esta bloqueada");
        }

        //Validar cantidad a retirar
        if (cantidad <= 0){

            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor a cero");

        }

        //Validar saldo mínimo a mantener
        if ((saldoActual - cantidad) < SALDO_MINIMO){

            throw new IllegalArgumentException("No se puede retirar, Debe mantener un saldo mínimo de " + SALDO_MINIMO);

        }

        //Realizar retiro
        saldoActual -= cantidad;

    }

    //Calcular interés
    @Override
    public void calcularComisionMensual(){

        //Tasa interés: 3.6% anual
        double interesMensual = saldoActual * (0.036 / 12);

        saldoActual += interesMensual;

    }

}
