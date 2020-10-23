﻿using System;
using System.ComponentModel.DataAnnotations;
using System.Runtime.CompilerServices;

namespace CosNet.API.Entities
{
    public class Cosplay
    {
        [Key]
        public int Id { get; set; }

        public Guid CosplayId { get; set; }
        public Guid UserId { get; set; }

        [Required]
        public string Name { get; set; }

        public string Serie { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime DueDate { get; set; }
        public decimal Budget { get; set; }
        public string Status { get; set; }
    }
}
