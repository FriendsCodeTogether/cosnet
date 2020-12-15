using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public class CosplayItem
    {
        [Key]
        public int Id { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid CosplayItemId { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(25)]
        public string Status { get; set; }

        [MaxLength(500)]
        public string Description { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Price { get; set; }

        public DateTime DueDate { get; set; }

        public bool IsMade { get; set; }

        //Bought Info
        [MaxLength(200)]
        public string BuyLink { get; set; }

        //Made Info
        public int Progress { get; set; }

        public int WorkTimeHours { get; set; }
        public int WorkTimeMinutes { get; set; }

        //Relations Cosplay
        public Guid CosplayId { get; set; }

        public Cosplay Cosplay { get; set; }

        //Relations Materials
        public IEnumerable<CosplayItemMaterial> Materials { get; set; }

        //Relations Notes
        public IEnumerable<CosplayItemNote> Notes { get; set; }
    }
}
