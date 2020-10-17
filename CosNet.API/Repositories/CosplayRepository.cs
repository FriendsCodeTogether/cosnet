using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Repositories
{
   public class CosplayRepository : ICosplayRepository
   {
      private readonly ApplicationDbContext _dbContext;

      public CosplayRepository(ApplicationDbContext dbContext)
      {
         _dbContext = dbContext;
      }

      public IEnumerable<Cosplay> GetAllCosplays()
      {
         return _dbContext.Cosplays;
      }

      public Cosplay GetCosplayById(Guid cosplayId)
      {
         return _dbContext.Cosplays.FirstOrDefault(a => a.Id == cosplayId);
      }

      public void AddCosplay(Cosplay cosplay)
      {
         _dbContext.Cosplays.Add(cosplay);
         _dbContext.SaveChanges();
      }

      public void UpdateCosplay(Cosplay cosplay)
      {
         _dbContext.Cosplays.Update(cosplay);
         _dbContext.SaveChanges();
      }

      public void DeleteCosplay(Guid cosplayId)
      {
         Cosplay cosplay = GetCosplayById(cosplayId);
         _dbContext.Cosplays.Remove(cosplay);
         _dbContext.SaveChanges();
      }
   }
}
