package com.example.mdbspringboot.repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.mdbspringboot.modelo.Oficina;



public interface OficinaRepository extends MongoRepository<Oficina, Integer> {
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
