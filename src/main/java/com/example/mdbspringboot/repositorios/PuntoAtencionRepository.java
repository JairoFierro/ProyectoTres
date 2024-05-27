package com.example.mdbspringboot.repositorios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.PuntoAtencion;



public interface PuntoAtencionRepository extends MongoRepository<PuntoAtencion, Integer>{
    @Query(value="{_id:?0}", delete=true)
    void eliminaPuntoAtencion(long id);

    @Query("{'_id': ?0}")
    PuntoAtencion encontrarPuntoAtencionPorId(int id);


}
