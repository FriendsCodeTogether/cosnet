using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CosNet.Shared.DTOs.CosplayItemMaterial
{
    public class CosplayItemMaterialForCreationDTO
    {
        [Required]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(500)]
        public string Description { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Price { get; set; }

        [MaxLength(200)]
        public string BuyLink { get; set; }

        //Relations
        public Guid CosplayItemId { get; set; }
    }
}
