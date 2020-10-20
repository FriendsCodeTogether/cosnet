using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel.DataAnnotations;

namespace CosNet.Shared.ViewModels
{
   public class CosplayVM
   {
      public Guid Id { get; set; }
      public Guid UserId { get; set; }

      [Required]
      public string Name { get; set; }

      public string Serie { get; set; }
      public DateTime StartDate { get; set; } = DateTime.Now;
      public DateTime DueDate { get; set; } = DateTime.Now.AddDays(5);
      public decimal Budget { get; set; }
      public string Status { get; set; }
   }
}
