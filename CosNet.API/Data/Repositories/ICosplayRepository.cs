using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayRepository
    {
        IEnumerable<Cosplay> GetCosplays();

        Cosplay GetCosplay(Guid cosplayId);

        void AddCosplay(Cosplay cosplay);

        void UpdateCosplay(Cosplay cosplay);

        void DeleteCosplay(Guid cosplayId);

        bool CheckExisting(Guid cosplayId);

        bool SaveChanges();
    }
}
