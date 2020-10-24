using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayRepository
    {
        IEnumerable<Cosplay> GetAllCosplays();

        Cosplay GetCosplayById(Guid cosplayId);

        void AddCosplay(Cosplay cosplay);

        void UpdateCosplay(Cosplay cosplay);

        void DeleteCosplay(Guid cosplayId);

        bool SaveChanges();
    }
}
