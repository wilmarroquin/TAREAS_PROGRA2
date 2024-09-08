/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author WILMER
 */
public class Puesto {
    private int id_puesto;
    private String puesto;
    Conexion cn;
    
    public Puesto(){}

    public Puesto(int id_puesto, String puestos) {
        this.id_puesto = id_puesto;
        this.puesto = puestos;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuestos() {
        return puesto;
    }

    public void setPuestos(String puestos) {
        this.puesto = puesto;
    }
    
    public HashMap<String, Integer> map = new HashMap <>();
    
    public DefaultComboBoxModel <String> leer(){
        DefaultComboBoxModel <String> p = new  DefaultComboBoxModel <>();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM puestos;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            while  (consulta.next()){
                int id = consulta.getInt("id_puesto");
                String puesto = consulta.getString("puesto");
                p.addElement(puesto);
                map.put(puesto, id);
        }
            cn.cerrar_conexion();
        }catch (SQLException ex){
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,"Error al leer los datos" + ex.getMessage());
        }
        return p;
    }    
}
