using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.Shared.DTOs.Cosplay
{
    public class CosplayDTO
    {
        public Guid CosplayId { get; set; }
        public Guid UserId { get; set; }

        [Required]
        public string Name { get; set; }

        public string Serie { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime DueDate { get; set; }
        public decimal Budget { get; set; }
        public string Status { get; set; }
        public IEnumerable<CosplayItemDTO> Items { get; set; }

    }
}
