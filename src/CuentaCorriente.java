public class CuentaCorriente extends Cuenta{

    private static final double SOBREGIRO = -500000;

    //Constructor
    public CuentaCorriente(String numeroCuenta, String titular, double saldoActual, String fechaApertura){

        //Llamamos al constructor de la clase principal (Cuenta)
        super(numeroCuenta, titular, saldoActual, fechaApertura);
    }

    //MÉTODOS
    //Retirar
    @Override
    public void retirar(double cantidad){

        //Validar estado de cuenta
        if (!estadoCuenta.equals("ACTIVA")){

            throw new IllegalArgumentException("No se puede retirar. La cuenta esta bloqueada");

        }

        //Validar cantidad a retirar
        if (cantidad <= 0){

            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor a cero");

        }

        //Validar que no supere el límite de sobregiro
        if ((saldoActual - cantidad) < -SOBREGIRO){

            throw new IllegalArgumentException("Supera el límite de sobregiro");

        }

        //Realizar retir
        saldoActual -= cantidad;

    }

    //Calcular cobro comisión mensual fija $15,000
    //Calcular cobro interés mensual sobre monto de sobregiro
    @Override
    public void calcularComisionMensual(){

        //Descuento por comisión mensual
        saldoActual -= 15000;

        //Si el saldo de la cuenta está en negativo, cobrar 2% de interés
        if (saldoActual < 0){

            saldoActual += saldoActual * 0.02;

        }

    }

}
