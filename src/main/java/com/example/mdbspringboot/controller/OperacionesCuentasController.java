package com.example.mdbspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mdbspringboot.modelo.Cuenta;
import com.example.mdbspringboot.modelo.OperacionCuenta;
import com.example.mdbspringboot.modelo.Usuario;
import com.example.mdbspringboot.repositorios.CuentaRepository;
import com.example.mdbspringboot.repositorios.OperacionCuentaRepository;
import com.example.mdbspringboot.repositorios.UsuarioRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OperacionesCuentasController {
  @Autowired
  private OperacionCuentaRepository operacionCuentaRepository;


  @Autowired
  private CuentaRepository cuentaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/operacionesCuentas")
  public String operaciones_cuentas(Model model, Integer numero_cuenta ,Integer numero_cuentaCm) {
    model.addAttribute("usuarios", usuarioRepository.findAll());
  //   Date fecha = new Date(System.currentTimeMillis());
  //   int retryCount = 0;
  //   while (true) {
  //     try {
  //       if (numero_cuenta != null) {
  //         model.addAttribute("operacionesCuentas1", operacionesCuentasServicio.consultaOpCuentaUltimoMesSerializable(numero_cuenta).get("operacion_cuenta1"));
  //         model.addAttribute("operacionesCuentas2", operacionesCuentasServicio.consultaOpCuentaUltimoMesSerializable(numero_cuenta).get("operacion_cuenta2"));
  //         return "operacionesCuentasDos";
  //       }else if (numero_cuentaCm != null) {  
  //         model.addAttribute("operacionesCuentas1", operacionesCuentasServicio.consultaOpCuentaUltimoMesReadCommited(numero_cuentaCm).get("operacion_cuenta1"));
  //         model.addAttribute("operacionesCuentas2", operacionesCuentasServicio.consultaOpCuentaUltimoMesReadCommited(numero_cuentaCm).get("operacion_cuenta2"));
  //         return "operacionesCuentasDos";
  //       } else {
  //         model.addAttribute("operacionesCuentas", operacionCuentaRepository.darOperacionesCuentas());
  //       }
  //       break; // Si se completa con éxito, salir del bucle
  //     } catch (Exception e) {
  //         System.out.println("Intento " + (retryCount + 1) + ": " + e);
  //     }
  // }
  return "operacionesCuentas";

  }

  @GetMapping("/operacionesCuentas/new")
  public String operacionesCuentasForm(Model model) {
    model.addAttribute("operacionCuenta", new OperacionCuenta());
    model.addAttribute("cuentaSalida");
    return "operacionesCuentasNew";
  }

  @PostMapping("/operacionesCuentas/new/save")
  public String operacionesCuentasSave(@ModelAttribute OperacionCuenta operacionCuenta,@ModelAttribute("cuentaSalida") String cuentaSalida, RedirectAttributes redirectAttributes) {

    Usuario usuario = usuarioRepository.darUsuario(operacionCuenta.getCliente());

    List<Usuario> usuarios = usuarioRepository.darUsuarios();

    int num_operaciones=0;
    for (Usuario user : usuarios) {
      if(user.getCuentas() != null){
      for (Cuenta c : user.getCuentas()) { 
        num_operaciones+=c.getOperaciones_cuenta().size();
      
      }
    }  }  

    //Obtener cuenta de salida

    Cuenta cuenta_salida=null;
    int cuenta_salida_index=0;
    int usuario_llegada_index=0;

    for (Usuario u : usuarios) {
      if(u.getCuentas() != null){
    for (Cuenta c : u.getCuentas()) { 
      if (c.getNumero_cuenta().equals(cuentaSalida)) {
        if(c.getEstado().equals("Activa") && c.getSaldo()>=operacionCuenta.getMonto_operacion() ){ 
          cuenta_salida = c;
          cuenta_salida_index=u.getCuentas().indexOf(c);
          usuario_llegada_index=usuarios.indexOf(u);
        }else{
          return "noPuede";
        }
      } 
    }
    }
  }
    
    if(operacionCuenta.getTipo_operacion().equals("Consignacion") || operacionCuenta.getTipo_operacion().equals("Retiro") ){

      

      if(operacionCuenta.getTipo_operacion().equals("Consignacion")){
        List<OperacionCuenta> operaciones_cuenta =  usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta();
        operaciones_cuenta.add(operacionCuenta);
        int a = usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().indexOf(operacionCuenta);
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().get(a).setId(num_operaciones+1);
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setSaldo(cuenta_salida.getSaldo()+operacionCuenta.getMonto_operacion());
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setUltima_transaccion(operacionCuenta.getFecha());
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setOperaciones_cuenta(operaciones_cuenta);

      }else{

        List<OperacionCuenta> operaciones_cuenta =  usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta();
        operaciones_cuenta.add(operacionCuenta);
        int a = usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().indexOf(operacionCuenta);
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().get(a).setId(num_operaciones+1);
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setSaldo(cuenta_salida.getSaldo()-operacionCuenta.getMonto_operacion());
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setUltima_transaccion(operacionCuenta.getFecha());
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setOperaciones_cuenta(operaciones_cuenta);

      }
      usuarioRepository.saveAll(usuarios);

    }
    if (operacionCuenta.getTipo_operacion().equals("Transferencia") ){

      Cuenta cuenta_llegada=null;
      int usuario_index=0;
      int cuenta_llegada_index=0;

      //Obtener cuenta de llegada
      for (Usuario u : usuarios) { 
      for (Cuenta c : u.getCuentas()) { 
        if (c.getNumero_cuenta().equals(operacionCuenta.getCuenta_llegada()) ) {
          cuenta_llegada = c;
          usuario_index=usuarios.indexOf(u);
          cuenta_llegada_index= u.getCuentas().indexOf(c);
        } 
      }}

      if(cuenta_llegada!=null){
        //Actualizar cuenta salida
        List<OperacionCuenta> operaciones_cuenta =  usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta();
        operaciones_cuenta.add(operacionCuenta);
        

        int a = usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().indexOf(operacionCuenta);
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).getOperaciones_cuenta().get(a).setId(num_operaciones+1);

        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setSaldo(cuenta_salida.getSaldo()-operacionCuenta.getMonto_operacion());

        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setUltima_transaccion(operacionCuenta.getFecha());
        usuarios.get(usuario_llegada_index).getCuentas().get(cuenta_salida_index).setOperaciones_cuenta(operaciones_cuenta);



        // List<OperacionCuenta> operaciones_cuenta2 =  usuarios.get(usuario_index).getCuentas().get(cuenta_llegada_index).getOperaciones_cuenta();
        

        
        // OperacionCuenta operacionCuentaCopia = new OperacionCuenta();
        // operacionCuentaCopia.setId(operacionCuenta.getId());
        // operacionCuentaCopia.setTipo_operacion(operacionCuenta.getTipo_operacion());
        // operacionCuentaCopia.setFecha(operacionCuenta.getFecha());
        // operacionCuentaCopia.setMonto_operacion(operacionCuenta.getMonto_operacion());
        // operacionCuentaCopia.setCliente(operacionCuenta.getCliente());
        // operacionCuentaCopia.setCuenta_llegada(operacionCuenta.getCuenta_llegada());
        // operacionCuentaCopia.setPunto_atencion(operacionCuenta.getPunto_atencion());
        // operaciones_cuenta2.add(operacionCuentaCopia);
        
        
        // int b = operaciones_cuenta2.indexOf(operacionCuentaCopia);
        // operaciones_cuenta2.get(b).setId(num_operaciones+2);

        usuarios.get(usuario_index).getCuentas().get(cuenta_llegada_index).setSaldo(cuenta_llegada.getSaldo()+operacionCuenta.getMonto_operacion());

        usuarios.get(usuario_index).getCuentas().get(cuenta_llegada_index).setUltima_transaccion(operacionCuenta.getFecha());

        // usuarios.get(usuario_index).getCuentas().get(cuenta_llegada_index).setOperaciones_cuenta(operaciones_cuenta2);

        usuarioRepository.saveAll(usuarios);
        

      }else{
        return "noPuede";
      }
  }
  return "redirect:/operacionesCuentas";
}

  // @GetMapping("/operacionesCuentas/{id}/edit")
  // public String operacionesCuentasEditForm(@PathVariable("id") int id, Model model) {
  //   OperacionCuenta operacionCuenta = operacionCuentaRepository.darOperacioneCuenta(id);
  //   if (operacionCuenta != null) {
  //     model.addAttribute("operacionesCuentas", operacionCuenta);
  //     return "operacionesCuentas";
  //   } else {
  //     return "redirect:/operacionesCuentas";
  //   }
  // }


  


  // @GetMapping("/ConsultaOpCuentasSe")
  // public String consultaOpCuentasSe(Model model) {
  //   return "ConsultaOpCuentasSe";
  // }

  // @GetMapping("/ConsultaOpCuentasRc")
  // public String consultaOpCuentasRc(Model model) {
  //   return "ConsultaOpCuentasRc";
  // }
  
  // @GetMapping("/operacionCuentaCajero")
  // public String operacionesCuentasCajero(Model model) {
  //   model.addAttribute("operacionesCuentas", operacionCuentaRepository.darOperacionesCuentas());
  //   return "operacionCuentaCajero";
  // }


  // @GetMapping("/operacionesCuentasCajero/new")
  // public String operaciones_cuentasCajeroForm(Model model) {
  //   model.addAttribute("operacionCuenta", new OperacionCuenta());
  //   return "operacionesCuentaCajeroNew";
  // }

  // @PostMapping("/operacionesCuentas/new/save")
  // public String operaciones_cuentasSave(@ModelAttribute OperacionCuenta operacionCuenta, RedirectAttributes redirectAttributes) {
  //   //Cuenta cuentaLlegada=cuentaRepository.darCuenta(operacionCuenta.getCuenta_llegada());
  //   Cuenta cuentaSalida=cuentaRepository.darCuenta(operacionCuenta.getCuenta_salida());
    
  //   if(operacionCuenta.getTipo_operacion().equals("Consignacion")){
  //       //ACA SOLO SE MODIFICA CUENTA LLEGADA
  //       try {
  //         Float valorOperacion=operacionCuenta.getMonto_operacion();
  //         Float saldo=cuentaSalida.getSaldo();
  //         operacionesCuentasServicio.operacionConsignacion(cuentaSalida,valorOperacion,saldo);
  //       } 
  //       catch (InterruptedException e) {
  //         System.err.println("Error : " + e.getMessage());
  //         redirectAttributes.addFlashAttribute("errorMessage", "error");
  //         return "redirect:/operacionesCuentas";
  //         }
  //   }
  //   if (operacionCuenta.getTipo_operacion().equals("Retiro") ){
  //     try {
  //       Float valorOperacion=operacionCuenta.getMonto_operacion();
  //       Float saldo=cuentaSalida.getSaldo();
  //       operacionesCuentasServicio.operacionRetiro(cuentaSalida, valorOperacion,saldo);
  //     } 
  //     catch (InterruptedException e) {
  //       System.err.println("Error : " + e.getMessage());
  //       redirectAttributes.addFlashAttribute("errorMessage", "error");
  //       return "redirect:/operacionesCuentas";
  //       }
  //   }

  //   if (operacionCuenta.getTipo_operacion().equals("Transferencia") ){
  //     try {
  //       operacionesCuentasServicio.operacionTransferencia(operacionCuenta,cuentaSalida);

  //     } 
  //     catch (InterruptedException e) {
  //       System.err.println("Error : " + e.getMessage());
  //       redirectAttributes.addFlashAttribute("errorMessage", "error");
  //       return "redirect:/operacionesCuentas";
  //       }

  //   }
    
  //   operacionCuentaRepository.insertarOperacioneCuenta(operacionCuenta.getTipo_operacion(), operacionCuenta.getFecha(),
  //       operacionCuenta.getCuenta_salida(), operacionCuenta.getMonto_operacion(), operacionCuenta.getCliente(),
  //       operacionCuenta.getPunto_atencion().getId(), operacionCuenta.getCuenta_llegada());
  //   return "redirect:/operacionesCuentas";
  // }

  // @GetMapping("/operacionesCuentas/{id}/edit")
  // public String operaciones_cuentasEditForm(@PathVariable("id") int id, Model model) {
  //   OperacionCuenta operacionCuenta = operacionCuentaRepository.darOperacioneCuenta(id);
  //   if (operacionCuenta != null) {
  //     model.addAttribute("operacionesCuentas", operacionCuenta);
  //     return "operacionesCuentas";
  //   } else {
  //     return "redirect:/operacionesCuentas";
  //   }
  // }

  // @PostMapping("/operacionesCuentas/{id}/edit/save")
  // public String operaciones_cuentasEditSave(@PathVariable("id") long id,
  //     @ModelAttribute OperacionCuenta operacionCuenta) {
  //   operacionCuentaRepository.actualizarOperacioneCuenta(id, operacionCuenta.getTipo_operacion(),
  //       operacionCuenta.getFecha(), operacionCuenta.getCuenta_salida(), operacionCuenta.getMonto_operacion(),
  //       operacionCuenta.getCliente(), operacionCuenta.getCliente(), operacionCuenta.getCuenta_llegada());
  //   return "redirect:/operacionesCuentas";
  // }

  // @GetMapping("/operacionesCuentas/{id}/delete")
  // public String operaciones_cuentasBorrar(@PathVariable("id") long id) {
  //   operacionCuentaRepository.eliminaOperacioneCuenta(id);
  //   return "redirect:/operacionesCuentas";
  // }

  // @GetMapping("/operacionesCuentas/consultaSerializable")
  // public String consultaOpCuentaUltimoMesSerializable(RedirectAttributes redirectAttributes, Integer numero_cuenta,Model model) {
  //   try {
  //     System.out.println("entro");
  //     operacionesCuentasServicio.consultaOpCuentaUltimoMesSerializable(numero_cuenta);
  //     model.addAttribute("operacionesCuentas", operacionesCuentasServicio.consultaOpCuentaUltimoMesSerializable(numero_cuenta));
  //   } 
  //   catch (InterruptedException e) {
  //     System.err.println("Error durante la consulta : " + e.getMessage());
  //     redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones correctamente.");
  //     return "redirect:/operacionesCuentas";
  //     }

  //   return "redirect:/operacionesCuentas";
  // }

  // @GetMapping("/operacionesCuentas/consultaReadUncommited")
  // public String consultaOpCuentaUltimoMesReadCommited(RedirectAttributes redirectAttributes, Integer numero_cuenta) {
  //   try {
  //     operacionesCuentasServicio.consultaOpCuentaUltimoMesReadCommited(numero_cuenta);
  //   } 
  //   catch (InterruptedException e) {
  //     System.err.println("Error durante la consulta : " + e.getMessage());
  //     redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones correctamente.");
  //     return "redirect:/operacionesCuentas";
  //     }

  //   return "redirect:/operacionesCuentas";
  // }

  }
