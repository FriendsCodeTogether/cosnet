using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayItemRepository
    {
        void AddCosplayItem(CosplayItemBase cosplayItem);
        void DeleteCosplayItem(Guid cosplayItemId);
        CosplayItemBase GetCosplayItem(Guid cosplayItemId);
        IEnumerable<CosplayItemBase> GetCosplayItems();
        bool SaveChanges();
        void UpdateCosplayItem(CosplayItemBase cosplayItem);
    }
}