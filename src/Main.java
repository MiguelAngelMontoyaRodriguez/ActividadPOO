import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        boolean continuarSistema = true; // Para crear varias cuentas

        while (continuarSistema) {

            try {

                //Seleccionar el tipo de cuenta
                String[] opcionCuenta = {"Cuenta Ahorros", "Cuenta Corriente", "Cuenta Nómina"};

                int tipoCuenta = JOptionPane.showOptionDialog(null, "Seleccione el tipo" +
                                " de cuenta que desea crear", "SISTEMA BANCARIO", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, opcionCuenta, opcionCuenta[0]
                );

                if (tipoCuenta == -1) {

                    return; //Cuando el usuario cierra la ventana
                }

                //Datos del titular
                String titular = "";
                boolean titularValido = false;

                while (!titularValido) {

                    titular = JOptionPane.showInputDialog("Ingrese el nombre del titular: ");

                    if (titular == null || titular.isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del titular para continuar");

                    } else {

                        boolean soloLetras = true;

                        for (int i = 0; i < titular.length(); i++) {

                            char nombreTitular = titular.charAt(i);

                            if (!Character.isLetter(nombreTitular) && nombreTitular != ' ') {

                                soloLetras = false;
                                break;
                            }
                        }

                        if (!soloLetras) {

                            JOptionPane.showMessageDialog(null, "El nombre debe contener solo letras");

                        } else {

                            titularValido = true;

                        }
                    }
                }

                //Número de cuenta
                String numeroCuenta = "";
                boolean numeroCuentaValido = false;

                while (!numeroCuentaValido) {

                    numeroCuenta = JOptionPane.showInputDialog("Ingrese el número de la cuenta (10 dígitos): ");

                    if (numeroCuenta != null && numeroCuenta.length() == 10) {

                        boolean soloNumeros = true;

                        for (int i = 0; i < numeroCuenta.length(); i++) {

                            if (!Character.isDigit(numeroCuenta.charAt(i))) {

                                soloNumeros = false;
                                break;

                            }
                        }

                        if (soloNumeros) {

                            numeroCuentaValido = true;

                        } else {

                            JOptionPane.showMessageDialog(null, "La cuenta debe tener solo números");

                        }

                    } else {

                        JOptionPane.showMessageDialog(null, "El número de la cuenta debe tener 10 dígitos");

                    }
                }

                //Saldo Inicial
                double saldoIncial = 0;
                boolean saldoValido = false;

                while (!saldoValido) {

                    try {

                        String ingresarSaldo = JOptionPane.showInputDialog("Ingrese un saldo inicial: ");
                        saldoIncial = Double.parseDouble(ingresarSaldo);

                        if (saldoIncial < 0) {

                            JOptionPane.showMessageDialog(null, "El saldo no puede ser negativo");

                        } else {

                            saldoValido = true;
                        }

                    } catch (NumberFormatException excepcion) {

                        JOptionPane.showMessageDialog(null, "Ingrese un saldo válido");
                    }
                }

                //Fecha de apertura
                String fecha = "";
                boolean fechaValida = false;

                while (!fechaValida) {

                    fecha = JOptionPane.showInputDialog("Ingrese la fecha de apertura (dd(mm/aaaa): ");

                    if (fecha != null && fecha.length() == 10 && fecha.charAt(2) == '/' && fecha.charAt(5) == '/') {

                        boolean numerosFecha = true;

                        for (int i = 0; i < fecha.length(); i++) {

                            if (i == 2 || i == 5) {

                                continue;

                            }

                            char caracteresFecha = fecha.charAt(i);

                            if (caracteresFecha < '0' || caracteresFecha > '9') {

                                numerosFecha = false;
                                break;
                            }
                        }

                        if (numerosFecha) {

                            fechaValida = true;

                        } else {

                            JOptionPane.showMessageDialog(null, "La fecha debe contener solo numeros");

                        }

                    } else {

                        JOptionPane.showMessageDialog(null, "Formato inválido. Use dd/mm/ aaaa");
                    }
                }

                //Crear objeto cuenta
                Cuenta cuenta = null;


                //Crear cuenta según el tipo
                switch (tipoCuenta) {

                    case 0:

                        cuenta = new CuentaAhorros(numeroCuenta, titular, saldoIncial, fecha);
                        break;

                    case 1:

                        cuenta = new CuentaCorriente(numeroCuenta, titular, saldoIncial, fecha);
                        break;

                    case 2:
                        cuenta = new CuentaNomina(numeroCuenta, titular, saldoIncial, fecha);
                        break;

                }

                if (cuenta == null) {

                    JOptionPane.showMessageDialog(null, "Ocurrio un error al crear la cuenta");
                    return;

                }

                //Menú de opciones luego de crear la cuenta
                boolean salirMenu = false;

                while (!salirMenu) {

                    String menuTexto = "Seleccione una opción:\n" + "1. Depositar\n" + "2. Retirar\n" +
                            "3. Bloquear cuenta\n" + "4. Desbloquear cuenta\n";

                    //Opción adicional si es cuenta de nómina
                    if (cuenta instanceof CuentaNomina){

                        menuTexto += "5. Registrar salario del mes\n";

                    }

                    //Opción salir.
                    menuTexto += "6. Mostrar información y salir";

                    String opcion = JOptionPane.showInputDialog(menuTexto);

                    if (opcion == null) {

                        break;
                    }

                    switch (opcion) {

                        //Depositar
                        case "1":
                            try {

                                double deposito = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar:"));

                                cuenta.depositar(deposito);

                                JOptionPane.showMessageDialog(null, "Depósito exitoso");

                            } catch (Exception excepcion) {

                                JOptionPane.showMessageDialog(null, excepcion.getMessage());

                            }
                            break;

                        //Retirar
                        case "2":
                            try {

                                double retiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a retirar"));

                                cuenta.retirar(retiro);

                                JOptionPane.showMessageDialog(null, "Retiro exitoso");

                            } catch (Exception excepcion) {

                                JOptionPane.showMessageDialog(null, excepcion.getMessage());

                            }
                            break;

                        //Bloquear
                        case "3":

                            cuenta.bloquear();
                            JOptionPane.showMessageDialog(null, "Cuenta bloqueada");

                            break;

                        //Desbloquear
                        case "4":

                            cuenta.desbloquear();
                            JOptionPane.showMessageDialog(null, "Cuenta desbloqueada");

                            break;

                        //Registrar salario cuenta nomina
                        case "5":

                            if (cuenta instanceof CuentaNomina){

                                CuentaNomina nomina = (CuentaNomina) cuenta;

                                int respuesta = JOptionPane.showConfirmDialog(null,"Recibió salario" +
                                        "este mes?", "Salario", JOptionPane.YES_NO_OPTION);

                                boolean recibio = (respuesta == JOptionPane.YES_OPTION);

                                Cuenta nuevaCuenta = nomina.registrarSalario(recibio);

                                if (nuevaCuenta instanceof CuentaCorriente){

                                    JOptionPane.showMessageDialog(null,"La cuenta nómina se convirtio a" +
                                            "cuenta corriente");
                                }

                                cuenta = nuevaCuenta;

                            }
                            break;

                        case "6":

                            JOptionPane.showMessageDialog(null, cuenta.mostrarInformacion());

                            salirMenu = true;
                            break;

                        default:

                            JOptionPane.showMessageDialog(null, "Opción no valida");

                    }

                }

                //Crear otra cuenta
                int otraCuenta = JOptionPane.showConfirmDialog(null, "¿Desea crear otra cuenta?", "Continuar",
                        JOptionPane.YES_NO_OPTION);

                if (otraCuenta != JOptionPane.YES_OPTION) {

                    continuarSistema = false;

                }

            } catch (Exception excepcion) {

                JOptionPane.showMessageDialog(null, excepcion.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema finalizado");
    }
}
