using System;
using System.Collections.Generic;
using System.Linq;
using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.DBContexts
{
    public static class DbSeeder
    {
        public static void Seed(ApplicationDbContext context)
        {
            if (!context.Cosplays.Any())
            {
                var cosplays = new List<Cosplay>()
                {
                    new Cosplay
                    {
                       CosplayId = Guid.NewGuid(),
                       Name = "Ichigo Kurosaki - Final Gestuga Tenshou",
                       Serie = "Bleach",
                       Budget= 100.0m,
                       StartDate = DateTime.Now,
                       DueDate = DateTime.Now.AddDays(40),
                       Status = "In Progress"
                    },
                    new Cosplay
                    {
                       CosplayId = Guid.NewGuid(),
                       Name = "Lelouch Lamperouge",
                       Serie = "Code Geass",
                       Budget= 90.0m,
                       StartDate = DateTime.Now.AddDays(2),
                       DueDate = DateTime.Now.AddDays(50),
                       Status = "Planned"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Trafalgar D. Law",
                        Serie = "One Piece",
                        Budget= 120.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now,
                        Status = "Finished"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Satoru Gojo",
                        Serie = "Jujutsu Kaisen",
                        Budget= 70.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now.AddDays(100),
                        Status = "In Progress"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Misha Necron",
                        Serie = "Maō Gakuin no Futekigōsha ~Shijō Saikyō no Maō no Shiso, Tensei Shite Shison-tachi no Gakkō e Kayou~",
                        Budget= 120.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now.AddDays(170),
                        Status = "In Progress"
                    }
                };
                context.Cosplays.AddRange(cosplays);
                context.SaveChanges();
            }
        }

        public static void RecreateDatabase(ApplicationDbContext context)
        {
            context.Database.EnsureDeleted();
            context.Database.Migrate();
        }
    }
}
