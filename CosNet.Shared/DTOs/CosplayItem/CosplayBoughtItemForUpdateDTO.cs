﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayBoughtItemForUpdateDTO
    {
        [Required]
        public string Name { get; set; }
        public string Status { get; set; }
        public string Description { get; set; }

        public string BuyLink { get; set; }
        public decimal Price { get; set; }
    }
}
