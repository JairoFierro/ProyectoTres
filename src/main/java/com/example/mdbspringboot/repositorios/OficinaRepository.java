package com.example.mdbspringboot.repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.mdbspringboot.modelo.Oficina;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.example.mdbspringboot.modelo.Usuario;



public interface OficinaRepository extends MongoRepository<Oficina, Integer> {

  //@Query("{_id: ?0}")
  //@Update("{$push:{oficinas:{_id:?1, tipo:?2, ciudad:?3, horario_atencion:?4, direccion:?5 }}}")
  //void aniadirPuntoAtencionOficina(int oficina, int id, String tipo, String ciudad, String horario_atencion, String direccion);



  //   @Query(value = "SELECT * FROM oficinas", nativeQuery = true)
//   Collection<Oficina> darOficinas();

//   @Query(value = "SELECT * FROM oficinas WHERE id = :id", nativeQuery = true)
//   Oficina darOficina(@Param("id") long id);

//   @Modifying
//   @Transactional
//   @Query(value = "DELETE FROM oficinas WHERE id = :id", nativeQuery = true)
//   void eliminarOficina(@Param("id") long id);

//   @Modifying
//   @Transactional
//   @Query(value = "INSERT INTO oficinas (id, nombre, direccion, numero_puntos_atencion,ciudad,gerente) VALUES(proyecto_sequence.nextval,:nombre, :direccion,:numero_puntos_atencion,:ciudad,:gerente) ", nativeQuery = true)
//   void insertarOficina(@Param("nombre") String nombre,
//       @Param("direccion") String direccion,
//       @Param("numero_puntos_atencion") Integer numero_puntos_atencion,
//       @Param("gerente") Integer gerente,
//       @Param("ciudad") String ciudad);

//   @Modifying
//   @Transactional
//   @Query(value = "UPDATE oficinas SET nombre= :nombre , direccion= :direccion, numero_puntos_atencion =:numero_puntos_atencion,gerente=:gerente,ciudad =:ciudad WHERE id = :id", nativeQuery = true)
//   void actualizarOficina(@Param("id") long id,
//       @Param("nombre") String nombre,
//       @Param("direccion") String direccion,
//       @Param("numero_puntos_atencion") Integer numero_puntos_atencion,
//       @Param("gerente") Integer gerente,
//       @Param("ciudad") String ciudad);
}
