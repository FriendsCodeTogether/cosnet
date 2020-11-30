using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayItemMaterialRepository
    {
        void AddCosplayItemMaterial(CosplayItemMaterial cosplayItemMaterial);
        void DeleteCosplayItemMaterial(Guid cosplayItemMaterialId);
        CosplayItemMaterial GetCosplayItemMaterial(Guid cosplayItemMaterialId);
        IEnumerable<CosplayItemMaterial> GetCosplayItemMaterials(Guid cosplayItemId);
        bool SaveChanges();
        void UpdateCosplayItemMaterial(CosplayItemMaterial cosplayItemMaterial);
    }
}