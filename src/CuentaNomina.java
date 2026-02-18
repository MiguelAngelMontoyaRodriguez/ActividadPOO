public class CuentaNomina extends Cuenta{

    private int mesesSinSalario  = 0;

    //Constructor
    public CuentaNomina(String numeroCuenta, String titular, double saldoActual, String fechaApertura) {

        //Llamamos al constructor de la clase principal (Cuenta)
        super(numeroCuenta, titular, saldoActual, fechaApertura);

    }

    //MÉTODOS
    //Registrar si recibio salario
    public Cuenta registrarSalario(boolean recibioSalario){

        if (recibioSalario) {

            mesesSinSalario = 0;

        }else {

            mesesSinSalario++;

        }

        if (mesesSinSalario >= 3){

            return new CuentaCorriente(this.getNumeroCuenta(), this.getTitular(), this.getSaldoActual(), this.getFechaApertura());
        }

        return this;
    }

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

        //Validar
        if (cantidad > saldoActual){

            throw new IllegalArgumentException("No se puede realizar el retiro. Saldo insuficiente");

        }

        saldoActual-= cantidad;

    }

    //Calcular cobro comisión de S8,000 si no recibe salario en el mes
    @Override
    public void calcularComisionMensual(){

        //Solo se cobrará la comisión en caso de no recibir el salario
        if (mesesSinSalario > 0){

            saldoActual -= 8000;

        }
    }

}
