using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public class CosplayItemMaterialRepository : ICosplayItemMaterialRepository
    {
        private readonly ApplicationDbContext _dbContext;

        public CosplayItemMaterialRepository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IEnumerable<CosplayItemMaterial> GetCosplayItemMaterials(Guid cosplayItemId)
        {
            return _dbContext.CosplayItemMaterials.Where(c => c.CosplayItemId == cosplayItemId);
        }

        public CosplayItemMaterial GetCosplayItemMaterial(Guid cosplayItemMaterialId)
        {
            return _dbContext.CosplayItemMaterials.FirstOrDefault(a => a.CosplayItemMaterialId == cosplayItemMaterialId);
        }

        public void AddCosplayItemMaterial(CosplayItemMaterial cosplayItemMaterial)
        {
            if (cosplayItemMaterial.CosplayItemMaterialId == Guid.Empty)
            {
                cosplayItemMaterial.CosplayItemMaterialId = Guid.NewGuid();
            }
            _dbContext.CosplayItemMaterials.Add(cosplayItemMaterial);
        }

        public void UpdateCosplayItemMaterial(CosplayItemMaterial cosplayItemMaterial)
        {
        }

        public void DeleteCosplayItemMaterial(Guid cosplayItemMaterialId)
        {
            CosplayItemMaterial cosplayItemMaterial = GetCosplayItemMaterial(cosplayItemMaterialId);
            _dbContext.CosplayItemMaterials.Remove(cosplayItemMaterial);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}
