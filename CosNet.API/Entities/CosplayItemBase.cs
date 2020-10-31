using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public abstract class CosplayItemBase
    {
        [Key]
        public int Id { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid CosplayItemId { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }

        [MaxLength(25)]
        public string Status { get; set; }

        [MaxLength(150)]
        public string Description { get; set; }

        public bool IsMade { get; set; }

        public Guid CosplayId { get; set; }
        public Cosplay Cosplay { get; set; }
    }
}
