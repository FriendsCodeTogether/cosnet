using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayItemDTO
    {
        public Guid CosplayItemId { get; set; }

        [Required]
        public string Name { get; set; }
        public string Status { get; set; }
        public string Description { get; set; }

        public bool IsMade { get; set; }
        public CosplayItemBoughtInfoDTO BoughtInfo { get; set; }
        public CosplayItemMadeInfoDTO MadeInfo { get; set; }

        public Guid CosplayId { get; set; }
        public CosplayDTO Cosplay { get; set; }

    }
}
