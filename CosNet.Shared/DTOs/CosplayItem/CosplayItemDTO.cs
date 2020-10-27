using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayItemDTO
    {
        public Guid CosplayItemId { get; set; }
        public Guid CosplayId { get; set; }

        [Required]
        public string Name { get; set; }
        public string Status { get; set; }
        public string Description { get; set; }
    }
}
