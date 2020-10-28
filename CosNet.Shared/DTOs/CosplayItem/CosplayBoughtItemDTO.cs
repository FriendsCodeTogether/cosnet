using System;
using System.Collections.Generic;
using System.Text;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayBoughtItemDTO : CosplayItemBaseDTO
    {
        public string BuyLink { get; set; }
        public decimal Price { get; set; }
    }
}
