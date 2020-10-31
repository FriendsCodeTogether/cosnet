using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace CosNet.API.Data.Migrations
{
    public partial class CosplayItemAdded : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CosplayItems",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    CosplayItemId = table.Column<Guid>(nullable: false),
                    Name = table.Column<string>(maxLength: 150, nullable: false),
                    Status = table.Column<string>(maxLength: 25, nullable: true),
                    Description = table.Column<string>(maxLength: 150, nullable: true),
                    IsMade = table.Column<bool>(nullable: false),
                    BoughtInfo_BuyLink = table.Column<string>(maxLength: 150, nullable: true),
                    BoughtInfo_Price = table.Column<decimal>(type: "decimal(9,2)", nullable: true),
                    MadeInfo_StartDate = table.Column<DateTime>(nullable: true),
                    MadeInfo_EndDate = table.Column<DateTime>(nullable: true),
                    MadeInfo_Progress = table.Column<int>(nullable: true),
                    MadeInfo_WorkTime = table.Column<TimeSpan>(nullable: true),
                    CosplayId = table.Column<Guid>(nullable: false),
                    CosplayId1 = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CosplayItems", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CosplayItems_Cosplays_CosplayId1",
                        column: x => x.CosplayId1,
                        principalTable: "Cosplays",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CosplayItems_CosplayId1",
                table: "CosplayItems",
                column: "CosplayId1");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CosplayItems");
        }
    }
}
