using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public class CosplayItemBoughtInfo
    {
        [MaxLength(150)]
        public string BuyLink { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Price { get; set; }
    }
}
