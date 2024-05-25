package com.example.mdbspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Oficina;
import com.example.mdbspringboot.repositorios.OficinaRepository;



@Controller
public class OficinasController {
  @Autowired
  private OficinaRepository oficinaRepository;

  @GetMapping("/oficinas")
  public String oficinas(Model model) {
    model.addAttribute("oficinas", oficinaRepository.findAll());
    return "oficinas";
  }

  @GetMapping("/oficinas/new")
  public String oficinasForm(Model model) {
    model.addAttribute("oficina", new Oficina());
    return "oficinaNew";
  }

  @PostMapping("/oficinas/new/save")
  public String oficinassSave(@ModelAttribute Oficina oficina) {
    oficinaRepository.save(oficina);
    return "redirect:/oficinas";
  }

  // @GetMapping("/oficinas/{id}/edit")
  // public String oficinasEditForm(@PathVariable("id") int id, Model model) {
  //   Oficina oficina = oficinaRepository.darOficina(id);
  //   if (oficina != null) {
  //     model.addAttribute("oficina", oficina);
  //     return "oficinaEdit";
  //   } else {
  //     return "redirect:/oficinas";
  //   }
  // }

  // @PostMapping("/oficinas/{id}/edit/save")
  // public String oficinaEditSave(@PathVariable("id") long id, @ModelAttribute Oficina oficina) {
  //   oficinaRepository.actualizarOficina(id, oficina.getNombre(), oficina.getDireccion(),
  //       oficina.getNumero_puntos_atencion(), oficina.getGerente(), oficina.getCiudad());
  //   return "redirect:/oficinas";
  // }

  // @GetMapping("/oficinas/{id}/delete")
  // public String empleadoBorrar(@PathVariable("id") long id) {
  //   oficinaRepository.eliminarOficina(id);
  //   return "redirect:/oficinas";
  // }

}
