﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayItemDTO
    {
        public Guid CosplayItemId { get; set; }

        [Required]
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
    }
}
