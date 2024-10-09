/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILMER
 */
public class persona extends docente{
    protected String nit, nombres, apellidos, direccion, telefono, fecha_nacimiento;
    protected int id_persona, id_docente;
    Conexion cn;
    
    public persona(){}

    public persona(String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_docente, String fecha_ingreso, double salario, Timestamp fecha_registro) {
        super(codigo_docente, fecha_ingreso, salario, fecha_registro);
        this.nit = nit;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public persona(String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, int id_persona, int id_docente, String codigo_docente, String fecha_ingreso, double salario, Timestamp fecha_registro) {
        super(codigo_docente, fecha_ingreso, salario, fecha_registro);
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_persona = id_persona;
        this.id_docente = id_docente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }
    
    public void crearp() throws ParseException{
        try{
            PreparedStatement parametropersona;
            PreparedStatement parametrodocente;
            cn = new Conexion();
            cn.abrir_conexion();
            cn.conexionBD.setAutoCommit(false);
            String querypersona = "INSERT INTO persona (nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente) VALUES (?, ?, ?, ?, ?,?, ?)";
            String querydocente = "INSERT INTO docente (codigo_docente, salario, fecha_ingreso, fecha_registro) VALUES (?, ?, ?, ?)";
            
            parametropersona = (PreparedStatement) cn.conexionBD.prepareStatement(querypersona);
            parametropersona.setString(1, this.getNit());
            parametropersona.setString(2, this.getNombres());
            parametropersona.setString(3, this.getApellidos());
            parametropersona.setString(4, this.getDireccion());
            parametropersona.setString(5, this.getTelefono());
            parametropersona.setString(6, this.getFecha_nacimiento());
            parametropersona.setString(7, this.getCodigo_docente());
            int executar = parametropersona.executeUpdate();
            
            parametrodocente = (PreparedStatement) cn.conexionBD.prepareStatement(querydocente);
            parametrodocente.setString(1, this.getCodigo_docente());
            parametrodocente.setDouble(2, this.getSalario());
            parametrodocente.setString(3, this.getFecha_ingreso());
            java.sql.Timestamp Fecha_registro = new java.sql.Timestamp(System.currentTimeMillis());
            parametrodocente.setTimestamp(4, Fecha_registro);
            int executardocente = parametrodocente.executeUpdate();
            
            cn.conexionBD.commit();
            JOptionPane.showMessageDialog(null, "Ingreso Exitoso de la Persona");
        }catch(SQLException ex){
            try{
                if(cn.conexionBD != null){
                    cn.conexionBD.rollback();
                }
            }catch(SQLException rollbackEx){
            }
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: " + ex.getMessage());
        }finally{
            try{
                if(cn.conexionBD != null){
                    cn.conexionBD.setAutoCommit(true);
                }
                cn.cerrar_conexion();
            }catch(SQLException Ex){
            }
        }
    }
    
    public DefaultTableModel leerp(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select  b.nit, a.id_docente, b.nombres, b.apellidos, b.direccion, b.telefono, b.fecha_nacimiento, a.codigo_docente, a.salario, a.fecha_ingreso, a.fecha_registro, b.id_persona, a.id_docente from persona b inner join docente a on a.codigo_docente = b.codigo_docente;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"codigo_docente","nit","nombres", "apellidos","direccion","telefono","fecha_nacimiento","salario","fecha_ingreso","fecha_registro","id_persona","id_docente"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[12];
            while(consulta.next()){
                datos[0]= consulta.getString("Codigo_docente");
                datos[1]= consulta.getString("Nit");
                datos[2]= consulta.getString("Nombres");
                datos[3]= consulta.getString("Apellidos");
                datos[4]= consulta.getString("Direccion");
                datos[5]= consulta.getString("Telefono");
                datos[6]= consulta.getString("Fecha_nacimiento");
                datos[7]= consulta.getString("Salario");
                datos[8]= consulta.getString("Fecha_ingreso");
                datos[9]= consulta.getString("Fecha_registro");
                datos[10]= consulta.getString("id_persona");
                datos[11]= consulta.getString("id_docente");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,"Error al leer los datos"  + ex.getMessage());
        }
        return tabla;
    }
    
    public void actualizarp(){
    try{
        PreparedStatement parametropersona;
        PreparedStatement parametrodocente;
        cn = new Conexion();
        cn.abrir_conexion();
        cn.conexionBD.setAutoCommit(false);
        String querypersona = "update persona set nombres = ?, apellidos = ?, direccion = ?, nit = ?, telefono = ?, fecha_nacimiento = ?, codigo_docente = ? " + " where id_persona = ?; ";
        String querydocente = "update docente set codigo_docente = ?, salario = ?, fecha_ingreso = ?, fecha_registro = ? " + " where id_docente = ?; ";
        parametropersona = (PreparedStatement) cn.conexionBD.prepareStatement(querypersona);
        parametropersona.setString(1, getNombres());
        parametropersona.setString(2, getApellidos());
        parametropersona.setString(3, getDireccion());
        parametropersona.setString(4, getNit());
        parametropersona.setString(5, getTelefono());
        parametropersona.setString(6, getFecha_nacimiento());
        parametropersona.setString(7, getCodigo_docente());
        parametropersona.setInt(8, getId_persona());
        int executar = parametropersona.executeUpdate();
        
        parametrodocente = (PreparedStatement) cn.conexionBD.prepareStatement(querydocente);
        parametrodocente.setString(1, getCodigo_docente());
        parametrodocente.setDouble(2, getSalario());
        parametrodocente.setString(3, getFecha_ingreso());
        java.sql.Timestamp Fecha_registro = new java.sql.Timestamp(System.currentTimeMillis());
        parametroDocente.setTimestamp(4, Fecha_registro);
        parametroDocente.setInt(5, getId_docente());
        int executardocente = parametrodocente.executeUpdate();
        cn.conexionBD.commit();
        
    }catch(SQLException ex){
        try{
            if (cn.conexionBD != null) {
            cn.conexionBD.rollback();
            }            
        }catch(SQLException rollbackEx){
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + ex.getMessage());
        }
    }finally{
        try{
            if (cn.conexionBD != null) {
                cn.conexionBD.setAutoCommit(true);
            }
            cn.cerrar_conexion();
            }catch(SQLException ex){
        }
    }
    }
    
    public void eliminarp(){
        try{
            PreparedStatement parametropersona;
            PreparedStatement parametrodocente;
            cn = new Conexion();
            cn.abrir_conexion();
            String queryd = "delete from docente where id_docente = ?";
            String queryp = "delete from persona where id_persona = ?";
            parametrodocente = (PreparedStatement) cn.conexionBD.prepareStatement(queryd);
            parametrodocente.setInt(1, getId_docente());
            int executardocente = parametrodocente.executeUpdate();
            parametropersona = (PreparedStatement) cn.conexionBD.prepareStatement(queryp);
            parametropersona.setInt(1, getId_persona()); 
            int executar = parametropersona.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro Eliminado"  + Integer.toString(executar));
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al eliminar"  + ex.getMessage());
        }
    } 
}
