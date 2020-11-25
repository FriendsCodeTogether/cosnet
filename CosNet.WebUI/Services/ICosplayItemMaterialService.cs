using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemMaterialService
    {
        Task CreateCosplayItemMaterialAsync(Guid cosplayItemId, CosplayItemMaterialForCreationDTO cosplayItemMaterial);
        Task DeleteCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId);
        Task<CosplayItemMaterialDTO> GetCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId);
        Task<IEnumerable<CosplayItemMaterialDTO>> GetCosplayItemMaterialsAsync(Guid cosplayItemId);
        Task UpdateCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId, CosplayItemMaterialForUpdateDTO cosplayItemMaterial);
    }
}