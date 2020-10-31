using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.Data.DBContexts
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        public DbSet<Cosplay> Cosplays { get; set; }
        public DbSet<CosplayItem> CosplayItems { get; set; }
    }
}
