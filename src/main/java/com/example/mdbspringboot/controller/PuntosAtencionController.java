package com.example.mdbspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.modelo.Oficina;
import com.example.mdbspringboot.modelo.PuntoAtencion;
import com.example.mdbspringboot.repositorios.OficinaRepository;
import com.example.mdbspringboot.repositorios.PuntoAtencionRepository;

@Controller
public class PuntosAtencionController {
   @Autowired
   private PuntoAtencionRepository puntoAtencionRepository;

   @Autowired
   private OficinaRepository oficinaRepository;


   @GetMapping("/puntosAtencion")
   public String puntosAtencion(Model model) {
    model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
     return "puntosAtencion";
   }

   @GetMapping("/puntosAtencion/new")
   public String puntosAtencionForm(Model model) {
     model.addAttribute("puntoAtencion", new PuntoAtencion());
     return "puntosAtencionNew";
   }

  @PostMapping("/puntosAtencion/new/save")
  public String puntosAtencionSave(@ModelAttribute PuntoAtencion puntoAtencion) {

    String idOficina=puntoAtencion.getIdOficina();
    int idOficina2;
    idOficina2 = Integer.parseInt(idOficina);
    int idPuntoAtencion=puntoAtencion.getId();
    puntoAtencionRepository.save(puntoAtencion);

    List<Oficina> oficinas= oficinaRepository.buscarTodas();
    for(Oficina oficina: oficinas){
      if (oficina.getId()==idOficina2){
        List<Integer> puntos= oficina.getPuntos_atencion();
        if(puntos!=null){
          puntos.add(idPuntoAtencion);
          oficina.setPuntos_atencion(puntos);
        }
        else{
          return "noPuede";
        }

      }
    }
    oficinaRepository.saveAll(oficinas);
    return "redirect:/puntosAtencion";
  }



  // @GetMapping("/puntosAtencion/{id}/edit")
  // public String puntosAtencionEditForm(@PathVariable("id") int id, Model model) {
  //   PuntoAtencion puntoAtencion = puntoAtencionRepository.darPuntoAtencion(id);
  //   if (puntoAtencion != null) {
  //     model.addAttribute("puntoAtencion", puntoAtencion);
  //     return "puntosAtencionEdit";
  //   } else {
  //     return "redirect:/puntosAtencion";
  //   }
  // }

  // @PostMapping("/puntosAtencion/{id}/edit/save")
  // public String puntosAtencionEditSave(@PathVariable("id") long id,
  //     @ModelAttribute PuntoAtencion puntoAtencion) {
  //   puntoAtencionRepository.actualizarPuntoAtencion(id, puntoAtencion.getTipo(),
  //       puntoAtencion.getCiudad(), puntoAtencion.getHorario_atencion(), puntoAtencion.getDireccion(),
  //       puntoAtencion.getOficina());
  //   return "redirect:/puntosAtencion";
  // }

  @GetMapping("/puntosAtencion/{id}/delete")
  public String puntosAtencionBorrar(@PathVariable("id") long id) {
      puntoAtencionRepository.eliminaPuntoAtencion(id);
      return "redirect:/puntosAtencion";
    
  }
}
