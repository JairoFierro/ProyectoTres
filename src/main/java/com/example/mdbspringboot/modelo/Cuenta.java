package com.example.mdbspringboot.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.ToString;


@ToString
public class Cuenta {

    @Id
    private Integer id;

    private String numero_cuenta;
    private String estado;
    private Float saldo;
    private String tipo;
    private Date ultima_transaccion;
    private Integer gerente_oficina_creador;
    private Date fecha_creacion;
    private List<OperacionCuenta> operaciones_cuenta;

    public Cuenta() {
        ;
    }

    public Cuenta(Integer id, String numero_cuenta, String estado, Float saldo, String tipo, Date ultima_transaccion,
            Integer gerente_oficina_creador, Date fecha_creacion, List<OperacionCuenta> operaciones_cuenta) {
        this.id = id;
        this.numero_cuenta = numero_cuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.tipo = tipo;
        this.ultima_transaccion = ultima_transaccion;
        this.gerente_oficina_creador = gerente_oficina_creador;
        this.fecha_creacion = fecha_creacion;
        this.operaciones_cuenta = operaciones_cuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getUltima_transaccion() {
        return ultima_transaccion;
    }

    public void setUltima_transaccion(Date ultima_transaccion) {
        this.ultima_transaccion = ultima_transaccion;
    }

    public Integer getGerente_oficina_creador() {
        return gerente_oficina_creador;
    }

    public void setGerente_oficina_creador(Integer gerente_oficina_creador) {
        this.gerente_oficina_creador = gerente_oficina_creador;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public List<OperacionCuenta> getOperaciones_cuenta() {
        return operaciones_cuenta;
    }

    public void setOperaciones_cuenta(List<OperacionCuenta> operaciones_cuenta) {
        this.operaciones_cuenta = operaciones_cuenta;
    }

    

}