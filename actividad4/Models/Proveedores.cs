using System.ComponentModel.DataAnnotations;
namespace actividad4.Models;

public class Proveedores{
    [Key]
    public Int32? id_proveedor {get; set;}
    public string? nit {get; set;}
    public string? proveedor {get; set;}
    public string? direccion {get; set;}
    public string? razon_social {get; set;}
    public DateTime? fecha_creacion {get; set;}
}