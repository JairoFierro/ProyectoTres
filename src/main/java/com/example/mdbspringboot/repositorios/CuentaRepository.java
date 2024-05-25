package com.example.mdbspringboot.repositorios;
import com.example.mdbspringboot.modelo.Cuenta;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;


public interface CuentaRepository extends MongoRepository<Cuenta, Integer> {
    

    // @Query(value = "SELECT * FROM cuentas", nativeQuery = true)
    // Collection<Cuenta> darCuentas();

    // @Query(value = "SELECT * FROM cuentas WHERE id=:id", nativeQuery = true)
    // Cuenta darCuenta(@Param("id") long  id);
    
    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO cuentas (id, numero_cuenta, estado,saldo, tipo,cliente, ultima_transaccion, gerente_oficina_creador,fecha_creacion) VALUES(proyecto_sequence.nextval, :numero_cuenta, :estado, :saldo, :tipo, :cliente, :ultima_transaccion, :gerente_oficina_creador, :fecha_creacion) ", nativeQuery = true)
    // void insertarCuenta(@Param("numero_cuenta") String numero_cuenta,
    //     @Param("estado") String estado,
    //     @Param("saldo") Float saldo,
    //     @Param("tipo") String tipo,
    //     @Param("cliente") Integer cliente,
    //     @Param("ultima_transaccion") Date ultima_transaccion,
    //     @Param("gerente_oficina_creador") Integer gerente_oficina_creador,
    //     @Param("fecha_creacion") Date fecha_creacion);

    // @Modifying
    // @Transactional
    // @Query(value = "UPDATE cuentas SET numero_cuenta=:numero_cuenta, estado =:estado, saldo = :saldo, tipo = :tipo, cliente = :cliente, ultima_transaccion = :ultima_transaccion, gerente_oficina_creador = :gerente_oficina_creador ,fecha_creacion= :fecha_creacion WHERE id = :id", nativeQuery = true)
    // void actualizarCuenta(@Param("id") long  id,
    //     @Param("numero_cuenta") String numero_cuenta,
    //     @Param("estado") String estado,
    //     @Param("saldo") Float saldo,
    //     @Param("tipo") String tipo,
    //     @Param("cliente") Integer cliente,
    //     @Param("ultima_transaccion") Date ultima_transaccion,
    //     @Param("gerente_oficina_creador") Integer gerente_oficina_creador,
    //     @Param("fecha_creacion") Date fecha_creacion);

    // @Modifying
    // @Transactional
    // @Query(value = "DELETE FROM cuentas WHERE id=:id", nativeQuery = true)
    // void eliminarCuenta(@Param("id") long id);
}
