using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayItemBoughtInfoDTO
    {
        [MaxLength(150)]
        public string BuyLink { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Price { get; set; }
    }
}
