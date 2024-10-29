using Microsoft.EntityFrameworkCore;

namespace actividad4.Models;

public class Conexiones : DbContext{
    public Conexiones(DbContextOptions<Conexiones> options)
        : base(options)
    {
    }
    public DbSet<Proveedores> Proveedores { get; set; } = null!;
}