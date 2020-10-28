using System;
using System.Collections.Generic;
using System.Linq;
using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.Data.DBContexts
{
    public static class DbSeeder
    {
        public static void Seed(ApplicationDbContext context)
        {
            if (!context.Cosplays.Any() && !context.CosplayItems.Any())
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

                var cosplayItems = new List<CosplayItem>
                {
                    new CosplayBoughtItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Shirt",
                        Status = "Bought",
                        BuyLink = "https://www.kittyconnection.net/",
                        Price = 25.0m,
                        Description = "It's a black shirt! What else?",
                        Cosplay = cosplays[0]
                    },
                    new CosplayBoughtItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Trouser",
                        Status = "Bought",
                        BuyLink = "https://www.kittyconnection.net/",
                        Price = 30.0m,
                        Description = "It's a black trouser! What else?",
                        Cosplay = cosplays[0]
                    },
                    new CosplayBoughtItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Gloves",
                        Status = "Ordered",
                        BuyLink = "https://www.kittyconnection.net/",
                        Price = 15.0m,
                        Description = "It's a black gloves! What else?",
                        Cosplay = cosplays[0]
                    },
                    new CosplayMadeItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Katana",
                        Status = "On Hold",
                        Description = "It's a black katana! Forged by the underdark dwarves",
                        StartDate = DateTime.Now,
                        EndDate = DateTime.Now.AddMonths(5),
                        Progress = 10,
                        WorkTime = new TimeSpan(5, 10, 00),
                        Cosplay = cosplays[0]
                    },
                    new CosplayMadeItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Boots",
                        Status = "In Progress",
                        Description = "Their black boots! Made from demon-cow leather",
                        StartDate = DateTime.Now,
                        EndDate = DateTime.Now.AddMonths(1),
                        Progress = 50,
                        WorkTime = new TimeSpan(4, 25, 00),
                        Cosplay = cosplays[0]
                    },
                    new CosplayMadeItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Cap",
                        Status = "In Progress",
                        Description = "It's a black cap! To hide from nosy people or Corona",
                        StartDate = DateTime.Now,
                        EndDate = DateTime.Now.AddMonths(3),
                        Progress = 30,
                        WorkTime = new TimeSpan(1, 30, 00),
                        Cosplay = cosplays[0]
                    }
                };
                context.CosplayItems.AddRange(cosplayItems);
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
