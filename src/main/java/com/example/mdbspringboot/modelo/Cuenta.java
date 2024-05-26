package com.example.mdbspringboot.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.ToString;


@ToString
public class Cuenta {

    @Id
    private int id;

    private String numero_cuenta;
    private String estado;
    private Float saldo;
    private String tipo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultima_transaccion;
    private int gerente_oficina;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_creacion;
    private List<OperacionCuenta> operaciones_cuenta;

    public Cuenta() {
        ;
    }

    public Cuenta(int id, String numero_cuenta, String estado, Float saldo, String tipo, Date ultima_transaccion,
                 int gerente_oficina, Date fecha_creacion,List<OperacionCuenta> operaciones_cuenta) {
        this.id = id;
        this.numero_cuenta = numero_cuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.tipo = tipo;
        this.ultima_transaccion = ultima_transaccion;
        this.gerente_oficina = gerente_oficina;
        this.fecha_creacion = fecha_creacion;
        this.operaciones_cuenta = operaciones_cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getGerente_oficina() {
        return gerente_oficina;
    }

    public void setGerente_oficina(int gerente_oficina) {
        this.gerente_oficina = gerente_oficina;
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