package com.example.mdbspringboot.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Cuenta;
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

  // @GetMapping("/cuentas/{id}/edit")
  // public String cuentaEditForm(@PathVariable("id") int id, Model model) {
  //   Cuenta cuenta = cuentaRepository.darCuenta(id);
  //   if(cuenta.getSaldo()==0 && cuenta.getEstado().equals("Activa")){
  //     model.addAttribute("cuenta", cuenta);
  //     return "cuentaEdit";
  //   }
  //   else {
  //     return "redirect:/cuentas";
  //   }
  // }

  // @PostMapping("/cuentas/{id}/edit/save")
  // public String cuentaEditSave(@PathVariable("id") long id, @ModelAttribute Cuenta cuenta) {
  //   cuentaRepository.actualizarCuenta(id, cuenta.getNumero_cuenta(), cuenta.getEstado(), cuenta.getSaldo(),
  //       cuenta.getTipo(), cuenta.getCliente().getId(), cuenta.getUltima_transaccion(),
  //       cuenta.getGerente_oficina_creador(), cuenta.getFecha_creacion());
  //   return "redirect:/cuentas";
  // }

  // @GetMapping("/cuentas/{id}/delete")
  // public String cuentaBorrar(@PathVariable("id") long id) {
  //   Cuenta cuenta= new Cuenta();
    
  //   cuentaRepository.eliminarCuenta(id);
  //   return "redirect:/cuentas";
  // }
}
