package com.example.mdbspringboot.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="oficinas")
@ToString
public class Oficina {

  @Id
  private Integer id;

  private String nombre;

  private String direccion;

  private Integer numero_puntos_atencion;

  private Integer gerente;

  private String ciudad;

  private List<Integer> puntos_atencion;



  public Oficina() {
    ;
  }

  public Oficina(String nombre, String direccion, Integer numero_puntos_atencion, String ciudad, Integer gerente) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.numero_puntos_atencion = numero_puntos_atencion;
    this.ciudad = ciudad;
    this.gerente = gerente;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Integer getNumero_puntos_atencion() {
    return numero_puntos_atencion;
  }

  public void setNumero_puntos_atencion(Integer numero_puntos_atencion) {
    this.numero_puntos_atencion = numero_puntos_atencion;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public Integer getGerente() {
    return gerente;
  }

  public void setGerente(Integer gerente) {
    this.gerente = gerente;
  }

}
