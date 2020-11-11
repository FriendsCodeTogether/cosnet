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

        public IEnumerable<CosplayItem> GetCosplayItems(Guid cosplayId)
        {
            return _dbContext.CosplayItems.Where(c => c.CosplayId == cosplayId);
        }

        public CosplayItem GetCosplayItem(Guid cosplayItemId)
        {
            return _dbContext.CosplayItems.FirstOrDefault(a => a.CosplayItemId == cosplayItemId);
        }

        public void AddCosplayItem(CosplayItem cosplayItem)
        {
            if (cosplayItem.CosplayItemId == Guid.Empty)
            {
                cosplayItem.CosplayItemId = Guid.NewGuid();
            }
            _dbContext.CosplayItems.Add(cosplayItem);
        }

        public void UpdateCosplayItem(CosplayItem cosplayItem)
        {
        }

        public void DeleteCosplayItem(Guid cosplayItemId)
        {
            CosplayItem cosplayItem = GetCosplayItem(cosplayItemId);
            _dbContext.CosplayItems.Remove(cosplayItem);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}
