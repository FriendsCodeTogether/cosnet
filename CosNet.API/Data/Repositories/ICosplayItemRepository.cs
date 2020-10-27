using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayItemRepository
    {
        void AddCosplay(CosplayItem cosplayItem);
        void DeleteCosplay(Guid cosplayItemId);
        public IEnumerable<Cosplay> GetCosplayItems();
        CosplayItem GetCosplayItem(Guid cosplayItemId);
        bool SaveChanges();
        void UpdateCosplay(CosplayItem cosplayItem);
    }
}
