using System;
using System.Collections.Generic;
using System.Text;

namespace CosNet.Shared.DTOs.CosplayItem
{
    public class CosplayItemMadeInfoDTO
    {
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public int Progress { get; set; }
        public TimeSpan WorkTime { get; set; }
    }
}
