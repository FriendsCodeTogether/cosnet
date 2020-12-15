using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CosNet.API.Entities
{
    public class Cosplay
    {
        [Key]
        public int Id { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid CosplayId { get; set; }

        public Guid UserId { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(150)]
        public string Name { get; set; }
        [MaxLength(150)]
        public string Serie { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime DueDate { get; set; }

        [Column(TypeName = "decimal(9,2)")]
        public decimal Budget { get; set; }
        [MaxLength(25)]
        public string Status { get; set; }

        //Relations Item
        public IEnumerable<CosplayItem> Items { get; set; }

        //Relations Notes
        public IEnumerable<CosplayNote> Notes { get; set; }
    }
}
