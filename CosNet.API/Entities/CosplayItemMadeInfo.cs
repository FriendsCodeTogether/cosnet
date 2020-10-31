﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.Entities
{
    [Owned]
    public class CosplayItemMadeInfo
    {
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public int Progress { get; set; }
        public TimeSpan WorkTime { get; set; }
    }
}
