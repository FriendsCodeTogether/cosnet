using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public class CosplayNote
    {
        [Key]
        public int Id { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid CosplayNoteId { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(500)]
        public string Description { get; set; }

        public DateTime CreationDate { get; set; }

        //Relations Cosplay
        public Guid CosplayId { get; set; }
        public Cosplay Cosplay { get; set; }
    }
}
