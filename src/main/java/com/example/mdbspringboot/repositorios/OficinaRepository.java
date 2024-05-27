package com.example.mdbspringboot.repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.mdbspringboot.modelo.Oficina;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

import com.example.mdbspringboot.modelo.Usuario;



public interface OficinaRepository extends MongoRepository<Oficina, Integer> {


  @Query("{}")
  List<Oficina> buscarTodas();


}
