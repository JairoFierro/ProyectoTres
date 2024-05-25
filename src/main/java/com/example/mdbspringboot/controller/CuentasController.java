package com.example.mdbspringboot.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Cuenta;
import com.example.mdbspringboot.modelo.Usuario;
import com.example.mdbspringboot.repositorios.CuentaRepository;
import com.example.mdbspringboot.repositorios.UsuarioRepository;




@Controller
public class CuentasController {
  @Autowired
  private CuentaRepository cuentaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  

  @GetMapping("/cuentas")
  public String cuentas(Model model) {
    model.addAttribute("usuarios", usuarioRepository.findAll());
    return "cuentas";
  }

  @GetMapping("/cuentas/new")
  public String cuentasForm(Model model) {
    model.addAttribute("cuenta", new Cuenta());
    model.addAttribute("cliente");
    return "cuentaNew";
  }

  @PostMapping("/cuentas/new/save")
  public String cuentasSave(@ModelAttribute Cuenta cuenta, @ModelAttribute("cliente") int cliente){

    
    usuarioRepository.aniadirCuentaAUsuario(cliente, cuenta.getId(), cuenta.getNumero_cuenta(), cuenta.getEstado(), cuenta.getSaldo(), 
                                          cuenta.getTipo(), cuenta.getUltima_transaccion(), cuenta.getGerente_oficina(), cuenta.getFecha_creacion());
    return "redirect:/cuentas";

  }

  @GetMapping("/cuentas/{id_usuario}/edit/{id_cuenta}")
  public String cuentaEditForm(@PathVariable("id_usuario") int id_usuario, @PathVariable("id_cuenta") int id_cuenta, Model model) {
    Usuario usuario = usuarioRepository.darCuenta(id_usuario, id_cuenta);
    Cuenta cuenta = new Cuenta();
    
    for (Cuenta c : usuario.getCuentas()) {
      if (c.getId() == id_cuenta) {
        cuenta = c;
      }
    }
  
    model.addAttribute("estado");

    return "cuentaEdit";
  }

  @PostMapping("/cuentas/{id_usuario}/edit/{id_cuenta}/save")
  public String cuentaEditSave(@PathVariable("id_usuario") int id_usuario, @PathVariable("id_cuenta") int id_cuenta,  @ModelAttribute("estado") String estado) {

    Usuario usuario  = usuarioRepository.darCuenta(id_usuario, id_cuenta);


    for (Cuenta c : usuario.getCuentas()) { 
      if (c.getId() == id_cuenta) {
        if( estado.equals("Cerrada") && (c.getSaldo() != 0 || !c.getEstado().equals("Activa"))){
          return "noPuede";
        }else if(estado.equals("Desactivada") && !c.getEstado().equals("Activa") ){
          return "noPuede";
      }else{
        c.setEstado(estado);
        usuarioRepository.save(usuario);
        }
      } 
  }
  return "redirect:/cuentas";

}
}