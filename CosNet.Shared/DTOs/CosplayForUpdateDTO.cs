using System;
using System.ComponentModel.DataAnnotations;

namespace CosNet.Shared.DTOs
{
    public class CosplayForUpdateDTO
    {
        [Required]
        public string Name { get; set; }

        public string Serie { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime DueDate { get; set; }
        public decimal Budget { get; set; }
        public string Status { get; set; }
    }
}
