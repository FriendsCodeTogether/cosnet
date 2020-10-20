using System;
using System.Collections.Generic;
using System.Text;

namespace CosNet.Shared.ViewModels
{
   public class CosplayVM
   {
      public Guid Id { get; set; }
      public Guid UserId { get; set; }
      public string Name { get; set; }
      public string Serie { get; set; }
      public DateTime StartDate { get; set; }
      public DateTime DueDate { get; set; }
      public decimal Budget { get; set; }
      public string Status { get; set; }
   }
}
