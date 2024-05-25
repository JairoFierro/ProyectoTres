package com.example.mdbspringboot.modelo;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="usuarios")
@ToString
public class Usuario {

  @Id
  private Integer id;

  private String login;
  private String password;
  private String numero_documento;
  private String tipo_persona;
  private String tipo_documento;
  private String nombre;
  private String nacionalidad;
  private String direccion_fisica;
  private String direccion_electronica;
  private String telefono;
  private String codigo_postal;
  private String ciudad;
  private String departamento;
  private String tipo_usuario;
  private List<Cuenta> cuentas;

  public Usuario() {
    ;
  }

  

  public Usuario(Integer id, String login, String password, String numero_documento, String tipo_persona,
      String tipo_documento, String nombre, String nacionalidad, String direccion_fisica, String direccion_electronica,
      String telefono, String codigo_postal, String ciudad, String departamento, String tipo_usuario) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.numero_documento = numero_documento;
    this.tipo_persona = tipo_persona;
    this.tipo_documento = tipo_documento;
    this.nombre = nombre;
    this.nacionalidad = nacionalidad;
    this.direccion_fisica = direccion_fisica;
    this.direccion_electronica = direccion_electronica;
    this.telefono = telefono;
    this.codigo_postal = codigo_postal;
    this.ciudad = ciudad;
    this.departamento = departamento;
    this.tipo_usuario = tipo_usuario;
  }



  public Integer getId() {
    return id;
  }



  public void setId(Integer id) {
    this.id = id;
  }



  public String getLogin() {
    return login;
  }



  public void setLogin(String login) {
    this.login = login;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public String getNumero_documento() {
    return numero_documento;
  }



  public void setNumero_documento(String numero_documento) {
    this.numero_documento = numero_documento;
  }



  public String getTipo_persona() {
    return tipo_persona;
  }



  public void setTipo_persona(String tipo_persona) {
    this.tipo_persona = tipo_persona;
  }



  public String getTipo_documento() {
    return tipo_documento;
  }



  public void setTipo_documento(String tipo_documento) {
    this.tipo_documento = tipo_documento;
  }



  public String getNombre() {
    return nombre;
  }



  public void setNombre(String nombre) {
    this.nombre = nombre;
  }



  public String getNacionalidad() {
    return nacionalidad;
  }



  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }



  public String getDireccion_fisica() {
    return direccion_fisica;
  }



  public void setDireccion_fisica(String direccion_fisica) {
    this.direccion_fisica = direccion_fisica;
  }



  public String getDireccion_electronica() {
    return direccion_electronica;
  }



  public void setDireccion_electronica(String direccion_electronica) {
    this.direccion_electronica = direccion_electronica;
  }



  public String getTelefono() {
    return telefono;
  }



  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }



  public String getCodigo_postal() {
    return codigo_postal;
  }



  public void setCodigo_postal(String codigo_postal) {
    this.codigo_postal = codigo_postal;
  }



  public String getCiudad() {
    return ciudad;
  }



  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }



  public String getDepartamento() {
    return departamento;
  }



  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }



  public String getTipo_usuario() {
    return tipo_usuario;
  }



  public void setTipo_usuario(String tipo_usuario) {
    this.tipo_usuario = tipo_usuario;
  }



  public List<Cuenta> getCuentas() {
    return cuentas;
  }



  public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

  

}
