using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.VisualBasic;

namespace CosNet.API.Entities
{
   public class Cosplay
   {
      public Guid Id { get; set; }
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
