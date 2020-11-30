using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.API.Services
{
    public interface ICosplayItemMaterialService
    {
        void CreateCosplayItemMaterial(CosplayItemMaterialForCreationDTO cosplayItemMaterial);
        void DeleteCosplayItemMaterial(Guid cosplayItemMaterialId);
        CosplayItemMaterialDTO GetCosplayItemMaterial(Guid cosplayItemMaterialId);
        IEnumerable<CosplayItemMaterialDTO> GetCosplayItemMaterials(Guid cosplayItemId);
        void UpdateCosplayItemMaterial(Guid cosplayItemMaterialId, CosplayItemMaterialForUpdateDTO cosplayItemMaterial);
    }
}