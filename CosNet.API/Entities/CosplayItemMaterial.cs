using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public class CosplayItemMaterial
    {
        [Key]
        public int Id { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid CosplayItemMaterialId { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(500)]
        public string Description { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Price { get; set; }

        [MaxLength(200)]
        public string BuyLink { get; set; }

        //Relations CosplayItems
        public Guid CosplayItemId { get; set; }
        public CosplayItem CosplayItem { get; set; }
    }
}
