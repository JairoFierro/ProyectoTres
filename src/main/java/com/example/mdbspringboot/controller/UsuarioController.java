package com.example.mdbspringboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Cuenta;
import com.example.mdbspringboot.modelo.OperacionCuenta;
import com.example.mdbspringboot.modelo.PuntoAtencion;
import com.example.mdbspringboot.modelo.Usuario;
import com.example.mdbspringboot.repositorios.PuntoAtencionRepository;
import com.example.mdbspringboot.repositorios.UsuarioRepository;
import com.example.mdbspringboot.repositorios.UsuarioRepository.respuestaEstadoComun;
import com.example.mdbspringboot.repositorios.UsuarioRepository.respuestaTipoCuentaComun;



@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

    @GetMapping("/consultas")
    public String consultaDos(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "consultas";
    }

    @GetMapping("/consultaUno")
    public String consultaUno(Model model, String numero_documento,  Integer saldo_menor, Integer saldo_mayor, String tipo_cuenta) {
        
        Usuario usuario = usuarioRepository.encontrarUsuarioPorDocumento(numero_documento);
        
        model.addAttribute("usuario", usuario);
        
        
        
        List<Cuenta> cuentas = usuario.getCuentas();
        

        List<Integer> puntosAtencionId = new ArrayList<Integer>();

        List<PuntoAtencion> puntosAtencion = new ArrayList<PuntoAtencion>();

        if(saldo_menor != null && saldo_mayor != null)
        {
            List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
            for (Cuenta cuenta : cuentas) {
                if(cuenta.getSaldo() >= saldo_menor && cuenta.getSaldo() <= saldo_mayor)
                {
                    cuentasFiltradas.add(cuenta);
                }
            }
            model.addAttribute("cuentas", cuentasFiltradas);
        }

        if( tipo_cuenta != null)
        {
            List<Cuenta> cuentasFiltradasTipo = new ArrayList<Cuenta>();
            for (Cuenta cuenta : cuentas) {
                if(cuenta.getTipo().equals(tipo_cuenta))
                {
                    cuentasFiltradasTipo.add(cuenta);
                }
            }
            model.addAttribute("cuentasTipo", cuentasFiltradasTipo);
        }


        for (Cuenta cuenta : cuentas) {
            for (OperacionCuenta operacion : cuenta.getOperaciones_cuenta()){
                if( operacion.getPunto_atencion() != null && !puntosAtencionId.contains(operacion.getPunto_atencion()))
                    puntosAtencionId.add(operacion.getPunto_atencion());
            }
            
        }
        List<respuestaTipoCuentaComun> tipoComun= usuarioRepository.agruparPorTipoCuenta(numero_documento);
        model.addAttribute("tipoComun", tipoComun.get(0));

        List<respuestaEstadoComun> estadoComun= usuarioRepository.agruparEstadoCuenta(numero_documento);
        model.addAttribute("estadoComun", estadoComun.get(0));





        int a=0;


        return "consultaUno";
    }



    @GetMapping("/consultaDos")
    public String consultaDos(Model model, String numMes, String numCuenta) {
        int numCuenta2 = Integer.parseInt(numCuenta);
        String fecha1 = "2024-" + numMes + "-01";
        String fecha2 = "2024-" + numMes + "-30"; 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Double saldoRestar;
        saldoRestar=0.0;
        Double saldoRestar2;
        saldoRestar2=0.0;
        Date fechaInicial;
        Date fechaFinal;
        Float saldoInicial;
        Float saldoFinal;
        saldoInicial=0.0f;
        saldoFinal=0.0f;


        try {
            fechaInicial = formato.parse(fecha1);
            fechaFinal = formato.parse(fecha2);
        } catch (ParseException e) {
            model.addAttribute("error", "Error en el formato de las fechas");
            return "error"; // Vista de error, ajusta según tu configuración
        }

    
        List<OperacionCuenta> operacionesConsignacion = new ArrayList<>();
        List<OperacionCuenta> operacionesRetiro = new ArrayList<>();
        List<OperacionCuenta> operacionesTransferencia = new ArrayList<>();
    
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            List<Cuenta> cuentas = usuario.getCuentas();
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getId() == numCuenta2) {
                    List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                    for (OperacionCuenta operacionCuenta : operacionesCuenta) {
                        Date fechaOperacion = operacionCuenta.getFecha();
                        if (fechaOperacion.compareTo(fechaInicial) >= 0 && fechaOperacion.compareTo(fechaFinal) <= 0) {
                            switch (operacionCuenta.getTipo_operacion()) {
                                case "Consignacion":
                                    operacionesConsignacion.add(operacionCuenta);
                                    break;
                                case "Retiro":
                                    operacionesRetiro.add(operacionCuenta);
                                    break;
                                case "Transferencia":
                                    operacionesTransferencia.add(operacionCuenta);
                                    break;
                            }
                        }
                        //EMPIEZO A BUSCAR EL SALDO PARA LA OPERACION INICIAL
                        while (fechaInicial.getTime() <= fechaOperacion.getTime() ){
                            switch (operacionCuenta.getTipo_operacion()) {
                                case "Consignacion":
                                    saldoRestar+=operacionCuenta.getMonto_operacion();
                                    break;
                                case "Retiro":
                                    saldoRestar-=operacionCuenta.getMonto_operacion();
                                    break;
                                case "Transferencia":
                                    if(operacionCuenta.getCuenta_llegada().equals(numCuenta2)){
                                        //Si la cuenta de llegada es igual a la cuenta en la que estoy le sumo
                                        saldoRestar+=operacionCuenta.getMonto_operacion();
                                    }if (!operacionCuenta.getCuenta_llegada().equals(numCuenta2)) {
                                        saldoRestar-=operacionCuenta.getMonto_operacion();
                                    }
                                    break;
                            }
                            fechaInicial.setTime(fechaInicial.getTime() + (24 * 60 * 60 * 1000)); // Suma 1 día en milisegundos

                        }
                        //EMPIEZO A BUSCAR EL SALDO PARA LA OPERACION FINAL
                        while (fechaFinal.getTime() <= fechaOperacion.getTime() ){
                            switch (operacionCuenta.getTipo_operacion()) {
                                case "Consignacion":
                                    saldoRestar2+=operacionCuenta.getMonto_operacion();
                                    break;
                                case "Retiro":
                                    saldoRestar2-=operacionCuenta.getMonto_operacion();
                                    break;
                                case "Transferencia":
                                    if(operacionCuenta.getCuenta_llegada().equals(numCuenta2)){
                                        //Si la cuenta de llegada es igual a la cuenta en la que estoy le sumo
                                        saldoRestar2+=operacionCuenta.getMonto_operacion();
                                    }if (!operacionCuenta.getCuenta_llegada().equals(numCuenta2)) {
                                        saldoRestar2-=operacionCuenta.getMonto_operacion();
                                    }
                                    break;
                            }
                            fechaFinal.setTime(fechaInicial.getTime() + (24 * 60 * 60 * 1000)); // Suma 1 día en milisegundos
                        }


                        
                    }
                    //Hacer resta

                    saldoInicial=(float) (cuenta.getSaldo()-saldoRestar);
                    saldoFinal=(float) (cuenta.getSaldo()-saldoRestar2);

                    model.addAttribute("saldoInicial", saldoInicial);
                    model.addAttribute("saldoFinal", saldoInicial);
            
                    model.addAttribute("operacionesConsignacion", operacionesConsignacion);
                    model.addAttribute("operacionesRetiro", operacionesRetiro);
                    model.addAttribute("operacionesTransferencia", operacionesTransferencia);
                
                    return "consultaDos"; // Vista donde se mostrarán los resultados
                }
            }
        }
        return "consultaDos"; // Vista donde se mostrarán los resultados

    }


    @GetMapping("/consultaUno/new")
    public String consultaUnoForm(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "consultaUno";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String usuariosForm(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "usuariosNew";
    }

    @PostMapping("/usuarios/new/save")
    public String clientesSave(@ModelAttribute Usuario cliente, Model model) {

        usuarioRepository.save(cliente);

        return "redirect:/";
    }



    // @GetMapping("/clientes")
    // public String clientes(Model model,Integer numMes,Integer  numCuenta, Integer anio, String numero_documento) {

    //     if(numMes == null && numCuenta== null && anio==null && numero_documento == null )
    //     {
    //         model.addAttribute("clientes", clienteRepository.darClientes());
    //     }else if(numero_documento != null)
    //     {
    //         Integer id = clienteRepository.obtenerIdDadoDoc(numero_documento);
    //         if(id != null)
    //         {
    //             model.addAttribute("cliente", clienteRepository.darCliente(id));
    //             model.addAttribute("cuentas", clienteRepository.obtenerCuentasCliente(id));
    //             model.addAttribute("prestamos", clienteRepository.obtenerPrestamosCliente(id));
    //             model.addAttribute("oficinas", clienteRepository.obtenerOficinasDelCliente(id));
    //             return "consultaDeCliente";
    //         }



    //         model.addAttribute("clientes", clienteRepository.darClientes());
    //     }
    //     else
    //     {
    //         int a=1;
    //         Collection<RespuestaExtracto> informacion = clienteRepository.infoExtracto(numMes,numCuenta,anio);
    //         model.addAttribute("clientesExtracto", clienteRepository.infoExtracto(numMes, numCuenta, anio));

    //         RespuestaExtracto ultimoElemento = null;
    //         RespuestaExtracto primerElemento = null;

    //         if (!informacion.isEmpty()) {
    //             for (RespuestaExtracto elemento : informacion) {
    //                 if (primerElemento == null) {
    //                     primerElemento = elemento;
    //                 }
    //                 ultimoElemento = elemento;
    //             }
    //         }
    //         model.addAttribute("saldoFinalMes", ultimoElemento.getSALDO());
    //         model.addAttribute("saldoInicioMes", primerElemento.getSALDO());
    //         return "infoExtracto";


    //     }
    //     return "clientes";
    // }

    // @GetMapping("/clientes/new")
    // public String clientesForm(Model model) {

    //     model.addAttribute("cliente", new Cliente());
    //     return "clienteNew";
    // }

    // @PostMapping("/clientes/new/save")
    // public String clientesSave(@ModelAttribute Cliente cliente,Model model) {

    //     clienteRepository.insertarCliente(cliente.getNumero_documento(), cliente.getTipo(),
    //             cliente.getTipo_documento(), cliente.getNombre(),
    //             cliente.getNacionalidad(), cliente.getDireccion_fisica(),
    //             cliente.getDireccion_electronica(), cliente.getTelefono(),
    //             cliente.getCodigo_postal(), cliente.getCiudad(), cliente.getDepartamento());

    //     model.addAttribute("usuarioCliente", new UsuarioCliente());
    //     model.addAttribute("clientes", clienteRepository.darClientes());

    //     return "usuarioClienteNew";
    // }

    // @GetMapping("/clientes/{id}/edit")
    // public String clienteEditForm(@PathVariable("id") int id, Model model) {
    //     Cliente cliente = clienteRepository.darCliente(id);
    //     if (cliente != null) {
    //         model.addAttribute("cliente", cliente);
    //         return "clienteEdit";
    //     } else {
    //         return "redirect:/clientes";
    //     }
    // }

    // @PostMapping("/clientes/{id}/edit/save")
    // public String clienteEditSave(@PathVariable("id") long id, @ModelAttribute Cliente cliente) {
    //     clienteRepository.actualizarCliente(((long) id), cliente.getNumero_documento(), cliente.getTipo(),
    //             cliente.getTipo_documento(), cliente.getNombre(),
    //             cliente.getNacionalidad(), cliente.getDireccion_fisica(),
    //             cliente.getDireccion_electronica(), cliente.getTelefono(),
    //             cliente.getCodigo_postal(), cliente.getCiudad(), cliente.getDepartamento());
    //     return "redirect:/clientes";
    // }

    // @GetMapping("/clientes/{id}/delete")
    // public String clienteBorrar(@PathVariable("id") long id) {
    //     clienteRepository.eliminarCliente(id);
    //     return "redirect:/clientes";
    // }

}
