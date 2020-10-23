using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.DBContexts
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        public DbSet<Cosplay> Cosplays { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            builder.Entity<Cosplay>(entity =>
            {
                entity.HasIndex(e => e.CosplayId).IsUnique();
            });
        }
    }
}
