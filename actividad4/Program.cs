using Microsoft.EntityFrameworkCore;
using actividad4.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

//String? cadena = builder.Configuration.GetConnectionString("DefaultConnection") ?? "D. vacio";
builder.Services.AddControllers();
builder.Services.AddDbContext<Conexiones>(opt =>
    //opt.UseMySQL(cadena));
    opt.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
