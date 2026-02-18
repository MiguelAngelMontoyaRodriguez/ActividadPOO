import java.util.ArrayList;

public abstract class Cuenta {

    //Atributos
    private final String numeroCuenta;
    private final String titular;
    protected double saldoActual;
    private final String fechaApertura;
    protected String estadoCuenta;

    //Lista para evitar que se repitan los números de cuenta
    private static final ArrayList<String> cuentasExistentes = new ArrayList<>();

    //Constructor
    public Cuenta(String numeroCuenta, String titular, double saldoActual, String fechaApertura){

        //Validación para titular
        if (titular == null || titular.isEmpty()){

            throw new IllegalArgumentException("El campo titular no puede estar vacío");

        }

        //Validación para número de cuenta
        if (numeroCuenta == null || numeroCuenta.length() != 10){

            throw new IllegalArgumentException("El número de cuenta debe ser de 10 digitos");

        }

        //Verificar que solo contenga números la cuenta
        for (int i = 0; i < numeroCuenta.length(); i++){

            char digito = numeroCuenta.charAt(i);

            if (!Character.isDigit(digito)){

                throw new IllegalArgumentException("Solo puede contener números");

            }
        }

        //Verificación de que la cuenta no exista
        if (cuentasExistentes.contains(numeroCuenta)){

            throw new IllegalArgumentException("Número de cuenta existente");

        }

        //Validación para saldo
        if (saldoActual < 0){

            throw  new IllegalArgumentException("El saldo inicial deber ser mayor a cero");

        }

        //Validación para fecha
        if (fechaApertura == null || fechaApertura.isEmpty()){

            throw new IllegalArgumentException("El campo fecha no puede estar vacío");

        }

        //Asignación de valores
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldoActual = saldoActual;
        this.fechaApertura = fechaApertura;
        this.estadoCuenta = "ACTIVA";

        //Guardar cuenta creada en la lista
        cuentasExistentes.add(numeroCuenta);

    }

    //MÉTODOS
    //Depositar
    public void depositar(double cantidad){

        if (!estadoCuenta.equals("ACTIVA")){

            throw new IllegalArgumentException("No se puede depositar. La cuenta se encuentra bloqueada");

        }

        if (cantidad <= 0){

            throw new IllegalArgumentException("La cantidad a depositar debe ser mayor a cero");

        }

        saldoActual += cantidad;

    }

    //Retirar
    public abstract void retirar(double cantidad);

    //Calcular Comisión
    public abstract void calcularComisionMensual();

    //Bloquear cuenta
    public void bloquear(){

        estadoCuenta = "BLOQUEADA";

    }

    //Desbloquear cuenta
    public void desbloquear(){

        estadoCuenta = "ACTIVA";

    }

    //Mostrar información
    public String mostrarInformacion(){

        return "Titular = " + titular + "\nNúmero de Cuenta = " + numeroCuenta + "\nsaldo Actual = " + saldoActual +
                "\nFecha Apertura: " + fechaApertura + "\nestadoCuenta = " + estadoCuenta;

    }

    //Getters
    public String getTitular(){

        return titular;

    }

    public String getNumeroCuenta(){

        return numeroCuenta;

    }

    public double getSaldoActual(){

        return saldoActual;

    }

    public String getFechaApertura(){

        return fechaApertura;
    }

    public String getEstadoCuenta(){

        return estadoCuenta;
    }

}
