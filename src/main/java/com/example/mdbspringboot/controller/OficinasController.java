package com.example.mdbspringboot.controller;

import java.util.List;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Oficina;
import com.example.mdbspringboot.modelo.Usuario;
import com.example.mdbspringboot.repositorios.OficinaRepository;
import com.example.mdbspringboot.repositorios.UsuarioRepository;



@Controller
public class OficinasController {
  @Autowired
  private OficinaRepository oficinaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

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
    Integer gerente =oficina.getGerente();
    Usuario usuario =usuarioRepository.darUsuario(gerente);
    
    if(usuario.getTipo_usuario()==null){
      return "noPuede";
    }
    if (usuario.getTipo_usuario().equals("Empleado")){
        oficinaRepository.save(oficina);
        return "redirect:/oficinas";
      }
    else{
      return "noPuede";
    }
  }
}


