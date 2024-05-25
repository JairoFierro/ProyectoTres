package com.example.mdbspringboot.repositorios;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.modelo.PuntoAtencion;

// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import jakarta.transaction.Transactional;

public interface PuntoAtencionRepository extends MongoRepository<PuntoAtencion, Integer>{

    // @Query(value = "SELECT * FROM puntos_atencion", nativeQuery = true)
    // Collection<PuntoAtencion> darPuntosAtencion();

    // @Query(value = "SELECT * FROM puntos_atencion WHERE id = :id", nativeQuery = true)
    // PuntoAtencion darPuntoAtencion(@Param("id") long id);

    // @Modifying
    // @Transactional
    // @Query(value = "DELETE FROM puntos_atencion WHERE id = :id", nativeQuery = true)
    // void eliminaPuntoAtencion(@Param("id") long id);

    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO puntos_atencion (id, tipo, ciudad, horario_atencion, direccion,oficina)  \r\n" + //
    //                 "VALUES(proyecto_sequence.nextval, :tipo, :ciudad, :horario_atencion, :direccion, :oficina) ", nativeQuery = true)
    // void  insertarPuntoAtencion(@Param("tipo") String tipo,
    //     @Param("ciudad") String ciudad,
    //     @Param("horario_atencion") String horario_atencion,
    //     @Param("direccion") String direccion,
    //     @Param("oficina") Integer oficina);

    // @Modifying
    // @Transactional
    // @Query(value = "UPDATE puntos_atencion SET tipo= :tipo, ciudad= :ciudad, horario_atencion= :horario_atencion, direccion = :direccion, oficina = :oficina WHERE id = :id", nativeQuery = true)
    // void  actualizarPuntoAtencion(@Param("id") long id,
    //     @Param("tipo") String tipo,
    //     @Param("ciudad") String ciudad,
    //     @Param("horario_atencion") String horario_atencion,
    //     @Param("direccion") String direccion,
    //     @Param("oficina") Integer oficina);

    // @Query(value = "SELECT count(operaciones_cuentas.id)\r\n" + //
    //             "FROM operaciones_cuentas \r\n" + //
    //             "where punto_atencion = :id" , nativeQuery = true)
    // Integer verificarOperacionesCuentas(@Param("id") long id);

    // @Query(value = "SELECT count(operaciones_prestamos.id)\r\n" + //
    // "FROM operaciones_prestamos \r\n" + //
    // "where punto_atencion = :id" , nativeQuery = true)
    // Integer verificarOperacionesPrestamos(@Param("id") long id);
}
