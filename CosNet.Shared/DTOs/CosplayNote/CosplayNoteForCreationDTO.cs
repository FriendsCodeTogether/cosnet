using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CosNet.Shared.DTOs.CosplayNote
{
    public class CosplayNoteForCreationDTO
    {
        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(500)]
        public string Description { get; set; }

        public DateTime CreationDate { get; set; }

        //Relations Cosplay
        public Guid CosplayId { get; set; }
    }
}
