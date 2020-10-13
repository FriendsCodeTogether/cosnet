using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
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
   }
}
