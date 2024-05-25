package com.example.mdbspringboot.modelo;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class OperacionCuenta {
    @Id
    private Integer id;
    
    private String tipo_operacion;
    private Date fecha;
    private Integer cuenta_salida;
    private Float monto_operacion;
    private Integer cliente;

    private PuntoAtencion punto_atencion;

    private Integer cuenta_llegada;
    
    
    public OperacionCuenta() {
        ;
    }


    public OperacionCuenta(String tipo_operacion, Date fecha, Integer cuenta_salida, Float monto_operacion,
            Integer cliente, PuntoAtencion punto_atencion, Integer cuenta_llegada) {
        this.tipo_operacion = tipo_operacion;
        this.fecha = fecha;
        this.cuenta_salida = cuenta_salida;
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


    public Integer getCuenta_salida() {
        return cuenta_salida;
    }


    public void setCuenta_salida(Integer cuenta_salida) {
        this.cuenta_salida = cuenta_salida;
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


    public PuntoAtencion getPunto_atencion() {
        return punto_atencion;
    }


    public void setPunto_atencion(PuntoAtencion punto_atencion) {
        this.punto_atencion = punto_atencion;
    }


    public Integer getCuenta_llegada() {
        return cuenta_llegada;
    }


    public void setCuenta_llegada(Integer cuenta_llegada) {
        this.cuenta_llegada = cuenta_llegada;
    }
    
}
