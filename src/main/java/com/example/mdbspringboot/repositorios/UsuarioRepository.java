package com.example.mdbspringboot.repositorios;


import java.sql.Timestamp;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.mdbspringboot.modelo.Usuario;



public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {

      public interface RespuestaExtracto {

        double getSALDO();
        String getTIPO_OPERACION();
        Timestamp getFECHA();
      }

      public interface CuentasCliente {

        String getNUMERO_CUENTA();
        String getESTADO();
        double getSALDO();
        String getTIPO();
        Integer getCLIENTE();
        Timestamp getULTIMA_TRANSACCION();
        Integer getGERENTE_OFICINA_CREADOR();
        Timestamp getFECHA_CREACION();
      }

      public interface PrestamosCliente {

        Integer getID();
        String getESTADO();
        String getTIPO();
        double getMONTO();
        double getINTERES();
        Integer getNUMERO_CUOTAS();
        String getDIA_MES_PAGAR_CUOTA();
        double getVALOR_CUOTA();
        Integer getCLIENTE();
        Integer getGERENTE_CREADOR();
        double getSALDO_PENDIENTE();
      }

      public interface Oficinas {

        String getNOMBRE();
        String getDIRECCION();
        Integer getNUMERO_PUNTOS_ATENCION();
        String getGERENTE();
        String getCIUDAD();
      }


  // @Query(value = "SELECT * FROM clientes", nativeQuery = true)
  // Collection<Usuario> darClientes();

  // @Query(value = "SELECT * FROM clientes WHERE id=:id", nativeQuery = true)
  // Usuario darCliente(@Param("id") long  id);

  // @Modifying
  // @Transactional
  // @Query(value = "INSERT INTO clientes (id, numero_documento, tipo,tipo_documento, nombre,nacionalidad, direccion_fisica, direccion_electronica,telefono,codigo_postal,ciudad,departamento) VALUES(proyecto_sequence.nextval, :numero_documento, :tipo, :tipo_documento, :nombre, :nacionalidad, :direccion_fisica, :direccion_electronica, :telefono, :codigo_postal, :ciudad, :departamento) ", nativeQuery = true)
  // void insertarCliente(@Param("numero_documento") String numero_documento,
  //     @Param("tipo") String tipo,
  //     @Param("tipo_documento") String tipo_documento,
  //     @Param("nombre") String nombre,
  //     @Param("nacionalidad") String nacionalidad,
  //     @Param("direccion_fisica") String direccion_fisica,
  //     @Param("direccion_electronica") String direccion_electronica,
  //     @Param("telefono") String telefono,
  //     @Param("codigo_postal") String codigo_postal,
  //     @Param("ciudad") String ciudad,
  //     @Param("departamento") String departamento);

  // @Modifying
  // @Transactional
  // @Query(value = "UPDATE clientes SET numero_documento=:numero_documento, tipo = :tipo, tipo_documento = :tipo_documento,nombre = :nombre, nacionalidad = :nacionalidad, direccion_fisica = :direccion_fisica, direccion_electronica = :direccion_electronica , telefono = :telefono, codigo_postal = :codigo_postal, ciudad = :ciudad, departamento= :departamento  WHERE id = :id", nativeQuery = true)
  // void actualizarCliente(@Param("id") long  id,
  //     @Param("numero_documento") String numero_documento,
  //     @Param("tipo") String tipo,
  //     @Param("tipo_documento") String tipo_documento,
  //     @Param("nombre") String nombre,
  //     @Param("nacionalidad") String nacionalidad,
  //     @Param("direccion_fisica") String direccion_fisica,
  //     @Param("direccion_electronica") String direccion_electronica,
  //     @Param("telefono") String telefono,
  //     @Param("codigo_postal") String codigo_postal ,
  //     @Param("ciudad") String ciudad,
  //     @Param("departamento") String departamento);

  // @Modifying
  // @Transactional
  // @Query(value = "DELETE FROM clientes WHERE id= :id", nativeQuery = true)
  // void eliminarCliente(@Param("id") long  id);



  // @Query(value = "SELECT CUENTAS.saldo as SALDO, operaciones_cuentas.tipo_operacion AS TIPO_OPERACION,operaciones_cuentas.fecha AS FECHA \r\n" + //
  //         "FROM CUENTAS \r\n" + //
  //         "INNER JOIN OPERACIONES_CUENTAS ON operaciones_cuentas.cuenta_salida = cuentas.numero_cuenta \r\n" + //
  //         "WHERE EXTRACT(MONTH FROM operaciones_cuentas.fecha) = :numMes AND EXTRACT(YEAR FROM operaciones_cuentas.fecha) = :anio AND cuentas.numero_cuenta = :numCuenta\r\n" + //
  //         "ORDER BY operaciones_cuentas.fecha ASC", nativeQuery = true)
  // Collection<RespuestaExtracto>  infoExtracto(@Param("numMes") Integer  numMes,@Param("numCuenta") Integer  numCuenta, @Param("anio") Integer  anio);

  // @Query(value = "SELECT id FROM clientes WHERE numero_documento=:numero_documento", nativeQuery = true)
  // Integer obtenerIdDadoDoc(@Param("numero_documento") String numero_documento);

  // Del cliente toda la informacion
  // De cuentas todo
  // De oficinas donde tiene cuentas nombre, direccion y ciudad
  // De prestamos 

  // @Query(value = "SELECT * FROM CUENTAS WHERE cliente= :cliente", nativeQuery = true)
  // Collection<CuentasCliente>  obtenerCuentasCliente(@Param("cliente") Integer  cliente);

  // @Query(value = "SELECT * FROM PRESTAMOS WHERE cliente= :cliente", nativeQuery = true)
  // Collection<PrestamosCliente>  obtenerPrestamosCliente(@Param("cliente") Integer  cliente);

  //   @Query(value = "SELECT oficinas.nombre, oficinas.direccion,oficinas.numero_puntos_atencion ,oficinas.gerente, oficinas.ciudad\r\n" + //
  //         "FROM CUENTAS \r\n" + //
  //         "INNER JOIN empleados on empleados.id = cuentas.gerente_oficina_creador\r\n" + //
  //         "inner join oficinas on oficinas.id = empleados.id_oficina\r\n" + //
  //         "where cuentas.cliente= :cliente", nativeQuery = true)
  // Collection<Oficinas>  obtenerOficinasDelCliente(@Param("cliente") Integer  cliente);


}
