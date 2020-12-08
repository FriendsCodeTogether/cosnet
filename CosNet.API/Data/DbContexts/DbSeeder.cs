﻿using System;
using System.Collections.Generic;
using System.Linq;
using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.Data.DBContexts
{
    public static class DbSeeder
    {
        //Try keeping this file organized
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
                       Status = "In Progress",
                       UserId = Guid.Parse("f22f1397-c53f-4f88-9300-5dbdf13ee207")
                    },
                    new Cosplay
                    {
                       CosplayId = Guid.NewGuid(),
                       Name = "Lelouch Lamperouge",
                       Serie = "Code Geass",
                       Budget= 90.0m,
                       StartDate = DateTime.Now.AddDays(2),
                       DueDate = DateTime.Now.AddDays(50),
                       Status = "Planned",
                       UserId = Guid.Parse("f22f1397-c53f-4f88-9300-5dbdf13ee207")
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

                var cosplayNotes = new List<CosplayNote>()
                {
                    new CosplayNote
                    {
                        CosplayNoteId = Guid.NewGuid(),
                        Name = "Note #1 - Tips",
                        Description = "Don't hurry and take your time. The longer it takes the better it looks.",
                        CreationDate = DateTime.Now,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayNote
                    {
                        CosplayNoteId = Guid.NewGuid(),
                        Name = "Note #2 - Tricks",
                        Description = "Always measure twice. Be always sure to have al tools in hand so you can easily start.",
                        CreationDate = DateTime.Now,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    }
                };
                context.CosplayNotes.AddRange(cosplayNotes);
                context.SaveChanges();

                var cosplayItems = new List<CosplayItem>
                {
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Shirt",
                        Status = "To Buy",
                        Description = "It's a black shirt! What else?",
                        Price = 25.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Trouser",
                        Status = "Completed",
                        Description = "It's a black trouser! What else?",
                        Price = 30.0m,
                        DueDate = DateTime.Now.AddMonths(2),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Gloves",
                        Status = "Ordered",
                        Description = "It's a black gloves! What else?",
                        Price = 15.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Katana",
                        Status = "In Progress",
                        Description = "It's a black katana! Forged by the underdark dwarves",
                        Price = 150.0m,
                        DueDate = DateTime.Now.AddMonths(5),
                        IsMade = true,
                        Progress = 10,
                        WorkTimeHours = 5,
                        WorkTimeMinutes = 15,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Boots",
                        Status = "In Progress",
                        Description = "Their black boots! Made from demon-cow leather",
                        Price = 20.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = true,
                        Progress = 50,
                        WorkTimeHours = 3,
                        WorkTimeMinutes = 30,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Cap",
                        Status = "Completed",
                        Description = "It's a black cap! To hide from nosy people or Corona",
                        Price = 15.0m,
                        DueDate = DateTime.Now.AddMonths(3),
                        IsMade = true,
                        Progress = 30,
                        WorkTimeHours = 1,
                        WorkTimeMinutes = 45,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    }
                };
                context.CosplayItems.AddRange(cosplayItems);
                context.SaveChanges();

                var cosplayItemMaterials = new List<CosplayItemMaterial>
                {
                    new CosplayItemMaterial
                    {
                        CosplayItemMaterialId = Guid.NewGuid(),
                        Name = "Steel Blade",
                        Description = "This is made out of steel with a size of 1 meter",
                        Price = 50.0m,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayItemId = cosplayItems[3].CosplayItemId,
                        CosplayItem = cosplayItems[3]
                    },
                    new CosplayItemMaterial
                    {
                        CosplayItemMaterialId = Guid.NewGuid(),
                        Name = "Black Hilt",
                        Description = "Hilt made out of wood with iron chain hanging",
                        Price = 10.0m,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayItemId = cosplayItems[3].CosplayItemId,
                        CosplayItem = cosplayItems[3]
                    }
                };
                context.CosplayItemMaterials.AddRange(cosplayItemMaterials);
                context.SaveChanges();

                var cosplayItemNotes = new List<CosplayItemNote>
                {
                    new CosplayItemNote
                    {
                        CosplayItemNoteId = Guid.NewGuid(),
                        Name = "Item Note #1 - Tips",
                        Description = "Don't hurry and take your time. The longer it takes the better it looks.",
                        CreationDate = DateTime.Now,
                        CosplayItemId = cosplayItems[3].CosplayItemId,
                        CosplayItem = cosplayItems[3]
                    },
                };
            }
        }

        public static void RecreateDatabase(ApplicationDbContext context)
        {
            context.Database.EnsureDeleted();
            context.Database.Migrate();
        }
    }
}
