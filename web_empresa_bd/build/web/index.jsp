<%-- 
    Document   : index
    Created on : 29 sept 2024, 9:06:57 p.m.
    Author     : Bomiki
--%>
<%@page import="modelo.Puesto" %>
<%@page import="java.util.HashMap" %>
<%@page import="modelo.Empleado" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Empleados</h1>
        
        
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_empleado" onclick="limpiar()">
  Nuevo
</button>
  
        <div class="container">
            
            <div class="modal fade" id="modal_empleado" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Formulario Empleado</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
     <form action="sr_empleado" method="get" class="form-group"> 
                <label for="lbl_id"><b>ID:</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control"  value="0" readonly>
                <label for="lbl_codigo"><b>Codigo:</b></label>
                <input type="text" name="txt_codigo" id="txt_codigo" class="form-control" placeholder="Ejemplo" required>
                <label for="lbl_nombres"><b>Nombres:</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ingrese el Nombre" required>
                <label for="lbl_apellidos"><b>Apellidos:</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ingrese el Apellido" required>
                <label for="lbl_direccion"><b>Direccion:</b></label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ingrese la Direccion" required>
                <label for="lbl_telefono"><b>Telefono:</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ingrese el numero de telefono" required>
                <label for="lbl_fn"><b>Fecha Nacimiento:</b></label>
                <input type="date" name="txt_fn" id="txt_fn" class="form-control" placeholder="Ingrese la fecha de nacimiento" required>
                <label for="lbl_puesto"><b>Tipo de Puesto:</b></label>
                <select name="drop_puesto" id="drop_puesto" class="form-control">
                   <%
                      Puesto puesto = new Puesto();
                      HashMap<String,String> drop = puesto.drop_puesto();
                      for(String i: drop.keySet()){
                        out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                      }
                   %>
                </select>
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('Desea Eliminar?'))return false">Eliminar</button>
                
            </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
            
            
            

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Codigo</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Direccion</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">Nacimiento</th>
                        <th scope="col">Puesto</th>
                    </tr>
                </thead>
                <tbody id="tbl_empleado">
                    <%
                        Empleado empleado = new Empleado();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = empleado.leer();
                        for(int t = 0; t < tabla.getRowCount(); t++){
                            out.println("<tr data-id_empleados=" + tabla.getValueAt(t, 0) + " data-id_puesto=" + tabla.getValueAt(t, 8) + ">");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_codigo").val(' ');
                $("#txt_nombres").val(' ');
                $("#txt_apellidos").val(' ');
                $("#txt_direccion").val(' ');
                $("#txt_telefono").val(' ');
                $("#txt_fn").val(' ');
                $("#drop_puesto").val(1);
                
            }
               
 
            
            $('#tbl_empleado').on('click', 'tr td', function(evt) {
                console.log("Clic en una celda");
                var target, id_empleados, id_puesto, codigo, nombres, apellidos, direccion, telefono, nacimiento;
                target = $(event.target);
                id_empleados = target.parent().data('id_empleados');
                id_puesto = target.parent().data('id_puesto');
                codigo = target.parent("tr").find("td").eq(0).html();
                nombres = target.parent("tr").find("td").eq(1).html();
                apellidos = target.parent("tr").find("td").eq(2).html();
                direccion = target.parent("tr").find("td").eq(3).html();
                telefono = target.parent("tr").find("td").eq(4).html();
                nacimiento = target.parent("tr").find("td").eq(5).html();
                $("#txt_id").val(id_empleados);
                $("#txt_codigo").val(codigo);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_direccion").val(direccion);
                $("#txt_telefono").val(telefono);
                $("#txt_fn").val(nacimiento);
                $("#drop_puesto").val(id_puesto);
                $("#modal_empleado").modal('show');
                
            });
        </script>
    </body>
</html>
