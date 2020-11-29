﻿// <auto-generated />
using System;
using CosNet.API.Data.DBContexts;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace CosNet.API.Data.Migrations
{
    [DbContext(typeof(ApplicationDbContext))]
    partial class ApplicationDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .UseIdentityColumns()
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.0");

            modelBuilder.Entity("CosNet.API.Entities.Cosplay", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .UseIdentityColumn();

                    b.Property<decimal>("Budget")
                        .HasColumnType("decimal(9,2)");

                    b.Property<Guid>("CosplayId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uniqueidentifier");

                    b.Property<DateTime>("DueDate")
                        .HasColumnType("datetime2");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(150)
                        .HasColumnType("nvarchar(150)");

                    b.Property<string>("Serie")
                        .HasMaxLength(150)
                        .HasColumnType("nvarchar(150)");

                    b.Property<DateTime>("StartDate")
                        .HasColumnType("datetime2");

                    b.Property<string>("Status")
                        .HasMaxLength(25)
                        .HasColumnType("nvarchar(25)");

                    b.Property<Guid>("UserId")
                        .HasColumnType("uniqueidentifier");

                    b.HasKey("Id");

                    b.ToTable("Cosplays");
                });

            modelBuilder.Entity("CosNet.API.Entities.CosplayItem", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .UseIdentityColumn();

                    b.Property<string>("BuyLink")
                        .HasMaxLength(200)
                        .HasColumnType("nvarchar(200)");

                    b.Property<Guid>("CosplayId")
                        .HasColumnType("uniqueidentifier");

                    b.Property<int?>("CosplayId1")
                        .HasColumnType("int");

                    b.Property<Guid>("CosplayItemId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uniqueidentifier");

                    b.Property<string>("Description")
                        .HasMaxLength(500)
                        .HasColumnType("nvarchar(500)");

                    b.Property<DateTime>("DueDate")
                        .HasColumnType("datetime2");

                    b.Property<bool>("IsMade")
                        .HasColumnType("bit");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(150)
                        .HasColumnType("nvarchar(150)");

                    b.Property<decimal>("Price")
                        .HasColumnType("decimal(9,2)");

                    b.Property<int>("Progress")
                        .HasColumnType("int");

                    b.Property<string>("Status")
                        .HasMaxLength(25)
                        .HasColumnType("nvarchar(25)");

                    b.Property<int>("WorkTimeHours")
                        .HasColumnType("int");

                    b.Property<int>("WorkTimeMinutes")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("CosplayId1");

                    b.ToTable("CosplayItems");
                });

            modelBuilder.Entity("CosNet.API.Entities.CosplayItemMaterial", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .UseIdentityColumn();

                    b.Property<string>("BuyLink")
                        .HasMaxLength(200)
                        .HasColumnType("nvarchar(200)");

                    b.Property<Guid>("CosplayItemId")
                        .HasColumnType("uniqueidentifier");

                    b.Property<int?>("CosplayItemId1")
                        .HasColumnType("int");

                    b.Property<Guid>("CosplayItemMaterialId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uniqueidentifier");

                    b.Property<string>("Description")
                        .HasMaxLength(500)
                        .HasColumnType("nvarchar(500)");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(150)
                        .HasColumnType("nvarchar(150)");

                    b.Property<decimal>("Price")
                        .HasColumnType("decimal(9,2)");

                    b.HasKey("Id");

                    b.HasIndex("CosplayItemId1");

                    b.ToTable("CosplayItemMaterials");
                });

            modelBuilder.Entity("CosNet.API.Entities.CosplayItem", b =>
                {
                    b.HasOne("CosNet.API.Entities.Cosplay", "Cosplay")
                        .WithMany("Items")
                        .HasForeignKey("CosplayId1");

                    b.Navigation("Cosplay");
                });

            modelBuilder.Entity("CosNet.API.Entities.CosplayItemMaterial", b =>
                {
                    b.HasOne("CosNet.API.Entities.CosplayItem", "CosplayItem")
                        .WithMany("Materials")
                        .HasForeignKey("CosplayItemId1");

                    b.Navigation("CosplayItem");
                });

            modelBuilder.Entity("CosNet.API.Entities.Cosplay", b =>
                {
                    b.Navigation("Items");
                });

            modelBuilder.Entity("CosNet.API.Entities.CosplayItem", b =>
                {
                    b.Navigation("Materials");
                });
#pragma warning restore 612, 618
        }
    }
}
