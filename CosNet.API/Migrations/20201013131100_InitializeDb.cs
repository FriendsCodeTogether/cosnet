using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace CosNet.API.Migrations
{
   public partial class InitializeDb : Migration
   {
      protected override void Up(MigrationBuilder migrationBuilder)
      {
         migrationBuilder.CreateTable(
             name: "Cosplays",
             columns: table => new
             {
                Id = table.Column<Guid>(nullable: false),
                UserId = table.Column<Guid>(nullable: false),
                Name = table.Column<string>(nullable: true),
                Serie = table.Column<string>(nullable: true),
                StartDate = table.Column<DateTime>(nullable: false),
                DueDate = table.Column<DateTime>(nullable: false),
                Budget = table.Column<decimal>(nullable: false),
                Status = table.Column<string>(nullable: true)
             },
             constraints: table =>
             {
                table.PrimaryKey("PK_Cosplays", x => x.Id);
             });
      }

      protected override void Down(MigrationBuilder migrationBuilder)
      {
         migrationBuilder.DropTable(
             name: "Cosplays");
      }
   }
}
