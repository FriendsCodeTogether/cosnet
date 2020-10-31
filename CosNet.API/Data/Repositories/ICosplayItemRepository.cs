using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayItemRepository
    {
        void AddCosplayItem(CosplayItem cosplayItem);
        void DeleteCosplayItem(Guid cosplayItemId);
        CosplayItem GetCosplayItem(Guid cosplayItemId);
        IEnumerable<CosplayItem> GetCosplayItems();
        bool SaveChanges();
        void UpdateCosplayItem(CosplayItem cosplayItem);
    }
}