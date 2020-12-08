using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace CosNet.API.Data.Migrations
{
    public partial class NotesTableAdded : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CosplayItemNotes",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    CosplayItemNoteId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(150)", maxLength: 150, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(500)", maxLength: 500, nullable: true),
                    CreationDate = table.Column<DateTime>(type: "datetime2", nullable: false),
                    CosplayItemId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    CosplayItemId2 = table.Column<int>(type: "int", nullable: true),
                    CosplayItemId1 = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CosplayItemNotes", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CosplayItemNotes_CosplayItems_CosplayItemId1",
                        column: x => x.CosplayItemId1,
                        principalTable: "CosplayItems",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_CosplayItemNotes_Cosplays_CosplayItemId2",
                        column: x => x.CosplayItemId2,
                        principalTable: "Cosplays",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "CosplayNotes",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    CosplayNoteId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(150)", maxLength: 150, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(500)", maxLength: 500, nullable: true),
                    CreationDate = table.Column<DateTime>(type: "datetime2", nullable: false),
                    CosplayId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    CosplayId1 = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CosplayNotes", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CosplayNotes_Cosplays_CosplayId1",
                        column: x => x.CosplayId1,
                        principalTable: "Cosplays",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CosplayItemNotes_CosplayItemId1",
                table: "CosplayItemNotes",
                column: "CosplayItemId1");

            migrationBuilder.CreateIndex(
                name: "IX_CosplayItemNotes_CosplayItemId2",
                table: "CosplayItemNotes",
                column: "CosplayItemId2");

            migrationBuilder.CreateIndex(
                name: "IX_CosplayNotes_CosplayId1",
                table: "CosplayNotes",
                column: "CosplayId1");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CosplayItemNotes");

            migrationBuilder.DropTable(
                name: "CosplayNotes");
        }
    }
}
