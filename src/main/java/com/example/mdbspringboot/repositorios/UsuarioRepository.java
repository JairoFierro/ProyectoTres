package com.example.mdbspringboot.repositorios;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;


import com.example.mdbspringboot.modelo.OperacionCuenta;
import com.example.mdbspringboot.modelo.Usuario;



public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {

      public class respuestaTipoCuentaComun {
        public String tipo;
        public int conteo;
        public respuestaTipoCuentaComun(String tipo, int conteo) {
          this.tipo = tipo;
          this.conteo = conteo;
        }
        public String getTipo() {
          return tipo;
        }
        public void setTipo(String tipo) {
          this.tipo = tipo;
        }
        public int getConteo() {
          return conteo;
        }
        public void setConteo(int conteo) {
          this.conteo = conteo;
        }
      }

      public class respuestaEstadoComun{
        public String estado;
        public int conteo;
        public respuestaEstadoComun(String estado, int conteo) {
          this.estado = estado;
          this.conteo = conteo;
        }
        public String getEstado() {
          return estado;
        }
        public void setEstado(String estado) {
          this.estado = estado;
        }
        public int getConteo() {
          return conteo;
        }
        public void setConteo(int conteo) {
          this.conteo = conteo;
        }

        
      }

      @Aggregation(pipeline={"{$match:{numero_documento:?0} }","{ $unwind:'$cuentas'} " ,"{$group:{_id:'$cuentas.tipo', conteo:{$sum:1 } } }","{$project:{tipo:'$_id', conteo:1, _id:0} }","{$sort: {tipo: 1}}"})
      List<respuestaTipoCuentaComun> agruparPorTipoCuenta( String numero_documento);

      @Aggregation(pipeline={"{$match:{numero_documento:?0} }","{ $unwind:'$cuentas'} " ,"{$group:{_id:'$cuentas.estado', conteo:{$sum:1 } } }","{$project:{estado:'$_id', conteo:1, _id:0} }","{$sort: {estado: 1}}"})
      List<respuestaEstadoComun> agruparEstadoCuenta( String numero_documento);

      @Query("{_id: ?0}")
      @Update("{$push:{cuentas:{_id:?1, numero_cuenta:?2, estado:?3, saldo:?4, tipo:?5, ultima_transaccion:?6, gerente_oficina:?7, fecha_creacion:?8, operaciones_cuenta:?9 }}}")
      void aniadirCuentaAUsuario(int cliente, int id, String numero_cuenta, String estado, Float saldo, String tipo, Date ultima_transaccion, int gerente_oficina, Date fecha_creacion, List<OperacionCuenta> operaciones_cuenta);

      @Query("{'_id': ?0, 'cuentas._id': ?1}")
      Usuario darCuenta(int id_usuario, int id_cuenta);

      @Query("{'_id': ?0}")
      Usuario darUsuario(int id_usuario);

      @Query("{}")
      List<Usuario> darUsuarios();

      @Query("{_id: ?0, 'cuentas._id': ?1}")
      @Update("{$set:{cuentas.$[0].estado: ?3} }")
      void cambiarEstadoCuenta(int id_usuario, int id_cuenta, String estado);

      @Query("{}")
      @Update("{$pull:{cuentas ?0} }")
      void eliminarCuenta(int id_cuenta);

      @Query("{'numero_documento': ?0}")
      Usuario encontrarUsuarioPorDocumento(String numero_documento);


      


}
