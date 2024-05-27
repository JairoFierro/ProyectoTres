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
import com.example.mdbspringboot.repositorios.UsuarioRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OperacionesCuentasController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/operacionesCuentas")
  public String operaciones_cuentas(Model model, Integer numero_cuenta ,Integer numero_cuentaCm) {
    model.addAttribute("usuarios", usuarioRepository.findAll());

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
        if(c.getEstado().equals("Activa") && ( (c.getSaldo()>=operacionCuenta.getMonto_operacion() && operacionCuenta.getTipo_operacion().equals("Retiro")) || (c.getSaldo()>=operacionCuenta.getMonto_operacion() && operacionCuenta.getTipo_operacion().equals("Transferencia")) || operacionCuenta.getTipo_operacion().equals("Consignacion") )  ){ 
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


  }
