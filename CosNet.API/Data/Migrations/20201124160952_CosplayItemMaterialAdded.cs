using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace CosNet.API.Data.Migrations
{
    public partial class CosplayItemMaterialAdded : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CosplayItemMaterials",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    CosplayItemMaterialId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(150)", maxLength: 150, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(500)", maxLength: 500, nullable: true),
                    Price = table.Column<decimal>(type: "decimal(9,2)", nullable: false),
                    BuyLink = table.Column<string>(type: "nvarchar(200)", maxLength: 200, nullable: true),
                    CosplayItemId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    CosplayItemId1 = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CosplayItemMaterials", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CosplayItemMaterials_CosplayItems_CosplayItemId1",
                        column: x => x.CosplayItemId1,
                        principalTable: "CosplayItems",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CosplayItemMaterials_CosplayItemId1",
                table: "CosplayItemMaterials",
                column: "CosplayItemId1");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CosplayItemMaterials");
        }
    }
}
