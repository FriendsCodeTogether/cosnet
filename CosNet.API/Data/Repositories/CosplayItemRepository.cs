using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public class CosplayItemRepository : ICosplayItemRepository
    {
        private readonly ApplicationDbContext _dbContext;

        public CosplayItemRepository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IEnumerable<CosplayItemBase> GetCosplayItems()
        {
            return _dbContext.CosplayItems;
        }

        public CosplayItemBase GetCosplayItem(Guid cosplayItemId)
        {
            return _dbContext.CosplayItems.FirstOrDefault(a => a.CosplayItemId == cosplayItemId);
        }

        public void AddCosplayItem(CosplayItemBase cosplayItem)
        {
            if (cosplayItem.CosplayItemId == Guid.Empty || cosplayItem.CosplayItemId == null)
            {
                cosplayItem.CosplayItemId = Guid.NewGuid();
            }
            _dbContext.CosplayItems.Add(cosplayItem);
        }

        public void UpdateCosplayItem(CosplayItemBase cosplayItem)
        {
        }

        public void DeleteCosplayItem(Guid cosplayItemId)
        {
            CosplayItemBase cosplayItem = GetCosplayItem(cosplayItemId);
            _dbContext.CosplayItems.Remove(cosplayItem);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}
