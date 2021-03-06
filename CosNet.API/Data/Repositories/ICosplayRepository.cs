﻿using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayRepository
    {
        IEnumerable<Cosplay> GetCosplays();

        Cosplay GetCosplay(Guid cosplayId);

        void AddCosplay(Cosplay cosplay, Guid UserId);

        void UpdateCosplay(Cosplay cosplay);

        void DeleteCosplay(Guid cosplayId);

        bool CosplayExists(Guid cosplayId);

        bool SaveChanges();
    }
}
