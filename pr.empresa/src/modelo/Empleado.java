/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



/**
 *
 * @author WILMER
 */
public class Empleado extends Persona{
    private int id;
    private String cod;
    private String puesto;
    Conexion cn; 
    
    public Empleado() {}

    public Empleado(int id, String cod, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.cod = cod;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public void crearp (){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO empleados(codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?, ?, ?, ?, ?, ?, ?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getCod());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getPuesto());
            int executar = parametro.executeUpdate();
            /*JOptionPane.showMessageDialog(null, "Ingreso exitoso.." + Integer.toString(executar));*/
            cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, "Error en el ingreso.. " + ex.getMessage());
        }
    }
    
    public DefaultTableModel leerp (){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select b.id_empleado, b.codigo, b.nombres, b.apellidos, b.direccion,b.telefono,b.fecha_nacimiento,a.puesto from empleados b inner join puestos a on a.id_puesto = b.id_puesto";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"id_empleado","codigo","nombres","apellidos","direccion","telefono","fecha_nacimiento","puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while(consulta.next()){
                datos[0]= consulta.getString("id_empleado");
                datos[1]= consulta.getString("codigo");
                datos[2]= consulta.getString("nombres");
                datos[3]= consulta.getString("apellidos");
                datos[4]= consulta.getString("direccion");
                datos[5]= consulta.getString("telefono");
                datos[6]= consulta.getString("fecha_nacimiento");
                datos[7]= consulta.getString("puesto");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            /*JOptionPane.showMessageDialog(null, "Error al leer datos.." + ex.getMessage());*/
        }
        return tabla;
    }
    
    public void actualizarp(){
        try{
          PreparedStatement parametro;
          cn = new Conexion();
          cn.abrir_conexion();
          String query = "UPDATE empleados SET  codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ? "
                  + "WHERE id_empleado = ?;";
          parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
          parametro.setString(1, this.getCod());
          parametro.setString(2, this.getNombres());
          parametro.setString(3, this.getApellidos());
          parametro.setString(4, this.getDireccion());
          parametro.setString(5, this.getTelefono());
          parametro.setString(6, this.getFecha_nacimiento());
          parametro.setString(7, this.getPuesto());
          parametro.setInt(8, this.getId());
          int executar = parametro.executeUpdate();
          /*JOptionPane.showMessageDialog(null, "Actualizacion Exitosa..." + Integer.toString(executar));*/
          cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, "Error en la actualizar datos.." + ex.getMessage());
        }
    }
    
    public void elimiarp(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "delete from empleados where id_empleado = ?";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, this.getId());
            int executar = parametro.executeUpdate();
            /*JOptionPane.showMessageDialog(null, "Registro Eliminado.." + Integer.toString(executar));*/
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al elimiar registro.." + ex.getMessage());
        }
    }

 }