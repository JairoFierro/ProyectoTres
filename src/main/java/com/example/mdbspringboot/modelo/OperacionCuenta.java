package com.example.mdbspringboot.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.ToString;

@ToString
public class OperacionCuenta {
    @Id
    private int id;
    
    private String tipo_operacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Float monto_operacion;
    private int cliente;

    private String cuenta_llegada;

    private int punto_atencion;

    
    
    public OperacionCuenta() {
        ;
    }


    public OperacionCuenta(String tipo_operacion, Date fecha, Float monto_operacion,
            Integer cliente, Integer punto_atencion, String cuenta_llegada) {
        this.tipo_operacion = tipo_operacion;
        this.fecha = fecha;
        this.monto_operacion = monto_operacion;
        this.cliente = cliente;
        this.punto_atencion = punto_atencion;
        this.cuenta_llegada = cuenta_llegada;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTipo_operacion() {
        return tipo_operacion;
    }


    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Float getMonto_operacion() {
        return monto_operacion;
    }


    public void setMonto_operacion(Float monto_operacion) {
        this.monto_operacion = monto_operacion;
    }


    public Integer getCliente() {
        return cliente;
    }


    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }


    public Integer getPunto_atencion() {
        return punto_atencion;
    }


    public void setPunto_atencion(Integer punto_atencion) {
        this.punto_atencion = punto_atencion;
    }


    public String getCuenta_llegada() {
        return cuenta_llegada;
    }


    public void setCuenta_llegada(String cuenta_llegada) {
        this.cuenta_llegada = cuenta_llegada;
    }
    
}
