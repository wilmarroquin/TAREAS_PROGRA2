/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author WILMER
 */
public class docente {
    public String codigo_docente, fecha_ingreso;
    public double salario;
    public java.sql.Timestamp fecha_registro;
    Conexion cn;
    
    public docente(){}

    public docente(String codigo_docente, String fecha_ingreso, double salario, Timestamp fecha_registro) {
        this.codigo_docente = codigo_docente;
        this.fecha_ingreso = fecha_ingreso;
        this.salario = salario;
        this.fecha_registro = fecha_registro;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    protected void crear(){}
    protected void leer(){}
    protected void eliminar(){}
    protected void actualizar(){} 
}
