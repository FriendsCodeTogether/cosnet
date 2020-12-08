using Microsoft.EntityFrameworkCore.Migrations;

namespace CosNet.API.Data.Migrations
{
    public partial class CosplayItemNoteFixed : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CosplayItemNotes_Cosplays_CosplayItemId2",
                table: "CosplayItemNotes");

            migrationBuilder.DropIndex(
                name: "IX_CosplayItemNotes_CosplayItemId2",
                table: "CosplayItemNotes");

            migrationBuilder.DropColumn(
                name: "CosplayItemId2",
                table: "CosplayItemNotes");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "CosplayItemId2",
                table: "CosplayItemNotes",
                type: "int",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_CosplayItemNotes_CosplayItemId2",
                table: "CosplayItemNotes",
                column: "CosplayItemId2");

            migrationBuilder.AddForeignKey(
                name: "FK_CosplayItemNotes_Cosplays_CosplayItemId2",
                table: "CosplayItemNotes",
                column: "CosplayItemId2",
                principalTable: "Cosplays",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
