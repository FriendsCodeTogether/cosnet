using System;
using System.Collections.Generic;
using System.Linq;
using CosNet.API.Data.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public class CosplayRepository : ICosplayRepository
    {
        private readonly ApplicationDbContext _dbContext;

        public CosplayRepository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IEnumerable<Cosplay> GetCosplays()
        {
            return _dbContext.Cosplays;
        }

        public Cosplay GetCosplay(Guid cosplayId)
        {
            return _dbContext.Cosplays.FirstOrDefault(a => a.CosplayId == cosplayId);
        }

        public void AddCosplay(Cosplay cosplay)
        {
            if (cosplay.CosplayId == Guid.Empty || cosplay.CosplayId == null)
            {
                cosplay.CosplayId = Guid.NewGuid();
            }
            _dbContext.Cosplays.Add(cosplay);
        }

        public void UpdateCosplay(Cosplay cosplay)
        {
        }

        public void DeleteCosplay(Guid cosplayId)
        {
            Cosplay cosplay = GetCosplay(cosplayId);
            _dbContext.Cosplays.Remove(cosplay);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}
