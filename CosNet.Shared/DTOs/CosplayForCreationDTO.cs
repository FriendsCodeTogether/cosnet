using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace CosNet.Shared.DTOs
{
    public class CosplayForCreationDTO
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
