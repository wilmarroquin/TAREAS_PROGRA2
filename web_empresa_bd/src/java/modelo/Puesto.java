/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author WILMER
 */
public class Puesto {
    private int id_puesto;
    private String puesto;
    private Conexion cn;

    public Puesto(){}
    
    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
     public HashMap drop_puesto(){
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
    }
    
   public void agregar(){}
   
    
}
