/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILMER
 */
public class Empleado extends Persona {
    private String codigo;
    private int id_puesto;
    private Conexion cn;

    public Empleado() {
    }

    public Empleado(String codigo, int id_puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id_puesto = id_puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    /*public HashMap drop_sangre(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn= new Conexion ();
        String query="select id_puesto, puesto from puestos;";
                cn.abrir_conexion();
                ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
                while(consulta.next()){
                    drop.put(consulta.getString("id_puesto"), consulta.getString("puesto"));
                }
                
                cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }*/
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT e.id_empleados, e.codigo, e.nombres, e.apellidos, e.direccion, e.telefono, e.fecha_nacimiento, a.puesto, e.id_puesto FROM db_empresa.empleados e inner join puestos a on a.id_puesto = e.id_puesto;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado [] = {"id_empleados", "codigo", "nombres", "apellidos", "direccion", "telefono", "fecha_nacimiento", "puesto" , "id_puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()){
                datos[0] = consulta.getString("id_empleados");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("puesto");
                datos[8] = consulta.getString("id_puesto");
                tabla.addRow(datos);
                
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
                System.out.println(ex.getMessage());
            
        }
        
        return tabla;
    }
    
    @Override
   public int agregar(){
       int retorno = 0;
   try{
    PreparedStatement parametro;
    cn = new Conexion();
    String query = "INSERT INTO empleados (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?,?,?,?,?,?,?);";
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
    parametro.setString(1, getCodigo());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getFecha_nacimiento());
    parametro.setInt(7, getId_puesto());
    retorno = parametro.executeUpdate();
    cn.cerrar_conexion();
    
    }catch(SQLException ex){
    System.out.println(ex.getMessage());
    retorno = 0;
    }
   return retorno;
   }
    @Override
    public int modificar(){
       int retorno = 0;
   try{
    PreparedStatement parametro;
    cn = new Conexion();
    String query = "update empleados set codigo=?, nombres=?, apellidos=?, direccion=?, telefono=?, fecha_nacimiento=?, id_puesto=? where id_empleados = ?;";
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
    parametro.setString(1, getCodigo());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getFecha_nacimiento());
    parametro.setInt(7, getId_puesto());
    parametro.setInt(8, getId_empleados());
    retorno = parametro.executeUpdate();
    cn.cerrar_conexion();
    
    }catch(SQLException ex){
    System.out.println(ex.getMessage());
    retorno = 0;
    }
   return retorno;
   }
    
    @Override
    public int eliminar(){
       int retorno = 0;
   try{
    PreparedStatement parametro;
    cn = new Conexion();
    String query = "delete from empleados where id_empleados = ?;";
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
    parametro.setInt(1, getId_empleados());
    retorno = parametro.executeUpdate();
    cn.cerrar_conexion();
    
    }catch(SQLException ex){
    System.out.println(ex.getMessage());
    retorno = 0;
    }
   return retorno;
   }
    
    
}
